package ludumDare.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	private Clip clip;
	
	public Audio(String path) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Audio.class.getResourceAsStream(path));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}