package swing.ejercicio1;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	private final JFrame frame = new JFrame("Multicontador");
	
    Main() {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(new GridLayout(0, 1));
    	ContadorGlobal contadorGlobal = new ContadorGlobal(20);
    	for (int i=0; i<3; i++)
    		frame.add(new ContadorParcial(20, contadorGlobal));
        frame.add(contadorGlobal);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> new Main().show());
    }
}
