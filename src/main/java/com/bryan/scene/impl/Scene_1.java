package com.bryan.scene.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bryan.button.ImageButton;
import com.bryan.scene.Scene;
import com.bryan.utils.ContentUtil;

public class Scene_1 implements Scene {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bryan.scene.Sence#draw(java.awt.Graphics)
	 */
	ImageButton startTitle = null;
	ImageButton startButton = null;

	public Scene_1() {
		initialize();
	}

	private void initialize() {
		startTitle = new ImageButton("assets/gfx/title.png");
		startTitle.setLocation(240 - startTitle.getWidth() / 2,
				400 - startTitle.getHeight() / 2);
		startButton = new ImageButton("assets/gfx/start.png");
		startButton.setLocation(240 - startButton.getWidth() / 2, 400
				- startButton.getHeight() / 2 + startTitle.getHeight());
	}

	public void draw(Graphics g) {
		g.drawImage(startTitle.getButton(), startTitle.getX(), startTitle
				.getY(), null);
		g.drawImage(startButton.getButton(), startButton.getX(), startButton
				.getY(), null);

	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > startButton.getX()
				&& x < startButton.getX() + startButton.getWidth()
				&& y > startButton.getY()
				&& y < startButton.getY() + startButton.getHeight()) {
			System.out.println("按下了按钮");
			ContentUtil._scenes_now ++;
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
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
