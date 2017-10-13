package com.bryan.run;

import java.awt.EventQueue;

import com.bryan.window.BeseWindow;
import com.bryan.window.MainWindow;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeseWindow window = new MainWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
