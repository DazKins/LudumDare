package ludumDare.game.entity.mob;

import java.awt.event.KeyEvent;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class Player extends Mob {
	private InputHandler input;

	public Player(InputHandler i, int x, int y) {
		super(x, y);
		input = i;
	}
	
	public void render(Bitmap b) {
		b.blit(x, y, Art.sprites[0][0]);
	}

	public void tick() {
		if (input.keys[KeyEvent.VK_RIGHT]) 
			x++;
		if (input.keys[KeyEvent.VK_LEFT])
			x--;
		if (input.keys[KeyEvent.VK_DOWN]) 
			y++;
		if (input.keys[KeyEvent.VK_UP])
			y--;
	}
}