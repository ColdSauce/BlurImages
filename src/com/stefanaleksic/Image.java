package com.stefanaleksic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public Image(String s) {
		File f = new File(s);
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("An IOEXception occured! Oh no!");
		}
		System.out.println(img.getWidth() + "        " + img.getHeight());
		Compressor com = new Compressor(img.getWidth(), img.getHeight(), img);
	}

	public static void saveImage(BufferedImage img) {
		File f = new File("C:/users/stefan987/desktop/fileThingg.png");
		try {
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
