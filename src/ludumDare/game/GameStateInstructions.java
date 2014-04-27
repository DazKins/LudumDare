package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import ludumDare.MainComponent;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStateInstructions extends GameState {
	private int page;

	public GameStateInstructions(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
	}

	public void render(Bitmap b1, Bitmap b2) {
		for (int i = 0; i < 4; i++) {
			b1.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
			b2.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
		}

		if (page == 0) {
			Font.renderString(b1, 24, 24, "Controls screen 1", 0x410000);
			Font.renderString(b1, 20, 20, "Controls screen 1", 0xff0000);
			Font.renderString(b2, 24, 24, "Controls screen 2", 0x410000);
			Font.renderString(b2, 20, 20, "Controls screen 2", 0xff0000);
	
			Font.renderString(b1, 20, 50, "UP, DOWN, LEFT AND RIGHT to move", 0xff0000);
			Font.renderString(b2, 20, 50, "W, A, S and d to move", 0xff0000);
	
			Font.renderString(b1, 20, 90, "E to interact", 0xff0000);
			Font.renderString(b2, 20, 90, "Ctrl to interact", 0xff0000);

			Font.renderString(b1, 20, 130, "Press enter for page 2", 0xff0000);
		} else if (page == 1) {
			Font.renderString(b1, 24, 24, "Controls screen 1", 0x410000);
			Font.renderString(b1, 20, 20, "Controls screen 1", 0xff0000);
			Font.renderString(b2, 24, 24, "Controls screen 2", 0x410000);
			Font.renderString(b2, 20, 20, "Controls screen 2", 0xff0000);
			
			Font.renderString(b1, 20, 50, "Press buttons, levers and", 0xff0000);
			Font.renderString(b1, 20, 90, "pressure plates to interact", 0xff0000);
			Font.renderString(b1, 20, 130, "with the world", 0xff0000);

			Font.renderString(b2, 20, 50, "Yellow blocks can be", 0xff0000);
			Font.renderString(b2, 20, 90, "pushed by the player", 0xff0000);
		}
	}

	public void tick() {
		if (input.keyStream[KeyEvent.VK_ESCAPE]) {
			mc.changegameState(new GameStateMenu(mc, input, windowSizes));
		}
		if (input.keyStream[KeyEvent.VK_ENTER]) {
			page += 1;
		}
		
		if (page > 1)
			page = 0;
	}
}