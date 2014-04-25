package ludumDare.game;

import ludumDare.MainComponent;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public abstract class GameState {
	protected MainComponent mc;
	protected InputHandler input;
	
	public GameState(MainComponent m, InputHandler i) {
		mc = m;
		input = i;
	}
	
	public abstract void render(Bitmap b1, Bitmap b2);
	
	public abstract void tick();
}