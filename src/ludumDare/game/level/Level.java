package ludumDare.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import ludumDare.game.entity.ActivateableEntity;
import ludumDare.game.entity.Ball;
import ludumDare.game.entity.Button;
import ludumDare.game.entity.Door;
import ludumDare.game.entity.Entity;
import ludumDare.game.entity.EntitySwitch;
import ludumDare.game.entity.JumpBlock;
import ludumDare.game.entity.Lever;
import ludumDare.game.entity.PressurePlate;
import ludumDare.game.entity.SpeedBlock;
import ludumDare.game.entity.TimerButton;
import ludumDare.game.entity.Trapdoor;
import ludumDare.game.entity.XMovingPlatform;
import ludumDare.game.entity.mob.Player;
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
		for (int i = 0; i < w / 4 + 1; i++) {
			b.blit((int) (i * 128 - xOff / 4), 0, Art.background1, false, false, 1.0f, 1.0f);
			b.blit((int) (i * 128 - xOff / 2), 0, Art.background2, false, false, 1.0f, 1.0f);
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
	
	public static Level[] loadLevelsFromFile(String path, Player p1, Player p2) {
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
		Map<Integer, List<EntitySwitch>> switchMap = new HashMap<Integer, List<EntitySwitch>>();
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
						rr = (pixels2[x + y * img2.getWidth()] >> 16) & 0xFF;
					}
					rValue[i].tiles[x + y * rValue[i].w] = -1;
					if (bb == 255) {
						if (gg == 255)
							rValue[i].tiles[x + y * rValue[i].w] = 0;
						else {
							if (i == 0) {
								p1.setX(x * 8);
								p1.setY(y * 8);
								rValue[0].addEntity(p1);
							} else {
								p2.setX(x * 8);
								p2.setY(y * 8);
								rValue[1].addEntity(p2);
							}
						}
					} else if (bb == 0) {}
					else if (bb < 100) {
						EntitySwitch e = null;
						if (bb == 1) {
							e = new Button(x * 8, y * 8);
						} else if (bb == 2) {
							e = new PressurePlate(x * 8, y * 8);
						} else if (bb == 3) {
							e = new Lever(x * 8, y * 8);
						} else if (bb == 4) {
							e = new TimerButton(x * 8, y * 8, rr);
						}
						if (switchMap.containsKey(gg)) {
							switchMap.get(gg).add(e);
						} else {
							switchMap.put(gg, new ArrayList<EntitySwitch>());
							switchMap.get(gg).add(e);
						}
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity((Entity) e);
					} else if (bb < 200) {
						ActivateableEntity e = null;
						if (bb == 100) {
							e = new Trapdoor(x * 8, y * 8);
						} else if (bb == 101) {
							e = new Door(x * 8, y * 8);
						} else if (bb == 102) {
							e = new JumpBlock(x * 8, y * 8, rr / 10.0f);
						} else if (bb == 103) {
							e = new SpeedBlock(x * 8, y * 8, rr);
						} else if (bb == 104) {
							e = new XMovingPlatform(x * 8, y * 8, x * 8 + rr * 8);
						}
						if (activateableMap.containsKey(gg)) {
							activateableMap.get(gg).add(e);
						} else {
							activateableMap.put(gg, new ArrayList<ActivateableEntity>());
							activateableMap.get(gg).add(e);
						}
						if (!links.contains(gg))
							links.add(gg);
						rValue[i].addEntity((Entity) e);
					} else if (bb == 200) {
						rValue[i].addEntity(new Ball(x * 8, y * 8));
					}
				}
			}
		}
		
		for (int i = 0; i < links.size(); i++) {
			List<ActivateableEntity> e = activateableMap.get(links.get(i));
			List<EntitySwitch> es = switchMap.get(links.get(i));
			if (e != null && es != null) {
				for (int u = 0; u < es.size(); u++) {
					EntitySwitch es0 = es.get(u);
					for (int f = 0; f < e.size(); f++) {
						es0.linkEntity(e.get(f));
					}
				}
			}
		}
		
		return rValue;
	}
}