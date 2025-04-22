package swing.ejercicio2;

import javax.swing.ImageIcon;

public class Luna {
	
	private ImageIcon icon;
	private String fase;
	
	public Luna(String fase) {
		this.fase = fase;
		icon = new ImageIcon(Luna.class.getResource("/luna/" + fase + ".png"));
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getFase() {
		return fase;
	}
	
	
}
