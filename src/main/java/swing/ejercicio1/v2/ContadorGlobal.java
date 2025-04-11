package swing.ejercicio1.v2;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ContadorGlobal extends Contador {

	private static final long serialVersionUID = 1L;
	private final List<ContadorParcial> parciales = new ArrayList<>();

	public ContadorGlobal(int n) {
		super(n);
		inc.setVisible(false);
	}
	
	public ContadorGlobal() {
		this(0);
	}

	public void addContadorParcial(ContadorParcial contadorParcial) {
		parciales.add(contadorParcial);
	}
	
	@Override
	public void reset(ActionEvent e) {
		for (ContadorParcial contadorParcial : parciales) {
			contadorParcial.reset(e);
		}
		n = 0;
		valor.setText(String.valueOf(n));
	}

}
