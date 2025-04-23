package swing.ejemplos.e1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MyApp {
	
	private JFrame frame;
	private JButton boton1;
	private JButton boton2;
	
    public void crearMostrarUI() {
        frame = new JFrame("My App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(700, 500));
        frame.pack();
        boton1 = new JButton("Pulsa aquí");
        boton2 = new JButton("Pulsa aqui");
        boton1.setActionCommand("b1");
        boton2.setActionCommand("b2");
        boton1.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has pulsado el botón 1"));
		boton2.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has pulsado el botón 2"));
		frame.add(boton1, BorderLayout.CENTER);
        frame.add(boton2, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MyApp()::crearMostrarUI);
    }


}
