package java2d.ejemplo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class Lienzo extends JComponent {

	private static final long serialVersionUID = 1L;

	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillOval(100, 150, 50, 50);
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(170, 150, 50, 50);		
	}
}
