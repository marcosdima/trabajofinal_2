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
		int espacioCartas = 5;
		Fuente font = new Fuente(12);

		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		panelNorte.setOpaque(false);
		panelCentro.setOpaque(false);
		
		BorderLayout mayor = new BorderLayout(10,10);
		BorderLayout norte = new BorderLayout();
		GridLayout cartas = new GridLayout(3, 3, espacioCartas, espacioCartas);
		
		JButton nombre = new JButton(jugador.getNombre());
		JLabel dinero;
		JLabel puntos;
		
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
			JLabel carta = this.manager.imagenCarta(cartita);			
			panelCentro.add(carta);
		}
		
		// Appendeo
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);
		this.add(puntos, BorderLayout.SOUTH);
		
		// Agregar vacios
		JLabel vacio1 = new JLabel("");
		JLabel vacio2 = new JLabel("");
		this.add(vacio1, BorderLayout.WEST);
		this.add(vacio2, BorderLayout.EAST);
	};
	
	public void setManager(ImageManager manager) {
		this.manager = manager;
	}
}

