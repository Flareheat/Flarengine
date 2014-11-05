package org.flarengine.test;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import org.flarengine.Game;
import org.flarengine.audio.Audio;
import org.flarengine.input.bind.Keybind;

public class Minicraft extends Game
{
	private final Keybind escapeKey = new Keybind(KeyEvent.VK_ESCAPE);
	private final Keybind spaceKey = new Keybind(KeyEvent.VK_SPACE);
	private final Audio audioTheme;

	public Minicraft()
	{
		super("Minicraft");
		this.audioTheme = this.audioLoader.load("src/test/resources/theme.wav");
		this.audioLoader.set("theme", this.audioTheme);
	}

	@Override
	public void update()
	{
		if (this.keyboard.isKeyDown(this.escapeKey))
		{
			this.exit(0);
		}
		if (this.keyboard.isKeyDown(this.spaceKey))
		{
			this.audioLoader.play("theme");
		}
	}

	@Override
	public void render()
	{

	}

	public static void main(String[] paramStringArray)
	{
		Minicraft minicraft = new Minicraft();
		minicraft.setIcon(new ImageIcon("src/test/resources/minicraft.png"));
		minicraft.init();
		minicraft.start();
	}
}
