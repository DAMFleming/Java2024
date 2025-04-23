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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		add(panelLuna, BorderLayout.CENTER);
		add(slider, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}

	public void startStopAnimation(java.awt.event.MouseEvent e) {
		slider.setEnabled(!slider.startStopAnimation(e));
	}
	
	public void setFase(int n) {
		panelLuna.setFase(n);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
