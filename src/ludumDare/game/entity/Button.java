package ludumDare.game.entity;

import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Button extends AOESwitch {
	public Button(float x, float y) {
		super(new AABB(x, y, x + 8, y + 2));
		this.x = x;
		this.y = y;
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[activated ? 5 : 4][2], false, false, 1.0f);
	}
 }