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
import ludumDare.game.entity.Lever;
import ludumDare.game.entity.PressurePlate;
import ludumDare.game.entity.TimerButton;
import ludumDare.game.entity.Trapdoor;
import ludumDare.game.level.tile.Tile;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;

public class Level {
	//25 and a bit tiles in view
	
	private List<Entity> entities = new ArrayList<Entity>();
	private int[] tiles;
	
	private int w;
	private int h;
	
	private Level pairedLevel;
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public void registerSecondaryLevel(Level l2) {
		pairedLevel = l2;
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		for (int i = 0; i < w / 8 + 1; i++) {
			b.blit((int) (i * 64 - xOff / 4), 0, Art.background, false, false, 1.0f);
		}
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (tiles[x + y * w] != -1)
					Tile.tiles[tiles[x + y * w]].render(b, this, x, y, xOff, yOff);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(b, xOff, yOff);
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
	
	public static Level[] loadLevelsFromFile(String path) {
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		Level rValue[] = new Level[2];
		rValue[0] = new Level();
		rValue[1] = new Level();
		
		try {
			img1 = ImageIO.read(Level.class.getResourceAsStream(path + "_1.png"));
			img2 = ImageIO.read(Level.class.getResourceAsStream(path + "_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rValue[0].w = img1.getWidth();
		rValue[0].h = img1.getHeight();
		rValue[1].w = img2.getWidth();
		rValue[1].h = img2.getHeight();
		
		rValue[0].tiles = new int[rValue[0].w * rValue[0].h];
		rValue[1].tiles = new int[rValue[1].w * rValue[1].h];
		
		int pixels1[] = img1.getRGB(0, 0, rValue[0].w, rValue[0].h, null, 0, rValue[0].w);
		int pixels2[] = img2.getRGB(0, 0, rValue[1].w, rValue[1].h, null, 0, rValue[1].w);
		
		Map<Integer, List<ActivateableEntity>> activateableMap = new HashMap<Integer, List<ActivateableEntity>>();
		Map<Integer, EntitySwitch> switchMap = new HashMap<Integer, EntitySwitch>();
		List<Integer> links = new ArrayList<Integer>();
		
		for (int i = 0; i < rValue.length; i++) {
			for (int x = 0; x < rValue[i].w; x++) {
				for (int y = 0; y < rValue[i].h; y++) {
					int bb, gg, rr;
					if (i == 0) {
						bb = pixels1[x + y * img1.getWidth()] & 0xFF;
						gg = (pixels1[x + y * img1.getWidth()] >> 8) & 0xFF;
						rr = (pixels1[x + y * img1.getWidth()] >> 16) & 0xFF;
					} else {
						bb = pixels2[x + y * img2.getWidth()] & 0xFF;
						gg = (pixels2[x + y * img2.getWidth()] >> 8) & 0xFF;
						rr = (pixels1[x + y * img1.getWidth()] >> 16) & 0xFF;
					}
					rValue[i].tiles[x + y * rValue[i].w] = -1;
					if (bb == 255) {
						rValue[i].tiles[x + y * rValue[i].w] = 0;
					} else if (bb == 150) {
						EntitySwitch e = new Button(x * 8, y * 8);
						switchMap.put(gg, e);
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity(e);
					} else if (bb == 50) {
						EntitySwitch e = new PressurePlate(x * 8, y * 8);
						switchMap.put(gg, e);
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity(e);
					} else if (bb == 75) {
						EntitySwitch e = new Lever(x * 8, y * 8);
						switchMap.put(gg, e);
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity(e);
					} else if (bb == 125) {
						EntitySwitch e = new TimerButton(x * 8, y * 8, rr);
						switchMap.put(gg, e);
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity(e);
					} else if (bb == 25) {
						ActivateableEntity e = new Trapdoor(x * 8, y * 8);
						if (activateableMap.containsKey(gg)) {
							activateableMap.get(gg).add(e);
						} else {
							activateableMap.put(gg, new ArrayList<ActivateableEntity>());
							activateableMap.get(gg).add(e);
						}
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity((Entity) e);
					} else if (bb == 100) {
						ActivateableEntity e = new Door(x * 8, y * 8);
						if (activateableMap.containsKey(gg)) {
							activateableMap.get(gg).add(e);
						} else {
							activateableMap.put(gg, new ArrayList<ActivateableEntity>());
							activateableMap.get(gg).add(e);
						}
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity((Entity) e);
					}
				}
			}
		}
		
		for (int i = 0; i < links.size(); i++) {
			List<ActivateableEntity> e = activateableMap.get(links.get(i));
			for (int u = 0; u < e.size(); u++) {
				switchMap.get(links.get(i)).linkEntity(e.get(u));
			}
		}
		
		return rValue;
	}
}