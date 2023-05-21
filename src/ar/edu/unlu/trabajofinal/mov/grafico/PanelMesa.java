package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import ar.edu.unlu.trabajofinal.IJugador;

public class PanelMesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelJugadores;
	private JPanel panelCrupier;
	private ImageManager imageManager;
	
	public PanelMesa(ArrayList<IJugador> players, ImageManager imageManager) {
		this.imageManager = imageManager;

		this.setPanelJugadores();
		this.setPanelCrupier();
		this.setPanelPrincipal();
		this.setPlayers(players);
	}
	
	public void setPlayers(ArrayList<IJugador> players) {
		ModuloJugador moduloAuxiliar = null;
		
		this.panelCrupier.removeAll();
		this.panelJugadores.removeAll();
		
		for (int i = 0; i < (players.size() - 1); i++) {
			moduloAuxiliar = new ModuloJugador(players.get(i), this.imageManager);
			this.panelJugadores.add(moduloAuxiliar);
		}
		
		this.panelCrupier.add(new ModuloJugador(players.get((players.size() - 1)), this.imageManager));
	}

	private void setPanelPrincipal() {
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new GridLayout(2,1,10,10));
		this.panelPrincipal.add(panelJugadores);
		this.panelPrincipal.add(panelCrupier);
		
		this.setLayout(new GridLayout(1,1,100,100));
		this.add(this.panelPrincipal);
	}

	private void setPanelJugadores() {
		this.panelJugadores = new JPanel();
		this.panelJugadores.setLayout(new FlowLayout());
	}

	private void setPanelCrupier() {
		this.panelCrupier = new JPanel();
		this.panelCrupier.setLayout(new FlowLayout());	
	}
}
