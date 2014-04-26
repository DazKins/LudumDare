package ludumDare.game.entity;

import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class JumpBlock extends Entity implements ActivateableEntity {
	public boolean enabled;
	
	public JumpBlock(int x, int y) {
		super(x, y);
	}

	public void onActivate(Entity e) {
		enabled = !enabled;
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Player)
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