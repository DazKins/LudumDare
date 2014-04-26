package ludumDare.game.entity;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class AOESwitch extends EntitySwitch {
	private AABB aabb;
	
	public AOESwitch(ActivateableEntity ae, AABB bb) {
		super(0, 0, ae);
		aabb = bb;
	}
	
	public AABB getAABB() {
		return aabb;
	}

	public void render(Bitmap b) {
		
	}
	
	public void tick() {
		super.tick();
	}

	public void onCollide(Entity e) {
		super.onActivate(e);
	}
}