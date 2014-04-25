package ludumDare.game.entity;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public abstract class EntitySwitch extends Entity {
	public EntitySwitch(Level l, int x, int y) {
		super(l, x, y);
	}
	
	public abstract void onCollide(Entity e);
}