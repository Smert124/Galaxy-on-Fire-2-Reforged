package AE.PaintCanvas;

import javax.microedition.lcdui.Graphics;
import javax.microedition.m3g.Background;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.m3g.Transform;
import java.util.Hashtable;

public final class AEGraphics3D extends IGraphics3D {

    public static Graphics3D graphics3D;
    private static Background background;
	
	private static boolean preload = true;
    private static int lastClipX, lastClipY, lastClipW, lastClipH;
    private static boolean lastDepthEnabled;
    private static int lastHints;
	private static boolean lastClearHadBackground = false;
	private static long lastClearTime = 0;

    public AEGraphics3D() {
        graphics3D = Graphics3D.getInstance();
		
		if(background == null) {
            background = new Background();
            background.setColorClearEnable(false);
            background.setDepthClearEnable(true);
        }
    }

    public final void bindTarget(Graphics graphics, boolean depthEnabled, int hints) {
        try {
            int clipX = graphics.getTranslateX() + graphics.getClipX();
            int clipY = graphics.getTranslateY() + graphics.getClipY();
            int clipW = graphics.getClipWidth();
            int clipH = graphics.getClipHeight();
            
            if (preload && 
                clipX == lastClipX && clipY == lastClipY &&
                clipW == lastClipW && clipH == lastClipH &&
                depthEnabled == lastDepthEnabled &&
                hints == lastHints) {
                return;
            }
            
            graphics3D.bindTarget(graphics, depthEnabled, hints);
            
            lastClipX = clipX;
            lastClipY = clipY;
            lastClipW = clipW;
            lastClipH = clipH;
            lastDepthEnabled = depthEnabled;
            lastHints = hints;
            preload = false;
            
        } catch (Exception ex) {
            System.out.println("bindTarget error: " + ex.getMessage());
            graphics3D.releaseTarget();
        }
    }
	
	public final void bindTarget(Graphics graphics) {
        bindTarget(graphics, true, Graphics3D.ANTIALIAS | Graphics3D.DITHER);
    }
	
	public final void setViewport(int x, int y, int width, int height) {
    try {
        if (width <= 0 || height <= 0) {
            System.out.println("Invalid viewport size: " + width + "x" + height);
            return;
        }
        Hashtable props = Graphics3D.getProperties();
        int maxWidth = ((Integer)props.get("maxViewportWidth")).intValue();
        int maxHeight = ((Integer)props.get("maxViewportHeight")).intValue();
        
        if (width > maxWidth || height > maxHeight) {
            System.out.println("Viewport too large: " + width + "x" + height + 
                               " max: " + maxWidth + "x" + maxHeight);
            width = Math.min(width, maxWidth);
            height = Math.min(height, maxHeight);
        }
        
        graphics3D.setViewport(x, y, width, height);
        
    } catch (Exception ex) {
        System.out.println("setViewport error: " + ex.getMessage());
    }
}

    public final void clear() {
    try {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClearTime < 16 && lastClearHadBackground) {
            return;
        }
        
        graphics3D.clear(background);
        lastClearTime = currentTime;
        lastClearHadBackground = true;
        
    } catch (Exception ex) {
        System.out.println("clear error: " + ex.getMessage());
    }
}

    public final void releaseTarget() {
        try {
            graphics3D.releaseTarget();
        } catch (Exception ex) {
        }
    }
}
