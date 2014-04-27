package ludumDare.game.entity.mob;


import ludumDare.game.entity.Entity;
import ludumDare.math.AABB;

public abstract class Mob extends Entity {
	protected float previousFrameXA;
	private int health;
	public boolean isOnMovingPlatform = false;;
	public float nextFrameXA;
	
	public Mob(int x, int y) {
		super(x, y);
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}
	
	protected void move(float xa, float ya) {
		this.xa = xa;
		this.ya = ya;
		
		int x0 = ((int)x >> 3) - 4;
		int y0 = ((int)y >> 3) - 4;
		int x1 = x0 + 9;
		int y1 = y0 + 9;
		
		isOnFloor = false;
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				if (level.getTile(x, y) != null) {
					if (level.getTile(x, y).getAABB(level, x, y) != null) {
						if (this.getAABB().shifted(xa, 0).intersects(level.getTile(x, y).getAABB(level, x, y))) {
							this.xa = 0.0f;
						}
						if (this.getAABB().shifted(0, ya).intersects(level.getTile(x, y).getAABB(level, x, y))) {
							if (ya > 0)
								isOnFloor = true;
							this.ya = 0.0f;
						}
					}
				}
			}
		}
		
		super.move(this.xa, this.ya);
	}
}