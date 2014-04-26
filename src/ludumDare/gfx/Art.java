package ludumDare.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {
	public static Bitmap[][] sprites;
	public static Bitmap background;
	public static Bitmap background1;
	public static Bitmap background2;
	
	public static void init() {
		sprites = loadSpriteSheet("/sprite.png", 8, 8);
		background = loadImage("/bg.png");
		background1 = loadImage("/bg1.png");
		background2 = loadImage("/bg2.png");
	}
	
	public static Bitmap loadImage(String fileName) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(Art.class.getResourceAsStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException();
		}
		Bitmap rValue = new Bitmap(img.getWidth(), img.getHeight());
		
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rValue.pixels, 0, img.getWidth());
		
		return rValue;
	}
	
	public static Bitmap[][] loadSpriteSheet(String fileName, int tw, int th) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(Art.class.getResourceAsStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		if (img == null) 
			return null;
		
		int x0 = img.getWidth() / tw;
		int y0 = img.getHeight() / th;
		
		Bitmap[][] rValue = new Bitmap[x0][y0];
		
		for (int x = 0; x < x0; x++) {
			for (int y = 0; y < y0; y++) {
				rValue[x][y] = new Bitmap(tw, th);
				img.getRGB(x * tw, y * th, tw, th, rValue[x][y].pixels, 0, tw);
			}
		}
		
		return rValue;
	}
}