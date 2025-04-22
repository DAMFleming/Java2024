package swing.ejercicio2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	public Main() {
		super("Fases de la luna");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		PanelLuna panelLuna = new PanelLuna(0); 
		add(panelLuna, BorderLayout.CENTER);
		PanelControl panelControl = new PanelControl(panelLuna);
		add(panelControl, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
