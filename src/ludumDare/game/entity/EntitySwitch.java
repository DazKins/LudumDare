package ludumDare.game.entity;

import javax.crypto.AEADBadTagException;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public abstract class EntitySwitch extends Entity {
	protected ActivateableEntity linkedEntity;
	
	private long lastPulse;
	protected boolean activated;
	
	public EntitySwitch(int x, int y) {
		super(x, y);
	}
	
	public void linkEntity(ActivateableEntity ae) {
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