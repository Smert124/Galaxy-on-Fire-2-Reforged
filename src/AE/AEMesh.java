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

import AE.PaintCanvas.AEGraphics3D;

public final class AEMesh extends AbstractMesh {

    private static Transform localToWorldTransform = new Transform();
    private static float[] m_matrix = new float[16];
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
            System.out.println("ERROR | AEMesh(" + path + ") loading error!");
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
    }

    private void processObject3DArray(Object3D[] objects) {
        for (int i = 0; i < objects.length; i++) {
            Object3D obj = objects[i];
            
            if (obj instanceof World) {
                World world = (World) obj;
                for (int j = 0; j < world.getChildCount(); j++) {
                    processNode(world.getChild(j));
                }
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
            for (int i = 0; i < group.getChildCount(); i++) {
                processNode(group.getChild(i));
            }
        } else if (node instanceof Mesh) {
            processMesh((Mesh) node);
        }
    }

    private void processMesh(Mesh mesh) {
        boolean isGlowing = meshPath != null && meshPath.endsWith("_add.aem");
        boolean isTransparent = meshPath != null && meshPath.endsWith("_alpha.aem");
        
        setupMeshAppearance(mesh, isGlowing, isTransparent);
        
        if (isGlowing) {
            addAdditiveNode(mesh);
        } else if (isTransparent) {
            addTransparentNode(mesh);
        } else {
            addOpaqueNode(mesh);
        }
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

    private void addOpaqueNode(Node node) {
        if (opaqueNodes == null) {
            opaqueNodes = new Node[]{node};
        } else {
            Node[] newArray = new Node[opaqueNodes.length + 1];
            System.arraycopy(opaqueNodes, 0, newArray, 0, opaqueNodes.length);
            newArray[opaqueNodes.length] = node;
            opaqueNodes = newArray;
        }
        if (this.node == null) this.node = node;
    }

    private void addTransparentNode(Node node) {
        if (transparentNodes == null) {
            transparentNodes = new Node[]{node};
        } else {
            Node[] newArray = new Node[transparentNodes.length + 1];
            System.arraycopy(transparentNodes, 0, newArray, 0, transparentNodes.length);
            newArray[transparentNodes.length] = node;
            transparentNodes = newArray;
        }
        if (this.node == null) this.node = node;
    }

    private void addAdditiveNode(Node node) {
        if (additiveNodes == null) {
            additiveNodes = new Node[]{node};
        } else {
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

    public final void render() {
        if (opaqueNodes != null) {
            matrix.toFloatArray(m_matrix);
            localToWorldTransform.set(m_matrix);
            for (int i = 0; i < opaqueNodes.length; i++) {
                AEGraphics3D.graphics3D.render(opaqueNodes[i], localToWorldTransform);
            }
        }
    }

    public final void renderTransparent() {
        if (transparentNodes != null) {
            matrix.toFloatArray(m_matrix);
            localToWorldTransform.set(m_matrix);
            for (int i = 0; i < transparentNodes.length; i++) {
                AEGraphics3D.graphics3D.render(transparentNodes[i], localToWorldTransform);
            }
        }
        
        if (additiveNodes != null) {
            matrix.toFloatArray(m_matrix);
            localToWorldTransform.set(m_matrix);
            for (int i = 0; i < additiveNodes.length; i++) {
                AEGraphics3D.graphics3D.render(additiveNodes[i], localToWorldTransform);
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

        if (opaqueNodes != null) {
            for (int i = 0; i < opaqueNodes.length; i++) {
                applyTextureToNode(opaqueNodes[i], textures[0], false, false);
            }
        }
        
        if (transparentNodes != null) {
            for (int i = 0; i < transparentNodes.length; i++) {
                applyTextureToNode(transparentNodes[i], textures[0], true, false);
            }
        }
        
        if (additiveNodes != null) {
            for (int i = 0; i < additiveNodes.length; i++) {
                applyTextureToNode(additiveNodes[i], textures[0], false, true);
            }
        }
    }

    private void applyTextureToNode(Node node, Texture2D texture, boolean isTransparent, boolean isAdditive) {
        if (node instanceof Mesh) {
            Mesh mesh = (Mesh) node;
            for (int i = 0; i < mesh.getSubmeshCount(); i++) {
                Appearance appearance = mesh.getAppearance(i);
                if (appearance != null) {
                    appearance.setTexture(0, texture);
                    if (isTransparent) {
                        appearance.setMaterial(null);
                    } else if (isAdditive) {
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

    public void update(long var1) {}
    public int getCurrentAnimFrame() { return 0; }
    public void setAnimationSpeed(int speed) {}
    public void setAnimationRangeInTime(int start, int end) {}
    public void setAnimationMode(byte mode) {}
    public void disableAnimation() {}
    public boolean hasAnimation() { return false; }
}