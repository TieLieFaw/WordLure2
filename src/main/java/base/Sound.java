package base;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Sound class provides facilities for playing audio files in the .wav format
 * 
 * @author Kirill(github.com/TieLieFaw)
 * 
 */
public class Sound implements AutoCloseable {
	private boolean released = false;
	private boolean playing = false;
	private AudioInputStream stream = null;
	private Clip clip = null;
	
	public Sound(String soundPath) {
		try {
			stream = AudioSystem.getAudioInputStream(new File(soundPath));
			clip = AudioSystem.getClip();
			clip.open(stream);
			released = true;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			released = false;
			close();
		}
	}
	

	public void play() {
		if(released) {
			if(!playing) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else {
				clip.stop();
				clip.setFramePosition(0);
				playing = false;
			}	
		}
		clip.start();
	}
	
	@Override
	public void close() {
		if (clip != null)
			clip.close();
		
		if (stream != null)
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
