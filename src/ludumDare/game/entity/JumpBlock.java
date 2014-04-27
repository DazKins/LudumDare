package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class JumpBlock extends Entity implements ActivateableEntity {
	Audio on = new Audio("/entityon.wav");
	Audio off = new Audio("/entityoff.wav");

	public boolean enabled;
	public float boostHeight;
	
	public JumpBlock(int x, int y, float bh) {
		super(x, y);
		boostHeight = bh;
	}

	public void onActivate(Entity e) {
		if (enabled) {
			off.play(true);
		} else {
			on.play(true);
		}
		enabled = !enabled;
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Mob)
			return false;
		return true;
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[enabled ? 10 : 9][2], false, false, 1.0f, 1.0f);
	}
}