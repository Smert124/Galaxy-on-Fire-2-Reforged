package AE;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.m3g.Group;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

public class AEMeshLoader {
    
    private static final Hashtable meshCache = new Hashtable();
    
    public static class AnimationData {
        public Vector translationKeys = new Vector();
        public Vector rotationKeys = new Vector();
        public Vector scaleKeys = new Vector();
        public Vector alphaKeys = new Vector();
        public float boundingSphereRadius = 0;
        public boolean isHD = false;
    }
    
    public static class Keyframe {
        public float time;
        public float[] values;
        
        public Keyframe(float time, float[] values) {
            this.time = time;
            this.values = values;
        }
    }
    
    public static class MeshData {
        public final VertexBuffer vertexBuffer;
        public final TriangleStripArray triangleStripArray;
        
        public MeshData(VertexBuffer vertexBuffer, TriangleStripArray triangleStripArray) {
            this.vertexBuffer = vertexBuffer;
            this.triangleStripArray = triangleStripArray;
        }
    }
    
    public static Object3D[] loadAEMesh(String path) {
        Object3D[] cached = (Object3D[]) meshCache.get(path);
        if (cached != null) return cached;
        
        boolean isHD = path != null && path.indexOf("_hd") != -1;
        
        try {
            DataInputStream aemFile = openAEMFile(path);
            if (aemFile == null) return null;
            
            StringBuffer magic = new StringBuffer();
            while (!magic.toString().endsWith("AEMesh\0")) {
                int ch = aemFile.read();
                if (ch == -1) { aemFile.close(); return null; }
                magic.append((char) ch);
                if (magic.length() > 9) { aemFile.close(); return null; }
            }
            
            int version = 0;
            String magicStr = magic.toString();
            if (magicStr.equals("AEMesh\0")) version = 1;
            else if (magicStr.equals("V2AEMesh\0")) version = 2;
            else if (magicStr.equals("V3AEMesh\0")) version = 3;
            else if (magicStr.equals("V4AEMesh\0")) version = 4;
            else if (magicStr.equals("V5AEMesh\0")) version = 5;
            
            if (version == 0) { aemFile.close(); return null; }
            
            int flags = aemFile.readUnsignedByte();
            boolean meshPresent = (flags & 1) != 0;
            if (!meshPresent) { aemFile.close(); return null; }
            
            boolean uvsPresent = (flags & 2) != 0;
            boolean normalsPresent = (flags & 4) != 0;
            boolean unkPresent = (flags & 8) != 0;
            boolean enhancedDataPresent = (flags & 16) != 0;
            
            int submeshNum = 1;
            if (version >= 3) submeshNum = readUShortLE(aemFile);
            
            Group rootGroup = new Group();
            
            for (int meshIndex = 0; meshIndex < submeshNum; meshIndex++) {
                MeshData meshData = parseMeshData(aemFile, version, flags, uvsPresent, normalsPresent, unkPresent);
                
                Mesh lastMesh = null;
                if (meshData != null) {
                    lastMesh = new Mesh(meshData.vertexBuffer, meshData.triangleStripArray, null);
                    rootGroup.addChild(lastMesh);
                }
                
                if (version >= 3) {
                    AnimationData animData = readEnhancedData(aemFile, flags, enhancedDataPresent, version, isHD);
                    if (animData != null && lastMesh != null) {
                        animData.isHD = isHD;
                        lastMesh.setUserObject(animData);
                    }
                }
            }
            
            aemFile.close();
            
            Object3D[] result = new Object3D[]{rootGroup};
            meshCache.put(path, result);
            return result;
            
        } catch (Exception e) {
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
        
        float[] pivotPoint = new float[3];
        if (version >= 3) {
            pivotPoint[0] = readFloatLE(aemFile);
            pivotPoint[1] = readFloatLE(aemFile);
            pivotPoint[2] = readFloatLE(aemFile);
        }
        
        int indicesNum = 0;
        int[] indices = null;
        int[] stripLengths = null;
        
        if ((flags & 1) != 0) {
            if (version >= 3) indicesNum = readUShortLE(aemFile);
            else indicesNum = readShortLE(aemFile);
            
            if (indicesNum > 0 && indicesNum < 100000) {
                indices = new int[indicesNum];
                for (int i = 0; i < indicesNum; i++) indices[i] = readUShortLE(aemFile);
                stripLengths = new int[indicesNum / 3];
                for (int i = 0; i < stripLengths.length; i++) stripLengths[i] = 3;
            } else return null;
        }
        
        TriangleStripArray triangleStripArray = null;
        if (indices != null && indices.length > 0) {
            triangleStripArray = new TriangleStripArray(indices, stripLengths);
        }
        
        int vertexNum;
        if (version >= 4) vertexNum = readUShortLE(aemFile);
        else vertexNum = readShortLE(aemFile);
        
        float[] vertices = new float[vertexNum * 3];
        if (version >= 4) {
            for (int i = 0; i < vertexNum * 3; i++) vertices[i] = readFloatLE(aemFile);
        } else if (version >= 2) {
            for (int i = 0; i < vertexNum * 3; i++) vertices[i] = readIntLE(aemFile);
        } else {
            for (int i = 0; i < vertexNum * 3; i++) vertices[i] = readShortLE(aemFile);
        }
        
        VertexArray uvArray = null;
        if (uvsPresent) {
            if (version >= 4) {
                float[] uvData = new float[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i++) uvData[i] = readFloatLE(aemFile);
                short[] uvShorts = new short[vertexNum * 2];
                for (int i = 0; i < vertexNum * 2; i += 2) {
                    uvShorts[i] = (short) (uvData[i] * 4096);
                    uvShorts[i+1] = (short) ((1.0f - uvData[i+1]) * 4096);
                }
                uvArray = new VertexArray(vertexNum, 2, 2);
                uvArray.set(0, vertexNum, uvShorts);
            } else {
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
        
        VertexArray normalArray = null;
        if (normalsPresent) {
            if (version >= 4) {
                float[] normalData = new float[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i++) normalData[i] = readFloatLE(aemFile);
                short[] normalShorts = new short[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i++) normalShorts[i] = (short) (normalData[i] * 32767);
                normalArray = new VertexArray(vertexNum, 3, 2);
                normalArray.set(0, vertexNum, normalShorts);
            } else if (version >= 2) {
                short[] normalData = new short[vertexNum * 3];
                for (int i = 0; i < vertexNum * 3; i += 3) {
                    short nx = readShortLE(aemFile);
                    short ny = readShortLE(aemFile);
                    short nz = readShortLE(aemFile);
                    
                    float fx = nx / 32768.0f;
                    float fy = ny / 32768.0f;
                    float fz = nz / 32768.0f;
                    
                    float len = (float) Math.sqrt(fx*fx + fy*fy + fz*fz);
                    if (len > 0.0001f) { fx /= len; fy /= len; fz /= len; }
                    else { fx = 0.0f; fy = 1.0f; fz = 0.0f; }
                    
                    normalData[i] = (short) (fx * 32767);
                    normalData[i+1] = (short) (fy * 32767);
                    normalData[i+2] = (short) (fz * 32767);
                }
                normalArray = new VertexArray(vertexNum, 3, 2);
                normalArray.set(0, vertexNum, normalData);
            } else {
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
        
        if (unkPresent) {
            if (version >= 4) for (int i = 0; i < vertexNum * 4; i++) readFloatLE(aemFile);
            else if (version >= 2) for (int i = 0; i < vertexNum * 4; i++) aemFile.readUnsignedByte();
            else for (int i = 0; i < vertexNum * 2; i++) readShortLE(aemFile);
        }
        
        VertexArray positionArray = new VertexArray(vertices.length / 3, 3, 2);
        short[] positionShorts = new short[vertices.length];
        for (int i = 0; i < vertices.length; i++) positionShorts[i] = (short) vertices[i];
        positionArray.set(0, vertices.length / 3, positionShorts);
        
        VertexBuffer vertexBuffer = new VertexBuffer();
        vertexBuffer.setPositions(positionArray, 1.0f, null);
        if (normalArray != null) vertexBuffer.setNormals(normalArray);
        if (uvArray != null) vertexBuffer.setTexCoords(0, uvArray, 0.000244140625f, null);
        
        return new MeshData(vertexBuffer, triangleStripArray);
    }
    
    private static AnimationData readEnhancedData(DataInputStream in, int flags, 
                                                    boolean enhancedDataPresent, int version,
                                                    boolean isHD) throws IOException {
        AnimationData animData = new AnimationData();
        
        float bx = readFloatLE(in);
        float by = readFloatLE(in);
        float bz = readFloatLE(in);
        float br = readFloatLE(in);
        animData.boundingSphereRadius = br;
        
        // === Translation ===
        int transType = readUShortLE(in);
        if (transType == 0) {
            boolean swapYZ = !isHD;
            readAxisSeparatedKeys(in, animData.translationKeys, swapYZ, true);
        } else if (transType == 1) {
            readUnifiedKeys(in, animData.translationKeys, 3);
        }
        
        // === Rotation ===
        int rotType = readUShortLE(in);
        if (rotType == 0) {
            readAxisSeparatedEulerKeys(in, animData.rotationKeys);
        } else if (rotType == 1) {
            readUnifiedEulerKeys(in, animData.rotationKeys);
        }
        
        // === Scale ===
        int scaleType = readUShortLE(in);
        if (scaleType == 0) {
            readAxisSeparatedKeys(in, animData.scaleKeys, false, false);
        } else if (scaleType == 1) {
            readUnifiedKeys(in, animData.scaleKeys, 3);
        }
        
        // === V4 Special (typ4 = 2) — alpha ===
        int typ4 = readUShortLE(in);
        if (typ4 == 2) {
            int keyCount = readUShortLE(in);
            for (int i = 0; i < keyCount; i++) {
                float time = readFloatLE(in);
                float value = readFloatLE(in);
                animData.alphaKeys.addElement(new Keyframe(time, new float[]{value}));
            }
        }
        
        // === V5 UV Animation (skip) ===
        if ((flags & 16) != 0) {
            int uvKeys = readUShortLE(in);
            if (uvKeys != 0) {
                for (int i = 0; i < 7; i++) {
                    int keyCount = readUShortLE(in);
                    for (int j = 0; j < keyCount; j++) {
                        readFloatLE(in);
                        readFloatLE(in);
                    }
                }
                readShortLE(in);
            }
        }
        
        return animData;
    }
    
    private static void readAxisSeparatedKeys(DataInputStream in, Vector out, boolean swapYZ, boolean invertY) throws IOException {
        Vector timesX = new Vector(), valuesX = new Vector();
        Vector timesY = new Vector(), valuesY = new Vector();
        Vector timesZ = new Vector(), valuesZ = new Vector();
        
        for (int axis = 0; axis < 3; axis++) {
            int count = readUShortLE(in);
            for (int i = 0; i < count; i++) {
                float t = readFloatLE(in);
                float v = readFloatLE(in);
                if (axis == 0) { timesX.addElement(new Float(t)); valuesX.addElement(new Float(v)); }
                else if (axis == 1) { timesY.addElement(new Float(t)); valuesY.addElement(new Float(v)); }
                else { timesZ.addElement(new Float(t)); valuesZ.addElement(new Float(v)); }
            }
        }
        
        Vector allTimes = new Vector();
        for (int i = 0; i < timesX.size(); i++) allTimes.addElement(timesX.elementAt(i));
        for (int i = 0; i < timesY.size(); i++) allTimes.addElement(timesY.elementAt(i));
        for (int i = 0; i < timesZ.size(); i++) allTimes.addElement(timesZ.elementAt(i));
        
        Vector uniqueTimes = new Vector();
        for (int i = 0; i < allTimes.size(); i++) {
            Float t = (Float) allTimes.elementAt(i);
            if (!uniqueTimes.contains(t)) uniqueTimes.addElement(t);
        }
        sortFloats(uniqueTimes);
        
        for (int i = 0; i < uniqueTimes.size(); i++) {
            float t = ((Float) uniqueTimes.elementAt(i)).floatValue();
            float x = getValueAtTime(timesX, valuesX, t);
            float y = getValueAtTime(timesY, valuesY, t);
            float z = getValueAtTime(timesZ, valuesZ, t);
            
            if (swapYZ) {
                float tempY = y;
                y = z;
                z = -tempY;
            }
            if (invertY) {
                y = -y;
            }
            
            out.addElement(new Keyframe(t, new float[]{x, y, z}));
        }
    }
    
    private static void readAxisSeparatedEulerKeys(DataInputStream in, Vector out) throws IOException {
        Vector timesX = new Vector(), valuesX = new Vector();
        Vector timesY = new Vector(), valuesY = new Vector();
        Vector timesZ = new Vector(), valuesZ = new Vector();
        
        for (int axis = 0; axis < 3; axis++) {
            int count = readUShortLE(in);
            for (int i = 0; i < count; i++) {
                float t = readFloatLE(in);
                float v = readFloatLE(in);
                if (axis == 0) { timesX.addElement(new Float(t)); valuesX.addElement(new Float(v)); }
                else if (axis == 1) { timesY.addElement(new Float(t)); valuesY.addElement(new Float(v)); }
                else { timesZ.addElement(new Float(t)); valuesZ.addElement(new Float(v)); }
            }
        }
        
        Vector allTimes = new Vector();
        for (int i = 0; i < timesX.size(); i++) allTimes.addElement(timesX.elementAt(i));
        for (int i = 0; i < timesY.size(); i++) allTimes.addElement(timesY.elementAt(i));
        for (int i = 0; i < timesZ.size(); i++) allTimes.addElement(timesZ.elementAt(i));
        
        Vector uniqueTimes = new Vector();
        for (int i = 0; i < allTimes.size(); i++) {
            Float t = (Float) allTimes.elementAt(i);
            if (!uniqueTimes.contains(t)) uniqueTimes.addElement(t);
        }
        sortFloats(uniqueTimes);
        
        for (int i = 0; i < uniqueTimes.size(); i++) {
            float t = ((Float) uniqueTimes.elementAt(i)).floatValue();
            float x = getValueAtTime(timesX, valuesX, t);
            float y = getValueAtTime(timesY, valuesY, t);
            float z = getValueAtTime(timesZ, valuesZ, t);
            out.addElement(new Keyframe(t, eulerToQuaternion(x, z, y)));
        }
    }
    
    private static void readUnifiedKeys(DataInputStream in, Vector out, int comps) throws IOException {
        int count = readUShortLE(in);
        for (int i = 0; i < count; i++) {
            float t = readFloatLE(in);
            float[] v = new float[comps];
            for (int c = 0; c < comps; c++) v[c] = readFloatLE(in);
            out.addElement(new Keyframe(t, v));
        }
    }
    
    private static void readUnifiedEulerKeys(DataInputStream in, Vector out) throws IOException {
        int count = readUShortLE(in);
        for (int i = 0; i < count; i++) {
            float t = readFloatLE(in);
            float x = readFloatLE(in);
            float y = readFloatLE(in);
            float z = readFloatLE(in);
            out.addElement(new Keyframe(t, eulerToQuaternion(x, y, z)));
        }
    }
    
    private static void sortFloats(Vector vec) {
        for (int i = 0; i < vec.size() - 1; i++) {
            for (int j = 0; j < vec.size() - 1 - i; j++) {
                Float a = (Float) vec.elementAt(j);
                Float b = (Float) vec.elementAt(j + 1);
                if (a.floatValue() > b.floatValue()) {
                    vec.setElementAt(b, j);
                    vec.setElementAt(a, j + 1);
                }
            }
        }
    }
    
    private static float getValueAtTime(Vector times, Vector values, float t) {
        if (times.size() == 0) return 0.0f;
        for (int i = 0; i < times.size() - 1; i++) {
            float t0 = ((Float) times.elementAt(i)).floatValue();
            float t1 = ((Float) times.elementAt(i+1)).floatValue();
            if (t >= t0 && t <= t1) {
                float v0 = ((Float) values.elementAt(i)).floatValue();
                float v1 = ((Float) values.elementAt(i+1)).floatValue();
                float alpha = (t1 - t0 > 0.0001f) ? (t - t0) / (t1 - t0) : 0;
                return v0 + alpha * (v1 - v0);
            }
        }
        if (t < ((Float) times.elementAt(0)).floatValue()) return ((Float) values.elementAt(0)).floatValue();
        return ((Float) values.elementAt(values.size() - 1)).floatValue();
    }
    
    private static float[] eulerToQuaternion(float rotX, float rotY, float rotZ) {
        float ti = rotX * 0.5f;
        float tj = rotY * 0.5f;
        float th = rotZ * 0.5f;
        
        float ci = (float) Math.cos(ti);
        float cj = (float) Math.cos(tj);
        float ch = (float) Math.cos(th);
        float si = (float) Math.sin(ti);
        float sj = (float) Math.sin(tj);
        float sh = (float) Math.sin(th);
        
        float cc = ci * ch;
        float cs = ci * sh;
        float sc = si * ch;
        float ss = si * sh;
        
        float qw = ci * cj * ch + si * sj * sh;
        float qx = si * cj * ch - ci * sj * sh;
        float qy = ci * sj * ch + si * cj * sh;
        float qz = ci * cj * sh - si * sj * ch;
        
        return new float[]{qx, qy, qz, qw};
    }
    
    private static short readShortLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        return (short)((b2 << 8) | b1);
    }
    
    private static int readUShortLE(DataInputStream aemFile) throws IOException {
        int b1 = aemFile.readUnsignedByte();
        int b2 = aemFile.readUnsignedByte();
        return (b2 << 8) | b1;
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
    
    public static void clearCache() {
        meshCache.clear();
    }
}