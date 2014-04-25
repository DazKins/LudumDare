package ludumDare.game.entity;

import ludumDare.gfx.Bitmap;

public abstract class Entity {
	protected int x;
	protected int y;
	
	protected int lifeTicks;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract void render(Bitmap b);
	
	public void tick() {
		lifeTicks++;
	}
}