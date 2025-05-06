package java2d.ejemplos.texto;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.io.IOException;


public class Lienzo extends Canvas {

	private static final long serialVersionUID = 1L;
	private static final BasicStroke STROKE = new BasicStroke(10.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private static final Paint PAINT1 = new GradientPaint(50, 50,Color.RED, 350, 350, Color.WHITE);
	private static final Paint PAINT2 = new GradientPaint(500, 50, Color.RED, 800, 350, Color.WHITE);
	private static final Font FONT;
	private static final String TEXTO = "Texto de ejemplo";
	
	static {
		try {
			FONT = Font.createFont(Font.PLAIN, Lienzo.class.getResourceAsStream("/fuentes/Heraldic Shadows.otf"));
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Lienzo(int ancho, int alto) {
		setFont(FONT.deriveFont(70f));
		setPreferredSize(new Dimension(ancho , alto));
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		FontMetrics fm = g.getFontMetrics();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		
		int y = ascent;
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		
		g.drawString(TEXTO, x, y);
		g.drawLine(0, y, getWidth(), y);
		
		int x = (getWidth() - fm.stringWidth(TEXTO)) / 2;
		g.drawString(TEXTO, x, cy + (((ascent + descent) / 2) - descent));
		g.drawLine(0, cy, getWidth(), cy);
	}
}
