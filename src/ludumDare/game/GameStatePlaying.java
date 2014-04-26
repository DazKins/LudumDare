package ludumDare.game;

import java.awt.event.KeyEvent;

import ludumDare.MainComponent;
import ludumDare.game.entity.AcitvateableSwitch;
import ludumDare.game.entity.ActivateableEntity;
import ludumDare.game.entity.Button;
import ludumDare.game.entity.Door;
import ludumDare.game.entity.Entity;
import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.gfx.Font;
import ludumDare.input.InputHandler;
import ludumDare.math.AABB;

public class GameStatePlaying extends GameState {
	private Level l1;
	private Level l2;
	
	private Door testDoor;
	
	public GameStatePlaying(MainComponent m, InputHandler i) {
		super(m, i);
		l1 = new Level(64, 64);
		l2 = new Level(64, 64);
		
		testDoor = new Door(48, 112);
		l2.addEntity(testDoor);

		l1.addEntity(new Button(0, 120, testDoor));
		l1.addEntity(new Player(input, 50, 50, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A));
		l2.addEntity(new Player(input, 50, 50, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));
		l1.registerSecondaryLevel(l2);
		l2.registerSecondaryLevel(l1);
	}
	
	public void render(Bitmap b1, Bitmap b2) {
		l1.render(b1);
		l2.render(b2);
		
		Font.renderString(b1, 0, 0, "test123//;;-+");
	}

	public void tick() {
		l1.tick();
		l2.tick();
	}
}