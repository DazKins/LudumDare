package ludumDare.game.entity;

import java.util.List;

import ludumDare.game.entity.mob.Player;
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
	
	protected boolean isOnFloor = true;
	
	public AABB getAABB() {
		return null;
	}
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void registerLevel(Level l) {
		level = l;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getXA() {
		return xa;
	}
	
	public float getYA() {
		return ya;
	}
	
	public void setXA(float xa) {
		this.xa = xa;
	}
	
	public void setYA(float ya) {
		this.ya = ya;
	}
	
	protected void move(float xa, float ya) {
		this.xa = xa;
		this.ya = ya;
		List<Entity> entities = level.getEntities();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e != this) {
				if (e.getAABB() != null) {
					if (!e.mayPass(this)) {
						boolean anyCol = false;
						if (this.getAABB().shifted(this.xa, 0).intersects(e.getAABB())) {
							this.xa = 0;
							e.onXCollide(this);
							this.onXCollide(e);
							
							anyCol = true;
						}
						if (this.getAABB().shifted(0, this.ya).intersects(e.getAABB())) {
							if (y > 0) {
								if (this.ya > 0)
									isOnFloor = true;
							}
							this.ya = 0;
							e.onYCollide(this);
							this.onYCollide(e);
							
							anyCol = true;
						}
						if (anyCol) {
							e.onCollide(this);
							this.onCollide(e);
						}
					}
					if (this.getAABB().shifted(this.xa, this.ya).intersects(e.getAABB())) {
						e.onCollide(this);
						this.onCollide(e);
					}
					if (this.getAABB().shifted(0, this.ya).intersects(e.getAABB())) {
						if (!e.mayPassY(this)) {
							if (this.ya > 0)
								isOnFloor = true;
							this.ya = 0;
						}
						e.onYCollide(this);
						this.onYCollide(e);
					}
					if (this.getAABB().shifted(this.xa, 0).intersects(e.getAABB())) {
						if (!e.mayPassX(this))
							this.xa = 0;
						e.onXCollide(this);
						this.onXCollide(e);
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
	
	public abstract void render(Bitmap b, float xOff, float yOff);
	
	public void tick() {
		lifeTicks++;
	}
	
	public void onCollide(Entity e){}

	public void onXCollide(Entity e){}

	public void onYCollide(Entity e){}
	
	public boolean mayPassX(Entity e) {
		return true;
	}
	
	public boolean mayPassY(Entity e) {
		return true;
	}
	
	public boolean mayPass(Entity e) {
		return true;
	}
}