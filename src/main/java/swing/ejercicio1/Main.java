package swing.ejercicio1;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Main() {
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	getContentPane().setLayout(new GridLayout(0, 1));
    	ContadorGlobal contadorGlobal = new ContadorGlobal(20);
    	for (int i=0; i<3; i++)
    		add(new ContadorParcial(20, contadorGlobal));
        add(contadorGlobal);
        pack();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		int respuesta = JOptionPane.showConfirmDialog(
        				Main.this,
        				"¿Cerrar la aplicaicón?",
        				"Multicontador",
        				JOptionPane.YES_NO_OPTION);
        		if (respuesta == JOptionPane.YES_OPTION) {
        			dispose();
        			System.exit(0);
        		}
        	}
        });
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

	
}
