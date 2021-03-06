package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ludumDare.MainComponent;
import ludumDare.audio.Audio;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStateMenu extends GameState {
	private List<String> menuOptions = new ArrayList<String>();
	Audio blip = new Audio("/menublip.wav");
	Audio select = new Audio ("/menuselect.wav");
	private int selectedMenuOption;
	
	public GameStateMenu(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
		
		menuOptions.add("Play");
		menuOptions.add("Instructions");
		menuOptions.add("Exit");
	}

	public void render(Bitmap b1, Bitmap b2) {
		for (int i = 0; i < 4; i++) {
			b1.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
			b2.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
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
			blip.play(true);
		}
		if (input.keyStream[KeyEvent.VK_DOWN]) {
			selectedMenuOption += 1;
			blip.play(true);			
		}
		
		if (input.keyStream[KeyEvent.VK_ENTER]) {
			if (selectedMenuOption == 0) {
				mc.changegameState(new GameStateCharacterSelection(mc, input, windowSizes));
				select.play(true);
			}
			if (selectedMenuOption == 1) {
				mc.changegameState(new GameStateInstructions(mc, input, windowSizes));
				select.play(true);
			}
			if (selectedMenuOption == 2) {
				System.exit(0);
				select.play(true);
			}
		}
		
		if (selectedMenuOption >= menuOptions.size())
			selectedMenuOption = 0;
		if (selectedMenuOption < 0)
			selectedMenuOption = menuOptions.size() - 1;
	}
}