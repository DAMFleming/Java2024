package java2d.ejemplos.caballero2.timer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	private JFrame frame;
	
	private void crearUI() {
		frame = new JFrame("Ejemplo de giro y escalado");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setContentPane(new Lienzo(900, 900));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}
	
	private void iniciar() {
		crearUI();
		frame.setVisible(true);
		frame.requestFocus();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main()::iniciar);
	}

}
