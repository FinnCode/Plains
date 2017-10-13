package com.bryan.button;

import com.bryan.run.Main;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageButton {
	private BufferedImage buttonImage = null;
	private int x, y;

	public ImageButton(String src) {
		try {
			buttonImage = ImageIO.read(Main.class.getClassLoader().getResource(src));
		} catch (IOException e) {
			System.out.println("读取文件失败");
			e.printStackTrace();
		}
	}

	public ImageButton(BufferedImage image, int x, int y) {
		this.buttonImage = image;
		this.x = x - this.buttonImage.getWidth() / 2;
		this.y = y - this.buttonImage.getHeight() / 2;
	}

	public BufferedImage getButton() {
		return buttonImage;
	}

	public void setButton(BufferedImage button) {
		this.buttonImage = button;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getHeight() {
		return buttonImage.getHeight();
	}

	public int getWidth() {
		return buttonImage.getWidth();
	}
}
