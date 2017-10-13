package com.bryan.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bryan.items.base.Item;

public class Bomb extends Item {
	public Bomb(BufferedImage image, int x, int y) {
		setImage(image);
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);
		setY(getY() - 15);
	}

	@Override
	public boolean comllision(Item item) {
		int x1, x2, y1, y2, w1, w2, h1, h2;
		int subX, subY;
		x1 = this.getX();
		x2 = item.getX();
		y1 = this.getY() + this.getHeight() / 2;
		y2 = item.getY();
		w1 = this.getWidth();
		w2 = item.getWidth();
		h1 = 1;
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
