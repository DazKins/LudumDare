package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Trapdoor extends Entity implements ActivateableEntity {
	private boolean open = false;
	Audio trapdoorChange = new Audio("/door1.wav");

	public Trapdoor(int x, int y) {
		super(x, y);
	}

	public void onActivate(Entity e) {
		open = !open;
		trapdoorChange.play(true);
	}
	
	public AABB getAABB() {
		if (!open)
			return new AABB(x, y, 8 + x, 1 + y);
		return null;
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Mob) 
			return false;
		return true;
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[open ? 1 : 0][3], false, false, 1.0f, 1.0f);
	}
	
}