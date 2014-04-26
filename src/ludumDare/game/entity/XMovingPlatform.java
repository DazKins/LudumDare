package ludumDare.game.entity;

import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class XMovingPlatform extends Entity implements ActivateableEntity {
	public boolean enabled;
	public float speed = 0.1f;
	private int targetX;
	
	public XMovingPlatform(float x, float y, int tx) {
		super(x, y);
		targetX = tx;
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}

	public void onActivate(Entity e) {
		enabled = !enabled;
	}
	
	public void onYCollide(Entity e) {
		if (e instanceof Mob) {
			Mob m = (Mob) e;
			if (!m.isOnMovingPlatform)
				m.nextFrameXA += getXA();
			m.isOnMovingPlatform = true;
			if (m.getAABB().intersects(this.getAABB())) {
				System.out.println(m.getAABB().xDifference(this.getAABB()));
//				m.setX(m.getX() +);
//				m.setY(m.getY() + m.getAABB().yDifference(this.getAABB()));
			}
		}
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Mob) {
			return false;
		}
		return true; 
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[enabled ? 13 : 9][2], false, false, 1.0f, 1.0f);
	}
	
	public void tick() {
		super.tick();
		
		xa = 0;
		
		if (enabled) {
			if (x < targetX)
				xa = speed;
		}
		
		move(xa, ya);
	}
}