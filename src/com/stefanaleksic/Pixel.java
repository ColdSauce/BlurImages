package com.stefanaleksic;

public class Pixel {
	private int x = 0;
	private int y = 0;
	private int red = 0;
	private int green = 0;
	private int blue = 0;

	public Pixel(int x, int y, int red, int green, int blue) {
		this.x = x;
		this.y = y;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
}
