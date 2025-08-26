package AE.PaintCanvas;

import javax.microedition.lcdui.Graphics;

public abstract class IGraphics3D {

   public abstract void bindTarget(Graphics paramGraphics);

   public abstract void releaseTarget();

   public abstract void clear();
}
