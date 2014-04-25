package ludumDare.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {
	public static Bitmap[][] sprites;
	
	public static void init() {
		sprites = loadSpriteSheet("/sprite.png", 8, 8);
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