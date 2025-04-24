package swing.ejercicio2.version1;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final Visor visor;
	private final Selector selector;
	
	public Main() {
		super("Fases de la luna");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		visor = new Visor(this, 0); 
		add(visor, BorderLayout.CENTER);
		selector = new Selector(this);
		add(selector, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				visor.stopTimer();
				dispose();
				System.exit(0);
			}
		});
	}

	public Visor getVisor() {
		return visor;
	}

	public Selector getSelector() {
		return selector;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
