package ludumDare.game.entity;

import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class TimerButton extends AcitvateableSwitch {
	private int tickTimer;
	private int ticksSinceLastActivate;
	
	public TimerButton(float x, float y, int noOfTicks) {
		super(new AABB(x, y + 4, x + 8, y + 8));
		tickTimer = noOfTicks;
		this.x = x;
		this.y = y;
	}
	
	public void onCollide(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.isInteracting() && lifeTicks - ticksSinceLastActivate > tickTimer) {
				super.onActivate(e);
				ticksSinceLastActivate = lifeTicks;
				activated = true;
			}
		}
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[7][activated ? 2 : 3], false, false, 1.0f);
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