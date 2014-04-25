package ludumDare.game;

import java.awt.event.KeyEvent;

import ludumDare.MainComponent;
import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStatePlaying extends GameState {
	private Level l1;
	private Level l2;
	
	public GameStatePlaying(MainComponent m, InputHandler i) {
		super(m, i);
		l1 = new Level(64, 64);
		l2 = new Level(64, 64);
//		p2 = new Player(input, 50, 50);

		l1.addEntity(new Player(l1, input, 50, 50, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A));
		l2.addEntity(new Player(l2, input, 50, 50, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));
		l1.registerSecondaryLevel(l2);
		l2.registerSecondaryLevel(l1);
	}
	
	public void render(Bitmap b1, Bitmap b2) {
		l1.render(b1);
		l2.render(b2);
		
		Font.renderString(b1, 0, 0, "test123//;;-+");
	}

	public void tick() {
		l1.tick();
		l2.tick();
	}
}