package ludumDare.game.entity;

import java.util.List;

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
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void registerLevel(Level l) {
		level = l;
	}
	
	protected void move(float xa, float ya) {
		List<Entity> entities = level.getEntities();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e != this) {
				if (e.getAABB() != null) {
					if (this.getAABB().shifted(xa, ya).intersects(e.getAABB())) {
						e.onCollide(this);
					}
				}
			}
		}
		
		x += this.xa;
		y += this.ya;
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
	
	public void onCollide(Entity e){}
}