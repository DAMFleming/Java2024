package swing.ejercicio2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.Timer;

public class PanelLuna extends JLabel {

	private static final long serialVersionUID = 1L;
	private static final Luna [] LUNAS = new Luna[8];
	
	private int fase;
	private Timer timer = new Timer(500, this::siguiente);
	
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
	
	public PanelLuna(int fase) {
		super(LUNAS[fase].getFase(), LUNAS[fase].getIcon(), JLabel.CENTER);
		this.fase = fase;
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setForeground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer.isRunning())
					timer.stop();
				else
					timer.start();
			}
		});
	}

	
	public void anterior(ActionEvent e) {
		if (--fase < 0)
			fase = LUNAS.length - 1;
		setFase();
	}
	
	public void siguiente(ActionEvent e) {
		if (++fase == LUNAS.length)
			fase = 0;
		setFase();
	}
	
	private void setFase() {
		setIcon(LUNAS[fase].getIcon());
		setText(LUNAS[fase].getFase());
	}
	
	public void setFase(int fase) {
		this.fase = fase;
		setFase();
	}
	
}
