package swing.ejercicio1.v2;

import java.awt.event.ActionEvent;

public class ContadorParcial extends Contador {

	private static final long serialVersionUID = 1L;
	private final ContadorGlobal contadorGlobal;

	public ContadorParcial(long n, ContadorGlobal contadorGlobal) {
		super(n);
		this.contadorGlobal = contadorGlobal;
		contadorGlobal.addContadorParcial(this);
	}

	public ContadorParcial(ContadorGlobal contadorGlobal) {
		this(0, contadorGlobal);
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
