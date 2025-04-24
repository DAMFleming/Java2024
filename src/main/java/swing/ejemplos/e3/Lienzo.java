package swing.ejemplos.e3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Lienzo extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final BufferedImage img;
	static {
		try {
			img = ImageIO.read(Lienzo.class.getResourceAsStream("/caballero/Attack (6).png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private final Timer timer;
	private double angulo = 0;
	
	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
		timer = new Timer(50, this::girar);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer.isRunning())
					timer.stop();
				else
					timer.start();
			}
		});
	}
	
	private void girar(ActionEvent e) {
		angulo += Math.PI / 10;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double s = 128d / img.getWidth();
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(s, s);
		double x = (1000d + (img.getWidth() / 2d));
		double y = (1000d + (img.getHeight() / 2d));
		g2d.rotate(angulo, x, y);
		g2d.drawImage(img, 1000, 1000, this);
			
	}
}
