package HardEngine;

import javax.microedition.lcdui.Image;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import GoF2.Globals;

public class AEButtonManager {

    public final boolean debug = false;
    public Image interface_image;
	public interface_loader[] AEGraphics;
	
	public Image joystickNormal;
    public Image joystickPressed;
    public Image joystickBackground;
    public boolean joystickActive = false;
    public int joystickWidth;
    public int joystickHeight;
    public int joystickX;
    public int joystickY;
    public int joystickTouchXPosition;
    public int joystickTouchYPosition;
    public int joystickBackgroundWidth;
    public int joystickBackgroundHeight;
    public int[] joystickPixels;
    public int[] joystickBackgroundPixels;
    public int joystickXDefaultPosition;
    public int joystickYDefaultPosition;
    public float joystickXFloat = 0.0f;
    public float joystickYFloat = 0.0f;
    public float shipTilt = 0;
    public final float deadZone = 0.001f;
	
	public Image switchableButtonNormal;
	public Image switchableButtonPressed;
	public Image switchableButtonInactive;
	public boolean switchableButtonActive = false;
	public boolean switchableButtonState = false;
	public int switchableButtonWidth;
	public int switchableButtonHeight;
	public int switchableButtonX;
	public int switchableButtonY;
	public int switchableButtonTouchXPosition;
	public int switchableButtonTouchYPosition;
	public int[] switchableButtonPixels;
	
	public Image standartButtonNormal;
	public Image standartButtonPressed;
	private Image standartButtonPixelsSource;
	public boolean standartButtonActive = false;
	public int standartButtonWidth;
	public int standartButtonHeight;
	public int standartButtonX;
	public int standartButtonY;
	public int standartButtonTouchXPosition;
	public int standartButtonTouchYPosition;
	public int[] standartButtonPixels;

	private Image switchableButtonPixelsSource;

    public AEButtonManager() {
        if(joystickNormal == null) {
            
            AEGraphics = new interface_loader[1];
            for(int count = 0; count < AEGraphics.length; ++count) {
                AEGraphics[count] = new interface_loader();
            }
            
            joystickNormal = Globals.joystickNormal;
            joystickPressed = Globals.joystickPressed;
            joystickBackground = Globals.joystickBackground;
            joystickWidth = joystickNormal.getWidth();
            joystickHeight = joystickNormal.getHeight();
            joystickBackgroundWidth = joystickBackground.getWidth();
            joystickBackgroundHeight = joystickBackground.getHeight();
            
            cachePixelData();
        }
    }

    public void cachePixelData() {
        
        if(joystickNormal != null) {
            joystickPixels = new int[joystickNormal.getWidth() * joystickNormal.getHeight()];
            joystickNormal.getRGB(joystickPixels, 0, joystickNormal.getWidth(), 0, 0, joystickNormal.getWidth(), joystickNormal.getHeight());
        }
        
        if(joystickBackground != null) {
            joystickBackgroundPixels = new int[joystickBackgroundWidth * joystickBackgroundHeight];
            joystickBackground.getRGB(joystickBackgroundPixels, 0, joystickBackgroundWidth, 0, 0, joystickBackgroundWidth, joystickBackgroundHeight);
        }
    }
	
	public void drawStandartButton(Image normal, Image pressed, int buttonX, int buttonY) {
		if(normal != null && pressed != null) {
			
			standartButtonNormal = normal;
			standartButtonPressed = pressed;
			standartButtonWidth = normal.getWidth();
			standartButtonHeight = normal.getHeight();
			
			cacheStandartButtonPixels(normal);
			
			standartButtonX = buttonX;
			standartButtonY = buttonY;
			standartButtonTouchXPosition = buttonX - (standartButtonWidth / 2) - 1;
			standartButtonTouchYPosition = buttonY - (standartButtonHeight / 2) - 1;
			
			GlobalStatus.graphics.drawImage(standartButtonActive ? pressed : normal, buttonX, buttonY, 3);
			if(this.debug) {
				GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawRect(standartButtonTouchXPosition, standartButtonTouchYPosition, standartButtonWidth, standartButtonHeight);
			}
		}
	}
	
	public void drawswitchableButton(Image normal, Image pressed, Image inactive, int buttonX, int buttonY, boolean activity) {
		if(normal != null && pressed != null && inactive != null) {
			switchableButtonNormal = normal;
			switchableButtonPressed = pressed;
			switchableButtonInactive = inactive;
			
			switchableButtonWidth = normal.getWidth();
			switchableButtonHeight = normal.getHeight();
			
			cacheSwitchableButtonPixels(normal);
			
			switchableButtonX = buttonX;
			switchableButtonY = buttonY;
			
			switchableButtonState = activity;
			
			switchableButtonTouchXPosition = buttonX - (switchableButtonWidth / 2) - 1;
			switchableButtonTouchYPosition = buttonY - (switchableButtonHeight / 2) - 1;
			
			GlobalStatus.graphics.drawImage(switchableButtonState ? normal : inactive, buttonX, buttonY, 3);
			if(switchableButtonActive) {
				GlobalStatus.graphics.drawImage(pressed, buttonX, buttonY, 3);
			}
			
			if(this.debug) {
				GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawRect(switchableButtonTouchXPosition, switchableButtonTouchYPosition, switchableButtonWidth, switchableButtonHeight);
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
			}
			
			joystickTouchXPosition = buttonX - (joystickWidth / 2) - 1;
			joystickTouchYPosition = buttonY - (joystickHeight / 2) - 1;
			AEGraphics[0].drawImage(joystickBackground, joystickXDefaultPosition, joystickYDefaultPosition, 3);
			GlobalStatus.graphics.drawImage(joystickActive ? joystickPressed : joystickNormal, joystickX, joystickY, 3);
			if(this.debug) {
                GlobalStatus.graphics.setColor(240, 0, 255);
                GlobalStatus.graphics.drawRect(joystickX - (joystickWidth / 2), joystickY - (joystickHeight / 2), joystickWidth, joystickHeight);
				GlobalStatus.graphics.drawRect(joystickX, joystickY, 1, 1);
				Font.sub_14d_CENTER("X: " + getJoystickX() + ", Y: " + getJoystickY(), joystickX + (joystickWidth / 2), joystickY + (joystickHeight / 2), 7);
				Font.sub_14d_CENTER("X: " + getJoystickXFloat() + "F, Y: " + getJoystickYFloat() + "F", joystickX + (joystickWidth / 2), joystickY + (joystickHeight / 2) + 20, 7);
            }
		}
	}
	
	public void standartButtonTouch(int touchX, int touchY) {
		if(standartButtonNormal != null && standartButtonPressed != null) {
			int relativeX = touchX - standartButtonTouchXPosition;
            int relativeY = touchY - standartButtonTouchYPosition;
            if(relativeX >= 0 && relativeX < standartButtonWidth &&
                relativeY >= 0 && relativeY < standartButtonHeight &&
                isPixelOpaque(standartButtonPixels, standartButtonWidth, relativeX, relativeY)) {
                standartButtonActive = true;
            }
		}
	}
	
	public void switchableButtonTouch(int touchX, int touchY) {
		if(switchableButtonNormal != null && switchableButtonPressed != null && switchableButtonInactive != null) {
			int relativeX = touchX - switchableButtonTouchXPosition;
            int relativeY = touchY - switchableButtonTouchYPosition;
            if(relativeX >= 0 && relativeX < switchableButtonWidth &&
                relativeY >= 0 && relativeY < switchableButtonHeight &&
                isPixelOpaque(switchableButtonPixels, switchableButtonWidth, relativeX, relativeY)) {
                switchableButtonActive = true;
            }
		}
	}
	
	public void joystickTouch(int touchX, int touchY) {
		if(joystickNormal != null && joystickPressed != null) {
			int relativeX = touchX - joystickTouchXPosition;
			int relativeY = touchY - joystickTouchYPosition;
			if(relativeX >= 0 && relativeX < joystickWidth &&
				relativeY >= 0 && relativeY < joystickHeight &&
				isPixelOpaque(joystickPixels, joystickWidth, relativeX, relativeY)) {
					joystickActive = true;
				} else {
					joystickActive = false;
					joystickX = joystickXDefaultPosition;
					joystickY = joystickYDefaultPosition;
				}
		}
	}
	
	public void joystickDrag(int touchX, int touchY) {
		if(joystickNormal != null && joystickPressed != null && joystickBackgroundPixels != null) {
			if(joystickActive) {
				// Центр джойстика
				int centerX = joystickXDefaultPosition;
				int centerY = joystickYDefaultPosition;
				
				// Половина ширины и высоты фонового изображения (границы)
				int backgroundHalfWidth = joystickBackgroundWidth / 2;
				int backgroundHalfHeight = joystickBackgroundHeight / 2;
				
				// Вектор от центра к точке касания
				int deltaX = touchX - centerX;
				int deltaY = touchY - centerY;
				
				// Находим максимальное расстояние от центра до края фона по направлению к касанию
				float maxDistance = findMaxDistanceInDirection(centerX, centerY, deltaX, deltaY);
				
				// Если палец за пределами фона - ограничиваем позицию по направлению
				float distanceToTouch = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
				if(distanceToTouch > maxDistance && maxDistance > 0) {
					float ratio = maxDistance / distanceToTouch;
					deltaX = (int)(deltaX * ratio);
					deltaY = (int)(deltaY * ratio);
				}
				
				// Новая позиция джойстика
				joystickX = centerX + deltaX;
				joystickY = centerY + deltaY;
				
				// Вычисляем нормализованные значения
				joystickXFloat = -((float)deltaX / backgroundHalfWidth);
				if(GlobalStatus.invertedControlsOn) {
					joystickYFloat = -((float)deltaY / backgroundHalfHeight);
				} else {
					joystickYFloat = ((float)deltaY / backgroundHalfHeight);
				}
				
				// Применяем мертвую зону
				if(Math.abs(joystickXFloat) < deadZone) joystickXFloat = 0.0f;
				if(Math.abs(joystickYFloat) < deadZone) joystickYFloat = 0.0f;
				
				// Вычисляем наклон корабля
				shipTilt = joystickXFloat * 384.0f;
			}
		}
	}

    public void buttonsTouchReleased(int x, int y) {
		joystickActive = false;
		joystickX = joystickXDefaultPosition;
		joystickY = joystickYDefaultPosition;
		joystickXFloat = 0.0f;
		joystickYFloat = 0.0f;
		shipTilt = 0.0F;
		switchableButtonActive = false;
		standartButtonActive = false;
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
	
	// Метод для проверки прозрачности пикселя
    public boolean isPixelOpaque(int[] pixelData, int width, int x, int y) {
        int index = y * width + x;
        int pixel = pixelData[index];
        int alpha = (pixel >> 24) & 0xff;
        return alpha > 0;
    }
	
	private float findMaxDistanceInDirection(int centerX, int centerY, int deltaX, int deltaY) {
		if(deltaX == 0 && deltaY == 0) return 0;
		float maxDistance = 0;
		float length = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		float stepX = deltaX / length;
		float stepY = deltaY / length;
		
		// ищем границу непрозрачной области
		for(float distance = 0; distance < length; distance += 1f) {
			int x = centerX + (int)(stepX * distance);
			int y = centerY + (int)(stepY * distance);
			
			// Проверяем, находится ли точка в пределах фона
			int bgX = x - (centerX - joystickBackgroundWidth / 2);
			int bgY = y - (centerY - joystickBackgroundHeight / 2);
			
			if(bgX < 0 || bgY < 0 || bgX >= joystickBackgroundWidth || bgY >= joystickBackgroundHeight) {
				break;
			}
			
			// Проверяем прозрачность пикселя
			if(isPixelOpaque(joystickBackgroundPixels, joystickBackgroundWidth, bgX, bgY)) {
				maxDistance = distance;
			} else {
				break;
			}
		}
		return maxDistance;
	}

	private void cacheStandartButtonPixels(Image normal) {
		if (standartButtonPixelsSource != normal || standartButtonPixels == null || standartButtonPixels.length != standartButtonWidth * standartButtonHeight) {
			standartButtonPixels = new int[standartButtonWidth * standartButtonHeight];
			normal.getRGB(standartButtonPixels, 0, standartButtonWidth, 0, 0, standartButtonWidth, standartButtonHeight);
			standartButtonPixelsSource = normal;
		}
	}

	private void cacheSwitchableButtonPixels(Image normal) {
		if (switchableButtonPixelsSource != normal || switchableButtonPixels == null || switchableButtonPixels.length != switchableButtonWidth * switchableButtonHeight) {
			switchableButtonPixels = new int[switchableButtonWidth * switchableButtonHeight];
			normal.getRGB(switchableButtonPixels, 0, switchableButtonWidth, 0, 0, switchableButtonWidth, switchableButtonHeight);
			switchableButtonPixelsSource = normal;
		}
	}
}
