package ludumDare.game.level;

import java.util.ArrayList;
import java.util.List;

import ludumDare.game.entity.Entity;
import ludumDare.gfx.Bitmap;

public class Level {
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		
	}
	
	public void render(Bitmap b) {
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