package swing.ejemplos.e3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class Lienzo extends JComponent {

	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
			
	}
}
