package GoF2;

import AE.IApplicationModule;
import Main.GF2;

public final class ApplicationManager {

   private IApplicationModule scene = null;
   private GF2 midlet;
   private LoadingScreen loadingScreen;


   public ApplicationManager(GF2 var1, LoadingScreen var2) {
      this.midlet = var1;
      this.loadingScreen = var2;
   }

   public final void SetCurrentApplicationModule(IApplicationModule var1) {
      if(this.scene != null) {
         if(this.loadingScreen != null) {
            this.loadingScreen.startLoading_(true);
         }

         GameText.init();
         this.scene.OnRelease();
      }

      this.scene = var1;
   }

   public final IApplicationModule GetCurrentApplicationModule() {
      return this.scene;
   }

   public final void Quit() {
      this.midlet.sub_199();
   }

   public final void handleKeystate(int var1) {
      if(this.scene != null) {
         this.scene.handleKeystate(var1);
      }

   }

   public final void renderScene(int var1) {
      if(this.scene != null) {
         if(this.scene.isLoaded()) {
            this.scene.renderScene(var1);
            return;
         }

         this.scene.OnInitialize();
         if(this.scene.isLoaded() && this.loadingScreen != null) {
            this.loadingScreen.close();
         }

         System.gc();
      }

   }
}
