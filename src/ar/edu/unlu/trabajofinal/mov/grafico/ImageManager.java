package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

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

	public ImageIcon imagenScaled(String path, int ancho, int alto) {
		Image scaled = this.imagen(path).getImage();	
		
		ImageIcon scaledIcon = new ImageIcon(scaled.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
		
		return scaledIcon;
	}
	
	public ImageIcon imagen(String path) {
		String direccion = path;
		
		ImageIcon img = new ImageIcon(direccion);
		// .getScaledInstance(ancho, ancho, Image.SCALE_SMOOTH)
		return img;
	}

	public ImageIcon imagenCarta(String tag, int ancho, int alto) {
		String direccion = this.source + "/" + folderCarta + "/" + tag;
		
		if (!direccion.endsWith(".png")) {
			direccion += ".png";
		}

		ImageIcon res = this.imagenScaled(direccion, alto, ancho);

		return res;
	}
	
	public ImageIcon imagenCarta(String tag) {
		String direccion = this.source + "/" + folderCarta + "/" + tag;
		
		if (!direccion.endsWith(".png")) {
			direccion += ".png";
		}

		ImageIcon res = this.imagen(direccion);

		return res;
	}

	public ImageIcon randCard() {
		Random rand = new Random();
		int randomInt = rand.nextInt(this.nombresDeCartas.length);
		return this.imagenCarta(this.nombresDeCartas[randomInt]);
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

