package java2d.ejemplos.figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Figura {
	
	private static Stroke LINEACONTINUA = new BasicStroke(3);
	private static Random r = new Random(System.nanoTime());
	private static double MINVA = Math.PI / 10;
	private static double MAXVA = Math.PI / 2;
	
	private Shape shape;
	private Stroke stroke;
	private Color color;
	private double angulo;
	private double velocidadAngular;
	private double cx;
	private double cy;
	
	public Figura(int xMax, int yMax) {
		switch (r.nextInt(3)) {
			case 0: elipseAleatoria(xMax, yMax);
			case 1: rectanguloAleatorio(xMax, yMax);
			default: poligonoAleatorio(xMax, yMax);
		}
		stroke = LINEACONTINUA;
		color = new Color(r.nextInt(246) + 10, r.nextInt(246) + 10, r.nextInt(246) + 10);
		angulo = r.nextDouble() * 2 * Math.PI;
		velocidadAngular = (r.nextDouble() * (MAXVA - MINVA) + MINVA) * (r.nextInt(2) == 0 ? -1 : 1);
	}
	
	private void elipseAleatoria(int xMax, int yMax) {
		double x = r.nextInt(xMax);
		double y = r.nextInt(yMax);
		double w = r.nextInt(151) + 50;
		double h = (w * (r.nextInt(41) + 40)) / 100;
		cx = x + w / 2;
		cy = y + h / w;
		shape = new Ellipse2D.Double(x, y, w, h);
	}

	private void rectanguloAleatorio(int xMax, int yMax) {
		double x = r.nextInt(xMax);
		double y = r.nextInt(yMax);
		double w = r.nextInt(151) + 50;
		double h = r.nextInt(151) + 50;
		cx = x + w / 2;
		cy = y + h / 2;
		shape = new Rectangle2D.Double(x, y, w, h);
	}

	private void poligonoAleatorio(int xMax, int yMax) {
		double x = cx = r.nextInt(xMax);
		double y = cy = r.nextInt(yMax);
		double radio = r.nextInt(101) + 25;
		int lados = r.nextInt(8) + 3;
		double a = 2 * Math.PI / lados;
		int [] xpoints = new int[lados];
		int [] ypoints = new int[lados];
		for (int i=0; i<lados; i++) {
			xpoints[i] = (int) (x + radio * Math.cos(i * a));
			ypoints[i] = (int) (y + radio * Math.sin(i * a));
		}
		shape = new Polygon(xpoints, ypoints, lados);
	}
	
	public void girar(long lapso) {
		angulo += lapso * velocidadAngular / 1000000000d;
	}
	
	public void paint(Graphics2D g) {
		AffineTransform t = g.getTransform();
		g.setColor(color);
		g.rotate(angulo, cx, cy);
		g.setStroke(stroke);
		g.draw(shape);
		g.setTransform(t);
	}

}
