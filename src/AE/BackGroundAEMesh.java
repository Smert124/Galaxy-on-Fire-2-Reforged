package AE;

import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;

import AE.PaintCanvas.AEGraphics3D;

public final class BackGroundAEMesh extends AbstractMesh {

   private static Transform transform = new Transform();
   private static float[] tranformFloatArr = new float[16];
   private Node mesh;
   private static CompositingMode compositing;

   public BackGroundAEMesh(String path) {
      try {
         Object3D[] objects = AEMeshLoader.loadAEMesh(path);
         
         if (objects != null && objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
               if (objects[i] instanceof javax.microedition.m3g.Group) {
                  this.mesh = (Node) objects[i];
                  break;
               }
            }
         }
      } catch (Exception e) {
         this.mesh = null;
         e.printStackTrace();
      }

      this.radius = 0;
      if (compositing == null) {
         (compositing = new CompositingMode()).setBlending(CompositingMode.ALPHA);
         compositing.setDepthTestEnable(true);
         compositing.setDepthWriteEnable(false);
      }
   }

   private BackGroundAEMesh(BackGroundAEMesh var1) {
      this.radius = 0;
      this.mesh = var1.mesh;
      this.renderLayer = var1.renderLayer;
      this.draw = var1.draw;
   }

   public final void render() {
      if (this.mesh != null) {
         this.matrix.toFloatArray(tranformFloatArr);
         tranformFloatArr[3] = tranformFloatArr[7] = tranformFloatArr[11] = 0.0F;
         transform.set(tranformFloatArr);
         AEGraphics3D.graphics3D.render(this.mesh, transform);
      }
   }

   public final void appendToRender(AECamera var1, Renderer var2) {
      if (this.draw) {
         this.matrix = var1.tempTransform.getInverse(this.matrix);
         var2.drawNode(this.renderLayer, this);
      }
   }

   public final GraphNode clone() {
      return new BackGroundAEMesh(this);
   }

   public final void setTexture(ITexture var1) {
      if (this.mesh == null || var1 == null) {
         return;
      }
      
      Texture2D[] textures = ((JSRTexture) var1).getTexturesArray();
      
      if (textures == null || textures.length == 0) {
         return;
      }
      
      if (this.mesh instanceof javax.microedition.m3g.Group) {
         this.setTexture((javax.microedition.m3g.Group) this.mesh, textures);
      }
   }

   private void setTexture(javax.microedition.m3g.Group group, Texture2D[] textures) {
      if (group == null || textures == null) {
         return;
      }
      
      for (int i = 0; i < group.getChildCount(); ++i) {
         Node node;
         if ((node = group.getChild(i)) instanceof Mesh) {
            final int uid = ((Mesh) node).getUserID();

            for (int j = 0; j < ((Mesh) node).getSubmeshCount(); ++j) {
               Appearance appearance = ((Mesh) node).getAppearance(j);
               if (appearance == null) {
                  appearance = new Appearance();
                  ((Mesh) node).setAppearance(j, appearance);
               }
               
               appearance.setMaterial((Material) null);
               appearance.setCompositingMode(compositing);
               
               // Убираем проверку appearance.getTexture(0) != null
               // Применяем текстуру всегда
               if (uid < textures.length) {
                  appearance.setTexture(0, textures[uid]);
               } else {
                  appearance.setTexture(0, textures[0]);
               }
            }
         } else if (node instanceof javax.microedition.m3g.Group) {
            this.setTexture((javax.microedition.m3g.Group) node, textures);
         }
      }
   }

   public final void OnRelease() {
      this.mesh = null;
   }
}