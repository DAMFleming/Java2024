package swing.ejercicio2.version1;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Selector extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton ant;
	private JButton sgte;
	
	public Selector(Main main) {
		setOpaque(false);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(30, 30, 30, 30),
				getBorder()));
		ant = new JButton(new ImageIcon(getClass().getResource("/luna/anterior.png")));
		ant.addActionListener(main.getVisor()::anterior);
		sgte = new JButton(new ImageIcon(getClass().getResource("/luna/siguiente.png")));
		sgte.addActionListener(main.getVisor()::siguiente);
		add(ant);
		add(sgte);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		ant.setEnabled(enabled);
		sgte.setEnabled(enabled);
	}
	
}
