
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class getScreenSize {
	public static Dimension ScreenSize() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		for (int i = 0; i < 2; i++) {
			DisplayMode dm = gd[i].getDisplayMode();
			Main.Width[i] = dm.getWidth();
			Main.Height[i] = dm.getHeight();
		}
	}
}
