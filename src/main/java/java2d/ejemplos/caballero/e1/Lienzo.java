package java2d.ejemplos.caballero1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
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
	private static final BufferedImage CABALLERO;
	private static final Font FUENTE;
	static {
		try {
			CABALLERO = ImageIO.read(Lienzo.class.getResourceAsStream("/caballero/Attack (6).png"));
			FUENTE = Font.createFont(Font.PLAIN, Lienzo.class.getResourceAsStream("/fuentes/Heraldic Shadows.otf"));
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
	}
	private static final String INSTRUCCIONES = "Haz clic en el caballero";
	private final Timer timer;
	private double escala;
	private double x;
	private double y;
	private double angulo = 0;
	
	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
		setFont(FUENTE.deriveFont(30f));
		escala = 128d / CABALLERO.getWidth();
		x = (ancho / escala - CABALLERO.getWidth()) / 2;
		y = 400;
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
		FontMetrics fm = g.getFontMetrics();
		g.drawString(INSTRUCCIONES, (getWidth() - fm.stringWidth(INSTRUCCIONES)) / 2, 50);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(escala, escala);
		g2d.rotate(angulo, x + CABALLERO.getWidth() / 2d, y + CABALLERO.getHeight() / 2d);
		g2d.drawImage(CABALLERO, (int) x, (int) y, this);
			
	}
}
