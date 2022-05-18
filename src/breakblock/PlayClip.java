package breakblock;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayClip {

	Clip clip = null;
	
	PlayClip(String filename){
		try {
			AudioInputStream ais = 
					AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void play() {
		clip.start();
		System.out.println("Playing...");
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void reset() {
		clip.setFramePosition(0);
	}
	
	public void forward() {
		System.out.println(clip.getFrameLength() + ":"
				                + clip.getFramePosition());
		clip.setFramePosition((clip.getFramePosition()+10000));
	}
	
}
