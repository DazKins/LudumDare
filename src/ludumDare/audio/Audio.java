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

	public void play(final boolean start) {
		try {
			new Thread() {
				public void run() {
				if(start){					
					clip.stop();
					clip.setFramePosition(0);
					clip.start();
				}else{
					clip.stop();
				}
				}
			}.run();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void loopPlay(final boolean start) {
		try {
			new Thread() {
				public void run() {
					if (start == true) {
						clip.stop();
						clip.setFramePosition(0);
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
					clip.stop();
				}
			}.run();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void stopPlay(){
		clip.stop();
	}
}