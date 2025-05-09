package java2d.guiada.fullscreen;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	private Surface surface;
	private int size;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private Color color;
	
	public Ball(Surface surface) {
		this.surface = surface;
		int dim = Math.min(surface.getWidth(), surface.getHeight());
		int minSize = (int) (dim * 0.05f);
		int maxSize = (int) (dim * 0.6f);
		size = (int) (Math.random() * (maxSize - minSize) + minSize);
		x = (surface.getWidth() - size) / 2;
		y = (surface.getHeight() - size) / 2;
		double speed = Math.random() * 450 + 50;
		double direction = Math.random() * 2 * Math.PI;
		vx = Math.cos(direction) * speed;
		vy = Math.sin(direction) * speed;
		color = new Color(
				(float) Math.random(),
				(float) Math.random(),
				(float) Math.random(),
				1);
	}
	
	public Ball(Surface surface, double x, double y, int size, double direction, double speed, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		vx = Math.cos(direction) * speed;
		vy = Math.sin(direction) * speed;
		this.surface = surface;
		this.color = color;
	}

	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, (int) size, size);
	}

	public void move(long lapse) {
		x += (lapse * vx) / 1000000000L;
		y += (lapse * vy) / 1000000000L;
		if (x + size >= surface.getWidth()) {
			x = 2 * surface.getWidth() - x - 2 * size;
			vx *= -1;
		} else if (x < 0) {
			x = -x;
			vx *= -1;
		} else if (y + size >= surface.getHeight()) {
			y = 2 * surface.getHeight() - y - 2 * size;
			vy *= -1;
		} else if (y < 0) {
			y = -y;
			vy *= -1;
		}
	}
}
