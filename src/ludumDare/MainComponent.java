package ludumDare;

import ludumDare.game.GameState;
import ludumDare.game.GameStatePlaying;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;

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

public class MainComponent implements Runnable {
	private static int SCALE = 3;
	private static final String NAME = "Game";

	private Canvas[] canvas = new Canvas[2];
	private JFrame[] frame = new JFrame[2];
	private BufferedImage[] image = new BufferedImage[2];
	private Bitmap[] bitmap = new Bitmap[2];

	public boolean running = false;

	private boolean debugMode = true;
	
	private GameState gs;

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
		gs = new GameStatePlaying();
		for (int i = 0; i < 2; i++) {
			canvas[i] = new Canvas();

			if (!debugMode) {
				canvas[i].setMinimumSize(screenSize(i));
				canvas[i].setMaximumSize(screenSize(i));
				canvas[i].setPreferredSize(screenSize(i));
			} else {
				canvas[i].setMinimumSize(new Dimension(480, 270));
				canvas[i].setMaximumSize(new Dimension(480, 270));
				canvas[i].setPreferredSize(new Dimension(480, 270));
			}

			frame[i] = new JFrame(NAME + ": Window " + (i + 1));
			// to close the frames, use Alt+F4
			if (!debugMode)
				frame[i].setUndecorated(true);
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());

			frame[i].add(canvas[i], BorderLayout.CENTER);
			frame[i].pack();

			frame[i].setResizable(false);
			frame[i].setLocationRelativeTo(null);

			if (!debugMode)
				image[i] = new BufferedImage(screenSize(i).width / SCALE,
						screenSize(i).height / SCALE,
						BufferedImage.TYPE_INT_RGB);
			else
				image[i] = new BufferedImage((int) (480.0f / SCALE), (int) (270.0f / SCALE), BufferedImage.TYPE_INT_RGB);

			bitmap[i] = new Bitmap(image[i]);

			if (!debugMode) {
				GraphicsEnvironment ge = GraphicsEnvironment
						.getLocalGraphicsEnvironment();
				GraphicsDevice[] gs = ge.getScreenDevices();
				gs[i].setFullScreenWindow(frame[i]);
			} else {
				if (i == 1) {
					frame[i].setLocation(480, 0);
				} else {
					frame[i].setLocation(0, 0);
				}
			}

			frame[i].setVisible(true);
		}
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

	}

	public void render() {
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