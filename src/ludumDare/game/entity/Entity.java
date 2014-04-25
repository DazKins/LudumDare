package ludumDare.game.entity;

import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public abstract class Entity {
	protected Level level;
	
	protected float x;
	protected float y;
	protected float xa;
	protected float ya;
	
	protected int lifeTicks;
	
	public AABB getAABB() {
		return null;
	}
	
	public Entity(Level l, int x, int y) {
		this.x = x;
		this.y = y;
		level = l;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public abstract void render(Bitmap b);
	
	public void tick() {
		lifeTicks++;
	}
}