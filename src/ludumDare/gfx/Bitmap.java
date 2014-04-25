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
	
	public void setPixel(int x, int y,int c) {
		if (c != 0xFF00FF)
			pixels[x + y * w] = c;
	}
	
	public void clear() {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = 0;
		}
	}
	
	public void blit(int xp, int yp, Bitmap b, boolean xFlip, boolean yFlip, float alpha) {
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
			int xc = x - xp;
			if (xFlip)
				xc = b.w - xc - 1;
			for (int y = y0; y < y1; y++) {
				int yc = y - yp;
				if (yFlip)
					yc = b.h - yc - 1;
				int nPixel = b.getPixel(xc, yc);
				if (nPixel != 0xFFFF00FF) { 
					int oPixel = getPixel(x , y);
					int rr = (int) (((1 - alpha) * ((oPixel >> 16) & 0xFF)) + (alpha * ((nPixel >> 16) & 0xFF)));
					int gg = (int) (((1 - alpha) * ((oPixel >> 8) & 0xFF)) + (alpha * ((nPixel >> 8) & 0xFF)));
					int bb = (int) (((1 - alpha) * ((oPixel >> 0) & 0xFF)) + (alpha * ((nPixel >> 0) & 0xFF)));
					nPixel = (rr << 16) | (gg << 8) | bb;
					setPixel(x, y, nPixel);
				}
			}
		}
	}
}