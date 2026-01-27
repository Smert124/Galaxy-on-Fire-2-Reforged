package HardEngine;

import AE.Math.AEMath;

public class TouchTracker {
	
    private int lastX = -1;
    private int lastY = -1;
    private boolean isTouching = false;
    private TouchListener listener = null;
    
    private int sensitivity = 3;
    private boolean invertVertical = true;
    
    public static final int NONE = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    public interface TouchListener {
        void onTouchMove(int deltaX, int deltaY);
    }
    
    public TouchTracker() {}
    
    public TouchTracker(TouchListener listener) {
        this.listener = listener;
    }
    
    public void pointerPressed(int x, int y) {
        lastX = x;
        lastY = y;
        isTouching = true;
    }
    
    public void pointerDragged(int x, int y) {
        if(!isTouching) return;
        
        if(lastX == -1 || lastY == -1) {
            lastX = x;
            lastY = y;
            return;
        }
        
        int deltaX = x - lastX;
        int deltaY = y - lastY;
        
        if(invertVertical) {
            deltaY = -deltaY;
        }
        
        // Проверка чувствительности
        if(Math.abs(deltaX) > sensitivity || Math.abs(deltaY) > sensitivity) {
            if(listener != null) {
                listener.onTouchMove(deltaX, deltaY);
            }
            
            lastX = x;
            lastY = y;
        }
    }
    
    public void pointerReleased(int x, int y) {
        isTouching = false;
    }
    
    public void setListener(TouchListener listener) {
        this.listener = listener;
    }
    
    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }
    
    public void setInvertVertical(boolean invertVertical) {
        this.invertVertical = invertVertical;
    }
    
    public boolean isTouching() {
        return isTouching;
    }
    
    public void reset() {
        lastX = -1;
        lastY = -1;
        isTouching = false;
    }
    
    public static int getSimpleDirection(int deltaX, int deltaY, int sensitivity) {
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > sensitivity) return RIGHT;
            if (deltaX < -sensitivity) return LEFT;
        } else {
            if (deltaY > sensitivity) return DOWN;
            if (deltaY < -sensitivity) return UP;
        }
        return NONE;
    }
}