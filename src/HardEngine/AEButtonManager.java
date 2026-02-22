package HardEngine;

import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.Math.AEMath;
import GoF2.Globals;

public class AEButtonManager {
    
    public final boolean debug = false;
    public interface_loader[] AEGraphics;
    
    private int[] joystickPixels = null;
    private int[] joystickBackgroundPixels = null;
    private int[] switchableButtonPixels = null;
    private int[] standartButtonPixels = null;
    
    private boolean joystickCacheInitialized = false;
    private boolean switchableCacheInitialized = false;
    private boolean standartCacheInitialized = false;
    
    public Image joystickNormal;
    public Image joystickPressed;
    public Image joystickBackground;
    public boolean joystickActive = false;
    public int joystickWidth;
    public int joystickHeight;
    public int joystickX;
    public int joystickY;
    public int joystickBackgroundWidth;
    public int joystickBackgroundHeight;
    public int joystickXDefaultPosition;
    public int joystickYDefaultPosition;
    public float joystickXFloat = 0.0f;
    public float joystickYFloat = 0.0f;
    public float shipTilt = 0;
    public final float deadZone = 0.001f;
    
    private int joystickTouchCenterX;
    private int joystickTouchCenterY;
    private int joystickTouchRadius;
    
    private int joystickMoveCenterX;
    private int joystickMoveCenterY;
    private int joystickMoveMaxRadius;
    
    public Image switchableButtonNormal;
    public Image switchableButtonPressed;
    public Image switchableButtonInactive;
    public boolean switchableButtonActive = false;
    public boolean switchableButtonState = false;
    public int switchableButtonWidth;
    public int switchableButtonHeight;
    public int switchableButtonX;
    public int switchableButtonY;
    private int switchableTouchBoundLeft;
    private int switchableTouchBoundTop;
    
    public Image standartButtonNormal;
    public Image standartButtonPressed;
    public boolean standartButtonActive = false;
    public int standartButtonWidth;
    public int standartButtonHeight;
    public int standartButtonX;
    public int standartButtonY;
    private int standartTouchBoundLeft;
    private int standartTouchBoundTop;
    
    public AEButtonManager() {
        AEGraphics = new interface_loader[1];
        for(int count = 0; count < AEGraphics.length; ++count) {
            AEGraphics[count] = new interface_loader();
        }
        
        if(joystickNormal == null) {
            joystickNormal = Globals.joystickNormal;
            joystickPressed = Globals.joystickPressed;
            joystickBackground = Globals.joystickBackground;
            
            if(joystickNormal != null) {
                joystickWidth = joystickNormal.getWidth();
                joystickHeight = joystickNormal.getHeight();
                joystickTouchRadius = Math.min(joystickWidth, joystickHeight) / 2;
            }
            
            if(joystickBackground != null) {
                joystickBackgroundWidth = joystickBackground.getWidth();
                joystickBackgroundHeight = joystickBackground.getHeight();
                joystickMoveMaxRadius = Math.min(joystickBackgroundWidth, joystickBackgroundHeight) / 2;
            }
            
            cachePixelData();
        }
    }
    
    public void cachePixelData() {
        if(!joystickCacheInitialized && joystickNormal != null) {
            joystickPixels = new int[joystickWidth * joystickHeight];
            joystickNormal.getRGB(joystickPixels, 0, joystickWidth, 
                                  0, 0, joystickWidth, joystickHeight);
            
            if(joystickBackground != null) {
                joystickBackgroundPixels = new int[joystickBackgroundWidth * joystickBackgroundHeight];
                joystickBackground.getRGB(joystickBackgroundPixels, 0, joystickBackgroundWidth,
                                         0, 0, joystickBackgroundWidth, joystickBackgroundHeight);
            }
            joystickCacheInitialized = true;
        }
    }
    
    public void drawStandartButton(Image normal, Image pressed, int buttonX, int buttonY) {
        if(normal != null && pressed != null) {
            if(!standartCacheInitialized) {
                standartButtonNormal = normal;
                standartButtonPressed = pressed;
                standartButtonWidth = normal.getWidth();
                standartButtonHeight = normal.getHeight();
                
                standartButtonPixels = new int[standartButtonWidth * standartButtonHeight];
                normal.getRGB(standartButtonPixels, 0, standartButtonWidth,
                             0, 0, standartButtonWidth, standartButtonHeight);
                standartCacheInitialized = true;
            }
            
            standartButtonX = buttonX;
            standartButtonY = buttonY;
            standartTouchBoundLeft = buttonX - (standartButtonWidth / 2) - 1;
            standartTouchBoundTop = buttonY - (standartButtonHeight / 2) - 1;
            
            GlobalStatus.graphics.drawImage(standartButtonActive ? pressed : normal, 
                                           buttonX, buttonY, 3);
            
            if(debug) {
                GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawRect(standartTouchBoundLeft, standartTouchBoundTop,
                                              standartButtonWidth, standartButtonHeight);
            }
        }
    }
    
    public void drawswitchableButton(Image normal, Image pressed, Image inactive, 
                                    int buttonX, int buttonY, boolean activity) {
        if(normal != null && pressed != null && inactive != null) {
            if (!switchableCacheInitialized) {
                switchableButtonNormal = normal;
                switchableButtonPressed = pressed;
                switchableButtonInactive = inactive;
                switchableButtonWidth = normal.getWidth();
                switchableButtonHeight = normal.getHeight();
                
                switchableButtonPixels = new int[switchableButtonWidth * switchableButtonHeight];
                normal.getRGB(switchableButtonPixels, 0, switchableButtonWidth,
                             0, 0, switchableButtonWidth, switchableButtonHeight);
                switchableCacheInitialized = true;
            }
            
            switchableButtonX = buttonX;
            switchableButtonY = buttonY;
            switchableButtonState = activity;
            switchableTouchBoundLeft = buttonX - (switchableButtonWidth / 2) - 1;
            switchableTouchBoundTop = buttonY - (switchableButtonHeight / 2) - 1;
            
            Image toDraw = switchableButtonState ? normal : inactive;
            GlobalStatus.graphics.drawImage(toDraw, buttonX, buttonY, 3);
            
            if (switchableButtonActive) {
                GlobalStatus.graphics.drawImage(pressed, buttonX, buttonY, 3);
            }
            
            if (debug) {
                GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawRect(switchableTouchBoundLeft, switchableTouchBoundTop,
                                              switchableButtonWidth, switchableButtonHeight);
            }
        }
    }
    
    public void drawJoystick(int buttonX, int buttonY) {
        if(joystickNormal != null && joystickPressed != null) {
            if(joystickXDefaultPosition == 0 && joystickYDefaultPosition == 0) {
                joystickXDefaultPosition = buttonX;
                joystickYDefaultPosition = buttonY;
                joystickX = joystickXDefaultPosition;
                joystickY = joystickYDefaultPosition;
                
                joystickTouchCenterX = buttonX;
                joystickTouchCenterY = buttonY;
                joystickMoveCenterX = buttonX;
                joystickMoveCenterY = buttonY;
            }
			
            if(AEGraphics[0] != null) {
                AEGraphics[0].drawImage(joystickBackground, joystickXDefaultPosition, 
                                       joystickYDefaultPosition, 3);
            }
            
            // Рисуем джойстик по центру его текущей позиции
            GlobalStatus.graphics.drawImage(joystickActive ? joystickPressed : joystickNormal,
                                           joystickX, joystickY, 3);
            
            if(debug) {
                GlobalStatus.graphics.setColor(240, 0, 255);
                // Показываем область касания (округ)
                GlobalStatus.graphics.drawArc(joystickTouchCenterX - joystickTouchRadius,
                                            joystickTouchCenterY - joystickTouchRadius,
                                            joystickTouchRadius * 2, joystickTouchRadius * 2,
                                            0, 360);
                // Показываем границы движения (округ)
                GlobalStatus.graphics.drawArc(joystickMoveCenterX - joystickMoveMaxRadius,
                                            joystickMoveCenterY - joystickMoveMaxRadius,
                                            joystickMoveMaxRadius * 2, joystickMoveMaxRadius * 2,
                                            0, 360);
                
                GlobalStatus.graphics.setColor(255, 0, 0);
                GlobalStatus.graphics.drawRect(joystickX - 1, joystickY - 1, 3, 3); // Центр джойстика
                
                Font.sub_14d_CENTER("X: " + getJoystickX() + ", Y: " + getJoystickY(),
                                   joystickX + (joystickWidth / 2), 
                                   joystickY + (joystickHeight / 2), 7);
                Font.sub_14d_CENTER("X: " + getJoystickXFloat() + "F, Y: " + getJoystickYFloat() + "F",
                                   joystickX + (joystickWidth / 2), 
                                   joystickY + (joystickHeight / 2) + 20, 7);
            }
        }
    }
    
    public void standartButtonTouch(int touchX, int touchY) {
        if(standartButtonNormal != null && standartButtonPressed != null) {
            if(touchX < standartTouchBoundLeft || 
                touchY < standartTouchBoundTop ||
                touchX >= standartTouchBoundLeft + standartButtonWidth ||
                touchY >= standartTouchBoundTop + standartButtonHeight) {
                return;
            }
            
            int relativeX = touchX - standartTouchBoundLeft;
            int relativeY = touchY - standartTouchBoundTop;
            
            if(isPixelOpaqueOptimized(standartButtonPixels, standartButtonWidth, 
                                      relativeX, relativeY)) {
                standartButtonActive = true;
            }
        }
    }
    
    public void switchableButtonTouch(int touchX, int touchY) {
        if(switchableButtonNormal != null) {
            if(touchX < switchableTouchBoundLeft || 
                touchY < switchableTouchBoundTop ||
                touchX >= switchableTouchBoundLeft + switchableButtonWidth ||
                touchY >= switchableTouchBoundTop + switchableButtonHeight) {
                return;
            }
            
            int relativeX = touchX - switchableTouchBoundLeft;
            int relativeY = touchY - switchableTouchBoundTop;
            
            if(isPixelOpaqueOptimized(switchableButtonPixels, switchableButtonWidth,
                                      relativeX, relativeY)) {
                switchableButtonActive = true;
            }
        }
    }
    
    public void joystickTouch(int touchX, int touchY) {
        if(joystickNormal != null) {
            int dx = touchX - joystickTouchCenterX;
            int dy = touchY - joystickTouchCenterY;
            int distanceSquared = dx * dx + dy * dy;
            
            int radiusSquared = joystickTouchRadius * joystickTouchRadius;
            
            if(distanceSquared <= radiusSquared) {
                joystickActive = true;
            } else {
                int relativeX = touchX - (joystickTouchCenterX - joystickWidth / 2);
                int relativeY = touchY - (joystickTouchCenterY - joystickHeight / 2);
                
                if(relativeX >= 0 && relativeX < joystickWidth &&
                    relativeY >= 0 && relativeY < joystickHeight &&
                    isPixelOpaqueOptimized(joystickPixels, joystickWidth, relativeX, relativeY)) {
                    joystickActive = true;
                } else {
                    joystickActive = false;
                    resetJoystickPosition();
                }
            }
        }
    }
    
    public void joystickDrag(int touchX, int touchY) {
        if(!joystickActive || joystickBackgroundPixels == null) {
            return;
        }
        
        int deltaX = touchX - joystickMoveCenterX;
        int deltaY = touchY - joystickMoveCenterY;
        
        int distanceSquared = deltaX * deltaX + deltaY * deltaY;
        
        int maxRadiusSquared = joystickMoveMaxRadius * joystickMoveMaxRadius;
        if(distanceSquared > maxRadiusSquared) {
            float maxDistance = joystickMoveMaxRadius;
            float currentDistance = (float)Math.sqrt(distanceSquared);
            
            if(currentDistance > 0) {
                float ratio = maxDistance / currentDistance;
                deltaX = (int)(deltaX * ratio);
                deltaY = (int)(deltaY * ratio);
                distanceSquared = maxRadiusSquared;
            }
        }
        
        joystickX = joystickMoveCenterX + deltaX;
        joystickY = joystickMoveCenterY + deltaY;
		
        joystickXFloat = -((float)deltaX / joystickMoveMaxRadius);
        
        if(GlobalStatus.invertedControlsOn) {
            joystickYFloat = -((float)deltaY / joystickMoveMaxRadius);
        } else {
            joystickYFloat = ((float)deltaY / joystickMoveMaxRadius);
        }
        
        if(Math.abs(joystickXFloat) < deadZone) joystickXFloat = 0.0f;
        if(Math.abs(joystickYFloat) < deadZone) joystickYFloat = 0.0f;
		
        shipTilt = joystickXFloat * 384.0f;
    }
    
    private void resetJoystickPosition() {
        joystickX = joystickXDefaultPosition;
        joystickY = joystickYDefaultPosition;
        joystickXFloat = 0.0f;
        joystickYFloat = 0.0f;
        shipTilt = 0.0f;
    }
    
    public void buttonsTouchReleased(int x, int y) {
        joystickActive = false;
        resetJoystickPosition();
        switchableButtonActive = false;
        standartButtonActive = false;
    }
    
    public boolean isPixelOpaqueOptimized(int[] pixelData, int width, int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= pixelData.length / width) {
            return false;
        }
        
        int index = y * width + x;
        if(index < 0 || index >= pixelData.length) {
            return false;
        }
		
        return (pixelData[index] & 0xFF000000) != 0;
    }
    
    private float findMaxDistanceInDirection(int centerX, int centerY, int deltaX, int deltaY) {
        if(deltaX == 0 && deltaY == 0) return 0;
        float maxDistance = 0;
        float length = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        float stepX = deltaX / length;
        float stepY = deltaY / length;
        
        for(float distance = 0; distance < length; distance += 1f) {
            int x = centerX + (int)(stepX * distance);
            int y = centerY + (int)(stepY * distance);
            
            int bgX = x - (centerX - joystickBackgroundWidth / 2);
            int bgY = y - (centerY - joystickBackgroundHeight / 2);
            
            if(bgX < 0 || bgY < 0 || bgX >= joystickBackgroundWidth || bgY >= joystickBackgroundHeight) {
                break;
            }
            
            if(isPixelOpaqueOptimized(joystickBackgroundPixels, joystickBackgroundWidth, bgX, bgY)) {
                maxDistance = distance;
            } else {
                break;
            }
        }
        return maxDistance;
    }
    
    public boolean isPixelOpaque(int[] pixelData, int width, int x, int y) {
        return isPixelOpaqueOptimized(pixelData, width, x, y);
    }
    
    public final boolean getStandartButtonPressed() {
        return standartButtonActive;
    }
    
    public final boolean getSwitchableButtonPressed() {
        return switchableButtonActive;
    }
    
    public final int getJoystickX() {
        return joystickX;
    }
    
    public final int getJoystickY() {
        return joystickY;
    }
    
    public final float getJoystickXFloat() {
        return joystickXFloat;
    }
    
    public final float getJoystickYFloat() {
        return joystickYFloat;
    }
    
    public final float getShipTilt() {
        return shipTilt;
    }
    
    public final boolean getJoystickPressed() {
        return joystickActive;
    }
}