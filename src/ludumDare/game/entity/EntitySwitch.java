package ludumDare.game.entity;

import javax.crypto.AEADBadTagException;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public abstract class EntitySwitch extends Entity {
	protected ActivateableEntity linkedEntities[] = new ActivateableEntity[256];
	
	private long lastPulse;
	protected boolean activated;
	
	public EntitySwitch(int x, int y) {
		super(x, y);
	}
	
	private int it = 0;
	
	public void linkEntity(ActivateableEntity ae) {
		linkedEntities[it++] = ae;
	}
	
	public void onActivate(Entity e) {
		if (lifeTicks - lastPulse > 1) {
			for (int i = 0; i < linkedEntities.length; i++)
				if (linkedEntities[i] != null)
					linkedEntities[i].onActivate(null);
			activated = !activated;
		}
		lastPulse = lifeTicks;
	}
}