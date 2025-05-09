package java2d.guiada.fullscreen;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Surface().start());
	}
	
}
