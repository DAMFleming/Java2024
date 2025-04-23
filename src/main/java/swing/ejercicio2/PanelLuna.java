package swing.ejercicio2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * Aqui se crean los recursos para mostrar las imagenes (jLabel)
 * 
 * 	La clase ImageIcon nos permite cargar imagenes y JLabel las necesita La clase
 * BufferedImage se necesita un framework E/S de imagenes para cargarlas
 * 
 * Cuando se haga clic sobre la luna, se deben pasar automaticamente todas las
 * fases cada medio segundo y cuando llega a la ultima vuelve al inicio
 * 
 * Cuando se le da clic otra vez se para el bucle
 * 
 * 
 * 	La clase Timer nos va a servir para hacer animaciones simples y chapuceras
 * 
 * 
 */

public class PanelLuna extends JLabel {

	private static final long serialVersionUID = 1L;
	private static final Luna[] LUNAS = new Luna[8];

	private int indiceActual = 0;
	private Timer timer;
	//private Timer timer = new Timer(500, this::siguiente); otra opcion con los botones implementados
	
	private boolean animando = false;
	private int fase;
	private JSlider slider;
	private PanelControl panelControl;

	// Inicialización de las fases
	static {
		LUNAS[0] = new Luna("Luna Nueva");
		LUNAS[1] = new Luna("Luna Creciente");
		LUNAS[2] = new Luna("Cuarto Creciente");
		LUNAS[3] = new Luna("Quinto Octante");
		LUNAS[4] = new Luna("Luna Llena");
		LUNAS[5] = new Luna("Tercer Octante");
		LUNAS[6] = new Luna("Cuarto Menguante");
		LUNAS[7] = new Luna("Luna Menguante");
	}

	public PanelLuna(int fase) { // con el parametro me permite crear el panel con la fase de la luna que yo quiera, no que empiece siempre en [0]
		super(LUNAS[fase].getFase(), LUNAS[0].getIcon(), JLabel.CENTER);
		this.fase = fase;
		
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setForeground(Color.WHITE);

		// Crear el Timer con intervalo de medio segundo = 500 milisegundos
		timer = new Timer(500, e -> mostrarSiguienteFase()); //el constructor pide un intervalo de tiempo y un Listener


		/*
		 * Listener para el slider, botones y clic en la imagen, cuando esté en animacion el slider no se podra usar
		 * No se pueden poner dos addMouseListener ya que se sobreescriben y el Timer se activa y desactiva todo el rato
		 */
		 addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            if (animando) {
		                timer.stop(); // metodos de la clase Timer, si no corre se para y sino continua hasta que se pare
		                if (slider != null) slider.setEnabled(true);
		                if (panelControl != null) panelControl.setControlesActivos(true); //no hay animacion en curso
		            } else {
		                timer.start();
		                if (slider != null) slider.setEnabled(false);
		                if (panelControl != null) panelControl.setControlesActivos(false); //para los botones
		            }
		            animando = !animando;
		        }
		    });
	}

	private void mostrarSiguienteFase() {
		// el indice hace q incremente 1 foto   
		indiceActual = (indiceActual + 1) % LUNAS.length; //asegura que el valor se mantenga dentro del rango válido del array (0 a 7, 8 fases)
		setText(LUNAS[indiceActual].getFase());
		setIcon(LUNAS[indiceActual].getIcon());
		 if (slider != null) {
	            slider.setValue(indiceActual); //segun la posicion de la fto, el indice del slider coincide con la posicion
	        }
	}
	
	public void setPanelControl(PanelControl panelControl) {
	    this.panelControl = panelControl;
	}

	
	/**
	 * Funcionalidad de los botones
	 */

	public void anterior(ActionEvent e) {
		if(--fase < 0) // para que cuando llegue al final, vuelva al inicio
			fase = LUNAS.length -1;
		setFase();
	}

	public void siguiente(ActionEvent e) {
		if(++fase == LUNAS.length)  // para que al darle al boton de siguiente, se vayan aumentando las lunas, las fotos
			fase = 0;
		setFase();
	}
	
	// permite mostrar por la GUI las fotos con su respectivo texto
	private void setFase() {
		setIcon(LUNAS[fase].getIcon());
		setText(LUNAS[fase].getFase());
	}
	
	public void setFase(int fase) {
		this.fase = fase;
		setFase();
	}
	
	
	/**
	 * Slider
	 */

    private void actualizarVista() {
        setText(LUNAS[indiceActual].getFase());
        setIcon(LUNAS[indiceActual].getIcon());
    }

    // Llamado desde el slider, cambia la fase manualmente desde el slider
    public void setIndice(int i) {
        indiceActual = i;
        actualizarVista();
    }

    // Llamado desde PanelDeControl, para que pueda pasar la referencia del slider
    public void setSlider(JSlider slider) {
        this.slider = slider;
    }
}

