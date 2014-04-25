package ludumDare;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ludumDare.game.GameState;
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

	public boolean running = false;

	private boolean debugMode = true;
	
	private GameState gs;
	
	private InputHandler input;

	public static void main(String args[]) {
		Art.init();
		new MainComponent().start();
	}

	private static Dimension screenSize(int i) {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getScreenDevices();
		int height;
		int width;
		Dimension dim;

		DisplayMode dm = gd[i].getDisplayMode();
		width = dm.getWidth();
		height = dm.getHeight();
		dim = new Dimension(width, height);

		return dim;
	}

	public MainComponent() {
		input = new InputHandler();
		gs = new GameStatePlaying(this, input);
		for (int i = 0; i < 2; i++) {
			canvas[i] = new Canvas();
			
			Dimension d = screenSize(i);

			if (!debugMode) {
				canvas[i].setMinimumSize(d);
				canvas[i].setMaximumSize(d);
				canvas[i].setPreferredSize(d);
			} else {
				canvas[i].setMinimumSize(new Dimension(635, 405));
				canvas[i].setMaximumSize(new Dimension(635, 405));
				canvas[i].setPreferredSize(new Dimension(635, 405));
			}

			frame[i] = new JFrame(NAME + ": Window " + (i + 1));
			// to close the frames, use Alt+F4
//			if (!debugMode)
				frame[i].setUndecorated(true);
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());

			frame[i].add(canvas[i], BorderLayout.CENTER);
			frame[i].pack();

			frame[i].setResizable(true);
			frame[i].setLocationRelativeTo(null);

//			if (!debugMode)
//				image[i] = new BufferedImage(screenSize(i).width / SCALE, screenSize(i).height / SCALE, BufferedImage.TYPE_INT_RGB);
//			else
				image[i] = new BufferedImage((int) (635.0f / SCALE), (int) (405.0f / SCALE), BufferedImage.TYPE_INT_RGB);

			bitmap[i] = new Bitmap(image[i]);

			if (!debugMode) {
				if (i == 1) {
					frame[i].setLocation((int) d.getWidth(), 0);
				} else {
					frame[i].setLocation(0, 0);
				}
			} else {
				if (i == 1) {
					frame[i].setLocation(d.width / 2 + 5, d.height / 2 - 405 / 2);
				} else {
					frame[i].setLocation(d.width / 2 - 5 - 635, d.height / 2 - 405 / 2);
				}
			}

			frame[i].setVisible(true);
		}
		canvas[0].addKeyListener(input);
		canvas[0].requestFocus();
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
}