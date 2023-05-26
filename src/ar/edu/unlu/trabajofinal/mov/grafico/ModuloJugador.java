package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import ar.edu.unlu.trabajofinal.IJugador;

public class ModuloJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageManager manager;
	
	public ModuloJugador(IJugador player, ImageManager manager) {
		super();
		this.setManager(manager);
		this.setFormato(player);
	}
	
	private void setFormato(IJugador jugador) {
		Fuente font = new Fuente(12);

		// Setteo de paneles.
		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		panelNorte.setOpaque(false);
		panelCentro.setOpaque(false);
		
		// Setteo de layouts.
		BorderLayout mayor = new BorderLayout(10,10);
		BorderLayout norte = new BorderLayout();
		GridLayout cartas = new GridLayout(3, 3, 40, 30);
		
		// Variables para el jugador.
		JButton nombre = new JButton(jugador.getNombre());
		JLabel dinero;
		JLabel puntos;
		
		// Variables de tamaño de carta.
		int anchoCarta = 64;
		int altoCarta = 84;
		
		if (jugador.getDinero() == 0) {
			dinero = new JLabel("Dinero: -");
		} else {
			dinero = new JLabel("Dinero: " + jugador.getDinero());
		}
		
		if (jugador.getPuntaje() == 0) {
			puntos = new JLabel("Puntaje: -");
		} else {
			puntos = new JLabel("Puntaje: " + jugador.getPuntaje());
		}
		
		dinero.setFont(font.font());
		puntos.setFont(font.font());
		dinero.setHorizontalAlignment(JLabel.CENTER);
		puntos.setHorizontalAlignment(JLabel.CENTER);

		this.setLayout(mayor);
		panelNorte.setLayout(norte);
		panelCentro.setLayout(cartas);
		
		// Apago el botón por motivos estéticos.
		nombre.setEnabled(false);
		
		// Seteo panel norte
		panelNorte.add(nombre, BorderLayout.NORTH);
		panelNorte.add(dinero, BorderLayout.SOUTH);
		
		// Seteo panel central
		for (String cartita : jugador.getIdCartas()) {
			JLabel carta = new JLabel(this.manager.imagenCarta(cartita, anchoCarta, altoCarta));			
			panelCentro.add(carta);
		}
		
		// Appendeo
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(puntos, BorderLayout.SOUTH);
		
		// Agregar vacios
		this.add(new JLabel(""), BorderLayout.WEST);
		this.add(new JLabel(""), BorderLayout.EAST);
	};
	
	public void setManager(ImageManager manager) {
		this.manager = manager;
	}
}

