package AE;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.Hashtable;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

public class AEMeshLoader {
    
    private static final Hashtable meshCache = new Hashtable();
    private static short[] tempShortArray = null;
    private static int[] tempIntArray = null;
    private static float[] tempFloatArray = null;
    
    public static Object3D[] loadAEMesh(String path) {
        Object3D[] cached = (Object3D[]) meshCache.get(path);
        if (cached != null) {
            return cached;
        }
        
        try {
            DataInputStream aemFile = openAEMFile(path);
            if (aemFile == null) return null;
            
            // Read magic string (up to "AEMesh\0")
            StringBuffer magic = new StringBuffer();
            while (!magic.toString().endsWith("AEMesh\0")) {
                int ch = aemFile.read();
                if (ch == -1) {
                    aemFile.close();
                    return null;
                }
                magic.append((char) ch);
                if (magic.length() > 9) {
                    aemFile.close();
                    return null;
                }
            }
            
            int version = 0;
            String magicStr = magic.toString();
            if (magicStr.equals("AEMesh\0")) version = 1;
            else if (magicStr.equals("V2AEMesh\0")) version = 2;
            else if (magicStr.equals("V3AEMesh\0")) version = 3;
            else if (magicStr.equals("V4AEMesh\0")) version = 4;
            else if (magicStr.equals("V5AEMesh\0")) version = 5;
            
            if (version == 0) {
                aemFile.close();
                System.out.println("Unsupported .aem file. Invalid signature");
                return null;
            }
            
            int flags = aemFile.readUnsignedByte();
            boolean meshPresent = (flags & 1) != 0;
            
            System.out.println("FLAGS: " + flags);
            System.out.println("VERSION: " + version);
            
            if (!meshPresent) {
                aemFile.close();
                System.out.println("Basemesh flag is false!");
                return null;
            }
            
            boolean uvsPresent = (flags & 2) != 0;
            boolean normalsPresent = (flags & 4) != 0;
            boolean unkPresent = (flags & 8) != 0;
            boolean enhancedDataPresent = (flags & 16) != 0;
            
            int submeshNum = 1;
            if (version >= 3) {
                submeshNum = readUShortLE(aemFile);
                System.out.println("Number of submeshes: " + submeshNum);
            }
            
            Group rootGroup = new Group();
            
            for (int meshIndex = 0; meshIndex < submeshNum; meshIndex++) {
                System.out.println("READ_MESH " + (meshIndex + 1) + "/" + submeshNum);
                System.out.println("File position: " + aemFile.available());
                MeshData meshData = parseMeshData(aemFile, version, flags, uvsPresent, normalsPresent, unkPresent);
                if (meshData != null) {
                    Mesh mesh = new Mesh(meshData.vertexBuffer, meshData.triangleStripArray, null);
                    rootGroup.addChild(mesh);
                }
                
                // Для версий 3+ после каждого сабмеша читаем enhanced data
                if (version >= 3) {
                    System.out.println("READ_ENHANCED for submesh " + (meshIndex + 1));
                    readEnhancedData(aemFile, flags, enhancedDataPresent, version);
                }
            }
            
            aemFile.close();
            
            Object3D[] result = new Object3D[]{rootGroup};
            meshCache.put(path, result);
            return result;
            
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

    private static MeshData parseMeshData(DataInputStream aemFile, int version, int flags, 
                                           boolean uvsPresent, boolean normalsPresent, boolean unkPresent) throws IOException {
        
        // Pivot point для версий 3+
        float[] pivotPoint = new float[3];
        if (version >= 3) {
            pivotPoint[0] = readFloatLE(aemFile);
            pivotPoint[1] = readFloatLE(aemFile);
            pivotPoint[2] = readFloatLE(aemFile);
            System.out.println("PIVOT_POINT: X = " + clean(pivotPoint[0]) + "; Y = " + clean(pivotPoint[1]) + "; Z = " + clean(pivotPoint[2]));
        }
        
        int indicesNum = 0;
        int[] indices = null;
        int[] stripLengths = null;
        
        // Читаем индексы если флаг установлен (в C++: if ((*meshesArg)->flags & indices) != 0)
        if ((flags & 1) != 0) {
            if (version >= 3) {
                // В C++: bVar1 = AEFile::Read(2,&(*meshesArg)->n_indices,*file);
                indicesNum = readUShortLE(aemFile);
            } else {
                indicesNum = readShortLE(aemFile);
            }
            System.out.println("Indices num: " + indicesNum);
            
            if (indicesNum > 0 && indicesNum < 100000) { // Проверка на разумное значение
                indices = new int[indicesNum];
                for (int i = 0; i < indicesNum; i++) {
                    indices[i] = readUShortLE(aemFile);
                }
                // Создаем полосы
                stripLengths = new int[indicesNum / 3];
                for (int i = 0; i < stripLengths.length; i++) {
                    stripLengths[i] = 3;
                }
            } else {
                System.out.println("WARNING: Invalid indices count: " + indicesNum + ", skipping");
                // Пропускаем сабмеш
                return null;
            }
        }
        
        TriangleStripArray triangleStripArray = null;
        if (indices != null && indices.length > 0) {
            triangleStripArray = new TriangleStripArray(indices, stripLengths);
            System.out.println("Creating TriangleStripArray with " + indices.length + " indices");
        }
        
        // Читаем количество вершин
        int vertexNum;
        if (version >= 4) {
            vertexNum = readUShortLE(aemFile);
        } else {
            vertexNum = readShortLE(aemFile);
        }
        System.out.println("Vertex num: " + vertexNum);
        
        // Читаем вершины
        float[] vertices = new float[vertexNum * 3];
        if (version >= 4) {
            // V4+ вершины как float
            for (int i = 0; i < vertexNum * 3; i++) {
                vertices[i] = readFloatLE(aemFile);
            }
        } else if (version >= 2) {
            // V2/V3 вершины как int
            for (int i = 0; i < vertexNum * 3; i++) {
                vertices[i] = readIntLE(aemFile);
            }
        } else {
            // V1 вершины как short
            for (int i = 0; i < vertexNum * 3; i++) {
                vertices[i] = readShortLE(aemFile);
            }
        }
        
        // UVs
        VertexArray uvArray = null;
        if (uvsPresent) {
            System.out.println("Reading UVs");
            if (version >= 4) {
                // V4+ UVs как float
                float[] uvData = new float[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i++) {
                    uvData[i] = readFloatLE(aemFile);
                }
                short[] uvShorts = new short[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i += 2) {
                    uvShorts[i] = (short) (uvData[i] * 4096);
                    uvShorts[i+1] = (short) ((1.0f - uvData[i+1]) * 4096);
                }
                uvArray = new VertexArray(vertexNum, 2, 2);
                uvArray.set(0, vertexNum, uvShorts);
            } else {
                // V1-V3 UVs как short
                short[] uvData = new short[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i += 2) {
                    short u = readShortLE(aemFile);
                    short v = readShortLE(aemFile);
                    uvData[i] = u;
                    uvData[i+1] = (short) (4096 - v);
                }
                uvArray = new VertexArray(vertexNum, 2, 2);
                uvArray.set(0, vertexNum, uvData);
            }
        }
        
        // Normals
        VertexArray normalArray = null;
        if (normalsPresent) {
            System.out.println("Reading Normals");
            if (version >= 4) {
                // V4+ Normals как float
                float[] normalData = new float[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i++) {
                    normalData[i] = readFloatLE(aemFile);
                }
                short[] normalShorts = new short[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i++) {
                    normalShorts[i] = (short) (normalData[i] * 32767);
                }
                normalArray = new VertexArray(vertexNum, 3, 2);
                normalArray.set(0, vertexNum, normalShorts);
            } else if (version >= 2) {
                // V2/V3 Normals как short с нормализацией
                short[] normalData = new short[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i += 3) {
                    short nx = readShortLE(aemFile);
                    short ny = readShortLE(aemFile);
                    short nz = readShortLE(aemFile);
                    
                    float fx = nx / 32768.0f;
                    float fy = ny / 32768.0f;
                    float fz = nz / 32768.0f;
                    
                    float len = (float) Math.sqrt(fx*fx + fy*fy + fz*fz);
                    if (len > 0.0001f) {
                        fx /= len;
                        fy /= len;
                        fz /= len;
                    } else {
                        fx = 0.0f;
                        fy = 1.0f;
                        fz = 0.0f;
                    }
                    
                    normalData[i] = (short) (fx * 32767);
                    normalData[i+1] = (short) (fy * 32767);
                    normalData[i+2] = (short) (fz * 32767);
                }
                normalArray = new VertexArray(vertexNum, 3, 2);
                normalArray.set(0, vertexNum, normalData);
            } else {
                // V1
                short[] normalData = new short[vertexNum * 3];
                float unitPoint = 1.0f / 256.0f;
                for (int i = 0; i < vertexNum * 3; i++) {
                    short val = readShortLE(aemFile);
                    normalData[i] = (short) (val * unitPoint * 32767);
                }
                normalArray = new VertexArray(vertexNum, 3, 2);
                normalArray.set(0, vertexNum, normalData);
            }
        }
        
        // Unknown data (colors)
        if (unkPresent) {
            System.out.println("Reading Unknown data");
            if (version >= 4) {
                // V4+ colors как float
                float[] colorData = new float[vertexNum * 4];
                for (int i = 0; i < vertexNum * 4; i++) {
                    colorData[i] = readFloatLE(aemFile);
                }
            } else if (version >= 2) {
                // V2/V3 colors как byte
                byte[] colorData = new byte[vertexNum * 4];
                for (int i = 0; i < vertexNum * 4; i++) {
                    colorData[i] = (byte) aemFile.readUnsignedByte();
                }
            } else {
                // V1
                short[] unknownData = new short[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i++) {
                    unknownData[i] = readShortLE(aemFile);
                }
            }
        }
        
        // Build vertex buffer
        VertexArray positionArray = new VertexArray(vertices.length / 3, 3, 2);
        short[] positionShorts = new short[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            positionShorts[i] = (short) vertices[i];
        }
        positionArray.set(0, vertices.length / 3, positionShorts);
        
        VertexBuffer vertexBuffer = new VertexBuffer();
        vertexBuffer.setPositions(positionArray, 1.0f, null);
        if (normalArray != null) {
            vertexBuffer.setNormals(normalArray);
        }
        if (uvArray != null) {
            vertexBuffer.setTexCoords(0, uvArray, 0.000244140625f, null);
        }
        
        System.out.println("Mesh parsed successfully!");
        return new MeshData(vertexBuffer, triangleStripArray);
    }
    
    private static void readEnhancedData(DataInputStream in,
                                     int flags,
                                     boolean enhancedDataPresent,
                                     int version) throws IOException {

    // Bounding sphere
    float bx = readFloatLE(in);
    float by = readFloatLE(in);
    float bz = readFloatLE(in);
    float br = readFloatLE(in);

    // Конвертация координат как в оригинальном движке
    float tmp = by;
    by = bz;
    bz = -tmp;

    System.out.println("BOUNDING_SPHERE: X = " + clean(bx) +
                       "; Y = " + clean(by) +
                       "; Z = " + clean(bz) +
                       "; R = " + clean(br) + ";");

    // ------------------------
    // Translation
    // ------------------------

    int transType = readUShortLE(in);

    if (transType == 0) {

        readAnim0(in);

    } else if (transType == 1) {

        readAnim1(in);

    } else {

        System.out.println("Unknown Translation type: " + transType);

    }

    // ------------------------
    // Rotation
    // ------------------------

    int rotType = readUShortLE(in);

    if (rotType == 0) {

        readAnim0(in);

    } else if (rotType == 1) {

        readAnim1(in);

    } else {

        System.out.println("Unknown Rotation type: " + rotType);

    }

    // ------------------------
    // Scale
    // ------------------------

    int scaleType = readUShortLE(in);

    if (scaleType == 0) {

        readAnim0(in);

    } else if (scaleType == 1) {

        readAnim1(in);

    } else {

        System.out.println("Unknown Scale type: " + scaleType);

    }

    // ------------------------
    // V4 Special
    // ------------------------

    int typ4 = readUShortLE(in);

    if (typ4 == 2) {

        readAnim2(in);

    }

    // typ4 == FFFF -> ничего не делаем

    // ------------------------
    // V5 UV Animation
    // ------------------------

    if ((flags & 16) != 0) {

    int uvKeys = readUShortLE(in);

    if (uvKeys != 0) {

        for (int i = 0; i < 7; i++) {

            readAnim2(in);

        }

        readShortLE(in);

    }

}

}

private static void readAnim0(DataInputStream in) throws IOException {

    for (int axis = 0; axis < 3; axis++) {

        int keyCount = readUShortLE(in);

        for (int i = 0; i < keyCount; i++) {

            float time = readFloatLE(in);
            float value = readFloatLE(in);

        }

    }

}

private static void readAnim1(DataInputStream in) throws IOException {

    int keyCount = readUShortLE(in);

    for (int i = 0; i < keyCount; i++) {

        readFloatLE(in); // time

        readFloatLE(in); // x
        readFloatLE(in); // y
        readFloatLE(in); // z

    }

}

private static void readAnim2(DataInputStream in) throws IOException {

    int keyCount = readUShortLE(in);

    for (int i = 0; i < keyCount; i++) {

        readFloatLE(in); // time
        readFloatLE(in); // value

    }

}
    
    private static float signCheck(short value, short sign) {
        if ((sign == -1 && value < 0) || (sign == 0 && value >= 0)) {
            return -value;
        }
        return value;
    }
    
    private static int readUShortLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        return (b2 << 8) | b1;
    }

    private static short readShortLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        return (short)((b2 << 8) | b1);
    }
    
    private static int readIntLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        int b3 = aemFile.readUnsignedByte();
        int b4 = aemFile.readUnsignedByte();
        return (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
    }
    
    private static float readFloatLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        int b3 = aemFile.readUnsignedByte();
        int b4 = aemFile.readUnsignedByte();
        int bits = (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
        return Float.intBitsToFloat(bits);
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
    
    private static float[] getTempFloatArray(int size) {
        if(tempFloatArray == null || tempFloatArray.length < size) {
            tempFloatArray = new float[size];
        }
        return tempFloatArray;
    }

    public static class MeshData {
        public final VertexBuffer vertexBuffer;
        public final TriangleStripArray triangleStripArray;
        
        public MeshData(VertexBuffer vertexBuffer, TriangleStripArray triangleStripArray) {
            this.vertexBuffer = vertexBuffer;
            this.triangleStripArray = triangleStripArray;
        }
    }
	
	private static float clean(float f) {
		if (Math.abs(f) < 0.0001f) {
			return 0.0f;
		}
		return f;
	}
    
    public static void clearCache() {
        meshCache.clear();
        tempShortArray = null;
        tempIntArray = null;
        tempFloatArray = null;
    }
}