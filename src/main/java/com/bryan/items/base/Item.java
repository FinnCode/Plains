package com.bryan.items.base;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Item {
	private BufferedImage Image = null;
	private int x = 0, y = 0;
	private int totleFrame = 1;
	
	public int getTotleFrame() {
		return totleFrame;
	}

	public void setTotleFrame(int totleFrame) {
		this.totleFrame = totleFrame;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public BufferedImage getImage() {
		return Image;
	}

	public void setImage(BufferedImage plainImage) {
		this.Image = plainImage;
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

	public int getWidth() {
		return Image.getWidth();
	}

	public int getHeight() {
		return Image.getHeight() / totleFrame;
	}

	public void draw(Graphics g) {

	}

	public int getCenterX() {
		return x + this.getWidth() / 2;
	}

	public int getCenterY() {
		return y + this.getHeight() / 2;
	}

	public boolean comllision(Item item) {
		int x1, x2, y1, y2, w1, w2, h1, h2;
		int subX, subY;
		x1 = this.getX();
		x2 = item.getX();
		y1 = this.getY();
		y2 = item.getY();
		w1 = this.getWidth();
		w2 = item.getWidth();
		h1 = this.getHeight();
		h2 = item.getHeight();

		
		if (x1 > x2) {
			subX = x1 - x2;
			if (subX < w2) {
				if (y1 > y2) {
					subY = y1 - y2;
					if (subY < h2)
						return true;
				} else if (y1 < y2) {
					subY = y2 - y1;
					if (subY < h1)
						return true;
				}
			}
		} else {
			subX = x2 - x1;
			if (subX < w1) {
				if (y1 > y2) {
					subY = y1 - y2;
					if (subY < h2)
						return true;
				} else if (y1 < y2) {
					subY = y2 - y1;
					if (subY < h1)
						return true;
				}
			}
		}

		return false;
	}
}
