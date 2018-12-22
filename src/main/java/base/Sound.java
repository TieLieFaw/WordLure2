package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * The Sound class provides facilities for playing audio files in the .mp3 format
 * 
 * @author Kirill(github.com/TieLieFaw)
 * 
 */
public class Sound {
	
	private AdvancedPlayer ap;
	private String soundPath;
	private volatile boolean flag = false;
	
	public Sound(String soundPath) {
		this.soundPath = soundPath;
	}
	
	
	public void play() {
		
		Thread playThread = new Thread(() ->  {
			flag = true;
			try {
				InputStream is = new FileInputStream(new File(soundPath).toString());
				AudioDevice device = new JavaSoundAudioDevice();
				ap = new AdvancedPlayer(is,device);
				ap.play();
			} catch (FileNotFoundException e) {
				return;
			} catch (JavaLayerException e) {
				return;
			} finally {
				ap.close();
				flag = false;
			}
		});
		
		if(!flag) {
			playThread.setDaemon(true);
			playThread.start();
		}

	}

}