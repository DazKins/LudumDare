package ludumDare.game;

import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;

public class GameStatePlaying extends GameState {
	private Level l1;
	private Level l2;
	
	public GameStatePlaying() {
		l1 = new Level(64, 64);
		l2 = new Level(64, 64);
		
		l1.addEntity(new Player(50, 50));
	}
	
	public void render(Bitmap b1, Bitmap b2) {
		l1.render(b1);
		l2.render(b2);
	}

	public void tick() {
		
	}
}