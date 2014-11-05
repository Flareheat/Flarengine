package org.flarengine.input.bind;

public class Mousebind
{
	private final int keyCode;

	public Mousebind(int keyCode)
	{
		this.keyCode = keyCode;
	}

	public final int getKeyCode()
	{
		return this.keyCode;
	}
}
