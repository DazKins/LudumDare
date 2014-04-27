package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class SpeedBlock extends Entity implements ActivateableEntity {
	Audio on = new Audio("/entityon.wav");
	Audio off = new Audio("/entityoff.wav");

	public boolean active;
	public int speed;

	public SpeedBlock(int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
	}

	public void onActivate(Entity e) {
		if (active) {
			off.play(true);
		} else {
			on.play(true);
		}
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