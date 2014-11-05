package org.flarengine.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio
{
	private String path;
	private Clip clip;

	public Audio(String filename) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		File audioFile = new File(filename);
		if (audioFile.exists())
		{
			this.path = audioFile.getAbsolutePath();
			Mixer.Info[] infoArray = AudioSystem.getMixerInfo();
			Mixer mixer = AudioSystem.getMixer(infoArray[0]);
			DataLine.Info info = new DataLine.Info(Clip.class, null);
			this.clip = (Clip) mixer.getLine(info);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			this.clip.open(audioInputStream);
		} else
		{
			throw new FileNotFoundException(filename);
		}
	}

	public void start()
	{
		this.clip.start();
	}

	public void stop()
	{
		this.clip.stop();
	}

	public void reset() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		this.clip.close();
		this.clip = new Audio(this.path).getClip();
	}

	public String getPath()
	{
		return this.path;
	}

	public Clip getClip()
	{
		return this.clip;
	}
}
