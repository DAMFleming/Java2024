package swing.ejercicio2.version1;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelControl extends JPanel {

	private PanelLuna panelLuna;
	
	public PanelControl(PanelLuna panelLuna) {
		this.panelLuna = panelLuna;
		JButton ant = new JButton(new ImageIcon(getClass().getResource("/luna/anterior.png")));
		ant.addActionListener(panelLuna::anterior);
		JButton sgte = new JButton(new ImageIcon(getClass().getResource("/luna/siguiente.png")));
		sgte.addActionListener(panelLuna::siguiente);
		add(ant);
		add(sgte);
		
	}
	
}
