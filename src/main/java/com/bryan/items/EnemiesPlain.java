package com.bryan.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bryan.items.base.Item;

public class EnemiesPlain extends Item {
	private int life = 1;
	private int time = 0;
	private int speed = 0;
	private int status = 0;
	private int[] lifeFrames;
	private int[] shotFrames;
	private int[] deadFrames;
	private int[][] frames;

	public EnemiesPlain(int x, int y, int type, BufferedImage image) {
		switch (type) {
		case 1:
			life = 1;
			lifeFrames = new int[] { 0 };
			shotFrames = new int[] { 1 };
			deadFrames = new int[] { 1, 2, 3, 4, 4 };
			setTotleFrame(5);
			break;
		case 2:
			life = 15;
			lifeFrames = new int[] { 0 };
			shotFrames = new int[] { 1 };
			deadFrames = new int[] { 1, 2, 3, 4, 5, 5 };
			setTotleFrame(6);
			break;
		case 3:
			life = 40;
			lifeFrames = new int[] { 0, 2 };
			shotFrames = new int[] { 1 };
			deadFrames = new int[] { 3, 4, 5, 6, 7, 8, 8 };
			setTotleFrame(9);
			break;

		default:
			life = 1;
			lifeFrames = new int[] { 0 };
			shotFrames = new int[] { 1 };
			deadFrames = new int[] { 1, 2, 3, 4, 4 };
			setTotleFrame(5);
			break;
		}
		frames = new int[][] { lifeFrames, shotFrames, deadFrames };
		setImage(image);
		setX(x - image.getWidth() / 2);
		setY(y - image.getHeight() / 2);
		speed = (int) (Math.random() * 5) + 1;
	}

	public BufferedImage getImage(int frame) {
		BufferedImage image = super.getImage();
		return image.getSubimage(0, frame * this.getHeight(), image.getWidth(),
				this.getHeight());
	}

	@Override
	public void draw(Graphics g) {
		int frame = getFrame();
		g.drawImage(getImage(frame), getX(), getY(), null);
		setY(getY() + speed);

	}

	private int getFrame() {
		int time = getTime();
		int frame = frames[status][time % frames[status].length];
		if (time % frames[status].length == frames[status].length - 1
				&& status == 1) {
			status = 0;
			this.time = 0;
		}
		if (this.life == 0
				&& time % frames[status].length == frames[status].length - 1
				&& status == 2) {
			this.life = -1;
		}
		return frame;
	}

	public int shoted(int power) {
		time = 0;// 重置刷新次数（保证动画从第一帧开始播放）
		if (this.life > power) {
			this.life -= power;
			status = 1;
		} else if (this.life <= power) {
			this.life = 0;
			status = 2;
		}
		return this.life;
	}

	private int getTime() {
		if (time <= 99990)
			return ++time / 10;
		else
			return time = 0;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
