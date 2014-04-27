package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class XMovingPlatform extends Entity implements ActivateableEntity {
	Audio on = new Audio("/entityon.wav");
	Audio off = new Audio("/entityoff.wav");

	public boolean enabled;
	private boolean bounced = false;
	private boolean reverse = false;
	public float speed = 1.0f;
	private int targetX;
	private int startX;

	public XMovingPlatform(float x, float y, int tx) {
		super(x, y);
		startX = (int) x;
		targetX = tx;
	}

	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}

	public void onActivate(Entity e) {
		if (enabled) {
			off.play(true);
		} else {
			on.play(true);
		}
		if (targetX < startX)
			reverse = true;
		enabled = !enabled;
	}

	public void onYCollide(Entity e) {
		if (e instanceof Mob) {
			Mob m = (Mob) e;
			if (!m.isOnMovingPlatform) {
				System.out.println("hit");
				m.nextFrameXA = getXA();
				m.isOnMovingPlatform = true;
			}
			if (m.getAABB().intersects(this.getAABB())) {
				// m.setX(m.getX() +);
				// m.setY(m.getY() + m.getAABB().yDifference(this.getAABB()));
			}
		}
	}
	
//	public void onXCollide(Entity e) {
//		if (e instanceof XMovingPlatform) {
//			System.out.println("hit");
//			if (Math.abs(e.getXA()) > this.getXA())
//				e.setXA(0);
//		}
//	}

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
			if (!reverse) {
				if (x < targetX && !bounced) {
					xa = speed;
				} else if (x > startX) {
					xa = speed * -1;
					bounced = true;
				} else if (x == startX) {
					bounced = false;
				}
			} else {
				if (x > targetX && !bounced) {
					xa = speed;
				} else if (x < startX) {
					xa = speed * -1;
					bounced = true;
				} else if (x == startX) {
					bounced = false;
				}
			}
		}
		move(xa, ya);
	}
}