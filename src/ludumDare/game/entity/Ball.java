package ludumDare.game.entity;

import ludumDare.game.entity.mob.Mob;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Ball extends Mob {
	public Ball(int x, int y) {
		super(x, y);
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[12][2], false, false, 1.0f, 1.0f);
	}
	
	public void onXCollide(Entity e) {
		if (e instanceof Player) {
			this.xa = e.getXA();
			e.setXA(0);
		}
	}
	
	public boolean mayPassY(Entity e) {
		if (e instanceof Player)
			return false;
		return true;
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}
	
	public void tick() {
		super.tick();

		ya += 0.045;
		xa *= 0.875;
		
		super.move(xa, ya);
	}
}