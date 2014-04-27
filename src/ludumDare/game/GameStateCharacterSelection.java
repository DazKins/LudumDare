package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import ludumDare.MainComponent;
import ludumDare.audio.Audio;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;

public class GameStateCharacterSelection extends GameState {
	private int selectedCharacter1;
	private int selectedCharacter2;
	Audio blip = new Audio("/menublip.wav");
	Audio select = new Audio("/menuselect.wav");
	public String names[] = {"Steve", "Jim", "Zombie", "Tyrone", "Lego Man", "Notch", "Miner", "Lara"};

	public GameStateCharacterSelection(MainComponent m, InputHandler i, Dimension[] ws) {
		super(m, i, ws);
	}

	public void render(Bitmap b1, Bitmap b2) {
		for (int i = 0; i < 4; i++) {
			b1.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
			b2.blit(i * 128, 0, Art.background, false, false, 1.0f, 1.0f);
		}
		
		b1.blit(windowSizes[0].width / 2 - 20, windowSizes[0].height / 2 - 40, Art.sprites[selectedCharacter1 * 2][0], false, false, 1.0f, 5.0f);
		b1.blit(windowSizes[0].width / 2 - 20, windowSizes[0].height / 2 - 40 + 5 * 8, Art.sprites[selectedCharacter1 * 2][1], false, false, 1.0f, 5.0f);

		b2.blit(windowSizes[1].width / 2 - 20, windowSizes[1].height / 2 - 40, Art.sprites[selectedCharacter2 * 2][0], false, false, 1.0f, 5.0f);
		b2.blit(windowSizes[1].width / 2 - 20, windowSizes[1].height / 2 - 40 + 5 * 8, Art.sprites[selectedCharacter2 * 2][1], false, false, 1.0f, 5.0f);
		
		String name1 = names[selectedCharacter1];
		Font.renderString(b1, windowSizes[0].width / 2 - name1.length() * 4, windowSizes[1].height / 2 - 40 - 5 * 8 + 16, name1, 0xFF0000);
		String name2 = names[selectedCharacter2];
		Font.renderString(b2, windowSizes[0].width / 2 - name2.length() * 4, windowSizes[1].height / 2 - 40 - 5 * 8 + 16, name2, 0xFF0000);
		
		String title = "Choose your character";
		Font.renderString(b1, windowSizes[0].width / 2 - title.length() * 4 + 4, windowSizes[0].height / 2 - 80 + 4, title, 0x410000);
		Font.renderString(b1, windowSizes[0].width / 2 - title.length() * 4, windowSizes[0].height / 2 - 80, title, 0xFF0000);
		Font.renderString(b2, windowSizes[1].width / 2 - title.length() * 4 + 4, windowSizes[1].height / 2 - 80 + 4, title, 0x410000);
		Font.renderString(b2, windowSizes[1].width / 2 - title.length() * 4, windowSizes[1].height / 2 - 80, title, 0xFF0000);
	}

	public void tick() {
		if (input.keyStream[KeyEvent.VK_ENTER]){
			mc.changegameState(new GameStatePlaying(mc, input, windowSizes, selectedCharacter1, selectedCharacter2));
			select.play(true);
		}

		if (input.keyStream[KeyEvent.VK_D]){
			selectedCharacter1 += 1;
			blip.play(true);
		}
		if (input.keyStream[KeyEvent.VK_A]){
			selectedCharacter1 -= 1;
			blip.play(true);
		}
		
		if (input.keyStream[KeyEvent.VK_RIGHT]){
			selectedCharacter2 += 1;
			blip.play(true);
		}
		if (input.keyStream[KeyEvent.VK_LEFT]){
			selectedCharacter2 -= 1;
			blip.play(true);
		}
		if (input.keyStream[KeyEvent.VK_ESCAPE]){
			mc.changegameState(new GameStateMenu(mc, input, windowSizes));
		}
		
		if (selectedCharacter2 < 0)
			selectedCharacter2 = 7;
		if (selectedCharacter2 > 7)
			selectedCharacter2 = 0;
		
		if (selectedCharacter1 < 0)
			selectedCharacter1 = 7;
		if (selectedCharacter1 > 7)
			selectedCharacter1 = 0;
	}
	
}