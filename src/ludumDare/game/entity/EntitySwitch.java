package ludumDare.game.entity;

import javax.crypto.AEADBadTagException;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public abstract class EntitySwitch extends Entity {
	protected ActivateableEntity linkedEntity;
	
	private long lastPulse;
	protected boolean activated;
	
	public EntitySwitch(int x, int y, ActivateableEntity ae) {
		super(x, y);
		linkedEntity = ae;
	}
	
	public void onActivate(Entity e) {
		if (lifeTicks - lastPulse > 1) {
			linkedEntity.onActivate(e);
			activated = !activated;
		}
		lastPulse = lifeTicks;
	}
}