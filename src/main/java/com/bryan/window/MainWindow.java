package com.bryan.window;

import com.bryan.run.Main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.bryan.scene.Scene;
import com.bryan.utils.ContentUtil;
import com.bryan.utils.LoadScene;

public class MainWindow extends BeseWindow {

	private Image bgImage = null;
	private Image bufferImage = null;
	private int time = 0;

	public MainWindow() {
		try {
			bgImage = ImageIO.read(Main.class.getClassLoader().getResource("assets/gfx/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("背景加载失败！");
		}
		new Thread() {
			public void run() {
				while (true) {
					time += 3;
					if (time >= 800)
						time = 0;
					repaint();
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.keyTyped(e);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.keyReleased(e);
				}
			}
			
			public void keyPressed(KeyEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.keyPressed(e);
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseMoved(e);
				}
			}

			public void mouseDragged(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseDragged(e);
				}
			}
		});
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseReleased(e);
				}
			}

			public void mousePressed(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mousePressed(e);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseExited(e);
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseEntered(e);
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (ContentUtil._scenes_now >= 0
						&& ContentUtil._scenes_now < ContentUtil._scenes_count) {
					ContentUtil._scenes.get(ContentUtil._scenes_now)
							.mouseClicked(e);
				}
			}
		});
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ContentUtil._point = 0;
		ContentUtil._scenes_now = 0;
		// 加载关卡--扩展点
		ContentUtil._scenes = LoadScene.load();
		ContentUtil._scenes_count = ContentUtil._scenes.size();
	}

	@Override
	public void paint(Graphics g) {
		if (bufferImage == null)
			bufferImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics g4m = bufferImage.getGraphics();
		g4m.fillRect(0, 0, this.getWidth(), this.getHeight());
		g4m.drawImage(bgImage, 0, -800 + time, null);
		if (ContentUtil._scenes_now == -1)
			ContentUtil._scenes_now = ContentUtil._scenes.size() - 1;
		else if (ContentUtil._scenes_now < ContentUtil._scenes_count) {
			ContentUtil._scenes.get(ContentUtil._scenes_now).draw(g4m);
		}

		g.drawImage(bufferImage, 0, 0, null);
	}

}
