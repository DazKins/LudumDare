package ludumDare.gfx;

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
	
	public int getPixel(int x, int y) {
		return pixels[x + y * w];
	}
	
	public void clear() {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = 0;
		}
	}
	
	public void blit(int xp, int yp, Bitmap b) {
		int x0 = xp;
		int y0 = yp;
		int x1 = xp + b.w;
		int y1 = yp + b.h;
		
		if (x0 < 0)
			x0 = 0;
		if (y0 < 0)
			y0 = 0;
		if (x1 > w)
			x1 = w;
		if (y1 > h)
			y1 = h;
		
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				int nPixel = b.getPixel(x - xp, y - yp);
				pixels[x + y * w] = nPixel;
			}
		}
	}
}