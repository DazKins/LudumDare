package ludumDare.game.level.tile;

import ludumDare.game.level.Level;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;

public class Tile {
	public static Tile tiles[] = new Tile[256];
	
	public static Tile rock = new RockTile(0);

	private int tx;
	private int ty;
	private boolean col;
	
	public Tile(int id, int tx, int ty, boolean c) {
		this.tx = tx;
		this.ty = ty;
		this.col = c;
		tiles[id] = this;
	}
	
	public void render(Bitmap b, Level l, int x, int y) {
		b.blit(x * 8, y * 8, Art.sprites[tx][ty], false, false, 1.0f);
	}
	
	
}