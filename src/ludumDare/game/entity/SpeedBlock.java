package ludumDare.game.entity;

import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class SpeedBlock extends Entity implements ActivateableEntity {
	public boolean active;
	public int speed;
	
	public SpeedBlock(int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
		System.out.println(speed);
	}

	public void onActivate(Entity e) {
		active = !active;
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
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[active ? 11 : 9][2], false, false, 1.0f, 1.0f);
	}
}