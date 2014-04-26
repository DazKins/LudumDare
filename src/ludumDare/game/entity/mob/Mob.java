package ludumDare.game.entity.mob;

import java.util.List;

import ludumDare.game.entity.Entity;
import ludumDare.game.level.Level;
import ludumDare.math.AABB;

public abstract class Mob extends Entity {
	private int health;
	
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
		
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				if (level.getTile(x, y) != null) {
					if (level.getTile(x, y).getAABB(level, x, y) != null) {
						if (this.getAABB().shifted(xa, 0).intersects(level.getTile(x, y).getAABB(level, x, y))) {
							this.xa = 0.0f;
						}
						if (this.getAABB().shifted(0, ya).intersects(level.getTile(x, y).getAABB(level, x, y))) {
							this.ya = 0.0f;
						}
					}
				}
			}
		}
		
		super.move(xa, ya);
	}
}