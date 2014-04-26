package ludumDare.game;

import java.awt.Dimension;

import ludumDare.MainComponent;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public abstract class GameState {
	protected MainComponent mc;
	protected InputHandler input;
	
	protected Dimension[] windowSizes;
	
	public GameState(MainComponent m, InputHandler i, Dimension[] ws) {
		mc = m;
		input = i;
		windowSizes = ws;
	}
	
	public abstract void render(Bitmap b1, Bitmap b2);
	
	public abstract void tick();
}