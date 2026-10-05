package AE;

import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.PolygonMode;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.World;
import javax.microedition.m3g.VertexBuffer;

import AE.AEMeshLoader.AnimationData;
import AE.AEMeshLoader.Keyframe;
import AE.PaintCanvas.AEGraphics3D;

public final class AEMesh extends AbstractMesh {

    private static Transform localToWorldTransform = new Transform();
    private static float[] m_matrix = new float[16];
    private static float[] trsMatrix = new float[16];
    private static float[] resultMatrix = new float[16];
    
    private Node[] opaqueNodes;
    private Node[] transparentNodes;
    private Node[] additiveNodes;
    
    private static PolygonMode opaquePmode;
    private static PolygonMode transparentPmode;
    private static CompositingMode additiveCompositing;
    private static CompositingMode transparentCompositing;
    private static CompositingMode opaqueCompositing;
    private static Material specularMaterial;
    private boolean needsUvFix = false;
    private Texture2D texture = null;

    private Object3D[] rootObjects;
    public Node node;
    private String meshPath;
    
    private static final int REFERENCE_FRAME_TIME = 50;
    
    private int animStartFrame = 0;
    private int animEndFrame = 0;
    private int animCurrentFrame = 0;
    private int animationFrameTime = REFERENCE_FRAME_TIME;
    private byte animationMode = 2;
    
    private long sysTime = -1;
    private boolean hasAnimation = false;
    
    static {
        initializeMaterials();
    }

    public AEMesh(int resourceId, String path, int radius) {
        this.resourceId = resourceId;
        this.meshPath = path;

        try {
            rootObjects = AEMeshLoader.loadAEMesh(path);
            if (rootObjects != null && rootObjects.length > 0) {
                processObject3DArray(rootObjects);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.opaqueNodes = null;
            this.transparentNodes = null;
            this.additiveNodes = null;
            this.rootObjects = null;
            this.node = null;
        }

        this.radius = radius;
    }

    private AEMesh(AEMesh source) {
        super(source);
        this.radius = source.radius;
        this.opaqueNodes = source.opaqueNodes;
        this.transparentNodes = source.transparentNodes;
        this.additiveNodes = source.additiveNodes;
        this.renderLayer = source.renderLayer;
        this.draw = source.draw;
        this.resourceId = source.resourceId;
        this.needsUvFix = source.needsUvFix;
        this.texture = source.texture;
        this.rootObjects = source.rootObjects;
        this.node = source.node;
        this.meshPath = source.meshPath;
        this.animStartFrame = source.animStartFrame;
        this.animEndFrame = source.animEndFrame;
        this.animCurrentFrame = source.animCurrentFrame;
        this.animationFrameTime = source.animationFrameTime;
        this.animationMode = source.animationMode;
        this.sysTime = source.sysTime;
        this.hasAnimation = source.hasAnimation;
    }

    private void processObject3DArray(Object3D[] objects) {
        for (int i = 0; i < objects.length; i++) {
            Object3D obj = objects[i];
            if (obj instanceof World) {
                World world = (World) obj;
                for (int j = 0; j < world.getChildCount(); j++) processNode(world.getChild(j));
            } else if (obj instanceof Group) {
                processNode((Group) obj);
            } else if (obj instanceof Mesh) {
                processMesh((Mesh) obj);
            }
        }
    }

    private void processNode(Node node) {
        if (node instanceof Group) {
            Group group = (Group) node;
            for (int i = 0; i < group.getChildCount(); i++) processNode(group.getChild(i));
        } else if (node instanceof Mesh) {
            processMesh((Mesh) node);
        }
    }

    private void processMesh(Mesh mesh) {
        boolean isGlowing = meshPath != null && meshPath.endsWith("_add.aem");
        boolean isTransparent = meshPath != null && meshPath.endsWith("_alpha.aem");
        
        setupMeshAppearance(mesh, isGlowing, isTransparent);
        setupAnimationData(mesh);
        
        if (isGlowing) addAdditiveNode(mesh);
        else if (isTransparent) addTransparentNode(mesh);
        else addOpaqueNode(mesh);
    }

    private void setupMeshAppearance(Mesh mesh, boolean isGlowing, boolean isTransparent) {
        for (int i = 0; i < mesh.getSubmeshCount(); i++) {
            Appearance appearance = new Appearance();
            if (isGlowing) {
                appearance.setCompositingMode(additiveCompositing);
                appearance.setPolygonMode(transparentPmode);
                appearance.setMaterial(null);
            } else if (isTransparent) {
                appearance.setCompositingMode(transparentCompositing);
                appearance.setPolygonMode(transparentPmode);
                appearance.setMaterial(null);
            } else {
                appearance.setCompositingMode(opaqueCompositing);
                appearance.setPolygonMode(opaquePmode);
                appearance.setMaterial(specularMaterial);
            }
            mesh.setAppearance(i, appearance);
        }
    }
    
    private void setupAnimationData(Mesh mesh) {
        Object userObj = mesh.getUserObject();
        if (!(userObj instanceof AnimationData)) return;
        
        AnimationData animData = (AnimationData) userObj;
        
        float maxTime = 0;
        float minTime = Float.MAX_VALUE;
        
        for (int i = 0; i < animData.translationKeys.size(); i++) {
            Keyframe kf = (Keyframe) animData.translationKeys.elementAt(i);
            if (kf.time > maxTime) maxTime = kf.time;
            if (kf.time < minTime) minTime = kf.time;
        }
        for (int i = 0; i < animData.rotationKeys.size(); i++) {
            Keyframe kf = (Keyframe) animData.rotationKeys.elementAt(i);
            if (kf.time > maxTime) maxTime = kf.time;
            if (kf.time < minTime) minTime = kf.time;
        }
        for (int i = 0; i < animData.scaleKeys.size(); i++) {
            Keyframe kf = (Keyframe) animData.scaleKeys.elementAt(i);
            if (kf.time > maxTime) maxTime = kf.time;
            if (kf.time < minTime) minTime = kf.time;
        }
        for (int i = 0; i < animData.alphaKeys.size(); i++) {
            Keyframe kf = (Keyframe) animData.alphaKeys.elementAt(i);
            if (kf.time > maxTime) maxTime = kf.time;
            if (kf.time < minTime) minTime = kf.time;
        }
        
        if (maxTime <= 0) return;
        if (minTime == Float.MAX_VALUE) minTime = 0;
        
        int startFrame = (int)(minTime / REFERENCE_FRAME_TIME);
        int endFrame = (int)(maxTime / REFERENCE_FRAME_TIME);
        
        this.animStartFrame = startFrame;
        this.animEndFrame = endFrame;
        this.animCurrentFrame = startFrame;
        this.hasAnimation = true;
        this.sysTime = -1;
    }

    private void addOpaqueNode(Node node) {
        if (opaqueNodes == null) opaqueNodes = new Node[]{node};
        else {
            Node[] newArray = new Node[opaqueNodes.length + 1];
            System.arraycopy(opaqueNodes, 0, newArray, 0, opaqueNodes.length);
            newArray[opaqueNodes.length] = node;
            opaqueNodes = newArray;
        }
        if (this.node == null) this.node = node;
    }

    private void addTransparentNode(Node node) {
        if (transparentNodes == null) transparentNodes = new Node[]{node};
        else {
            Node[] newArray = new Node[transparentNodes.length + 1];
            System.arraycopy(transparentNodes, 0, newArray, 0, transparentNodes.length);
            newArray[transparentNodes.length] = node;
            transparentNodes = newArray;
        }
        if (this.node == null) this.node = node;
    }

    private void addAdditiveNode(Node node) {
        if (additiveNodes == null) additiveNodes = new Node[]{node};
        else {
            Node[] newArray = new Node[additiveNodes.length + 1];
            System.arraycopy(additiveNodes, 0, newArray, 0, additiveNodes.length);
            newArray[additiveNodes.length] = node;
            additiveNodes = newArray;
        }
        if (this.node == null) this.node = node;
    }

    private static void initializeMaterials() {
        if (opaquePmode == null) {
            (opaquePmode = new PolygonMode()).setCulling(PolygonMode.CULL_NONE);
            opaquePmode.setShading(PolygonMode.SHADE_SMOOTH);
            opaquePmode.setPerspectiveCorrectionEnable(true);
            opaquePmode.setLocalCameraLightingEnable(true);
            opaquePmode.setTwoSidedLightingEnable(true);
            opaquePmode.setWinding(PolygonMode.WINDING_CCW);
        }

        if (transparentPmode == null) {
            (transparentPmode = new PolygonMode()).setCulling(PolygonMode.CULL_NONE);
            transparentPmode.setShading(PolygonMode.SHADE_FLAT);
            transparentPmode.setPerspectiveCorrectionEnable(true);
        }

        if (additiveCompositing == null) {
            (additiveCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA_ADD);
            additiveCompositing.setDepthTestEnable(true);
            additiveCompositing.setDepthWriteEnable(false);
        }
        
        if (transparentCompositing == null) {
            (transparentCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA);
            transparentCompositing.setDepthTestEnable(true);
            transparentCompositing.setDepthWriteEnable(false);
        }

        if (opaqueCompositing == null) {
            (opaqueCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA);
            opaqueCompositing.setDepthTestEnable(true);
            opaqueCompositing.setDepthWriteEnable(true);
        }

        if (specularMaterial == null) {
            (specularMaterial = new Material()).setColor(Material.DIFFUSE, 0xFF444444);
            specularMaterial.setColor(Material.SPECULAR, GoF2.Level.skyNormalizedLight());
            specularMaterial.setVertexColorTrackingEnable(false);
            specularMaterial.setShininess(127.0F);
        }
    }

    private AnimationData getAnimationData(Node node) {
        if (node instanceof Mesh) {
            Object userObj = ((Mesh) node).getUserObject();
            if (userObj instanceof AnimationData) return (AnimationData) userObj;
        }
        return null;
    }

    private float[] interpolate3(java.util.Vector keys, float timeMs) {
        if (keys == null || keys.size() == 0) return null;
        if (keys.size() == 1) return ((Keyframe) keys.elementAt(0)).values;
        
        Keyframe first = (Keyframe) keys.elementAt(0);
        if (timeMs <= first.time) return first.values;
        
        Keyframe last = (Keyframe) keys.elementAt(keys.size() - 1);
        if (timeMs >= last.time) return last.values;
        
        for (int i = 0; i < keys.size() - 1; i++) {
            Keyframe a = (Keyframe) keys.elementAt(i);
            Keyframe b = (Keyframe) keys.elementAt(i + 1);
            if (timeMs >= a.time && timeMs <= b.time) {
                float dt = b.time - a.time;
                float alpha = (dt > 0.0001f) ? (timeMs - a.time) / dt : 0;
                return new float[]{
                    a.values[0] + alpha * (b.values[0] - a.values[0]),
                    a.values[1] + alpha * (b.values[1] - a.values[1]),
                    a.values[2] + alpha * (b.values[2] - a.values[2])
                };
            }
        }
        return last.values;
    }
    
    private float[] interpolate4(java.util.Vector keys, float timeMs) {
        if (keys == null || keys.size() == 0) return null;
        if (keys.size() == 1) return normalizeQuat(((Keyframe) keys.elementAt(0)).values);
        
        Keyframe first = (Keyframe) keys.elementAt(0);
        if (timeMs <= first.time) return normalizeQuat(first.values);
        
        Keyframe last = (Keyframe) keys.elementAt(keys.size() - 1);
        if (timeMs >= last.time) return normalizeQuat(last.values);
        
        for (int i = 0; i < keys.size() - 1; i++) {
            Keyframe a = (Keyframe) keys.elementAt(i);
            Keyframe b = (Keyframe) keys.elementAt(i + 1);
            if (timeMs >= a.time && timeMs <= b.time) {
                float dt = b.time - a.time;
                float alpha = (dt > 0.0001f) ? (timeMs - a.time) / dt : 0;
                return normalizeQuat(new float[]{
                    a.values[0] + alpha * (b.values[0] - a.values[0]),
                    a.values[1] + alpha * (b.values[1] - a.values[1]),
                    a.values[2] + alpha * (b.values[2] - a.values[2]),
                    a.values[3] + alpha * (b.values[3] - a.values[3])
                });
            }
        }
        return normalizeQuat(last.values);
    }
    
    private float interpolate1(java.util.Vector keys, float timeMs) {
        if (keys == null || keys.size() == 0) return -1.0f;
        if (keys.size() == 1) return ((Keyframe) keys.elementAt(0)).values[0];
        
        Keyframe first = (Keyframe) keys.elementAt(0);
        if (timeMs <= first.time) return first.values[0];
        
        Keyframe last = (Keyframe) keys.elementAt(keys.size() - 1);
        if (timeMs >= last.time) return last.values[0];
        
        for (int i = 0; i < keys.size() - 1; i++) {
            Keyframe a = (Keyframe) keys.elementAt(i);
            Keyframe b = (Keyframe) keys.elementAt(i + 1);
            if (timeMs >= a.time && timeMs <= b.time) {
                float dt = b.time - a.time;
                float alpha = (dt > 0.0001f) ? (timeMs - a.time) / dt : 0;
                return a.values[0] + alpha * (b.values[0] - a.values[0]);
            }
        }
        return last.values[0];
    }
    
    private float[] normalizeQuat(float[] q) {
        float len2 = q[0]*q[0] + q[1]*q[1] + q[2]*q[2] + q[3]*q[3];
        if (len2 < 0.000001f) return new float[]{0, 0, 0, 1};
        float invLen = 1.0f / (float) Math.sqrt(len2);
        return new float[]{q[0]*invLen, q[1]*invLen, q[2]*invLen, q[3]*invLen};
    }

    private void buildTRSMatrix(float[] t, float[] q, float[] s, float[] out) {
        float tx = (t != null) ? t[0] : 0;
        float ty = (t != null) ? t[1] : 0;
        float tz = (t != null) ? t[2] : 0;
        float sx = (s != null) ? s[0] : 1;
        float sy = (s != null) ? s[1] : 1;
        float sz = (s != null) ? s[2] : 1;
        float qx = (q != null) ? q[0] : 0;
        float qy = (q != null) ? q[1] : 0;
        float qz = (q != null) ? q[2] : 0;
        float qw = (q != null) ? q[3] : 1;
        
        float xx = qx*qx, yy = qy*qy, zz = qz*qz;
        float xy = qx*qy, xz = qx*qz, yz = qy*qz;
        float wx = qw*qx, wy = qw*qy, wz = qw*qz;
        
        // Rotation: row-major
        out[0]  = (1 - 2*(yy + zz)) * sx;
        out[1]  = 2*(xy - wz) * sy;
        out[2]  = 2*(xz + wy) * sz;
        out[3]  = tx;
        
        out[4]  = 2*(xy + wz) * sx;
        out[5]  = (1 - 2*(xx + zz)) * sy;
        out[6]  = 2*(yz - wx) * sz;
        out[7]  = ty;
        
        out[8]  = 2*(xz - wy) * sx;
        out[9]  = 2*(yz + wx) * sy;
        out[10] = (1 - 2*(xx + yy)) * sz;
        out[11] = tz;
        
        out[12] = 0;
        out[13] = 0;
        out[14] = 0;
        out[15] = 1;
    }

    private void multiplyMatrix(float[] a, float[] b, float[] out) {
        for (int i = 0; i < 4; i++) {
            int ai = i * 4;
            for (int j = 0; j < 4; j++) {
                out[ai + j] = a[ai + 0] * b[0 + j]
                            + a[ai + 1] * b[4 + j]
                            + a[ai + 2] * b[8 + j]
                            + a[ai + 3] * b[12 + j];
            }
        }
    }

    private void renderNodeAnimated(Node renderNode, float[] baseMatrix) {
        AnimationData animData = getAnimationData(renderNode);
        
        if (animData != null && hasAnimation) {
            float timeMs = animCurrentFrame * REFERENCE_FRAME_TIME;
            
            float[] s = interpolate3(animData.scaleKeys, timeMs);
            float[] q = interpolate4(animData.rotationKeys, timeMs);
            float[] t = interpolate3(animData.translationKeys, timeMs);
            
            float[] tScaled = null;
			if (t != null) {
				tScaled = new float[]{ t[0], t[1], t[2] };
			}
            
            buildTRSMatrix(tScaled, q, s, trsMatrix);
            multiplyMatrix(baseMatrix, trsMatrix, resultMatrix);
            
            if (animData.alphaKeys.size() > 0) {
                float alphaRaw = interpolate1(animData.alphaKeys, timeMs);
                float alpha = alphaRaw / 100.0f;
                if (alpha < 0.0f) alpha = 0.0f;
                if (alpha > 1.0f) alpha = 1.0f;
                renderNode.setAlphaFactor(alpha);
            }
        } else {
            System.arraycopy(baseMatrix, 0, resultMatrix, 0, 16);
        }
        
        localToWorldTransform.set(resultMatrix);
        AEGraphics3D.graphics3D.render(renderNode, localToWorldTransform);
    }

    public final void render() {
        if (opaqueNodes != null) {
            matrix.toFloatArray(m_matrix);
            for (int i = 0; i < opaqueNodes.length; i++) {
                renderNodeAnimated(opaqueNodes[i], m_matrix);
            }
        }
    }

    public final void renderTransparent() {
        if (transparentNodes != null) {
            matrix.toFloatArray(m_matrix);
            for (int i = 0; i < transparentNodes.length; i++) {
                renderNodeAnimated(transparentNodes[i], m_matrix);
            }
        }
        
        if (additiveNodes != null) {
            matrix.toFloatArray(m_matrix);
            for (int i = 0; i < additiveNodes.length; i++) {
                renderNodeAnimated(additiveNodes[i], m_matrix);
            }
        }
    }

    public final GraphNode clone() {
        return new AEMesh(this);
    }

    public final void OnRelease() {}

    public final void setTexture(ITexture texture) {
        Texture2D[] textures = ((JSRTexture) texture).getTexturesArray();
        if (textures == null || textures.length == 0) return;

        if (opaqueNodes != null) for (int i = 0; i < opaqueNodes.length; i++) applyTextureToNode(opaqueNodes[i], textures[0], false, false);
        if (transparentNodes != null) for (int i = 0; i < transparentNodes.length; i++) applyTextureToNode(transparentNodes[i], textures[0], true, false);
        if (additiveNodes != null) for (int i = 0; i < additiveNodes.length; i++) applyTextureToNode(additiveNodes[i], textures[0], false, true);
    }

    private void applyTextureToNode(Node node, Texture2D texture, boolean isTransparent, boolean isAdditive) {
        if (node instanceof Mesh) {
            Mesh mesh = (Mesh) node;
            for (int i = 0; i < mesh.getSubmeshCount(); i++) {
                Appearance appearance = mesh.getAppearance(i);
                if (appearance != null) {
                    appearance.setTexture(0, texture);
                    if (isTransparent) appearance.setMaterial(null);
                    else if (isAdditive) {
                        appearance.setMaterial(null);
                        appearance.setCompositingMode(additiveCompositing);
                        appearance.setPolygonMode(transparentPmode);
                    }
                }
            }
        } else if (node instanceof Group) {
            Group group = (Group) node;
            for (int i = 0; i < group.getChildCount(); i++) {
                applyTextureToNode(group.getChild(i), texture, isTransparent, isAdditive);
            }
        }
    }

    public void update(long time) {
        if (!hasAnimation) return;
        if (sysTime == -1) sysTime = time;
        
        animCurrentFrame = animStartFrame + (int)((time - sysTime) / animationFrameTime);
        
        if (animCurrentFrame > animEndFrame) {
            if (animationMode == 2) {
                animCurrentFrame = animStartFrame;
                sysTime = time;
            } else {
                animCurrentFrame = animEndFrame;
                hasAnimation = false;
                sysTime = -1;
            }
        }
    }

    public int getCurrentAnimFrame() { return animCurrentFrame; }

    public void setAnimationSpeed(int speed) {
        this.animationFrameTime = speed > 0 ? speed : REFERENCE_FRAME_TIME;
    }

    public void setAnimationRangeInTime(int start, int end) {
        this.animStartFrame = start;
        this.animEndFrame = end;
        this.animCurrentFrame = start;
        this.sysTime = -1;
    }

    public void setAnimationMode(byte mode) {
        this.animationMode = mode;
        this.sysTime = -1;
    }

    public void disableAnimation() { this.hasAnimation = false; }
    public boolean hasAnimation() { return hasAnimation; }
}