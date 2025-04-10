package swing.ejercicio1;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	private final JFrame frame = new JFrame("Multicontador");
	
    Main(String[] args) {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(new GridLayout(0, 1));
        frame.add(new Contador());
        frame.add(new Contador());
        frame.add(new Contador());
        frame.add(new Contador());
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> new Main(args).show());
    }
}
