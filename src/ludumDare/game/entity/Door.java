package ludumDare.game.entity;

import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Door extends Entity implements ActivateableEntity {
	private boolean open;
	
	public Door(int x, int y) {
		super(x, y);
	}

	public void onActivate(Entity e) {
		open = !open;
	}
	
	public AABB getAABB() {
		if (!open)
			return new AABB(x, y, 8 + x, 16 + y);
		return null;
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Mob) 
			return false;
		return true;
	}
 
	public void render(Bitmap b) {
		int state = open ? 3 : 2;
		b.blit((int) x, (int) y, Art.sprites[state][2], false, false, 1.0f);
		b.blit((int) x, (int) y + 8, Art.sprites[state][3], false, false, 1.0f);
	}
}