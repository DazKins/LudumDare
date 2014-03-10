import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
 
 
public class Component {
        public static void main(String args[]) {
                GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                for (int i = 0; i < gd.length; i++) {
                        DisplayMode dm = gd[i].getDisplayMode();
                        int w = dm.getWidth();
                        int h = dm.getHeight();
                        System.out.println("Display " + i);
                        System.out.println("WIDTH: " + w + " HEIGHT: " + h);
                }
        }
}
