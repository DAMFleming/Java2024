package java2d.ejemplos.figuras;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Surface extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String MSG1 = "ESC: salir | G: iniciar giro pantalla | P: pausar/reiniciar";
	private final String MSG2 = "ESC: salir | G: parar giro pantalla | D: cambiar dirección de giro | P: pausar/reiniciar";
	private Thread t;
	private boolean paused;
	private ArrayList<Figura> figuras = new ArrayList<Figura>();
	private BufferStrategy bufferStrategy;
	private volatile int dir = 1;
	private double angulo = 0;
	private volatile boolean rotar = false;
	
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
	}

	public void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();
		GraphicsDevice device = devices.length == 2 ? devices[1] : ge.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		setFont(getFont().deriveFont(30f));
		for (int i=0; i < 100; i++)
			figuras.add(new Figura(getWidth(), getHeight()));
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
		SwingUtilities.invokeLater(this::dispose);
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
		if (rotar)
			angulo += lapse * 0.3d / 1000000000d * dir;
		for (Figura f: figuras)
			f.girar(lapse);
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
		AffineTransform at = g.getTransform();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
		g.rotate(angulo, getWidth() /2, getHeight() / 2);
		figuras.forEach(f -> f.paint(g));
		g.setTransform(at);
		FontMetrics fm = g.getFontMetrics();
		
		String msg = rotar ? MSG2 : MSG1;
		int w =  fm.stringWidth(msg);
		int x = (int) ((getWidth() - w) / 2);
		int y = getHeight() - fm.getDescent() - 20;
		g.setColor(Color.WHITE);
		g.fillRect(x - 10, y - fm.getAscent() - 10, w + 20, fm.getHeight() + 20);
		g.setColor(Color.BLACK);
		g.drawString(msg, x, y);
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				stop();
				break;
			case KeyEvent.VK_P:
				if (isPaused())
					resume();
				else
					pause();
				break;
			case KeyEvent.VK_G:
				rotar = !rotar;
				break;
			case KeyEvent.VK_D:
				dir *= -1;
				break;
			}
		}
	}
}
