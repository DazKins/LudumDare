package ludumDare.game.entity.mob;

import ludumDare.game.entity.Entity;

public abstract class Mob extends Entity {
	private int health;
	
	public Mob(int x, int y) {
		super(x, y);
	}
}