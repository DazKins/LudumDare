package ludumDare.game.entity;

import ludumDare.gfx.Bitmap;

public abstract class Entity {
	protected float x;
	protected float y;
	protected float xa;
	protected float ya;
	
	protected int lifeTicks;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
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