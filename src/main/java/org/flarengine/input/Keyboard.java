package org.flarengine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.flarengine.input.bind.Keybind;

public final class Keyboard extends KeyAdapter
{
	public static final Keybind KEY_0 = new Keybind(KeyEvent.VK_0);
	public static final Keybind KEY_1 = new Keybind(KeyEvent.VK_1);
	public static final Keybind KEY_2 = new Keybind(KeyEvent.VK_2);
	public static final Keybind KEY_3 = new Keybind(KeyEvent.VK_3);
	public static final Keybind KEY_4 = new Keybind(KeyEvent.VK_4);
	public static final Keybind KEY_5 = new Keybind(KeyEvent.VK_5);
	public static final Keybind KEY_6 = new Keybind(KeyEvent.VK_6);
	public static final Keybind KEY_7 = new Keybind(KeyEvent.VK_7);
	public static final Keybind KEY_8 = new Keybind(KeyEvent.VK_8);
	public static final Keybind KEY_9 = new Keybind(KeyEvent.VK_9);
	public static final Keybind KEY_A = new Keybind(KeyEvent.VK_A);
	public static final Keybind KEY_B = new Keybind(KeyEvent.VK_B);
	public static final Keybind KEY_C = new Keybind(KeyEvent.VK_C);
	public static final Keybind KEY_CAPS_LOCK = new Keybind(KeyEvent.VK_CAPS_LOCK);
	public static final Keybind KEY_CTRL = new Keybind(KeyEvent.VK_CONTROL);
	public static final Keybind KEY_D = new Keybind(KeyEvent.VK_D);
	public static final Keybind KEY_DELETE = new Keybind(KeyEvent.VK_DELETE);
	public static final Keybind KEY_E = new Keybind(KeyEvent.VK_E);
	public static final Keybind KEY_ENTER = new Keybind(KeyEvent.VK_ENTER);
	public static final Keybind KEY_ESCAPE = new Keybind(KeyEvent.VK_ESCAPE);
	public static final Keybind KEY_F = new Keybind(KeyEvent.VK_F);
	public static final Keybind KEY_F1 = new Keybind(KeyEvent.VK_F1);
	public static final Keybind KEY_F10 = new Keybind(KeyEvent.VK_F10);
	public static final Keybind KEY_F11 = new Keybind(KeyEvent.VK_F11);
	public static final Keybind KEY_F12 = new Keybind(KeyEvent.VK_F12);
	public static final Keybind KEY_F2 = new Keybind(KeyEvent.VK_F2);
	public static final Keybind KEY_F3 = new Keybind(KeyEvent.VK_F3);
	public static final Keybind KEY_F4 = new Keybind(KeyEvent.VK_F4);
	public static final Keybind KEY_F5 = new Keybind(KeyEvent.VK_F5);
	public static final Keybind KEY_F6 = new Keybind(KeyEvent.VK_F6);
	public static final Keybind KEY_F7 = new Keybind(KeyEvent.VK_F7);
	public static final Keybind KEY_F8 = new Keybind(KeyEvent.VK_F8);
	public static final Keybind KEY_F9 = new Keybind(KeyEvent.VK_F9);
	public static final Keybind KEY_G = new Keybind(KeyEvent.VK_G);
	public static final Keybind KEY_H = new Keybind(KeyEvent.VK_H);
	public static final Keybind KEY_I = new Keybind(KeyEvent.VK_I);
	public static final Keybind KEY_INSERT = new Keybind(KeyEvent.VK_INSERT);
	public static final Keybind KEY_J = new Keybind(KeyEvent.VK_J);
	public static final Keybind KEY_K = new Keybind(KeyEvent.VK_K);
	public static final Keybind KEY_L = new Keybind(KeyEvent.VK_L);
	public static final Keybind KEY_M = new Keybind(KeyEvent.VK_M);
	public static final Keybind KEY_N = new Keybind(KeyEvent.VK_N);
	public static final Keybind KEY_O = new Keybind(KeyEvent.VK_O);
	public static final Keybind KEY_P = new Keybind(KeyEvent.VK_P);
	public static final Keybind KEY_Q = new Keybind(KeyEvent.VK_Q);
	public static final Keybind KEY_R = new Keybind(KeyEvent.VK_R);
	public static final Keybind KEY_S = new Keybind(KeyEvent.VK_S);
	public static final Keybind KEY_SHIFT = new Keybind(KeyEvent.VK_SHIFT);
	public static final Keybind KEY_SPACE = new Keybind(KeyEvent.VK_SPACE);
	public static final Keybind KEY_T = new Keybind(KeyEvent.VK_T);
	public static final Keybind KEY_TAB = new Keybind(KeyEvent.VK_TAB);
	public static final Keybind KEY_U = new Keybind(KeyEvent.VK_U);
	public static final Keybind KEY_V = new Keybind(KeyEvent.VK_V);
	public static final Keybind KEY_W = new Keybind(KeyEvent.VK_W);
	public static final Keybind KEY_X = new Keybind(KeyEvent.VK_X);
	public static final Keybind KEY_Y = new Keybind(KeyEvent.VK_Y);
	public static final Keybind KEY_Z = new Keybind(KeyEvent.VK_Z);
	private final Map<Integer, Boolean> pressed_input_map;
	private final Map<Integer, Boolean> released_input_map;

	public Keyboard()
	{
		this.pressed_input_map = new HashMap<Integer, Boolean>();
		this.released_input_map = new HashMap<Integer, Boolean>();
	}

	public boolean isKeyDown(Keybind keybind)
	{
		int keyCode = keybind.getKeyCode();
		if (this.pressed_input_map.containsKey(keyCode))
		{
			return this.pressed_input_map.get(keyCode);
		} else
		{
			this.pressed_input_map.put(keyCode, false);
			return false;
		}
	}

	public boolean isKeyUp(Keybind keybind)
	{
		int keyCode = keybind.getKeyCode();
		if (this.released_input_map.containsKey(keyCode))
		{
			return this.released_input_map.get(keyCode);
		} else
		{
			this.released_input_map.put(keyCode, false);
			return false;
		}
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		super.keyPressed(event);
		this.pressed_input_map.put(event.getKeyCode(), true);
		this.released_input_map.remove(event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		super.keyReleased(event);
		this.released_input_map.put(event.getKeyCode(), false);
		this.pressed_input_map.remove(event.getKeyCode());
	}
}
