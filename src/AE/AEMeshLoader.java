package AE;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

public class AEMeshLoader {
    
    private static final java.util.Hashtable meshCache = new java.util.Hashtable();
    private static short[] tempShortArray = null;
    private static int[] tempIntArray = null;
    
    public static MeshData loadAEMesh(String path) {
        MeshData cached = (MeshData)meshCache.get(path);
        if(cached != null) {
            return cached;
        }
        
        try {
            DataInputStream aemFile = openAEMFile(path);
            if(aemFile == null) return null;
			
            byte[] magic = new byte[9];
            aemFile.read(magic, 0, 9);
            int version = magic[1] - (byte) '0';
            int flags = aemFile.readUnsignedByte();
			
			if(version == 4) {
                int submeshCount = swapBytes(aemFile.readUnsignedShort());
                aemFile.skip(12);
            }

            if((flags & 16) != 0) {
                MeshData result = parseMeshData(aemFile, version, flags);
                aemFile.close();
                
                if(result != null) {
                    meshCache.put(path, result);
                }
                return result;
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
        int indexBufferSize = swapBytes(aemFile.readUnsignedShort());
        int[] indices = getTempIntArray(indexBufferSize);
        int[] stripLengths = new int[indexBufferSize / 3];
        
        for(int i = 0; i < indexBufferSize; i++) {
            indices[i] = swapBytes(aemFile.readUnsignedShort());
            if (i % 3 == 0) {
                stripLengths[i / 3] = 3;
            }
        }

        TriangleStripArray triangleStripArray = new TriangleStripArray(indices, stripLengths);
		
        int vertexBufferSize = swapBytes(aemFile.readUnsignedShort());
        short[] vertexCoords = readVertexCoords(aemFile, version, vertexBufferSize);

        VertexArray vertexArray = new VertexArray(vertexCoords.length / 3, 3, 2);
        vertexArray.set(0, vertexCoords.length / 3, vertexCoords);
		
		VertexArray uvArray = readUVArray(aemFile, version, flags, vertexCoords.length / 3);
        VertexArray normalArray = readNormalArray(aemFile, version, flags, vertexCoords.length);
		
		VertexBuffer vertexBuffer = new VertexBuffer();
        vertexBuffer.setPositions(vertexArray, 1.0F, null);
        vertexBuffer.setNormals(normalArray);
        if(uvArray != null) {
            vertexBuffer.setTexCoords(0, uvArray, 0.000244140625F, null);
        }
		
        tempShortArray = null;
        tempIntArray = null;
        
        return new MeshData(vertexBuffer, triangleStripArray);
    }

    private static short[] readVertexCoords(DataInputStream aemFile, int version, int vertexBufferSize) throws IOException {
        int arraySize = vertexBufferSize * 3;
        short[] vertexCoords = getTempShortArray(arraySize);
        
        if(version == 2) {
            for(int i = 0; i < arraySize; i++) {
                short cord = (short) swapBytes(aemFile.readShort());
                short sign = (short) swapBytes(aemFile.readShort());
                if((sign == -1 && cord >= 0) || (sign == 0 && cord < 0)) {
                    cord *= -1;
                }
                vertexCoords[i] = cord;
            }
        } else if (version == 4) {
            for(int i = 0; i < arraySize; i++) {
                vertexCoords[i] = (short) intBitReversedToFloat(aemFile.readInt());
            }
        }
        
        return vertexCoords;
    }

    private static VertexArray readUVArray(DataInputStream aemFile, int version, int flags, int vertexCount) throws IOException {
        if((flags & 2) == 0) return null;

        int uvBufferSize = vertexCount * 2;
        short[] uvBuffer = getTempShortArray(uvBufferSize);
        
        if(version == 2) {
            for(int i = 0; i < uvBufferSize; i += 2) {
                uvBuffer[i] = (short) swapBytes(aemFile.readShort());
                uvBuffer[i + 1] = (short) (4096 - (short) swapBytes(aemFile.readShort()));
            }
        } else if(version == 4) {
            for(int i = 0; i < uvBufferSize; i += 2) {
                uvBuffer[i] = (short) intBitReversedToFloat(aemFile.readInt(), 4096);
                uvBuffer[i + 1] = (short) (4096 - (short) intBitReversedToFloat(aemFile.readInt(), 4096));
            }
        }

        VertexArray uvArray = new VertexArray(uvBufferSize / 2, 2, 2);
        uvArray.set(0, uvBufferSize / 2, uvBuffer);
        return uvArray;
    }

    private static VertexArray readNormalArray(DataInputStream aemFile, int version, int flags, int vertexCount) throws IOException {
        if((flags & 4) == 0 || version != 4) return null;

        int normalBufferSize = vertexCount;
        float[] normalsBuffer = new float[normalBufferSize];
        for(int i = 0; i < normalsBuffer.length; i++) {
            normalsBuffer[i] = intBitReversedToFloat(aemFile.readInt());
        }
        
        short[] shortNormalsBuffer = getTempShortArray(normalBufferSize);
        for(int i = 0; i < normalsBuffer.length; i++) {
            shortNormalsBuffer[i] = (short) (normalsBuffer[i] * 32767);
        }

        VertexArray normalArray = new VertexArray(shortNormalsBuffer.length / 3, 3, 2);
        normalArray.set(0, shortNormalsBuffer.length / 3, shortNormalsBuffer);
        return normalArray;
    }

    private static int swapBytes(int value) {
        return ((value << 8) & 0xFF00) | ((value >> 8) & 0x00FF);
    }

    private static float intBitReversedToFloat(int rawInt) {
        return Float.intBitsToFloat(
            ((rawInt << 24) & 0xFF000000) |
            ((rawInt << 8)  & 0x00FF0000) |
            ((rawInt >> 8)  & 0x0000FF00) |
            ((rawInt >> 24) & 0x000000FF)
        );
    }

    private static float intBitReversedToFloat(int rawInt, int scale) {
        int swappedInt = ((rawInt << 24) & 0xFF000000) |
                        ((rawInt << 8)  & 0x00FF0000) |
                        ((rawInt >> 8)  & 0x0000FF00) |
                        ((rawInt >> 24) & 0x000000FF);
        return Float.intBitsToFloat(swappedInt) * scale;
    }
    
    private static short[] getTempShortArray(int size) {
        if(tempShortArray == null || tempShortArray.length < size) {
            tempShortArray = new short[size];
        }
        return tempShortArray;
    }
    
    private static int[] getTempIntArray(int size) {
        if(tempIntArray == null || tempIntArray.length < size) {
            tempIntArray = new int[size];
        }
        return tempIntArray;
    }

    public static class MeshData {
        
        public final VertexBuffer vertexBuffer;
        public final TriangleStripArray triangleStripArray;
        
        public MeshData(VertexBuffer vertexBuffer, TriangleStripArray triangleStripArray) {
            this.vertexBuffer = vertexBuffer;
            this.triangleStripArray = triangleStripArray;
        }
    }
	
    public static void clearCache() {
        meshCache.clear();
        tempShortArray = null;
        tempIntArray = null;
    }
}