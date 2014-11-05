package org.flarengine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import org.flarengine.audio.loader.AudioLoader;
import org.flarengine.input.Keyboard;
import org.flarengine.input.Mouse;

public abstract class Game implements Runnable
{
	protected String title;
	protected double width, height;
	protected boolean inited, running;
	protected double framerate;
	protected boolean debug;
	protected boolean fullscreen;
	protected final Thread thread;
	protected final Frame frame;
	protected ImageIcon icon;
	protected final Canvas canvas;
	protected final Keyboard keyboard;
	protected final Mouse mouse;
	protected final GraphicsDevice device;
	protected final Toolkit toolkit;
	protected final AudioLoader audioLoader;

	public Game(String title)
	{
		this.thread = new Thread(this, title);
		this.frame = new Frame(title);
		this.icon = new ImageIcon("src/main/resources/icon.png");
		this.canvas = new Canvas();
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
		this.device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		this.toolkit = Toolkit.getDefaultToolkit();
		this.audioLoader = new AudioLoader(this);
		this.title = title;
		this.width = this.toolkit.getScreenSize().getWidth() / 2;
		this.height = this.toolkit.getScreenSize().getHeight() / 2;
		this.inited = false;
		this.running = false;
		this.debug = false;
		this.fullscreen = false;
		this.framerate = 60.0d;
	}

	public final void init()
	{
		if (this.inited)
		{
			return;
		}
		if (this.fullscreen)
		{
			this.frame.setIconImage(this.icon.getImage());
			this.frame.setUndecorated(true);
			this.device.setFullScreenWindow(this.frame);
			this.canvas.setMaximumSize(new Dimension((int) this.toolkit.getScreenSize().getWidth(), (int) this.toolkit.getScreenSize().getHeight()));
			this.canvas.setMinimumSize(new Dimension((int) this.toolkit.getScreenSize().getWidth(), (int) this.toolkit.getScreenSize().getHeight()));
			this.canvas.setPreferredSize(new Dimension((int) this.toolkit.getScreenSize().getWidth(), (int) this.toolkit.getScreenSize().getHeight()));
			this.frame.add(this.canvas);
		} else
		{
			this.canvas.setMaximumSize(new Dimension((int) this.width, (int) this.height));
			this.canvas.setMinimumSize(new Dimension((int) this.width, (int) this.height));
			this.canvas.setPreferredSize(new Dimension((int) this.width, (int) this.height));
			this.frame.setIconImage(this.icon.getImage());
			this.frame.add(this.canvas);
			this.frame.pack();
			this.frame.setResizable(false);
			this.frame.setLocationRelativeTo(null);
			this.frame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent event)
				{
					Game.this.exit(0);
				}
			});
		}
		this.canvas.addKeyListener(this.keyboard);
		this.canvas.addMouseListener(this.mouse);
		this.canvas.addMouseMotionListener(this.mouse);
		this.canvas.addMouseWheelListener(this.mouse);
		this.canvas.setFocusable(true);
		this.inited = true;
	}

	public final void start()
	{
		if (!(this.inited))
		{
			return;
		}
		if (this.running)
		{
			return;
		}
		this.running = true;
		this.thread.start();
	}

	public final void stop()
	{
		if (!(this.running) || (!(this.inited)))
		{
			return;
		}
		this.running = false;
		this.thread.interrupt();
	}

	public final void run()
	{
		this.show();
		long startTime = System.nanoTime();
		double nanosecondsPerTick = 1000000000D / this.framerate;
		int updateCount = 0;
		int frameCount = 0;
		long timer = System.currentTimeMillis();
		double delta = 0.0D;
		while (this.running)
		{
			long currentTime = System.nanoTime();
			delta += (currentTime - startTime) / nanosecondsPerTick;
			startTime = currentTime;
			boolean shouldRender = false;
			while (delta >= 1)
			{
				updateCount++;
				this.update();
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender)
			{
				frameCount++;
				this.render();
				if ((System.currentTimeMillis() - timer) >= 1000)
				{
					timer += 1000;
					if (this.debug)
					{
						this.frame.setTitle(this.title + " [FPS=" + frameCount + "][UPS=" + updateCount + "]");
					}
					frameCount = 0;
					updateCount = 0;
				}
			}
		}
	}

	public final void exit(int code)
	{
		this.hide();
		this.stop();
		System.exit(code);
	}

	public final void show()
	{
		this.frame.setVisible(true);
	}

	public final void hide()
	{
		this.frame.setVisible(false);
	}

	public abstract void update();

	public abstract void render();

	public String getTitle()
	{
		return this.title;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

	public boolean isInited()
	{
		return this.inited;
	}

	public boolean isRunning()
	{
		return this.running;
	}

	public double getFramerate()
	{
		return this.framerate;
	}

	public boolean isDebug()
	{
		return this.debug;
	}

	public boolean isFullscreen()
	{
		return this.fullscreen;
	}

	public Thread getThread()
	{
		return this.thread;
	}

	public Frame getFrame()
	{
		return this.frame;
	}

	public ImageIcon getIcon()
	{
		return this.icon;
	}

	public Canvas getCanvas()
	{
		return this.canvas;
	}

	public Keyboard getKeyboard()
	{
		return this.keyboard;
	}

	public Mouse getMouse()
	{
		return this.mouse;
	}

	public GraphicsDevice getDevice()
	{
		return this.device;
	}

	public Toolkit getToolkit()
	{
		return this.toolkit;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public void setFramerate(double framerate)
	{
		this.framerate = framerate;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}

	public void setFullscreen(boolean fullscreen)
	{
		this.fullscreen = fullscreen;
	}

	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}
}
