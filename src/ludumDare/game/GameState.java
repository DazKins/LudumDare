package ludumDare.game;

import ludumDare.MainComponent;
import ludumDare.gfx.Bitmap;

public abstract class GameState {
	private MainComponent mc;
	
	public abstract void render(Bitmap b1, Bitmap b2);
	
	public abstract void tick();
}