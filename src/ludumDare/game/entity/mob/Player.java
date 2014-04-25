package ludumDare.game.entity.mob;

import ludumDare.game.level.Level;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;
import ludumDare.math.AABB;

public class Player extends Mob {
	private InputHandler input;
	
	private int jumpKey, leftKey, rightKey;

	public Player(Level le, InputHandler i, int x, int y, int u, int d, int r, int l) {
		super(le, x, y);
		input = i;
		jumpKey = u;
		leftKey = l;
		rightKey = r;
	}
	
	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 16);
	}
	
	public void render(Bitmap b) {
		int frame = 0;
		if (Math.abs(xa) >= 0.5)
			frame = (int) (lifeTicks /  20.0f) % 2;
		b.blit((int)x, (int)y, Art.sprites[2 - frame][0],  xa < 0 ? true : false, false, 1.0f);
		b.blit((int)x, (int)y + 8, Art.sprites[2 - frame][1],  false, false, 1.0f);
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
		
		System.out.println(x);
		
		move(xa, ya);
	}
}