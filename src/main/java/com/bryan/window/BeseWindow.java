package com.bryan.window;

import javax.swing.JFrame;

public class BeseWindow extends JFrame {

	/**
	 * Create the application.
	 */
	public BeseWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setSize(480, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
