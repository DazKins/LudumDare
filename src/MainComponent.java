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

	public static void main(String args[]) {
		System.out.println("test");
		new MainComponent().start();

	}

	private void start() {

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

			frame[i] = new JFrame(NAME + ": Window " + i);
			frame[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame[i].setLayout(new BorderLayout());

			frame[i].add(canvas[i], BorderLayout.CENTER);
			frame[i].pack();

			frame[i].setResizable(false);
			frame[i].setLocationRelativeTo(null);
			frame[i].setVisible(true);

		}
	}

	public void run() {

	}

}