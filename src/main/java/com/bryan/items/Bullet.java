package com.bryan.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import com.bryan.items.base.Item;

public class Bullet extends Item {
	private int power = 1;
	private int xa = 0;

	public Bullet(BufferedImage image, int x, int y) {
		setImage(image);
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
	}
	
	public Bullet(BufferedImage image, int x, int y,int xa) {
		setImage(image);
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
		this.xa = xa;
	}
	
	public Bullet(BufferedImage image, int x, int y,int xa,int power) {
		setImage(image);
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
		this.xa = xa;
		this.power = power;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), null);
		setY(getY() - 25);
		setX(getX() - xa);
	}

	public int getPower() {
		return power;
	}	
	
	public void setPower(int power) {
		this.power = power;
	}
}
