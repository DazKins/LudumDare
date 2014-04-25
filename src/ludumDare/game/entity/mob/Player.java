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
		b.blit(x, y, Art.sprites[0][0], false, false);
		b.blit(x, y, Art.sprites[0][1], false, false);
	}

	public void tick() {
		if (input.keys[rightKey]) 
			x++;
		if (input.keys[leftKey])
			x--;
		if (input.keys[downKey]) 
			y++;
		if (input.keys[upKey])
			y--;
	}
}