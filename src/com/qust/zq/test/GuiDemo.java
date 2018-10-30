package com.qust.zq.test;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GuiDemo {
	public static void main(String[] args) {
		Frame f = new Frame("my awt");
		f.setSize(500, 400);
		f.setLocation(300, 200);
		f.setLayout(new FlowLayout());

		Button b = new Button("我是一个按钮");

		f.add(b);

		f.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});

		f.setVisible(true);

		System.out.println("Hello world!");
	}

}