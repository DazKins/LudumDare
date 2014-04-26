package ludumDare.game.entity;

import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class PressurePlate extends AOESwitch {
	private int ticksSinceLastActivate;
	
	public PressurePlate(float x, float y) {
		super(new AABB(x, y, x + 8, y + 2));
		this.x = x;
		this.y = y;
	}
	
	public void onCollide(Entity e) {
		super.onCollide(e);
		ticksSinceLastActivate = lifeTicks;
	}
	
	public void render(Bitmap b) {
		b.blit((int) x, (int) y, Art.sprites[activated ? 5 : 4][3], false, false, 1.0f);
	}
	
	public void tick() {
		super.tick();
		if (lifeTicks - ticksSinceLastActivate > 1 && activated) {
			activated = false;
			linkedEntity.onActivate(null);
		}
	}
}