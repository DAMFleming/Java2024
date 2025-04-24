package swing.ejercicio2.version2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final LunaSlider slider = new LunaSlider(this);
	private final PanelLuna panelLuna = new PanelLuna(this, slider.getValue());
	
	public Main() {
		super("Fases de la luna");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		add(panelLuna, BorderLayout.CENTER);
		add(slider, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				slider.startStopTimer(true);
				dispose();
				System.exit(0);
			}
		});
	}

	public LunaSlider getSlider() {
		return slider;
	}

	public PanelLuna getPanelLuna() {
		return panelLuna;
	}
		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
