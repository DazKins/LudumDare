package ludumDare.gfx;

public class Font {
	private static String alphabet = " !\"#$%&'()*+,-./0123456789:;<=>?@abcdefghijklmnopqrstuvwxyz";
	
	public static void renderString(Bitmap b, int x, int y, String text) {
		text = text.toLowerCase();
		for (int i = 0; i < text.length(); i++) {
			int indexOf = alphabet.indexOf("" + text.charAt(i));
			b.blit(i * 9 + x, y, Art.sprites[indexOf % 32][30 + indexOf / 32], false, false, 1.0f);
		}
	}
}