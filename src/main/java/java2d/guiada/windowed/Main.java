package java2d.guiada.windowed;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends WindowAdapter {
	private final JFrame frame;
	private final Surface surface;

	public Main() {
		frame = new JFrame("Bola Rebotando");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		frame.add(surface = new Surface(650, 450));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(new MyKeyAdapter());;
	}

	public void iniciar() {
		frame.setVisible(true);
		surface.start();
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent e) {
		surface.stop();
		frame.dispose();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main()::iniciar);
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (surface.isPaused())
					surface.resume();
				else
					surface.pause();
					
			}
		}
	}

}
