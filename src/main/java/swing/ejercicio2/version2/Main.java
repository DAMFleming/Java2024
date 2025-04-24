package swing.ejercicio2.version2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final Selector selector = new Selector(this);
	private final Visor visor = new Visor(this, selector.getValue());
	
	public Main() {
		super("Fases de la luna");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		add(visor, BorderLayout.CENTER);
		add(selector, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				selector.startStopTimer(true);
				dispose();
				System.exit(0);
			}
		});
	}

	public Selector getSelector() {
		return selector;
	}

	public Visor getVisor() {
		return visor;
	}
		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
