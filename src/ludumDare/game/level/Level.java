package ludumDare.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import ludumDare.game.entity.ActivateableEntity;
import ludumDare.game.entity.Button;
import ludumDare.game.entity.Door;
import ludumDare.game.entity.Entity;
import ludumDare.game.entity.EntitySwitch;
import ludumDare.game.entity.PressurePlate;
import ludumDare.game.level.tile.Tile;
import ludumDare.gfx.Bitmap;

public class Level {
	//25 and a bit tiles in view
	
	private List<Entity> entities = new ArrayList<Entity>();
	private int[] tiles;
	
	private int w;
	private int h;
	
	private Level pairedLevel;
	
	public Level(String path) {
		loadLevelFromFile(path);
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
	
	public void loadLevelFromFile(String path) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(Level.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		w = img.getWidth();
		h = img.getHeight();
		tiles = new int[w * h];
		
		int pixels[] = img.getRGB(0, 0, w, h, null, 0, w);
		
		Map<Integer, ActivateableEntity> activateableMap = new HashMap<Integer, ActivateableEntity>();
		Map<Integer, EntitySwitch> switchMap = new HashMap<Integer, EntitySwitch>();
		List<Integer> links = new ArrayList<Integer>();
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int bb = pixels[x + y * img.getWidth()] & 0xFF;
				int gg = (pixels[x + y * img.getWidth()] >> 8) & 0xFF;
				tiles[x + y * w] = -1;
				if (bb == 255) { 
					tiles[x + y * w] = 0;
				} else if (bb == 100) {
					ActivateableEntity e = new Door(x * 8, y * 8);
					activateableMap.put(gg, e);
					if (!links.contains(gg))
						links.add(gg);
				} else if (bb == 150) {
					EntitySwitch e = new Button(x * 8, y * 8);
					switchMap.put(gg, e);
					if (!links.contains(gg))
						links.add(gg);
				} else if (bb == 50) {
					EntitySwitch e = new PressurePlate(x * 8, y * 8);
					switchMap.put(gg, e);
					if (!links.contains(gg))
						links.add(gg);
				}
			}
		}
		
		for (int i = 0; i < links.size(); i++) {
			switchMap.get(links.get(i)).linkEntity(activateableMap.get(links.get(i)));
			this.addEntity(switchMap.get(links.get(i)));
			this.addEntity((Entity) activateableMap.get(links.get(i)));
		}
	}
}