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
	
	private int sp1;
	private int sp2;
	
	private int cLevel = 1;
	
	public GameStatePlaying(MainComponent m, InputHandler i, Dimension[] ws, int cs1, int cs2) {
		super(m, i, ws);
		setLevel(cLevel);
		
		sp1 = cs1;
		sp2 = cs2;
		
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
		
		if (input.keyStream[KeyEvent.VK_R]) {
			System.out.println("hit");
			restart();
		}
	}
	
	public void restart() {
		setLevel(cLevel);
	}
	
	public void nextLevel() {
		setLevel(cLevel + 1);
	}
	
	public void setLevel(int level) {
		Level levels[] = Level.loadLevelsFromFile("/Level2", p1 = new Player(sp1, input, 50, 50, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_E), p2 = new Player(sp2, input, 50, 50, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_CONTROL));
		l1 = levels[0];
		l2 = levels[1];
		cLevel = level;
	}
}