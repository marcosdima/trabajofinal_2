package ar.edu.unlu.trabajofinal.mov.grafico;

import ar.edu.unlu.tools.Rand;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;


public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel menu = new JPanel();
	private BotonCarta jugar;
	private BotonCarta rank;
	private BotonCarta load;
	private BotonCarta salir;
	private BotonCarta[] botones;
	
	public MenuPrincipal(ImageManager imageManager) {
		this.setLayout(new BorderLayout(100,100));
		this.setButtons(imageManager);
		this.setVacio();
		this.setMenu(imageManager);
	}

	public BotonCarta getJugar() {
		return jugar;
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

	public void setMenu(ImageManager imageManager) {
		JPanel panelBotones = new JPanel();
		JPanel title = new JPanel();
		
		// Preparo el título.
		title.setLayout(new FlowLayout());
		JLabel menuPrincipalText = new JLabel("Menu Principal");
		menuPrincipalText.setHorizontalAlignment(JLabel.CENTER);
		menuPrincipalText.setVerticalAlignment(JLabel.BOTTOM);
		Fuente customFont = new Fuente("files/fonts/Pokemon Classic.ttf", 60);
		menuPrincipalText.setFont(customFont.font());
		title.add(menuPrincipalText);
		title.add(new JLabel(imageManager.imagenCarta("AS_PICA")));
		title.add(new JLabel(imageManager.imagenCarta("CABALLERO_PICA")));
		
		// Preparo los botones.
		panelBotones.setLayout(new GridLayout(1, 4, 10, 10));
		for (BotonCarta bt : this.botones) {
			panelBotones.add(bt);
		}
		
		this.menu.setLayout(new GridLayout(2,1));
		this.menu.add(title);
		this.menu.add(panelBotones);
		
		this.add(this.menu, BorderLayout.CENTER);
	}

	private void setButtons(ImageManager imageManager) {
		this.jugar = new BotonCarta("Jugar", imageManager);
		this.rank = new BotonCarta("Ranking", imageManager);
		this.load = new BotonCarta("Load", imageManager);
		this.salir = new BotonCarta("Salir", imageManager);
		
		Rand random = new Rand();
		this.botones = new BotonCarta[4];
		int[] lista = random.randomList(this.botones.length);
		
		this.botones[lista[0] - 1] = this.jugar;
		this.botones[lista[1] - 1] = this.rank;
		this.botones[lista[2] - 1] = this.load;
		this.botones[lista[3] - 1] = this.salir;
	}
}
