package HardEngine;

import javax.microedition.lcdui.Image;
import AE.GlobalStatus;

public class interface_loader {
	
	public boolean debug = false;
	private int imageX;
	private int imageY;
	private int imageWidth;
	private int imageHeight;
	
	public interface_loader() {
		
	}
	
	public void drawImage(Image image, int x, int y, int anchor) {
		this.imageX = x;
		this.imageY = y;
		this.imageWidth = image.getWidth();
		this.imageHeight = image.getHeight();
		GlobalStatus.graphics.drawImage(image, x, y, anchor);
		if(this.debug) {
			GlobalStatus.graphics.setColor(240, 0, 255);
			GlobalStatus.graphics.drawRect(imageX - (imageWidth / 2), imageY - (imageHeight / 2), imageWidth, imageHeight);
			GlobalStatus.graphics.drawRect(imageX, imageY, 1, 1);
		}
	}
	
	public void drawRegion(Image image, int startX, int startY, int srcWidth, int srcHeight, int transform, int x, int y, int anchor) {
		this.imageX = x;
		this.imageY = y;
		this.imageWidth = image.getWidth();
		this.imageHeight = image.getHeight();
		GlobalStatus.graphics.drawRegion(image, startX, startY, srcWidth, srcHeight, transform, x, y, anchor);
		if(this.debug) {
			GlobalStatus.graphics.setColor(240, 0, 255);
			GlobalStatus.graphics.drawRect(imageX, imageY, srcWidth, srcHeight);
			GlobalStatus.graphics.drawRect(imageX, imageY, 1, 1);
		}
	}
	
	public final int getImageX() {
		return this.imageX;
	}
	
	public final int getImageY() {
		return this.imageY;
	}
	
	public final int getImageWidth() {
		return this.imageWidth;
	}
	
	public final int getImageHeight() {
		return this.imageHeight;
	}
	
}