package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ludumDare.MainComponent;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStateMenu extends GameState {
	private List<String> menuOptions = new ArrayList<String>();
	
	private int selectedMenuOption;
	
	public GameStateMenu(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
		
		menuOptions.add("Play");
		menuOptions.add("Exit");
	}

	public void render(Bitmap b1, Bitmap b2) {
		for (int i = 0; i < 8; i++) {
			b1.blit(i * 64, 0, Art.background, false, false, 1.0f, 1.0f);
			b2.blit(i * 64, 0, Art.background, false, false, 1.0f, 1.0f);
		}
		
		for (int i = 0; i < menuOptions.size(); i++) {
			String msg = menuOptions.get(i);
			if (i == selectedMenuOption)
				Font.renderString(b1, (int) windowSizes[0].getWidth() / 2 - msg.length() * 4 + 4, i * 16 + 68, msg, 0x410000);
			Font.renderString(b1, (int) windowSizes[0].getWidth() / 2 - msg.length() * 4, i * 16 + 64, msg, 0xFF0000);
		}
	}

	public void tick() {
		if (input.keyStream[KeyEvent.VK_UP]) {
			selectedMenuOption -= 1;
		}
		if (input.keyStream[KeyEvent.VK_DOWN]) {
			selectedMenuOption += 1;
		}
		
		if (input.keyStream[KeyEvent.VK_ENTER]) {
			if (selectedMenuOption == 0) {
				mc.changegameState(new GameStateCharacterSelection(mc, input, windowSizes));
			}
			if (selectedMenuOption == 1) {
				System.exit(0);
			}
		}
		
		if (selectedMenuOption >= menuOptions.size())
			selectedMenuOption = 0;
		if (selectedMenuOption < 0)
			selectedMenuOption = menuOptions.size() - 1;
	}
}