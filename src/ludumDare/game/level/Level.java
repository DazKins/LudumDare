package ludumDare.game.level;

import java.util.ArrayList;
import java.util.List;

import ludumDare.game.entity.Entity;
import ludumDare.game.level.tile.Tile;
import ludumDare.gfx.Bitmap;

public class Level {
	private List<Entity> entities = new ArrayList<Entity>();
	private int[] tiles;
	
	private int w;
	private int h;
	
	public Level(int width, int height) {
		w = width;
		h = height;
		tiles = new int[w * h];
	}
	
	public void render(Bitmap b) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Tile.tiles[tiles[x + y * w]].render(b, this, x, y);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(b);
		}
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
}