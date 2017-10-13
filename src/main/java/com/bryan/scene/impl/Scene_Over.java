package com.bryan.scene.impl;

import com.bryan.run.Main;
import java.awt.Button;
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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.bryan.button.ImageButton;
import com.bryan.scene.Scene;
import com.bryan.utils.ContentUtil;
import com.bryan.utils.LoadScene;

public class Scene_Over implements Scene {

	private BufferedImage[] images = null;
	private List<ImageButton> buttons = new ArrayList<ImageButton>();

	public Scene_Over() {
		try {
			images = new BufferedImage[] { ImageIO.read(Main.class.getClassLoader().getResource(
					"assets/gfx/restart.png")) };
		} catch (IOException e) {
			System.out.println("文件加载失败！");
			e.printStackTrace();
		}
		buttons.add(new ImageButton(images[0], ContentUtil._formWidth / 2,
				ContentUtil._formHeight / 2 + 50));
	}

	public void draw(Graphics g) {
		drawUserInterFace(g);// 绘制用户界面
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		for (ImageButton button : buttons)
			g.drawImage(button.getButton(), button.getX(), button.getY(), null);
	}

	private void drawUserInterFace(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("楷体", Font.PLAIN, 30));
		g2d.drawString("您的分数:" + ContentUtil._point, 50, ContentUtil._formHeight / 2);
	}

	public void mouseClicked(MouseEvent e) {
		ContentUtil._scenes_now = 0;
		ContentUtil._point = 0;
		ContentUtil._scenes = LoadScene.load();

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
