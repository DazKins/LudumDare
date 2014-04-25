package ludumDare.game;

import ludumDare.MainComponent;
import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class GameStatePlaying extends GameState {
	private Level l1;
	private Level l2;
	
	private Player p1;
	private Player p2;
	
	public GameStatePlaying(MainComponent m, InputHandler i) {
		super(m, i);
		l1 = new Level(64, 64);
		l2 = new Level(64, 64);
		
		p1 = new Player(input, 50, 50);
		p2 = new Player(input, 50, 50);
		
//		for (int i0 = 0; i0 < 1000; i0++)
//			l1.addEntity(new Player(input, i0 % 10, i0 / 10));
	}
	
	public void render(Bitmap b1, Bitmap b2) {
		l1.render(b1);
		l2.render(b2);
	}

	public void tick() {
		l1.tick();
		l2.tick();
	}
}