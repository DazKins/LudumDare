package ludumDare.game.entity.mob;

import java.awt.event.KeyEvent;

import ludumDare.game.level.Level;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;
import ludumDare.math.AABB;

public class Player extends Mob {
	private InputHandler input;
	
	private int jumpKey, leftKey, rightKey, interactKey;

	public Player(InputHandler i, int x, int y, int u, int r, int l, int interactKey) {
		super(x, y);
		input = i;
		jumpKey = u;
		leftKey = l;
		rightKey = r;
		this.interactKey = interactKey;
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 7, y + 15);
	}
	
	public boolean isInteracting() {
		return input.keyStream[interactKey];
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		int frame = 0;
		if (Math.abs(xa) >= 0.5)
			frame = (int) (lifeTicks /  20.0f) % 2;
		b.blit((int)(x - xOff), (int)(y - yOff), Art.sprites[2 - frame][0],  xa < 0 ? true : false, false, 1.0f);
		b.blit((int)(x - xOff), (int)(y - yOff) + 8, Art.sprites[2 - frame][1],  false, false, 1.0f);
	}

	public void tick() {
		super.tick();
		if (input.keys[rightKey])
			xa = 1;
		if (input.keys[leftKey])
			xa = -1;
		if (input.keys[jumpKey]) {
			if (ya == 0)
				ya = -2;
		}
		
		ya += 0.045;
		xa *= 0.875;
		
		move(xa, ya);
	}
}