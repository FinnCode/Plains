package com.bryan.scene.impl;

import com.bryan.run.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.bryan.items.Bomb;
import com.bryan.items.ItemBomb;
import com.bryan.items.Bullet;
import com.bryan.items.EnemiesPlain;
import com.bryan.items.ItemBullet;
import com.bryan.items.Plain;
import com.bryan.scene.Scene;
import com.bryan.utils.ContentUtil;

public class Scene_2 implements Scene {

	private Plain plain = null;
	private List<Bullet> myBullets = new ArrayList<Bullet>();
	private List<EnemiesPlain> enemiesPlains = new ArrayList<EnemiesPlain>();
	private List<ItemBomb> itemBombs = new ArrayList<ItemBomb>();
	private List<ItemBullet> itemBullets = new ArrayList<ItemBullet>();
	private List<Bomb> bombs = new ArrayList<Bomb>();
	private int time = 0;
	private BufferedImage[] images;
	private boolean god = false;
	private boolean isFire = false;

	public Scene_2() {
		try {
			images = new BufferedImage[] {
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/my.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/small.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/middle.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/big.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/bullet0.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/bullet.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/item0.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/item1.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/show_item.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/bomb.png")),
					ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/bullet1.png")) };
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {
		plain = new Plain(images[0]);
		plain.setLocation(ContentUtil._formWidth / 2 - plain.getWidth(),
				ContentUtil._formHeight - plain.getHeight());
	}

	public void draw(Graphics g) {
		if (time <= 999999999)
			time++;
		else
			time = 0;
		plain.draw(g);
		createBullte(g);// 创建子弹
		createEnemiesPlain(g);// 创建敌人
		createItemBullet(g);// 创建特殊子弹补给
		createItemBomb(g);// 创建炸弹补给和释放炸弹
		drawUserInterFace(g);// 绘制用户界面
		judheShot();// 判断击中
	}

	private void drawUserInterFace(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("楷体", Font.PLAIN, 25));
		g2d.drawString("分数：" + ContentUtil._point, 20, 60);
		g.drawImage(images[8], 20,
				ContentUtil._formHeight - images[8].getHeight() - 80, null);
		g2d.setFont(new Font("楷体", Font.BOLD, 30));
		g2d.drawString("X" + plain.getBomb_count(), images[8].getWidth() + 30,
				ContentUtil._formHeight - images[8].getHeight() - 40);
		g2d.setFont(new Font("楷体", Font.BOLD, 20));
		g2d.drawString("特种子弹:" + plain.getSuperBullet(),
				ContentUtil._formWidth - 160, ContentUtil._formHeight
						- images[8].getHeight() - 40);
	}

	private void createItemBomb(Graphics g) {
		// 创建炸弹补给物品
		for (int i = 0; i < itemBombs.size(); i++) {
			ItemBomb ib = itemBombs.get(i);
			ib.draw(g);
			if (ib.getHeight() > ContentUtil._formHeight + 70
					|| ib.getHeight() < -100) {
				itemBombs.remove(i);
			}
		}
		if ((int) (Math.random() * 1500) == 0) {
			itemBombs.add(new ItemBomb(images[7],
					(int) (Math.random() * ContentUtil._formWidth), -20));
		}

		// 检测用户是否释放炸弹
		for (int i = 0; i < bombs.size(); i++) {
			Bomb bomb = bombs.get(i);
			bomb.draw(g);
			if (bomb.getY() < -bomb.getWidth())
				bombs.remove(i);
		}
	}

	private void createItemBullet(Graphics g) {
		for (int i = 0; i < itemBullets.size(); i++) {
			ItemBullet ib = itemBullets.get(i);
			ib.draw(g);
			if (ib.getHeight() > ContentUtil._formHeight + 70
					|| ib.getHeight() < -100) {
				itemBullets.remove(i);
			}
		}
		if ((int) (Math.random() * 3000) == 0) {
			itemBullets.add(new ItemBullet(images[6],
					(int) (Math.random() * ContentUtil._formWidth), -20));
		}
	}

	private void judheShot() {
		// 敌人被子弹击中
		w: for (int i = 0; i < myBullets.size(); i++) {
			for (int j = 0; j < enemiesPlains.size(); j++) {
				Bullet b = myBullets.get(i);
				EnemiesPlain ep = enemiesPlains.get(j);
				if (ep.getLife() > 0) {
					if (b.getX() >= ep.getX()
							&& b.getX() + b.getWidth() <= ep.getX()
									+ ep.getWidth()
							&& b.getY() + b.getHeight() < ep.getY()
									+ ep.getHeight()
							&& b.getY() > ep.getY() - 50) {
						ContentUtil._point += ep.getLife() + 100;
						if (ep.getLife() >= b.getPower()) {
							ep.shoted(b.getPower());
							myBullets.remove(i);
						} else {
							b.setPower(b.getPower() - ep.getLife());
							ep.shoted(ep.getLife());
						}
						break w;
					}
				}
			}
		}
		// 清除敌人尸体

		for (int j = 0; j < enemiesPlains.size(); j++) {
			EnemiesPlain ep = enemiesPlains.get(j);
			if (ep.getLife() <= -1)
				enemiesPlains.remove(j);
		}
		// 敌人被炸弹击中
		for (Bomb b : bombs) {
			for (int j = 0; j < enemiesPlains.size(); j++) {
				EnemiesPlain ep = enemiesPlains.get(j);
				if (b.comllision(ep)) {
					ContentUtil._point += ep.getLife() + 100;
					ep.shoted(10);
				}
			}
		}
		// 主角被击中
		for (int i = 0; i < enemiesPlains.size(); i++) {
			EnemiesPlain ep = enemiesPlains.get(i);
			if (plain.getLife() > 0 && ep.getLife() > 0) {
				if (plain.comllision(ep)) {
					plain.comllision(ep);
					plain.shoted();
				}
			}
		}

		// 主角吃到超级子弹
		for (int i = 0; i < itemBullets.size(); i++) {
			ItemBullet ib = itemBullets.get(i);
			if (plain.comllision(ib)) {
				plain.setSuperBullet(plain.getSuperBullet() + 300);
				if (plain.getBulletType() < 7)
					plain.setBulletType(plain.getBulletType() + 1);
				itemBullets.remove(i);
			}
		}

		// 主角吃到炸弹
		for (int i = 0; i < itemBombs.size(); i++) {
			ItemBomb ib = itemBombs.get(i);
			if (plain.comllision(ib)) {
				plain.setBomb_count(plain.getBomb_count() + 1);
				itemBombs.remove(i);
			}
		}

		// 主角死亡
		if (plain.getLife() == -1)
			ContentUtil._scenes_now = -1;
	}

	private void createEnemiesPlain(Graphics g) {
		for (int i = 0; i < enemiesPlains.size(); i++) {
			EnemiesPlain ep = enemiesPlains.get(i);
			ep.draw(g);
			if (ep.getY() >= ContentUtil._formHeight + 300)
				enemiesPlains.remove(i);
		}

		if ((int) (Math.random() * 100) <= ContentUtil._point / 10000 + 3
				&& enemiesPlains.size() < 70 || god) {
			int type = getType();
			enemiesPlains.add(new EnemiesPlain(
					(int) (Math.random() * ContentUtil._formWidth) + 1, -50,
					type, images[type]));
		}
	}

	private int getType() {
		int t = (int) (Math.random() * 100) + 1;
		if (t <= 3)
			return 3;
		else if (t >= 3 && t < 20)
			return 2;
		else
			return 1;
	}

	// 创建各种子弹~~~~代码稍微有点偷懒
	private void createBullte(Graphics g) {
		for (int i = 0; i < myBullets.size(); i++) {
			Bullet b = myBullets.get(i);
			b.draw(g);
			if (b.getY() <= -10)
				myBullets.remove(i);

		}

		if (time % 7 == 0 && isFire) {
			if (plain.getBulletType() == 0)
				myBullets.add(new Bullet(images[4], plain.getCenterX(), plain
						.getCenterY() - plain.getHeight() / 2));
			else {
				plain.setSuperBullet(plain.getSuperBullet() - 1);
				if (plain.getSuperBullet() == 0) {
					plain.setBulletType(plain.getBulletType() - 1);
					if (plain.getBulletType() > 0) {
						plain.setSuperBullet(100);
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 40, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 20, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX(), plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 20, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 40, plain.getCenterY()
										- plain.getHeight() / 2));
					}
				}
				switch (plain.getBulletType()) {
				case 0:
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() - 40, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() - 30, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() - 20, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() - 10, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10], plain.getCenterX(),
							plain.getCenterY() - plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() + 10, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() + 20, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() + 30, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[10],
							plain.getCenterX() + 40, plain.getCenterY()
									- plain.getHeight() / 2));
					break;
				case 1:
					myBullets.add(new Bullet(images[4],
							plain.getCenterX() - 20, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[4],
							plain.getCenterX() + 20, plain.getCenterY()
									- plain.getHeight() / 2));
					break;
				case 2:
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() - 20, plain.getCenterY()
									- plain.getHeight() / 2, 0, 2));
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() + 20, plain.getCenterY()
									- plain.getHeight() / 2, 0, 2));
					break;
				case 3:
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() - 25, plain.getCenterY()
									- plain.getHeight() / 2, +1, 2));
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() + 25, plain.getCenterY()
									- plain.getHeight() / 2, -1, 2));
					myBullets.add(new Bullet(images[4], plain.getCenterX(),
							plain.getCenterY() - plain.getHeight() / 2));
					break;

				case 4:
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() - 30, plain.getCenterY()
									- plain.getHeight() / 2, +2, 2));
					myBullets.add(new Bullet(images[5],
							plain.getCenterX() + 30, plain.getCenterY()
									- plain.getHeight() / 2, -2, 2));
					myBullets.add(new Bullet(images[4],
							plain.getCenterX() + 10, plain.getCenterY()
									- plain.getHeight() / 2));
					myBullets.add(new Bullet(images[4],
							plain.getCenterX() - 10, plain.getCenterY()
									- plain.getHeight() / 2));
					break;

				case 5:
					switch (time % 6) {
					case 0:
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 2));
						break;
					case 1:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 2));
						break;
					case 2:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 2));
						break;
					case 3:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 5));
						break;
					case 4:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 2));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 2));
						break;
					case 5:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +2, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -2, 2));
						break;
					}

					break;
				default:
					switch (time % 2) {
					case 0:
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +3, 5));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -3, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX(), plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2, +1));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2, -1));
						break;
					case 1:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +3, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -3, 5));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX(), plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2, +1));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2, -1));
						break;
					case 2:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +3, 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -3, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX(), plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2, 1, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2, -1));
						break;
					case 3:
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() - 30, plain.getCenterY()
										- plain.getHeight() / 2, +3, 2));
						myBullets.add(new Bullet(images[5],
								plain.getCenterX() + 30, plain.getCenterY()
										- plain.getHeight() / 2, -3, 2));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX(), plain.getCenterY()
										- plain.getHeight() / 2, 0, 5));
						myBullets.add(new Bullet(images[4],
								plain.getCenterX() + 10, plain.getCenterY()
										- plain.getHeight() / 2, 1));
						myBullets.add(new Bullet(images[10],
								plain.getCenterX() - 10, plain.getCenterY()
										- plain.getHeight() / 2, -1, 5));
						break;
					}

					break;
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			time = 6;
			isFire = true;
		}
		// 释放炸弹
		if (e.getButton() == MouseEvent.BUTTON3)
			if (plain.getBomb_count() > 0) {
				plain.setBomb_count(plain.getBomb_count() - 1);
				bombs.add(new Bomb(images[9], ContentUtil._formWidth / 2,
						ContentUtil._formHeight + 250));
			}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			isFire = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (plain.getLife() > 0) {
			if (plain.getX() + plain.getWidth() / 2 > 0
					&& plain.getX() + plain.getWidth() / 2 < ContentUtil._formWidth
					&& plain.getY() + plain.getHeight() / 2 > 0
					&& plain.getY() + plain.getHeight() / 2 < ContentUtil._formHeight) {
				int x = e.getX();
				int y = e.getY();
				plain.setLocation(x - plain.getWidth() / 2,
						y - plain.getHeight() / 2);
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (plain.getLife() > 0) {
			int x = e.getX();
			int y = e.getY();
			plain.setLocation(x - plain.getWidth() / 2, y - plain.getHeight()
					/ 2);
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'g')
			god = !god;
	}

}
