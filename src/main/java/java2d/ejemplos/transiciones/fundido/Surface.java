package java2d.ejemplos.transiciones.fundido;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java2d.ejemplos.caballero2.timer.Lienzo;

public class Surface extends Canvas {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean paused;
	
	private static final BufferedImage mario;
	private static final BufferedImage pacman;
	static {
		try {
			mario = ImageIO.read(Lienzo.class.getResourceAsStream("/fondos/mario.jpg"));
			pacman = ImageIO.read(Lienzo.class.getResourceAsStream("/fondos/pacman.jpg"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private BufferedImage img;
	private BufferedImage in;
	private BufferStrategy bufferStrategy;
	private double nanos = 1000000000d;
	private float alpha = 1;
	
	public Surface(int w, int h) {
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		img = pacman;
		in = mario;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isPaused())
					resume();
			}
		});
	}

	private void run() {
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		long t0 = System.nanoTime(), t1;
		while (!Thread.currentThread().isInterrupted()) {
			synchronized (this) {
				if (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
						break;
					}
					t0 = System.nanoTime();
				}
			}
			boolean end = next((t1 = System.nanoTime()) - t0);
			drawFrame();
			if (end)
				pause();
			t0 = t1;
		}
	}

	public void start(boolean paused) {
		this.paused = paused;
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

	private boolean next(long lapse) {
		alpha -= lapse / nanos;
		if (alpha <= 0) {
			alpha = 1;
			BufferedImage aux = img;
			img = in;
			in = aux;
		}
		return alpha == 1;
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
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite composite = (AlphaComposite) g2d.getComposite();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(img, 0, 0, this);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
		g2d.drawImage(in, 0, 0, this);
		g2d.setComposite(composite);
	}
}
