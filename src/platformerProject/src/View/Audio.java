package View;

import javax.sound.sampled.*;
import javax.sound.sampled.Clip;

public class Audio {
	
	private Clip musique;
	
	public Audio(String zik) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(zik));
			musique = AudioSystem.getClip();
			musique.open(audio);
		} catch (Exception e) {}
	}
	
	public Clip getClip() {
		return musique;
	}
	
	public void play() {musique.start();}
	public void stop() {musique.stop();}
	public static void playSound(String son) {
		Audio s = new Audio(son);
		s.play();
	}
	
}