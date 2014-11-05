package org.flarengine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import org.flarengine.input.bind.Mousebind;

public final class Mouse extends MouseAdapter
{
	private final Map<Integer, Boolean> map;
	private int x, y;

	public Mouse()
	{
		this.map = new HashMap<Integer, Boolean>();
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		super.mouseMoved(event);
		this.x = event.getX();
		this.y = event.getY();
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		super.mousePressed(event);
		this.map.put(event.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		super.mouseReleased(event);
		this.map.put(event.getButton(), false);
	}

	public boolean isKeyDown(Mousebind mousebind)
	{
		int keyCode = mousebind.getKeyCode();
		if (this.map.containsKey(keyCode))
		{
			return this.map.get(keyCode);
		} else
		{
			this.map.put(keyCode, false);
			return false;
		}
	}

	public boolean isKeyUp(Mousebind mousebind)
	{
		int keyCode = mousebind.getKeyCode();
		if (this.map.containsKey(keyCode))
		{
			return this.map.get(keyCode);
		} else
		{
			this.map.put(keyCode, false);
			return false;
		}
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}
}
