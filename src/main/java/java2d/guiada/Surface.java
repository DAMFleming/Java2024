package java2d.guiada;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Surface extends Canvas {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean paused;
	private ArrayList<Ball> balls = new ArrayList<>();
	private BufferStrategy bufferStrategy;
	
	public Surface(int w, int h) {
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
	}

	private void run() {
		for (int i=0; i<100; i++)
			balls.add(new Ball(this));
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		long t0 = System.nanoTime(), t1;
		while (!Thread.currentThread().isInterrupted()) {
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
	}

	public void start() {
		t = new Thread(this::run);
		t.start();
	}

	public void stop() {
		t.interrupt();
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
			paint(g);
			if (!bufferStrategy.contentsLost())
				bufferStrategy.show();
			Toolkit.getDefaultToolkit().sync();
		} finally {
			if (g != null)
				g.dispose();
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
//		balls.forEach(ball -> ball.paint(g2d));
		for (Ball b: balls) {
			b.paint(g2d);
		}
	}
}
