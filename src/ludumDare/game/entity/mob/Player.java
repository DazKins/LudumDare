package ludumDare.game.entity.mob;

import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;

public class Player extends Mob {

	public Player(int x, int y) {
		super(x, y);
	}
	
	public void render(Bitmap b) {
		b.blit(x, y, Art.sprites[0][0]);
	}

	public void tick() {
		
	}
}