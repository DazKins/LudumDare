package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import ludumDare.MainComponent;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStateCharacterSelection extends GameState {
	private int selectedCharacter;
	
	public String names[] = {"Steve", "Jim", "Zombie", "Tyrone", "Lego Man", "Notch", "Miner"};

	public GameStateCharacterSelection(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
	}

	public void render(Bitmap b1, Bitmap b2) {
		for (int i = 0; i < 8; i++) {
			b1.blit(i * 64, 0, Art.background, false, false, 1.0f, 1.0f);
			b2.blit(i * 64, 0, Art.background, false, false, 1.0f, 1.0f);
		}
		
		b1.blit(windowSizes[0].width / 2 - 20, windowSizes[1].height / 2 - 40, Art.sprites[selectedCharacter * 2][0], false, false, 1.0f, 5.0f);
		b1.blit(windowSizes[0].width / 2 - 20, windowSizes[1].height / 2 - 40 + 5 * 8, Art.sprites[selectedCharacter * 2][1], false, false, 1.0f, 5.0f);
		
		String name = names[selectedCharacter];
		Font.renderString(b1, windowSizes[0].width / 2 - name.length() * 4, windowSizes[1].height / 2 - 40 - 5 * 8 + 16, name, 0xFF0000);
		
		String title = "Choose your character";
		Font.renderString(b1, windowSizes[0].width / 2 - title.length() * 4 + 4, windowSizes[1].height / 2 - 80 + 4, title, 0x410000);
		Font.renderString(b1, windowSizes[0].width / 2 - title.length() * 4, windowSizes[1].height / 2 - 80, title, 0xFF0000);
	}

	public void tick() {
		if (input.keyStream[KeyEvent.VK_ENTER])
			mc.changegameState(new GameStatePlaying(mc, input, windowSizes,selectedCharacter));
		
		if (input.keyStream[KeyEvent.VK_RIGHT])
			selectedCharacter += 1;
		if (input.keyStream[KeyEvent.VK_LEFT])
			selectedCharacter -= 1;
		
		if (selectedCharacter < 0)
			selectedCharacter = 5;
		if (selectedCharacter > 5)
			selectedCharacter = 0;
	}
	
}