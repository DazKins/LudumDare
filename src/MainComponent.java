import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class MainComponent implements Runnable {

	private static final String NAME = "Game";
	private Canvas[] canvas = new Canvas[2];
	private JFrame[] frame = new JFrame[2];

	public boolean running = false;

	public static void main(String args[]) {
		System.out.println("test");
		new MainComponent().start();

	}

	private static Dimension ScreenSize(int i) {
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

	public MainComponent() {
		for (int i = 0; i < 2; i++) {
			canvas[i] = new Canvas();
			canvas[i].setMinimumSize(ScreenSize(i));
			canvas[i].setMaximumSize(ScreenSize(i));
			canvas[i].setPreferredSize(ScreenSize(i));

			frame[i] = new JFrame(NAME + ": Window " + (i + 1));
			// to close the frames, use Alt+F4
			frame[i].setUndecorated(true);
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());

			frame[i].add(canvas[i], BorderLayout.CENTER);
			frame[i].pack();

			frame[i].setResizable(false);
			frame[i].setLocationRelativeTo(null);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gs = ge.getScreenDevices();
			gs[i].setFullScreenWindow(frame[i]);

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
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int frames = 0;
		int ticks = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			
			while (delta >= 1){
				ticks++;
				tick();
				delta -= 1;
				frames++;
				render();
			}
			
			
			if (System.currentTimeMillis() - lastTimer >= 1000){
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

	}
}