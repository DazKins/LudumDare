package ludumDare.game;

import java.awt.Dimension;

import ludumDare.MainComponent;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class GameStateCharacterSelection extends GameState {
	private Player p;

	public GameStateCharacterSelection(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
		
		p = new Player(null, 200, 100, 0, 0, 0, 0);
	}

	public void render(Bitmap b1, Bitmap b2) {
		p.render(b1, 0, 0);
	}

	public void tick() {
		
	}
	
}