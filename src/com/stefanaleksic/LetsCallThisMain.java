package com.stefanaleksic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class LetsCallThisMain {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		Container pane;
		JProgressBar bar;
		JFrame frame = new JFrame("Blur");
		pane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL f = null;

		try {
			f = new URL(
					"http://www.deconvolve.net/bialith/Images/ClockBlur/ClockBlur.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			frame.setIconImage(ImageIO.read(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("lol");
		Label emptyLabel = new Label();
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(640, 400);
		frame.setVisible(true);
		pane.setLayout(null);
		int blurAmount = 0;
		try {
			blurAmount = Integer
					.parseInt(JOptionPane
							.showInputDialog(
									pane,
									"By what factor would you like to blur it? From 1 to 300 \nJust remember that it will be slower after 10!"));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(pane,
					"Please enter a correct amount!");
		}
		bar = new JProgressBar(0, blurAmount);
		pane.add(bar);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setBounds(100, 35, 100, 25);
		bar.setVisible(true);

		boolean shouldGoAgain = true;
		while (shouldGoAgain) {
			try {
				String path = JOptionPane.showInputDialog(pane,
						"Enter path here!!");

				for (int i = 0; i < blurAmount; i++) {
					Image img = new Image(path);
				}
				shouldGoAgain = false;

			} catch (Exception e) {
				shouldGoAgain = true;
				JOptionPane.showMessageDialog(pane,
						"Please enter a valid number!");
			}
		}
	}

}
