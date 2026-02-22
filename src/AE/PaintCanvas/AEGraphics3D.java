package AE.PaintCanvas;

import javax.microedition.lcdui.Graphics;
import javax.microedition.m3g.Background;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.m3g.Transform;

public final class AEGraphics3D extends IGraphics3D {

    public static Graphics3D graphics3D;
    private static Background background;

    public AEGraphics3D() {
        graphics3D = Graphics3D.getInstance();
		
		if(background == null) {
            background = new Background();
            background.setColorClearEnable(false);
            background.setDepthClearEnable(true);
        }
    }

    public final void bindTarget(Graphics graphics) {
        try {
			int hints = Graphics3D.ANTIALIAS | Graphics3D.DITHER;
            graphics3D.bindTarget(graphics, true, hints);
        } catch (Exception ex) {
            graphics3D.releaseTarget();
        }
    }

    public final void clear() {
        try {
            graphics3D.clear(background);
        } catch (Exception ex) {
        }
    }

    public final void releaseTarget() {
        try {
            graphics3D.releaseTarget();
        } catch (Exception ex) {
        }
    }
}
