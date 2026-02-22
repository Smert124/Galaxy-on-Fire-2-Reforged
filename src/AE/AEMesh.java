package AE;

import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.PolygonMode;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

import AE.PaintCanvas.AEGraphics3D;

public final class AEMesh extends AbstractMesh {

   private static Transform localToWorldTransform = new Transform();
   private static float[] m_matrix = new float[16];
   private Node[] opaqueNodes;
   private Node[] transparentNodes;
   
   private int opaqueNodesCount = 0;
   private int transparentNodesCount = 0;
   
   private static PolygonMode opaquePmode;
   private static PolygonMode transparentPmode;
   private static CompositingMode additiveCompositing;
   private static CompositingMode transparentCompositing;
   private static CompositingMode opaqueCompositing;
   private static Material specularMaterial;
   private boolean needsUvFix = false;
   private Texture2D texture = null;

   public Node node;
   
   static {
       initializeMaterials();
   }

   public AEMesh(int resourceId, String path, int radius) {
      this.resourceId = resourceId;

      try {
         AEMeshLoader.MeshData meshData = AEMeshLoader.loadAEMesh(path);
         
		 if(meshData != null) {
            Mesh mesh = new Mesh(meshData.vertexBuffer, meshData.triangleStripArray, null);
            this.node = mesh;
            
            boolean isGlowing = path.endsWith("_add.aem");
            boolean isTransparent = path.endsWith("_alpha.aem") || isGlowing;
			
            setupMeshAppearance(mesh, isGlowing, isTransparent);
            
            if(isTransparent) {
               this.transparentNodes = new Node[1];
               this.transparentNodes[0] = mesh;
               this.transparentNodesCount = 1;
            } else {
               this.opaqueNodes = new Node[1];
               this.opaqueNodes[0] = mesh;
               this.opaqueNodesCount = 1;
            }
         }
      } catch (Exception e) {
         System.out.println("ERROR | AEMesh(" + path + ") loading error!");
         e.printStackTrace();
         this.opaqueNodes = null;
         this.transparentNodes = null;
         this.node = null;
      }

      this.radius = radius;
   }

   private AEMesh(AEMesh source) {
      super(source);
      this.radius = source.radius;
      this.opaqueNodes = source.opaqueNodes;
      this.transparentNodes = source.transparentNodes;
      this.opaqueNodesCount = source.opaqueNodesCount;
      this.transparentNodesCount = source.transparentNodesCount;
      this.renderLayer = source.renderLayer;
      this.draw = source.draw;
      this.resourceId = source.resourceId;
      this.needsUvFix = source.needsUvFix;
      this.texture = source.texture;
      this.node = source.node;
   }

   private static void initializeMaterials() {
      if (opaquePmode == null) {
         (opaquePmode = new PolygonMode()).setCulling(PolygonMode.CULL_BACK);
         opaquePmode.setShading(PolygonMode.SHADE_SMOOTH);
         opaquePmode.setPerspectiveCorrectionEnable(true);
         opaquePmode.setLocalCameraLightingEnable(true);
         opaquePmode.setTwoSidedLightingEnable(true);
         opaquePmode.setWinding(PolygonMode.WINDING_CCW);
      }

      if (transparentPmode == null) {
         (transparentPmode = new PolygonMode()).setCulling(PolygonMode.CULL_BACK);
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

   public final void render() {
      if(this.opaqueNodes != null && this.opaqueNodesCount > 0) {
         this.matrix.toFloatArray(m_matrix);
         localToWorldTransform.set(m_matrix);
         
         Node[] nodes = this.opaqueNodes;
         for(int i = 0; i < this.opaqueNodesCount; i++) {
            AEGraphics3D.graphics3D.render(nodes[i], localToWorldTransform);
         }
      }
   }

   public final void renderTransparent() {
      if(this.transparentNodes != null && this.transparentNodesCount > 0) {
         this.matrix.toFloatArray(m_matrix);
         localToWorldTransform.set(m_matrix);
         
         Node[] nodes = this.transparentNodes;
         for(int i = 0; i < this.transparentNodesCount; i++) {
            AEGraphics3D.graphics3D.render(nodes[i], localToWorldTransform);
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

      if (this.opaqueNodes != null) {
         for (int i = 0; i < this.opaqueNodesCount; i++) {
            applyTextureToNode(this.opaqueNodes[i], textures[0], false);
         }
      }
      
      if (this.transparentNodes != null) {
         for (int i = 0; i < this.transparentNodesCount; i++) {
            applyTextureToNode(this.transparentNodes[i], textures[0], true);
         }
      }
   }

   private void applyTextureToNode(Node node, Texture2D texture, boolean isTransparent) {
      if (node instanceof Mesh) {
         Mesh mesh = (Mesh) node;
         for (int i = 0; i < mesh.getSubmeshCount(); i++) {
            Appearance appearance = mesh.getAppearance(i);
            if (appearance != null) {
               appearance.setTexture(0, texture);
               if (isTransparent && texture != null && this.texture == null) {
                  appearance.setMaterial(null);
               }
            }
         }
      } else if (node instanceof Group) {
         Group group = (Group) node;
         for (int i = 0; i < group.getChildCount(); i++) {
            applyTextureToNode(group.getChild(i), texture, isTransparent);
         }
      }
   }

   public void update(long deltaTime) {
      // AEMesh не поддерживает анимацию, но метод оставлен для совместимости
   }

   public int getCurrentAnimFrame() {
      return 0;
   }

   public void setAnimationSpeed(int speed) {
      // Не поддерживается
   }

   public void setAnimationRangeInTime(int start, int end) {
      // Не поддерживается
   }

   public void setAnimationMode(byte mode) {
      // Не поддерживается
   }

   public void disableAnimation() {
      // Не поддерживается
   }

   public boolean hasAnimation() {
      return false;
   }
}