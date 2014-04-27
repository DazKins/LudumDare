package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class EntranceTunnel extends AOESwitch implements ActivateableEntity {
		
	public EntranceTunnel(float x, float y) {
		super(new AABB(x, 23 + y, 16 + x, 24 + y));
		this.x = x;
		this.y = y;
	}


	public void render(Bitmap b, float xOff, float yOff) {
		//column 1
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[16][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 8, Art.sprites[16][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 16, Art.sprites[16][4], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 24, Art.sprites[16][5], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 32, Art.sprites[16][6], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 40, Art.sprites[16][7], false, false, 1.0f, 1.0f);
		//column 2
		b.blit((int) (x - xOff) + 8, (int) (y - yOff), Art.sprites[17][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 8, Art.sprites[17][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 16, Art.sprites[17][4], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 24, Art.sprites[17][5], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 32, Art.sprites[17][6], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 40, Art.sprites[17][7], false, false, 1.0f, 1.0f);
	}

	
	
	
	
}
	