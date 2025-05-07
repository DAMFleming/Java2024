package java2d.ejemplos.texto.e2;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.IOException;


public class Lienzo extends Canvas {

	private static final long serialVersionUID = 1L;
	private static final BasicStroke STROKE = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
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
		setFont(FONT.deriveFont(110f));
		setPreferredSize(new Dimension(ancho , alto));
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		
		int x = (getWidth() - fm.stringWidth(TEXTO)) / 2;
		int y = ascent;
		int cy = getHeight() / 2;
		
		g.drawLine(0, cy, getWidth(), cy);
		
		g2d.setStroke(STROKE);
		AffineTransform af = g2d.getTransform();
		g2d.translate(x, cy + (((ascent + descent) / 2) - descent));
		Shape shape = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), TEXTO).getOutline();
		g2d.setColor(Color.MAGENTA);
		g2d.fill(shape);
		g2d.setColor(Color.GREEN);
		g2d.draw(shape);
		g2d.setTransform(af);
	}
}
