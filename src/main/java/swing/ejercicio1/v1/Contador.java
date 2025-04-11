package swing.ejercicio1.v1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contador extends JPanel {

	private static final long serialVersionUID = 1L;
	protected long n;
	protected final JTextField valor;
		
	public Contador(long n, int cols) {
		this.n = n;
		((FlowLayout) getLayout()).setAlignment(FlowLayout.LEFT);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.BLACK),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)
				)
		));
		JButton reset = new JButton("Reset");
		reset.addActionListener(this::reset);
		add(reset);
		add(valor = new JTextField(cols));
		valor.setText(String.valueOf(n));
		valor.setEditable(false);
	}
	
	public Contador(int cols) {
		this(0, cols);
	}
	
	protected void inc(ActionEvent e) {
		n++;
		valor.setText(String.valueOf(n));
	}
	
	public void dec(long n) {
		this.n -= n;
		valor.setText(String.valueOf(this.n));
	}
	
	protected void reset(ActionEvent e) {
		n = 0;
		valor.setText(String.valueOf(n));
	}

}
