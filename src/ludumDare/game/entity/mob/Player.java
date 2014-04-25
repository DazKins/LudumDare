package ludumDare.game.entity.mob;

import java.awt.event.KeyEvent;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class Player extends Mob {
	private InputHandler input;
	
	private int upKey, downKey, leftKey, rightKey;

	public Player(InputHandler i, int x, int y, int u, int d, int r, int l) {
		super(x, y);
		input = i;
		upKey = u;
		downKey = d;
		leftKey = l;
		rightKey = r;
	}
	
	public void render(Bitmap b) {
		b.blit((int)x, (int)y, Art.sprites[1 + (int) (lifeTicks /  20.0f) % 2][0], false, false, 0.5f);
		b.blit((int)x, (int)y + 8, Art.sprites[1 + (int) (lifeTicks / 20.0f) % 2][1], false, false, 0.5f);
	}

	public void tick() {
		super.tick();
		if (input.keys[rightKey]) 
			xa = 1;
		if (input.keys[leftKey])
			xa = -1;
		if (input.keys[downKey]) 
			ya = 1;
		if (input.keys[upKey])
			ya = -1;
		
		xa *= 0.875;
		ya *= 0.875;
		
		move(xa, ya);
	}
}