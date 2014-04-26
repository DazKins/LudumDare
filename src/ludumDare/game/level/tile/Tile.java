package ludumDare.game.level.tile;

import ludumDare.game.level.Level;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Tile {
	public static Tile tiles[] = new Tile[256];

	public static Tile rock = new RockTile(0);

	private int id;
	private int tx;
	private int ty;
	private int bm;
	private boolean col;

	public Tile(int id, int tx, int ty, boolean c) {
		this.tx = tx;
		this.ty = ty;
		this.col = c;
		this.id = id;
		tiles[id] = this;
	}

	public void render(Bitmap b, Level l, int x, int y, float xOff, float yOff) {
		Tile tu = l.getTile(x, y - 1);
		Tile tr = l.getTile(x + 1, y);
		Tile td = l.getTile(x, y + 1);
		Tile tl = l.getTile(x - 1, y);
		
		bm = 0;
		
		if (tu != null) {
			if (this.getID() == tu.getID()) {
				bm += 1;
			}
		}
		if (tr != null) {
			if (this.getID() == tr.getID()) {
				bm += 2;
			}
		}
		if (td != null) {
			if (this.getID() == td.getID()) {
				bm += 4;
			}
		}
		if (tl != null) {
			if (this.getID() == tl.getID()) {
				bm += 8;
			}
		}
		this.tx = bm;

		b.blit((int) (x * 8 - xOff), (int) (y * 8 - yOff), Art.sprites[tx][ty], false, false, 1.0f, 1.0f);
	}

	public AABB getAABB(Level l, int x, int y) {
		return null;
	}

	public int getID() {
		return id;
	}
}