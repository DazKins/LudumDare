package ludumDare.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import ludumDare.MainComponent;
import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class GameStatePlaying extends GameState {
	private Level l1;
	private Level l2;
	
	private float xOff1;
	
	private float xOff2;
	
	private Player p1;
	private Player p2;
	
	public GameStatePlaying(MainComponent m, InputHandler i, Dimension[] ws, int cs) {
		super(m, i, ws);
		Level levels[] = Level.loadLevelsFromFile("/testLevel");
		l1 = levels[0];
		l2 = levels[1];

		l1.addEntity(p1 = new Player(cs, input, 50, 50, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_E));
		l2.addEntity(p2 = new Player(cs, input, 50, 50, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_CONTROL));
		
		l1.registerSecondaryLevel(l2);
		l2.registerSecondaryLevel(l1);
		
		windowSizes = ws;
	}
	
	public void render(Bitmap b1, Bitmap b2) {
		l1.render(b1, xOff1, 0);
		l2.render(b2, xOff2, 0);
	}

	public void tick() {
		l1.tick();
		l2.tick();
		
		xOff1 = (float) (p1.getX() - windowSizes[0].getWidth() / 2);
		if (xOff1 + windowSizes[0].getWidth() > l1.getWidth() * 8)
			xOff1 = (float) (l1.getWidth() * 8 - windowSizes[0].getWidth());
		if (xOff1 < 0)
			xOff1 = 0;
		
		xOff2 = (float) (p2.getX() - windowSizes[1].getWidth() / 2);
		if (xOff2 + windowSizes[1].getWidth() > l2.getWidth() * 8)
			xOff2 = (float) ((l2.getWidth() * 8) - windowSizes[1].getWidth());
		if (xOff2 < 0)
			xOff2 = 0;
	}
}