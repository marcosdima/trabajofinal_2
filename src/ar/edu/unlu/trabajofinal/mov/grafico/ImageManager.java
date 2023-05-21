package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageManager {
	
	// El path a la carpeta 'cartas'.
	private String source;
	// La carpeta de cartas seleccionada.
	private String folderCarta;
	private String[] nombresDeCartas;
	
	public ImageManager(String direccion, String folderCarta) {
		this.setSource(direccion);
		this.setFolderCarta(folderCarta);
		this.setNombres();
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public void setFolderCarta(String folderCarta) {
		this.folderCarta = folderCarta;
	}

	public ImageIcon imagen(String tag, int ancho, int largo) {
		String direccion = this.source + "/" + this.folderCarta + "/" + tag;
		
		if (!direccion.endsWith(".png")) {
			direccion += ".png";
		}
		
		ImageIcon img = new ImageIcon(direccion);
		// .getScaledInstance(ancho, ancho, Image.SCALE_SMOOTH)
		return img;
	}

	public JLabel imagenCarta(String tag) {
		
		String direccion = this.source + "/" + folderCarta + "/" + tag;
		
		if (!direccion.endsWith(".png")) {
			direccion += ".png";
		}
		
		Image img = new ImageIcon(direccion).getImage();
		ImageIcon img2 =new ImageIcon(img);
		JLabel label = new JLabel(img2);

		return label;
	}

	public ImageIcon randCard(int ancho, int largo) {
		Random rand = new Random();
		int randomInt = rand.nextInt(this.nombresDeCartas.length);
		return this.imagen(this.nombresDeCartas[randomInt], ancho, largo);
	}
	
	private void setNombres() {
		File dir = new File(this.source + "/" + this.folderCarta);
		File[] archivos = dir.listFiles();
		String[] strs = new String[archivos.length];
		int contador = 0;
		
		for (File archivo : archivos) {
			strs[contador] = archivo.getName();
			contador++;		
		}
		
		this.nombresDeCartas = strs;
	}
}

