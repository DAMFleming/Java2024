package java2d.caballero2.timer;

import java.awt.Color;
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
	private static final BufferedImage img;
	private static final Font FUENTE;
	static {
		try {
			img = ImageIO.read(Lienzo.class.getResourceAsStream("/caballero/Idle (1).png"));
			FUENTE = Font.createFont(Font.PLAIN, Lienzo.class.getResourceAsStream("/fuentes/Heraldic Shadows.otf"));
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
	}
	private static final String INSTRUCCIONES = "Haz clic en el caballero";
	private final Timer timer;
	private double angulo = 0;
	private double escala = 1;
	private double x;
	private double y;
	private double xs;
	private double ys;
	private double cx;
	private double cy;
	private double ds = -0.01;
	
	public Lienzo(int ancho, int alto) {
		setPreferredSize(new Dimension(ancho , alto));
		setFont(FUENTE.deriveFont(70f));
		setBackground(Color.BLACK);
		x = xs = (ancho - img.getWidth()) / 2d;
		y = ys = (alto - img.getHeight()) / 2d;
		cx = x + (img.getWidth() / 2d);
		cy = y + (img.getHeight() / 2d);
		timer = new Timer(50, this::desaparecer);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!timer.isRunning())
					timer.start();
			}
		});
	}
	
	private void desaparecer(ActionEvent e) {
		angulo += Math.PI / 10;
		escala += ds;
		if (escala <= 0) {
			escala = -escala;
			ds *= -1;
		}
		else if (escala > 1) {
			escala = 1;
			ds *= -1;
			timer.stop();
		}
		double f1 = 1 - escala;
		double f2 = 2 * escala;
//		xs = (x + ((img.getWidth() - (img.getWidth() * escala)) / 2)) / escala;
//		ys = (y + ((img.getHeight() - (img.getHeight() * escala)) / 2)) / escala;
		xs = ((2 * x) + img.getWidth() * f1) / f2;
		ys = ((2 * y) + img.getHeight() * f1) / f2;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		FontMetrics fm = g.getFontMetrics();
		g.drawString(INSTRUCCIONES, (getWidth() - fm.stringWidth(INSTRUCCIONES)) / 2, 100);
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angulo, cx, cy);
		g2d.scale(escala, escala);
		g2d.drawImage(img, (int) xs, (int) ys, this);
	}
}
