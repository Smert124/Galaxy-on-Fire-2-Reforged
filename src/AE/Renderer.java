package AE;

// renderer

import AE.PaintCanvas.IGraphics3D;
import AE.PaintCanvas.RenderLayer;

public final class Renderer {

   private AECamera var_1d7 = null;
   private GraphNode cameraTopGroup;
   private RenderLayer[] layers = new RenderLayer[1];
   private boolean[] needGraphicsClear;
   private IGraphics3D graphics3D;


   public Renderer(IGraphics3D var1) {
      this.layers[0] = new RenderLayer();
      this.needGraphicsClear = new boolean[1];
      this.needGraphicsClear[0] = true;
      this.graphics3D = var1;
   }

   public final void setActiveCamera(AECamera var1) {
      this.var_1d7 = var1;
      this.var_1d7.setActive();

      for(this.cameraTopGroup = this.var_1d7; this.cameraTopGroup.getGroup() != null; this.cameraTopGroup = this.cameraTopGroup.getGroup()) {
         ;
      }

   }

   public final AECamera sub_85() { // get camera
      return this.var_1d7;
   }

   public final void drawNodeInVF(GraphNode var1) {
      try {
         if(var1 != null && this.layers != null && this.var_1d7 != null) {
            var1.updateTransform(false);
            if(this.cameraTopGroup != null) {
               this.cameraTopGroup.updateTransform(false);
            } else {
               this.var_1d7.updateTransform(false);
            }

            var1.appendToRender(this.var_1d7, this);
         }

      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }
   
   public final void forceRenderModel(GraphNode var1) {
      try {
         if(var1 != null && this.layers != null && this.var_1d7 != null) {
            var1.updateTransform(false);
            if(this.cameraTopGroup != null) {
               this.cameraTopGroup.updateTransform(false);
            } else {
               this.var_1d7.updateTransform(false);
            }

            var1.forceAppendToRender(this.var_1d7, this);
         }

      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public final void renderStillFrame() {
      for(int var1 = 0; var1 < this.layers.length; ++var1) {
         this.layers[var1].render();
         if(this.needGraphicsClear[var1]) {
            this.graphics3D.clear();
         }

         this.layers[var1].reset();
      }

   }

   public final void renderFrame(long var1) {
      for(int var3 = 0; var3 < this.layers.length; ++var3) {
         this.layers[var3].update(var1);
         this.layers[var3].render();
         if(this.needGraphicsClear[var3]) {
            this.graphics3D.clear();
         }

         this.layers[var3].reset();
      }

   }

   public final void addLayer() {
      boolean var1 = true;
      RenderLayer[] var2 = new RenderLayer[this.layers.length + 1];
      System.arraycopy(this.layers, 0, var2, 0, this.layers.length);
      var2[this.layers.length] = new RenderLayer();
      this.layers = var2;
      boolean[] var3 = new boolean[this.needGraphicsClear.length + 1];
      System.arraycopy(this.needGraphicsClear, 0, var3, 0, this.needGraphicsClear.length);
      var3[this.needGraphicsClear.length] = true;
      this.needGraphicsClear = var3;
   }

   public final void drawNode(int var1, AbstractMesh var2) {
      this.layers[var1].appendNode(var2);
   }
}
