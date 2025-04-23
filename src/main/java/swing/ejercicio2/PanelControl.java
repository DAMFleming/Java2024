package swing.ejercicio2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creamos los botones para controlar las fases de la luna, recibe referencia a
 * PanelLuna ya que ahi es donde estan todas las fotos
 * 
 * En el listener de la clase PanelLuna hemos hecho dos metodos para que cambie
 * automaticamente de foto sin tener que usar botones
 * 
 * 
 * - Tarea: sustituir los botones por un JSlider (barra de desplazamiento),
 * tienen que cambiar las fases de la luna y el Slider desplazandose solo al
 * hacer clic en la luna hay que dehabilitar los botones cuando la animacion
 * esté en marcha y volver a habilitarlo cuando la animacion se pare
 * 
 */

public class PanelControl extends JPanel {

	private PanelLuna panelLuna;
	private JSlider slider;

	JButton izq;
	JButton der;

	public PanelControl(PanelLuna panelLuna) {
		this.panelLuna = panelLuna;

		slider = new JSlider(0, 7, 0); // mínimo, máximo, valor inicial
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		// Notificar a PanelLuna cuando el usuario mueve el slider
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!slider.getValueIsAdjusting()) {
					panelLuna.setIndice(slider.getValue());
				}
			}
		});

		panelLuna.setSlider(slider);

		// imagen de los botones
		izq = new JButton(new ImageIcon(getClass().getResource("/luna/anterior.png")));
		izq.addActionListener(panelLuna::anterior);
		der = new JButton(new ImageIcon(getClass().getResource("/luna/siguiente.png")));
		der.addActionListener(panelLuna::siguiente);

		add(izq);
		add(der);
		add(slider);

	}

	//este metodo establece cuando los botones esten activos
	public void setControlesActivos(boolean activo) {
		izq.setEnabled(activo);
		der.setEnabled(activo);

	}
}
