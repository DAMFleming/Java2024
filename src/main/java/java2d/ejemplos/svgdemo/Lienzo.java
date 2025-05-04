package java2d.ejemplos.svgdemo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
public class Lienzo extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final SVGDiagram svg;
	private static final BufferedImage img;
	
	static {
		SVGUniverse universe = new SVGUniverse();
		svg = universe.getDiagram(universe.loadSVG(Lienzo.class.getResource("/svgdemo/ghost.svg")));
		try {
			img = ImageIO.read(Lienzo.class.getResourceAsStream("/svgdemo/ghost.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		double scale = 600d / svg.getWidth();
		g2d.scale(scale, scale);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		try {
			svg.render(g2d);
		} catch (SVGException e) {
			throw new RuntimeException(e);
		}
		g2d.drawImage(img, 32, 0, this);	
	}
}
