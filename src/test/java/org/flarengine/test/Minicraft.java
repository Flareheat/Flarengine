package org.flarengine.test;

import javax.swing.ImageIcon;

import org.flarengine.Game;
import org.flarengine.input.Keyboard;

public class Minicraft extends Game
{
	public Minicraft()
	{
		super("Minicraft");
	}

	@Override
	public void update()
	{
		if (this.keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			this.exit(0);
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
