package org.flarengine.audio.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.flarengine.Game;
import org.flarengine.audio.Audio;

public class AudioLoader
{
	private final Game game;
	private final Map<String, Audio> audioMap;

	public AudioLoader(Game game)
	{
		this.game = game;
		this.audioMap = new HashMap<String, Audio>();
	}

	public Audio load(String path)
	{
		try
		{
			return new Audio(path);
		} catch (LineUnavailableException e)
		{
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		this.game.exit(-2);
		return null;
	}

	public void set(String key, Audio value)
	{
		this.audioMap.put(key, value);
	}

	public Audio get(String key)
	{
		return this.audioMap.get(key);
	}

	public void play(String key)
	{
		this.audioMap.get(key).start();
	}

	public void stop(String key)
	{
		this.audioMap.get(key).stop();
	}

	public void reset(String key)
	{
		try
		{
			this.audioMap.get(key).reset();
		} catch (LineUnavailableException e)
		{
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Game getGame()
	{
		return this.game;
	}

	public Map<String, Audio> getAudioMap()
	{
		return this.audioMap;
	}
}
