package com.stefanaleksic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Compressor {
	private int height = 3;
	private int width = 2;
	private BufferedImage img = null;
	Pixel[][] pixels = new Pixel[height][width];
	Pixel[][] compressedPixels = new Pixel[height][width];

	public Compressor(int width, int height, BufferedImage img) {
		System.out.println(height);
		System.out.println(width);
		this.height = height;
		this.width = width;
		this.img = img;

		getCompressedRGB(addRGB());
	}

	public int[][] addRGB() {
		pixels = new Pixel[height][width];
		int[][] rgbValues = new int[height][width];
		for (int col = 0; col < height; col++) {
			for (int row = 0; row < width; row++) {
				Color rgb = new Color(img.getRGB(row, col));
				try {
					pixels[col][row] = new Pixel(row, col, rgb.getRed(),
							rgb.getGreen(), rgb.getBlue());
				} catch (Exception e) {
					System.out.println("col: " + col + "\nrow: " + row);
					System.exit(-1);
				}
			}
		}
		return rgbValues;
	}

	private int getAvg(ArrayList<Integer> list) {
		int avg = 0;
		for (int i = 0; i < list.size(); i++) {
			avg += list.get(i);
		}
		return avg / list.size();
	}

	private int getAvg(int row, int col) {
		ArrayList<Integer> red = new ArrayList<Integer>();
		ArrayList<Integer> green = new ArrayList<Integer>();
		ArrayList<Integer> blue = new ArrayList<Integer>();
		boolean testLeft = !(row == 0);
		boolean testRight = !(row == width - 1);
		boolean testTopRight = !((row == width - 1) || (col == 0));
		boolean testTopLeft = !((row == 0) || (col == 0));
		boolean testBottomRight = !((row == width - 1) || (col == height - 1));
		boolean testBottomLeft = !((row == 0) || (col == height - 1));
		boolean testBottom = (col != height - 1);
		boolean testTop = !(col == 0);

		// if any of the above are true, it's safe to test them
		if (testLeft) {
			red.add(this.getLeftPixel(row, col).getRed());
			green.add(this.getLeftPixel(row, col).getGreen());
			blue.add(this.getLeftPixel(row, col).getBlue());
		}
		if (testRight) {
			red.add(this.getRightPixel(row, col).getRed());
			green.add(this.getRightPixel(row, col).getGreen());
			blue.add(this.getRightPixel(row, col).getBlue());
		}
		if (testBottom) {
			red.add(this.getBottomPixel(row, col).getRed());
			green.add(this.getBottomPixel(row, col).getGreen());
			blue.add(this.getBottomPixel(row, col).getBlue());
		}
		if (testTop) {
			red.add(this.getTopPixel(row, col).getRed());
			green.add(this.getTopPixel(row, col).getGreen());
			blue.add(this.getTopPixel(row, col).getBlue());
		}
		if (testBottomRight) {
			red.add(this.getBottomRightPixel(row, col).getRed());
			green.add(this.getBottomRightPixel(row, col).getGreen());
			blue.add(this.getBottomRightPixel(row, col).getBlue());
		}
		if (testBottomLeft) {
			red.add(this.getBottomLeftPixel(row, col).getRed());
			green.add(this.getBottomLeftPixel(row, col).getGreen());
			blue.add(this.getBottomLeftPixel(row, col).getBlue());
		}
		if (testTopLeft) {
			red.add(this.getTopLeftPixel(row, col).getRed());
			green.add(this.getTopLeftPixel(row, col).getGreen());
			blue.add(this.getTopLeftPixel(row, col).getBlue());
		}
		if (testTopRight) {
			red.add(this.getTopRightPixel(row, col).getRed());
			green.add(this.getTopRightPixel(row, col).getGreen());
			blue.add(this.getTopRightPixel(row, col).getBlue());
		}
		int redAvg = getAvg(red);
		int greenAvg = getAvg(green);
		int blueAvg = getAvg(blue);
		Color c = new Color(redAvg, greenAvg, blueAvg);
		return c.getRGB();

	}

	// left
	private Color getLeftPixel(int row, int col) {
		return new Color(pixels[col][row - 1].getRed(),
				pixels[col][row - 1].getGreen(), pixels[col][row - 1].getBlue());

	}

	// bottom
	private Color getBottomPixel(int row, int col) {
		try {
			return new Color(pixels[col + 1][row].getRed(),
					pixels[col + 1][row].getGreen(),
					pixels[col + 1][row].getBlue());
		} catch (Exception e) {
			System.out.println("Col: " + (col + 1) + "\nRow: " + row);
			System.exit(-1);
		}
		return null;

	}

	// top
	private Color getTopPixel(int row, int col) {
		return new Color(pixels[col - 1][row].getRed(),
				pixels[col - 1][row].getGreen(), pixels[col - 1][row].getBlue());

	}

	private Color getBottomLeftPixel(int row, int col) {
		return new Color(pixels[col + 1][row - 1].getRed(),
				pixels[col + 1][row - 1].getGreen(),
				pixels[col + 1][row - 1].getBlue());

	}

	private Color getTopLeftPixel(int row, int col) {
		return new Color(pixels[col - 1][row - 1].getRed(),
				pixels[col - 1][row - 1].getGreen(),
				pixels[col - 1][row - 1].getBlue());

	}

	private Color getBottomRightPixel(int row, int col) {
		return new Color(pixels[col + 1][row + 1].getRed(),
				pixels[col + 1][row + 1].getGreen(),
				pixels[col + 1][row + 1].getBlue());
	}

	private Color getTopRightPixel(int row, int col) {
		return new Color(pixels[col - 1][row + 1].getRed(),
				pixels[col - 1][row + 1].getGreen(),
				pixels[col - 1][row + 1].getBlue());
	}

	private Color getRightPixel(int row, int col) {

		return new Color(pixels[col][row + 1].getRed(),
				pixels[col][row + 1].getGreen(), pixels[col][row + 1].getBlue());
	}

	public void getCompressedRGB(int[][] rgbVal) {
		// look at pixel to the right
		// look at pixel to the left
		// look at pixel down
		// look at pixel up
		// look at pixel up-right
		// look at pixel up-left
		// look at pixel down-right
		// look at pixel down- left
		// add the avg rgb value to the array
		// go +2 to the right until the end
		// at the end, go back x = 0 and down +2
		// keep doing the same thing until img.getheight and img.getwidth = i
		// and = j

		int finalRow = width;
		int finalCol = height;
		int[][] fixedRGB = new int[height][width];
		for (int col = 0; col < finalCol; col++) {
			for (int row = 0; row < finalRow; row++) {
				if (getAvg(row, col) != 0)
					fixedRGB[col][row] = getAvg(row, col);
				else {

					finalRow = row;
					finalCol = col;
				}
			}
		}
		BufferedImage finalImage = drawCompressedImage(finalCol, finalRow,
				fixedRGB);
		Image.saveImage(finalImage);
	}

	private BufferedImage drawCompressedImage(int height, int width,
			int[][] fixedRGB) {

		BufferedImage compImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				compImg.setRGB(row, col, fixedRGB[col][row]);
			}
		}
		return compImg;

	}

}
