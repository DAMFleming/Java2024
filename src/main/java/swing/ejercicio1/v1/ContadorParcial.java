package swing.ejercicio1.v1;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class ContadorParcial extends Contador {

	private static final long serialVersionUID = 1L;
	private final ContadorGlobal contadorGlobal;
	private final JButton inc;

	public ContadorParcial(long n, int cols, ContadorGlobal contadorGlobal) {
		super(n, cols);
		this.contadorGlobal = contadorGlobal;
		contadorGlobal.addContadorParcial(this);
		add(inc = new JButton("+"));
		inc.addActionListener(this::inc);
	}

	public ContadorParcial(int cols, ContadorGlobal contadorGlobal) {
		this(0, cols, contadorGlobal);
	}
	
	@Override
	public void inc(ActionEvent e) {
		super.inc(e);
		contadorGlobal.inc(e);
	}

	@Override
	public void reset(ActionEvent e) {
		contadorGlobal.dec(n);
		super.reset(e);
	}

}
