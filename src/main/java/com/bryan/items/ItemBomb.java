package com.bryan.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bryan.items.base.Item;

public class ItemBomb extends Item {
	private int acceleration = -40;

	public ItemBomb(BufferedImage image, int x, int y) {
		setImage(image);
		setX(x - getWidth());
		setY(y - getHeight());
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);

		setY(getY() - acceleration);
		acceleration++;
	}
}
