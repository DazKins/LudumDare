package gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Bitmap {
	public int pixels[];
	public int w, h;
	
	public Bitmap(int w, int h) {
		pixels = new int[w * h];
		this.w = w;
		this.h = h;
		for (int i = 0; i < w * h; i++) {
			pixels[i] = new Random().nextInt(0xFFFFFF);
		}
	}
	
	public Bitmap(BufferedImage img) {
		w = img.getWidth();
		h = img.getHeight();
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	public void randomize() {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = new Random().nextInt(0xFFFFFF);
		}
	}
}