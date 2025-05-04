package java2d.ejemplos.caballero.e2.renderingactivo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
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

public class Surface extends Canvas {
	private static final long serialVersionUID = 1L;
	private Thread t;
	private boolean paused;
	
	private static final BufferedImage img;
	private static final Font FUENTE;
	static {
		try {
			img = ImageIO.read(Surface.class.getResourceAsStream("/caballero/Idle (1).png"));
			FUENTE = Font.createFont(Font.PLAIN, Surface.class.getResourceAsStream("/fuentes/Heraldic Shadows.otf"));
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
	}
	private static final String INSTRUCCIONES = "Haz clic en el caballero";
	private BufferStrategy bufferStrategy;
	private double angulo = 0;
	private double escala = 1;
	private double x;
	private double y;
	private double xs;
	private double ys;
	private double cx;
	private double cy;
	private double dir = -1;
	private double nanos = 3000000000d;
	private double radians = 4 * Math.PI;
	
	public Surface(int w, int h) {
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.WHITE);
		setFont(FUENTE.deriveFont(70f));
		x = xs = (w - img.getWidth()) / 2d;
		y = ys = (h - img.getHeight()) / 2d;
		cx = x + (img.getWidth() / 2d);
		cy = y + (img.getHeight() / 2d);
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
		angulo += lapse * radians / nanos * -dir;
		escala += lapse / nanos * dir;
		if (escala <= 0) {
			escala = -escala;
			if (angulo > radians)
				angulo = radians - (angulo - radians);
			dir *= -1;
		}
		else if (escala > 1) {
			escala = 1;
			angulo = 0;
			dir *= -1;
		}
		double f1 = 1 - escala;
		double f2 = 2 * escala;
//		xs = (x + ((img.getWidth() - (img.getWidth() * escala)) / 2)) / escala;
//		ys = (y + ((img.getHeight() - (img.getHeight() * escala)) / 2)) / escala;
		xs = ((2 * x) + img.getWidth() * f1) / f2;
		ys = ((2 * y) + img.getHeight() * f1) / f2;
		return escala == 1;
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
		FontMetrics fm = g.getFontMetrics();
		g.drawString(INSTRUCCIONES, (getWidth() - fm.stringWidth(INSTRUCCIONES)) / 2, 100);
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angulo, cx, cy);
		g2d.scale(escala, escala);
		g2d.drawImage(img, (int) xs, (int) ys, this);
	}
}
