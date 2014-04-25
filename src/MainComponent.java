import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class MainComponent {
	public static void main(String args[]) {
		System.out.println("test");
	}

	public static Dimension[] ScreenSize() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		Dimension[] ss = new Dimension[2];
		int height;
		int width;
		Dimension dim;
		for (int i = 0; i < 2; i++) {
			DisplayMode dm = gd[i].getDisplayMode();
			width = dm.getWidth();
			height = dm.getHeight();
			dim = new Dimension(width, height);
			ss[i] = dim;
		}
		return ss;

	}

}