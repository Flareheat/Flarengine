package org.flarengine.input.bind;

public class Keybind
{
	private final int keyCode;

	public Keybind(int keyCode)
	{
		this.keyCode = keyCode;
	}

	public final int getKeyCode()
	{
		return this.keyCode;
	}
}
