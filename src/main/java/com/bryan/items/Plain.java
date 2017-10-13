package com.bryan.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bryan.items.base.Item;

public class Plain extends Item {
	private int[] lifeFrames;
	private int[] shotFrames;
	private int[] deadFrames;
	private int[][] frames;
	private int status = 0;
	private int time = 0;
	private int life = 1;
	private int bulletType = 0;
	private int superBullet = 0;
	private int bomb_count = 2;

	public Plain(BufferedImage image) {
		setImage(image);
		lifeFrames = new int[] { 0, 1 };
		shotFrames = new int[] { 0 };
		deadFrames = new int[] { 2, 3, 4 };
		frames = new int[][] { lifeFrames, shotFrames, deadFrames };
		setTotleFrame(5);
	}

	public BufferedImage getPlainImage(int frame) {
		return getImage().getSubimage(0, frame * (getHeight()), getWidth(),
				getHeight());
	}

	public int shoted() {
		time = 0;// 重置刷新次数（保证动画从第一帧开始播放）
		if (this.life > 1)
			status = 1;
		else if (this.life == 1) {
			status = 2;
		}
		if (this.life > 0)
			this.life -= 1;
		return this.life;
	}

	private int getTime() {
		if (time <= 99990)
			return ++time / 10;
		else
			return time = 0;
	}

	private int getFrame() {
		int time = getTime();
		int frame = frames[status][time % frames[status].length];
		if (frame == frames[status].length && status == 1) {
			status = 0;
			this.time = 0;
		}
		if (this.life == 0 && frame == getTotleFrame() - 1 && status == 2) {
			this.life = -1;
		}
		return frame;
	}

	
	
	public int getBomb_count() {
		return bomb_count;
	}

	public void setBomb_count(int bomb_count) {
		this.bomb_count = bomb_count;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getSuperBullet() {
		return superBullet;
	}

	public void setSuperBullet(int superBullet) {
		this.superBullet = superBullet;
	}

	public int getBulletType() {
		return bulletType;
	}

	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	public void draw(Graphics g) {
		g.drawImage(this.getPlainImage(getFrame()), getX(), getY(), null);
	}

	public boolean comllision(Item item) {
		int x1, x2, y1, y2, w1, w2, h1, h2;
		int subX, subY;
		x1 = this.getX() + 20;
		x2 = item.getX();
		y1 = this.getY() + 60;
		y2 = item.getY();
		w1 = this.getWidth() - 40;
		w2 = item.getWidth();
		h1 = this.getHeight() - 110;
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
