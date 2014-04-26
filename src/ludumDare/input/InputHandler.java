package ludumDare.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	public boolean keys[] = new boolean[65536];
	public boolean keyStream[] = new boolean[65536];
	
	private boolean isKeyUp[] = new boolean[65536];

	public InputHandler() {
		for (int i = 0; i < isKeyUp.length; i++) {
			isKeyUp[i] = true;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		if (isKeyUp[e.getKeyCode()]) {
			keyStream[e.getKeyCode()] = true;
		}
		isKeyUp[e.getKeyCode()] = false;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		isKeyUp[e.getKeyCode()] = true;
	}

	public void keyTyped(KeyEvent e) { }
	
	public void tick() {
		for (int i = 0; i < 65535; i++) {
			keyStream[i] = false;
		}
	}
}