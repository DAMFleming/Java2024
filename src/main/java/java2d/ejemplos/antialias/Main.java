package java2d.ejemplos.antialias;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	private JFrame frame;
	
	private void crearUI() {
		frame = new JFrame("Ejemplo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Lienzo(850, 400));
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void iniciar() {
		crearUI();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main()::iniciar);
	}

}
