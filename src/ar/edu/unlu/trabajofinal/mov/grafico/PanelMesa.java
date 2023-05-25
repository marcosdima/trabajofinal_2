package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.trabajofinal.IJugador;

public class PanelMesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelJugadores;
	private JPanel panelCrupier;
	private ImageManager imageManager;
	private Displayer display;
	
	public PanelMesa(ArrayList<IJugador> players, ImageManager imageManager) {
		this.imageManager = imageManager;

		this.setPanelJugadores();
		this.setPanelCrupier();
		this.setPanelPrincipal();
		this.setPlayers(players);
	}
	
	public void setPlayers(ArrayList<IJugador> players) {
		ModuloJugador moduloAuxiliar = null;
		
		this.display = new Displayer();
		
		this.panelCrupier.removeAll();
		this.panelJugadores.removeAll();
		
		for (int i = 0; i < (players.size() - 1); i++) {
			moduloAuxiliar = new ModuloJugador(players.get(i), this.imageManager);
			this.panelJugadores.add(moduloAuxiliar);
		}
		
		// Esto es para que el crupier tenga un margen.
		JPanel panelParaElCrupier = new JPanel();
		GridLayout gridCrupier = new GridLayout(1,1);
		panelParaElCrupier.setLayout(gridCrupier);
		panelParaElCrupier.add(new ModuloJugador(players.get((players.size() - 1)), this.imageManager));
		panelParaElCrupier.setBorder(new EmptyBorder(0,100,10,100));
		
		this.panelCrupier.add(panelParaElCrupier);	
		this.panelCrupier.add(this.display);
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
		GridLayout lay = new GridLayout(1, 2, 10, 10);
		this.panelCrupier = new JPanel();
		this.panelCrupier.setBorder(new EmptyBorder(0,100,10,100));
		this.panelCrupier.setLayout(lay);	
	}
}
