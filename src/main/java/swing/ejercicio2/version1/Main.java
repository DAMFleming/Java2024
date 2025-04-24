package swing.ejercicio2.version1;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final PanelLuna panelLuna;
	private final PanelControl panelControl;
	
	public Main() {
		super("Fases de la luna");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		panelLuna = new PanelLuna(this, 0); 
		add(panelLuna, BorderLayout.CENTER);
		panelControl = new PanelControl(this);
		add(panelControl, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				panelLuna.stopTimer();
				dispose();
				System.exit(0);
			}
		});
	}

	public PanelLuna getPanelLuna() {
		return panelLuna;
	}

	public PanelControl getPanelControl() {
		return panelControl;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
