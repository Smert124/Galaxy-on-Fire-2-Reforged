package AE;

import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.PolygonMode;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;

import AE.PaintCanvas.AEGraphics3D;

public final class BackGroundAEMesh extends AbstractMesh {

   private static Transform transform = new Transform();
   private static float[] tranformFloatArr = new float[16];
   private Node mesh;
   private static CompositingMode compositing;

   public BackGroundAEMesh(String path) {
      String var2 = path;
      BackGroundAEMesh var5 = this;

      try {
         AEMesh aeMeshLoader = new AEMesh(0, var2, 0);
         var5.mesh = aeMeshLoader.node;
      } catch (Exception var4) {
         this.mesh = null;
         var4.printStackTrace();
      }

      this.radius = 0;
      if(compositing == null) {
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
      this.matrix.toFloatArray(tranformFloatArr);
      tranformFloatArr[3] = tranformFloatArr[7] = tranformFloatArr[11] = 0.0F;
      tranformFloatArr[7] = 0.0F;
      transform.set(tranformFloatArr);
	  AEGraphics3D.graphics3D.render(this.mesh, transform);
   }

   public final void appendToRender(AECamera var1, Renderer var2) {
      if(this.draw) {
         this.matrix = var1.tempTransform.getInverse(this.matrix);
         var2.drawNode(this.renderLayer, this);
      }
   }

   public final GraphNode clone() {
      return new BackGroundAEMesh(this);
   }

   public final void setTexture(ITexture var1) {
	   this.setTexture((Mesh)this.mesh, ((JSRTexture)var1).getTexturesArray());
   }

   private void setTexture(Mesh var1, Texture2D[] var2) {
      if (var1 != null) {
         for(int var3 = 0; var3 < var1.getSubmeshCount(); ++var3) {
            Appearance var4 = var1.getAppearance(var3);
            if (var4 == null) {
               var4 = new Appearance();
               var1.setAppearance(var3, var4);
               //System.out.println("Created new Appearance for submesh: " + var3);
            }
            var4.setMaterial((Material)null);
			PolygonMode polygonMode = new PolygonMode();
            polygonMode.setPerspectiveCorrectionEnable(true);
            var4.setPolygonMode(polygonMode);
            var4.setCompositingMode(compositing);
            if(var2 != null && var2.length > 0) {
               int var5 = var1.getUserID();
               if(var5 < var2.length) {
                  var4.setTexture(0, var2[var5]);
                  //System.out.println("Set texture for submesh: " + var3 + " to " + var2[var5]);
               } else {
                  var4.setTexture(0, var2[0]);
                  //System.out.println("Set default texture for submesh: " + var3 + " to " + var2[0]);
               }
            } else {
               //System.out.println("No textures available for submesh: " + var3);
            }
         }
      } else {
         //System.out.println("Mesh is null");
      }
   }

   public final void OnRelease() {
   }
}