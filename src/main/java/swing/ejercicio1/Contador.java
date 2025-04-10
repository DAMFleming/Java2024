package swing.ejercicio1;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contador extends JPanel {

	private static final long serialVersionUID = 1L;
	private long n;
	private final JTextField valor;
	
	public Contador(long n) {
		this.n = n;
		JButton reset = new JButton("Reset");
		add(reset);
		valor = new JTextField(20);
		valor.setText(String.valueOf(n));
		add(valor);
		JButton inc = new JButton("+");
		inc.addActionListener(this::inc);
		add(inc);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.BLACK),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)
				)
		));
	}
	
	public Contador() {
		this(0);
	}
	
	private void inc(ActionEvent e) {
		n++;
		valor.setText(String.valueOf(n));
	}

}
