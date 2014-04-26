package ludumDare.game.entity;

import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class TimerButton extends AOESwitch {
	private int tickTimer;
	private int ticksSinceLastActivate;
	
	public TimerButton(float x, float y, int noOfTicks) {
		
		tickTimer = noOfTicks;
		this.x = x;
		this.y = y;
	}
	
	public void onCollide(Entity e) {
		super.onCollide(e);
		ticksSinceLastActivate = lifeTicks;
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[activated ? 5 : 4][3], false, false, 1.0f);
	}
	
	public void tick() {
		super.tick();
		if (lifeTicks - ticksSinceLastActivate > tickTimer && activated) {
			activated = false;
			for (int i = 0; i < linkedEntities.length; i++) {
				if (linkedEntities[i] != null)
					linkedEntities[i].onActivate(null);
			}
		}
	}
}