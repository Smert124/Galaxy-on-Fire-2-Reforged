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
	protected Transform tranforma = new Transform();
	
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
	
	public void appendToRender(AECamera var1, Renderer var2) { // append render
		if(this.draw && var1.isInViewFrustum(this.boundingSphere) != 0) {
			this.matrix = var1.tempTransform.getInverse(this.matrix);
			this.matrix.multiply(this.tempTransform);
			var2.drawNode(this.renderLayer, this);
		}
	}
	
	public void forceAppendToRender(AECamera var1, Renderer var2) { // force append render
		if(this.draw) {
			this.matrix = var1.tempTransform.getInverse(this.matrix);
			this.matrix.multiply(this.tempTransform);
			
			if(this.getID() >= 3100 && this.getID() <= 3110) { // model-source of light
				this.sourceOfLight.setIntensity(3.5F);
				this.sourceOfLight.setColor(Level.maxLight());
				this.sourceOfLight.setMode(Light.OMNI);
				
				this.ambientLight.setIntensity(3.5F);
				this.ambientLight.setColor(Level.skyNormalizedLight());
				this.ambientLight.setMode(Light.AMBIENT);
				
				AEGraphics3D.graphics3D.resetLights();
				float arr[] = new float[16];
				this.matrix.toFloatArray(arr);
				this.tranforma.set(arr);
				AEGraphics3D.graphics3D.addLight(this.sourceOfLight, this.tranforma);
				AEGraphics3D.graphics3D.addLight(this.ambientLight, this.tranforma);
			}
			var2.drawNode(this.renderLayer, this);
		}
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