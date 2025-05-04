package java2d.ejemplos.antialias;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

public class Lienzo extends Canvas {

	private static final long serialVersionUID = 1L;
	private static final BasicStroke STROKE = new BasicStroke(10.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private static final Paint PAINT1 = new GradientPaint(50, 50,Color.RED, 350, 350, Color.WHITE);
	private static final Paint PAINT2 = new GradientPaint(500, 50, Color.RED, 800, 350, Color.WHITE);

	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(STROKE);
		
		g2d.setPaint(PAINT1);
		g2d.fillOval(50, 50, 300, 300);
		g2d.setPaint(Color.BLACK);
		g2d.drawOval(50, 50, 300, 300);
		
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setPaint(PAINT2);
		g2d.fillOval(500, 50, 300, 300);
		g2d.setPaint(Color.BLACK);
		g2d.drawOval(500, 50, 300, 300);
	}
}
