package org.flarengine.test;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import org.flarengine.Game;
import org.flarengine.input.bind.Keybind;

public class Minicraft extends Game
{
	private Keybind escapeKey = new Keybind(KeyEvent.VK_ESCAPE);

	public Minicraft()
	{
		super("Minicraft");
	}

	@Override
	public void update()
	{
		if (this.keyboard.isKeyDown(escapeKey))
		{
			this.hide();
			this.stop();
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
