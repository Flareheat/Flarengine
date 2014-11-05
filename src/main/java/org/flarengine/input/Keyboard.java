package org.flarengine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.flarengine.input.bind.Keybind;

public final class Keyboard extends KeyAdapter
{
	private final Map<Integer, Boolean> INPUT_MAP_PRIMARY;
	private final Map<Integer, Boolean> INPUT_MAP_SECONDARY;

	public Keyboard()
	{
		this.INPUT_MAP_PRIMARY = new HashMap<Integer, Boolean>();
		this.INPUT_MAP_SECONDARY = new HashMap<Integer, Boolean>();
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		super.keyPressed(event);
		this.INPUT_MAP_PRIMARY.put(event.getKeyCode(), true);
		this.INPUT_MAP_SECONDARY.remove(event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		super.keyReleased(event);
		this.INPUT_MAP_SECONDARY.put(event.getKeyCode(), false);
		this.INPUT_MAP_PRIMARY.remove(event.getKeyCode());
	}

	public boolean isKeyDown(Keybind keybind)
	{
		int keyCode = keybind.getKeyCode();
		if (this.INPUT_MAP_PRIMARY.containsKey(keyCode))
		{
			return this.INPUT_MAP_PRIMARY.get(keyCode);
		} else
		{
			this.INPUT_MAP_PRIMARY.put(keyCode, false);
			return false;
		}
	}

	public boolean isKeyUp(Keybind keybind)
	{
		int keyCode = keybind.getKeyCode();
		if (this.INPUT_MAP_SECONDARY.containsKey(keyCode))
		{
			return this.INPUT_MAP_SECONDARY.get(keyCode);
		} else
		{
			this.INPUT_MAP_SECONDARY.put(keyCode, false);
			return false;
		}
	}
}
