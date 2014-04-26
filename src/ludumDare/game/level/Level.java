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
	
	private Level pairedLevel;
	
	public Level(int width, int height) {
		w = width;
		h = height;
		tiles = new int[w * h];
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (y > 15)
					tiles[x + y * w] = 0;
				else
					tiles[x + y * w] = -1;
			}
		}
		
		tiles[8 + 13 * w] = 0;
		tiles[8 + 14 * w] = 0;
		tiles[8 + 15 * w] = 0;
	}
	
	public void registerSecondaryLevel(Level l2) {
		pairedLevel = l2;
	}
	
	public void render(Bitmap b) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (tiles[x + y * w] != -1)
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
		e.registerLevel(this);
		entities.add(e);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void setTile(int x, int y, Tile t) {
		tiles[x + y * w] = t.getID();
	}
	
	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < w && y < h) {
			if(tiles[x + y * w] != -1)
				return Tile.tiles[tiles[x + y * w]];
			else
				return null;
		}
		return null;
	}
}