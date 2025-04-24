package swing.ejercicio2.version2;

import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.Timer;

public class LunaSlider extends JSlider {

	private static final long serialVersionUID = 1L;
	private final Timer timer = new Timer(500, this::siguiente);
	
	public LunaSlider(Main main) {
		super(0, 7, 0);
		addChangeListener(e -> main.getPanelLuna().setFase(getValue()));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 20, 20, 20),
				getBorder()));
		setOpaque(false);
	}
	
	public void anterior(ActionEvent e) {
		int value = getValue();
		if (--value < getMinimum())
			value = getMaximum();
		setValue(value);
	}

	public void siguiente(ActionEvent e) {
		int value = getValue();
		if (++value > getMaximum())
			value = 0;
		setValue(value);
	}
	
	public void startStopTimer(boolean forceStop) {
		if (timer.isRunning()) {
			timer.stop();
		} else if (!forceStop) {
			timer.start();
		}
		setEnabled(!timer.isRunning());
	}
	
}
