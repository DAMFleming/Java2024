package java2d.ejemplos.texto.e1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;


public class Lienzo extends Canvas {

	private static final long serialVersionUID = 1L;
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
		
		int x = (getWidth() - fm.stringWidth(TEXTO)) / 2;
		int y = ascent;
		int cy = getHeight() / 2;
		
		g.drawString(TEXTO, x, y);
		g.drawLine(0, y, getWidth(), y);
		
		
		g.drawString(TEXTO, x, cy + (((ascent + descent) / 2) - descent));
		g.drawLine(0, cy, getWidth(), cy);
	}
}
