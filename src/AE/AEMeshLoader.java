package AE;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

public class AEMeshLoader {
    
    public static MeshData loadAEMesh(String path) {
        try {
            DataInputStream aemFile = openAEMFile(path);
            if (aemFile == null) return null;

            // Чтение заголовка
            byte[] magic = new byte[9];
            aemFile.read(magic, 0, 9);
            int version = magic[1] - (byte) '0';
            int flags = aemFile.readUnsignedByte();

            // Пропускаем дополнительные данные для версии 4
            if (version == 4) {
                int submeshCount = swapBytes(aemFile.readUnsignedShort());
                aemFile.skip(12);
            }

            if ((flags & 16) != 0) {
                return parseMeshData(aemFile, version, flags);
            }
            
            aemFile.close();
        } catch (Exception e) {
            System.out.println("ERROR | AEMeshLoader(" + path + ") loading error!");
            e.printStackTrace();
        }
        return null;
    }

    private static DataInputStream openAEMFile(String path) {
        try {
            if (!path.endsWith(".aem")) {
                return new DataInputStream(AEMesh.class.getResourceAsStream(path + ".aem"));
            } else {
                return new DataInputStream(AEMesh.class.getResourceAsStream(path));
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static MeshData parseMeshData(DataInputStream aemFile, int version, int flags) throws IOException {
        // Чтение индексов
        int bufferSize = swapBytes(aemFile.readUnsignedShort());
        int[] indices = new int[bufferSize];
        int[] stripLengths = new int[bufferSize / 3];
        
        for (int i = 0; i < bufferSize; i++) {
            indices[i] = swapBytes(aemFile.readUnsignedShort());
            if (i % 3 == 0) {
                stripLengths[i / 3] = 3;
            }
        }

        TriangleStripArray triangleStripArray = new TriangleStripArray(indices, stripLengths);

        // Чтение вершин
        bufferSize = swapBytes(aemFile.readUnsignedShort());
        short[] vertexCoords = readVertexCoords(aemFile, version, bufferSize);

        VertexArray vertexArray = new VertexArray(vertexCoords.length / 3, 3, 2);
        vertexArray.set(0, vertexCoords.length / 3, vertexCoords);

        // Чтение UV-координат
        VertexArray uvArray = readUVArray(aemFile, version, flags, vertexCoords.length / 3);

        // Чтение нормалей
        VertexArray normalArray = readNormalArray(aemFile, version, flags, vertexCoords.length);

        // Создание VertexBuffer
        VertexBuffer vertexBuffer = new VertexBuffer();
        vertexBuffer.setPositions(vertexArray, 1.0F, null);
        vertexBuffer.setNormals(normalArray);
        if (uvArray != null) {
            vertexBuffer.setTexCoords(0, uvArray, 0.000244140625F, null);
        }

        aemFile.close();
        
        return new MeshData(vertexBuffer, triangleStripArray);
    }

    private static short[] readVertexCoords(DataInputStream aemFile, int version, int bufferSize) throws IOException {
        short[] vertexCoords = new short[bufferSize * 3];
        
        if (version == 2) {
            for (int i = 0; i < bufferSize * 3; i++) {
                short cord = (short) swapBytes(aemFile.readShort());
                short sign = (short) swapBytes(aemFile.readShort());
                if ((sign == -1 && cord >= 0) || (sign == 0 && cord < 0)) {
                    cord *= -1;
                }
                vertexCoords[i] = cord;
            }
        } else if (version == 4) {
            for (int i = 0; i < bufferSize * 3; i++) {
                vertexCoords[i] = (short) intBitReversedToFloat(aemFile.readInt());
            }
        }
        
        return vertexCoords;
    }

    private static VertexArray readUVArray(DataInputStream aemFile, int version, int flags, int vertexCount) throws IOException {
        if ((flags & 2) == 0) return null;

        short[] uvBuffer = new short[vertexCount * 2];
        
        if (version == 2) {
            for (int i = 0; i < uvBuffer.length; i += 2) {
                uvBuffer[i] = (short) swapBytes(aemFile.readShort());
                uvBuffer[i + 1] = (short) (4096 - (short) swapBytes(aemFile.readShort()));
            }
        } else if (version == 4) {
            for (int i = 0; i < uvBuffer.length; i += 2) {
                uvBuffer[i] = (short) intBitReversedToFloat(aemFile.readInt(), 4096);
                uvBuffer[i + 1] = (short) (4096 - (short) intBitReversedToFloat(aemFile.readInt(), 4096));
            }
        }

        VertexArray uvArray = new VertexArray(uvBuffer.length / 2, 2, 2);
        uvArray.set(0, uvBuffer.length / 2, uvBuffer);
        return uvArray;
    }

    private static VertexArray readNormalArray(DataInputStream aemFile, int version, int flags, int vertexCount) throws IOException {
        if ((flags & 4) == 0 || version != 4) return null;

        float[] normalsBuffer = new float[vertexCount];
        for (int i = 0; i < normalsBuffer.length; i++) {
            normalsBuffer[i] = intBitReversedToFloat(aemFile.readInt());
        }
        
        short[] shortNormalsBuffer = new short[normalsBuffer.length];
        for (int i = 0; i < normalsBuffer.length; i++) {
            shortNormalsBuffer[i] = (short) (normalsBuffer[i] * 32767);
        }

        VertexArray normalArray = new VertexArray(shortNormalsBuffer.length / 3, 3, 2);
        normalArray.set(0, shortNormalsBuffer.length / 3, shortNormalsBuffer);
        return normalArray;
    }

    private static int swapBytes(int value) {
        return (value & 255) << 8 | value >> 8 & 255;
    }

    private static float intBitReversedToFloat(int rawInt) {
        int swappedInt = ((rawInt & 0xff000000) >>> 24) |
                ((rawInt & 0x00ff0000) >>> 8) |
                ((rawInt & 0x0000ff00) << 8) |
                ((rawInt & 0x000000ff) << 24);
        return Float.intBitsToFloat(swappedInt);
    }

    private static float intBitReversedToFloat(int rawInt, int scale) {
        int swappedInt = ((rawInt & 0xff000000) >>> 24) |
                ((rawInt & 0x00ff0000) >>> 8) |
                ((rawInt & 0x0000ff00) << 8) |
                ((rawInt & 0x000000ff) << 24);
        return Float.intBitsToFloat(swappedInt) * scale;
    }

    public static class MeshData {
        
		public final VertexBuffer vertexBuffer;
        public final TriangleStripArray triangleStripArray;
        
        public MeshData(VertexBuffer vertexBuffer, TriangleStripArray triangleStripArray) {
            this.vertexBuffer = vertexBuffer;
            this.triangleStripArray = triangleStripArray;
        }
    }
}