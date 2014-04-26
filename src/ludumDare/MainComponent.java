package ludumDare;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ludumDare.game.GameState;
import ludumDare.game.GameStateMenu;
import ludumDare.game.GameStatePlaying;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;

public class MainComponent implements Runnable {
	private static int SCALE = 2;
	private static final String NAME = "Game";

	private Canvas[] canvas = new Canvas[2];
	private JFrame[] frame = new JFrame[2];
	private BufferedImage[] image = new BufferedImage[2];
	private Bitmap[] bitmap = new Bitmap[2];
	
	private Dimension windowPixelSizes[] = new Dimension[2];

	public boolean running = false;

	private boolean debugMode = true;
	
	private GameState gs;
	
	private InputHandler input;

	public static void main(String args[]) {
		Art.init();
		new MainComponent().start();
	}

	private static Dimension screenSize(int i) {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		int height;
		int width;
		Dimension dim;

		DisplayMode dm = gd[i].getDisplayMode();
		width = dm.getWidth();
		height = dm.getHeight();
		dim = new Dimension(width, height);

		return dim;
	}
	
	private static int getNumberOfMonitors() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		return gd.length;
	}

	public MainComponent() {
//		if (getNumberOfMonitors() >= 2)
//			debugMode = false;
//		else
//			debugMode = true;
		input = new InputHandler();
		for (int i = 0; i < 2; i++) {
			canvas[i] = new Canvas();
				
			Dimension d = screenSize(i);
			
			if (debugMode)
				d = screenSize(0);
			else
				d = screenSize(i);

			if (!debugMode) {
				canvas[i].setMinimumSize(d);
				canvas[i].setMaximumSize(d);
				canvas[i].setPreferredSize(d);
			} else {
				int w = (d.width - 10) / 2;
				int h = (int) (w * ((float) d.height / (float) d.width));
				canvas[i].setMinimumSize(new Dimension(w, h));
				canvas[i].setMaximumSize(new Dimension(w, h));
				canvas[i].setPreferredSize(new Dimension(w, h));
			}

			frame[i] = new JFrame(NAME + ": Window " + (i + 1));
			frame[i].setUndecorated(true);
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());

			frame[i].add(canvas[i], BorderLayout.CENTER);
			frame[i].pack();

			frame[i].setResizable(true);
			frame[i].setLocationRelativeTo(null);
			
			image[i] = new BufferedImage((int) ((d.getWidth() / d.getHeight() * 405.0f) / SCALE), (int) (405.0f / SCALE), BufferedImage.TYPE_INT_RGB);
			windowPixelSizes[i] = new Dimension(image[i].getWidth(), image[i].getHeight());

			bitmap[i] = new Bitmap(image[i]);

			if (!debugMode) {
				if (i == 1) {
					frame[i].setLocation((int) screenSize(i-1).getWidth(), 0);
				} else {
					frame[i].setLocation(0, 0);
				}
			} else {
				int w = (d.width - 10) / 2;
				int h = (int) (w * ((float) d.height / (float) d.width));
				if (i == 1) {
					frame[i].setLocation(d.width / 2 + 5, d.height / 2 - h / 2);
				} else {
					frame[i].setLocation(d.width / 2 - 5 - w, d.height / 2 - h / 2);
				}
			}

			frame[i].setVisible(true);
		}
		canvas[0].addKeyListener(input);
		canvas[1].addKeyListener(input);
		canvas[0].requestFocus();

		gs = new GameStateMenu(this, input, windowPixelSizes);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();

	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		double limit = 60D;
		long lastTime = System.nanoTime();
		
		double nsPerTick = 1000000000D/limit;

		int frames = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
			}

			frames++;
			render();

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("FPS: " + frames + ", UPS: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		gs.tick();
		input.tick();
	}

	public void render() {
		for (int i = 0; i < 2; i++) {
			bitmap[i].clear();
		}
		gs.render(bitmap[0], bitmap[1]);
		for (int i = 0; i < 2; i++) {
			Canvas cCanvas = canvas[i];
			BufferStrategy bs = cCanvas.getBufferStrategy();

			if (bs == null) {
				cCanvas.createBufferStrategy(3);
				continue;
			}

			Graphics g = bs.getDrawGraphics();

			g.drawImage(image[i], 0, 0, cCanvas.getWidth(), cCanvas.getHeight(), null);

			g.dispose();
			bs.show();
		}
	}
	
	public void changegameState(GameState gs) {
		this.gs = gs;
	}
}