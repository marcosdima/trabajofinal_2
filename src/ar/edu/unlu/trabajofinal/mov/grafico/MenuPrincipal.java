package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;


public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel menu = new JPanel();
	private BotonCarta jugar;
	private BotonCarta config;
	private BotonCarta rank;
	private BotonCarta load;
	private BotonCarta salir ;
	
	public MenuPrincipal(ImageManager imageManager) {
		this.setLayout(new BorderLayout(100,100));
		this.setButtons(imageManager);
		this.setVacio();
		this.setMenu();
	}

	public BotonCarta getJugar() {
		return jugar;
	}

	public BotonCarta getConfig() {
		return config;
	}

	public BotonCarta getRank() {
		return rank;
	}

	public BotonCarta getLoad() {
		return load;
	}

	public BotonCarta getSalir() {
		return salir;
	}

	private void setVacio() {
		JLabel vacio = new JLabel("");
		JLabel vacio1 = new JLabel("");
		JLabel vacio2 = new JLabel("");
		JLabel vacio3 = new JLabel("");

		this.add(vacio, BorderLayout.NORTH);
		this.add(vacio1, BorderLayout.EAST);
		this.add(vacio2, BorderLayout.WEST);
		this.add(vacio3, BorderLayout.SOUTH);
	}

	private void setMenu() {
		JPanel panelBotones = new JPanel();
		JPanel title = new JPanel();
		
		// Preparo el título.
		title.setLayout(new FlowLayout());
		JLabel menuPrincipalText = new JLabel("Menu Principal");
		menuPrincipalText.setHorizontalAlignment(JLabel.CENTER);
		menuPrincipalText.setVerticalAlignment(JLabel.BOTTOM);
		Fuente customFont = new Fuente("files/fonts/Pokemon Classic.ttf", 50);
		menuPrincipalText.setFont(customFont.font());
		title.add(menuPrincipalText);
		
		// Preparo los botones.
		panelBotones.setLayout(new GridLayout(1, 5, 10, 10));
		panelBotones.add(this.jugar);
		panelBotones.add(this.config);
		panelBotones.add(this.rank);
		panelBotones.add(this.load);
		panelBotones.add(this.salir);
		
		this.menu.setLayout(new GridLayout(2,1));
		this.menu.add(title);
		this.menu.add(panelBotones);
		
		this.add(this.menu, BorderLayout.CENTER);
	}

	private void setButtons(ImageManager imageManager) {
		this.jugar = new BotonCarta("Jugar", imageManager);
		config = new BotonCarta("Configuración", imageManager);
		this.rank = new BotonCarta("Ranking", imageManager);
		this.load = new BotonCarta("Load", imageManager);
		this.salir = new BotonCarta("Salir", imageManager);
	}
	
}
