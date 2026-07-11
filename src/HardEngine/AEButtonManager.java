package HardEngine;

import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import AE.Math.AEMath;
import GoF2.Globals;

public class AEButtonManager {
    
    public final boolean debug = false;
    public interface_loader[] AEGraphics;
    
    private boolean[] joystickPixelsOpaque = null;
    private boolean[] joystickBackgroundPixelsOpaque = null;
    private boolean[] switchableButtonPixelsOpaque = null;
    private boolean[] standartButtonPixelsOpaque = null;
    
    private Image cachedJoystickNormal = null;
    private Image cachedJoystickPressed = null;
    private Image cachedJoystickBackground = null;
    private Image cachedSwitchableNormal = null;
    private Image cachedSwitchablePressed = null;
    private Image cachedSwitchableInactive = null;
    private Image cachedStandartNormal = null;
    private Image cachedStandartPressed = null;
    
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
    
    /**
     * Кеширует пиксельные данные изображений с оптимизацией по альфа-каналу.
     */
    public void cachePixelData() {
        if(!joystickCacheInitialized || 
           cachedJoystickNormal != joystickNormal || 
           cachedJoystickPressed != joystickPressed ||
           cachedJoystickBackground != joystickBackground) {
            
            if(joystickNormal != null) {
                int[] tempPixels = new int[joystickWidth * joystickHeight];
                joystickNormal.getRGB(tempPixels, 0, joystickWidth, 
                                      0, 0, joystickWidth, joystickHeight);
                
                joystickPixelsOpaque = new boolean[joystickWidth * joystickHeight];
                for(int i = 0; i < tempPixels.length; i++) {
                    joystickPixelsOpaque[i] = (tempPixels[i] & 0xFF000000) != 0;
                }
                
                cachedJoystickNormal = joystickNormal;
                cachedJoystickPressed = joystickPressed;
                
                if(joystickBackground != null) {
                    tempPixels = new int[joystickBackgroundWidth * joystickBackgroundHeight];
                    joystickBackground.getRGB(tempPixels, 0, joystickBackgroundWidth,
                                             0, 0, joystickBackgroundWidth, joystickBackgroundHeight);
                    
                    joystickBackgroundPixelsOpaque = new boolean[joystickBackgroundWidth * joystickBackgroundHeight];
                    for(int i = 0; i < tempPixels.length; i++) {
                        joystickBackgroundPixelsOpaque[i] = (tempPixels[i] & 0xFF000000) != 0;
                    }
                    
                    cachedJoystickBackground = joystickBackground;
                }
                
                joystickCacheInitialized = true;
            }
        }
    }
    
    public void drawStandartButton(Image normal, Image pressed, int buttonX, int buttonY) {
        if(normal != null && pressed != null) {
            if(!standartCacheInitialized || 
               cachedStandartNormal != normal || 
               cachedStandartPressed != pressed) {
                
                standartButtonNormal = normal;
                standartButtonPressed = pressed;
                standartButtonWidth = normal.getWidth();
                standartButtonHeight = normal.getHeight();
                
                int[] tempPixels = new int[standartButtonWidth * standartButtonHeight];
                normal.getRGB(tempPixels, 0, standartButtonWidth,
                             0, 0, standartButtonWidth, standartButtonHeight);
                
                standartButtonPixelsOpaque = new boolean[standartButtonWidth * standartButtonHeight];
                for(int i = 0; i < tempPixels.length; i++) {
                    standartButtonPixelsOpaque[i] = (tempPixels[i] & 0xFF000000) != 0;
                }
                
                cachedStandartNormal = normal;
                cachedStandartPressed = pressed;
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
            if(!switchableCacheInitialized || 
               cachedSwitchableNormal != normal || 
               cachedSwitchablePressed != pressed ||
               cachedSwitchableInactive != inactive) {
                
                switchableButtonNormal = normal;
                switchableButtonPressed = pressed;
                switchableButtonInactive = inactive;
                switchableButtonWidth = normal.getWidth();
                switchableButtonHeight = normal.getHeight();
                
                int[] tempPixels = new int[switchableButtonWidth * switchableButtonHeight];
                normal.getRGB(tempPixels, 0, switchableButtonWidth,
                             0, 0, switchableButtonWidth, switchableButtonHeight);
                
                switchableButtonPixelsOpaque = new boolean[switchableButtonWidth * switchableButtonHeight];
                for(int i = 0; i < tempPixels.length; i++) {
                    switchableButtonPixelsOpaque[i] = (tempPixels[i] & 0xFF000000) != 0;
                }
                
                cachedSwitchableNormal = normal;
                cachedSwitchablePressed = pressed;
                cachedSwitchableInactive = inactive;
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
            
            GlobalStatus.graphics.drawImage(joystickActive ? joystickPressed : joystickNormal,
                                           joystickX, joystickY, 3);
            
            if(debug) {
                GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawArc(joystickTouchCenterX - joystickTouchRadius,
                                            joystickTouchCenterY - joystickTouchRadius,
                                            joystickTouchRadius * 2, joystickTouchRadius * 2,
                                            0, 360);
                GlobalStatus.graphics.drawArc(joystickMoveCenterX - joystickMoveMaxRadius,
                                            joystickMoveCenterY - joystickMoveMaxRadius,
                                            joystickMoveMaxRadius * 2, joystickMoveMaxRadius * 2,
                                            0, 360);
                
                GlobalStatus.graphics.setColor(255, 0, 0);
                GlobalStatus.graphics.drawRect(joystickX - 1, joystickY - 1, 3, 3);
                
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
            
            if(isPixelOpaque(standartButtonPixelsOpaque, standartButtonWidth, 
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
            
            if(isPixelOpaque(switchableButtonPixelsOpaque, switchableButtonWidth,
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
                    isPixelOpaque(joystickPixelsOpaque, joystickWidth, relativeX, relativeY)) {
                    joystickActive = true;
                } else {
                    joystickActive = false;
                    resetJoystickPosition();
                }
            }
        }
    }
    
    public void joystickDrag(int touchX, int touchY) {
        if(!joystickActive || joystickBackgroundPixelsOpaque == null) {
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
    
    /**
     * Оптимизированная проверка пикселя на непрозрачность.
     * 
     * @param pixelData массив boolean с информацией о непрозрачности
     * @return true если пиксель непрозрачный
     */
    public boolean isPixelOpaque(boolean[] pixelData, int width, int x, int y) {
        if(x < 0 || y < 0 || x >= width) {
            return false;
        }
        
        int index = y * width + x;
        if(index < 0 || index >= pixelData.length) {
            return false;
        }
        
        return pixelData[index];
    }
    
    /* public boolean isPixelOpaqueOld(int[] pixelData, int width, int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= pixelData.length / width) {
            return false;
        }
        
        int index = y * width + x;
        if(index < 0 || index >= pixelData.length) {
            return false;
        }
        
        return (pixelData[index] & 0xFF000000) != 0;
    } */
    
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