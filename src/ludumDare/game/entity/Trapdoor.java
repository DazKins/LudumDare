package ludumDare.game.entity;

import ludumDare.gfx.Bitmap;

public class Trapdoor extends Entity implements ActivateableEntity {
	private boolean open = false;

	public Trapdoor(int x, int y) {
		super(x, y);
	}

	public void onActivate(Entity e) {
		open = !open;
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[0][3], false, false, 1.0f);
	}
	
}