package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fuente {
	
	Font fuente;

	public Fuente(String ruta, int size) {
		this.setFuente(ruta, size);
	}
	
	public Fuente(int size) {
		this("files/fonts/Pokemon Classic.ttf", size);
	}
	
	public void setFuente(String ruta, int size) {
		try {
			this.fuente = Font.createFont(Font.TRUETYPE_FONT, new File(ruta));
			this.fuente  = this.fuente.deriveFont(Font.PLAIN, size);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Font font() {
		return this.fuente;
	}	
}
