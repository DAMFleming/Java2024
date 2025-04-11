package swing.ejercicio1.v2;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contador extends JPanel {

	private static final long serialVersionUID = 1L;
	protected long n;
	protected final JTextField valor;
	protected final JButton inc;
	
	public Contador(long n) {
		this.n = n;
		JButton reset = new JButton("Reset");
		reset.addActionListener(this::reset);
		add(reset);
		valor = new JTextField(20);
		valor.setText(String.valueOf(n));
		add(valor);
		inc = new JButton("+");
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
	
	public void inc(ActionEvent e) {
		n++;
		valor.setText(String.valueOf(n));
	}
	
	public void dec(long n) {
		this.n -= n;
		valor.setText(String.valueOf(this.n));
	}
	
	public void reset(ActionEvent e) {
		n = 0;
		valor.setText(String.valueOf(n));
	}

}
