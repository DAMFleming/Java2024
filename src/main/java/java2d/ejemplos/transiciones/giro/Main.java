package java2d.ejemplos.transiciones.giro;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends WindowAdapter {
	private final JFrame frame;
	private final Surface surface;

	public Main() {
	frame = new JFrame("Click sobre la imagen para ejecutar la transición \"giro\"");
	frame.setDefaultCloseOperation(JFrame.
	DO_NOTHING_ON_CLOSE);
	frame.addWindowListener(this);
	frame.add(surface = new Surface(1024, 768));
	frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public void iniciar() {
		frame.setVisible(true);
		surface.requestFocus();
		surface.start(true);
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent e) {
		surface.stop();
		frame.dispose();
		System.exit(0);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main()::iniciar);
	}

}
