package AE;

import javax.microedition.m3g.Light;
import javax.microedition.m3g.Transform;

import AE.Math.Matrix;
import AE.PaintCanvas.AEGraphics3D;
import GoF2.Level;

public abstract class AbstractMesh extends AEGeometry {
    
    protected int renderLayer;
    protected Matrix matrix = new Matrix();
    protected Light sourceOfLight = new Light();
    protected Light ambientLight = new Light();
    protected Transform transform = new Transform();
    private static final float[] tempFloatArray = new float[16];
    private boolean lightsInitialized = false;
    private final Matrix tempMatrix = new Matrix();
    
    public AbstractMesh(AbstractMesh var1) {
        super(var1);
        this.renderLayer = var1.renderLayer;
    }
    
    public AbstractMesh() {
        this.renderLayer = 0;
    }
    
    public final void setRenderLayer(int var1) {
        this.renderLayer = var1;
    }
    
    public void appendToRender(AECamera var1, Renderer var2) {
        if(this.draw && var1.isInViewFrustum(this.boundingSphere) != 0) {
            this.matrix = var1.tempTransform.getInverse(tempMatrix);
            this.matrix.multiply(this.tempTransform);
            var2.drawNode(this.renderLayer, this);
        }
    }
    
    public void forceAppendToRender(AECamera var1, Renderer var2) {
        if(!this.draw) {
            return;
        }
        this.matrix = var1.tempTransform.getInverse(tempMatrix);
        this.matrix.multiply(this.tempTransform);
        
        int id = this.getID();
        if(id >= 3100 && id <= 3110) {
            setupLightsOnce();
            applyLights();
            var2.drawNode(this.renderLayer, this);
        } else {
            var2.drawNode(this.renderLayer, this);
        }
    }
    
    private void setupLightsOnce() {
        if (!lightsInitialized) {
            this.sourceOfLight.setIntensity(3.5F);
            this.sourceOfLight.setColor(Level.maxLight());
            this.sourceOfLight.setMode(Light.OMNI);
            
            this.ambientLight.setIntensity(3.5F);
            this.ambientLight.setColor(Level.skyNormalizedLight());
            this.ambientLight.setMode(Light.AMBIENT);
            
            lightsInitialized = true;
        }
    }
    
    private void applyLights() {
        AEGraphics3D.graphics3D.resetLights();
        this.matrix.toFloatArray(tempFloatArray);
        this.transform.set(tempFloatArray);
        AEGraphics3D.graphics3D.addLight(this.sourceOfLight, this.transform);
        AEGraphics3D.graphics3D.addLight(this.ambientLight, this.transform);
    }
    
    public abstract void render();
    
    public void renderTransparent() {}
    
    public abstract GraphNode clone();
    
    public static AbstractMesh newPlaneStrip(int var0, int var1, byte var2) {
        return new ParticleSystemMesh(var0, var1, var2);
    }
    
    public abstract void setTexture(ITexture var1);
    
    public abstract void OnRelease();
    
    public void update(long var1) {}
}