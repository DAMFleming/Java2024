package swing.ejercicio2.version2;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class PanelLuna extends JLabel {

	private static final long serialVersionUID = 1L;
	private static final Luna [] LUNAS = new Luna[8];
	
	static {
		LUNAS[0] = new Luna("Luna Nueva");
		LUNAS[1] = new Luna("Luna Creciente");
		LUNAS[2] = new Luna("Cuarto Creciente");
		LUNAS[3] = new Luna("Quinto Octante");
		LUNAS[4] = new Luna("Luna Llena");
		LUNAS[5] = new Luna("Tercer Octante");
		LUNAS[6] = new Luna("Cuarto Menguante");
		LUNAS[7] = new Luna("Luna Menguante");
	}
	
	public PanelLuna(Main main, int fase) {
		super(LUNAS[fase].getFase(), LUNAS[fase].getIcon(), JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setForeground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.getSlider().startStopTimer(false);
			}
		});
	}
	
	public void setFase(int fase) {
		setIcon(LUNAS[fase].getIcon());
		setText(LUNAS[fase].getFase());
	}
	
}
