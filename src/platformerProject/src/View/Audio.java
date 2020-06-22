package View;

import javax.sound.sampled.*;
import javax.sound.sampled.Clip;

public class Audio {
	
	private Clip musique;
	public static Boolean joue;
	
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
	public void loop() {musique.loop(1);}
	
	
}