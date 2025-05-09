package java2d.guiada.fullscreen;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.ImageCapabilities;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Surface extends JFrame {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean paused;
	private ArrayList<Ball> balls = new ArrayList<>();
	private BufferStrategy bufferStrategy;
	
	public Surface() {
		setUndecorated(true);
		setIgnoreRepaint(true);
		setBackground(Color.BLACK);
		addKeyListener(new MyKeyAdapter());
	}

	private void run() {
		long t0 = System.nanoTime(), t1;
		while (!Thread.interrupted()) {
			synchronized (this) {
				if (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					t0 = System.nanoTime();
				}
			}
			next((t1 = System.nanoTime()) - t0);
			drawFrame();
			t0 = t1;
		}
		SwingUtilities.invokeLater(this::dispose);
	}

	public void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();
		GraphicsDevice device = devices.length == 2 ? devices[1] : ge.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		ImageCapabilities ic = new ImageCapabilities(true);
//		try {
//			createBufferStrategy(2, new BufferCapabilities(ic, ic, BufferCapabilities.FlipContents.BACKGROUND));
//		} catch (AWTException e) {
//			throw new RuntimeException(e);
//		}
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		for (int i=0; i<50; i++)
			balls.add(new Ball(this));
		t = new Thread(this::run);
		t.start();
	}

	public void stop() {
		t.interrupt();
		if (t != Thread.currentThread())
			try {
				t.join();
			} catch (InterruptedException e) {
			}
	}

	public synchronized void pause() {
		paused = true;
	}

	public synchronized void resume() {
		if (paused) {
			paused = false;
			notify();
		}
	}

	public synchronized boolean isPaused() {
		return paused;
	}

	private void next(long lapse) {
		balls.forEach(ball -> ball.move(lapse));
	}

	private void drawFrame() {
		Graphics2D g = null;
		try {
			g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			draw(g);
			if (!bufferStrategy.contentsLost())
				bufferStrategy.show();
			Toolkit.getDefaultToolkit().sync();
		} finally {
			if (g != null)
				g.dispose();
		}
	}

	public void draw(Graphics2D g) {
		g.setBackground(getForeground());
		g.fillRect(0, 0, getWidth(), getHeight());
		balls.forEach(ball -> ball.paint(g));
		g.setColor(Color.WHITE);
		g.drawString("Pulsa ESC para salir", 30, 30);
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (isPaused())
					resume();
				else
					pause();
			}
			else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				stop();
		}
	}
}
