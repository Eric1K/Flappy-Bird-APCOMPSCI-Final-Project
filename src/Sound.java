import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	static File wing = (new File("res/audio/wing.wav"));
	static File hit = (new File("res/audio/hit.wav"));
	static File die = (new File("res/audio/die.wav"));
	static File swooshdelay = (new File("res/audio/swooshdelay.wav"));
	static File swoosh = new File("res/audio/swoosh.wav");
	static File point = new File("res/audio/point.wav");
	public static void playSound(File sound)
	{
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			try {
				clip.open(AudioSystem.getAudioInputStream(sound));
			} catch (IOException e) {
				Game.showError(e.getMessage());
			} catch (UnsupportedAudioFileException e) {
				Game.showError(e.getMessage());
			}
			clip.start();
						
		}catch (LineUnavailableException e) {
			Game.showError(e.getMessage());
		}
	}
}
