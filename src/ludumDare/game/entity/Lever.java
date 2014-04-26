package ludumDare.game.entity;

import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Lever extends AcitvateableSwitch {
	public Lever(float x, float y) {
		super(new AABB(x, y + 4, x + 8, y + 8));
		this.x = x;
		this.y = y;
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[6][activated ? 2 : 3], false, false, 1.0f);
	}
}