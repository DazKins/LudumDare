package ludumDare.game.entity;

import javax.crypto.AEADBadTagException;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public abstract class EntitySwitch extends Entity {
	protected ActivateableEntity linkedEntity;
	
	public EntitySwitch(int x, int y, ActivateableEntity ae) {
		super(x, y);
		linkedEntity = ae;
	}
	
	public void onActivate(Entity e) {
		linkedEntity.onActivate(e);
	}
}