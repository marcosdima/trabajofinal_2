package ar.edu.unlu.trabajofinal.mov.grafico;

import java.util.ArrayList;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaRank extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public VentanaRank(ArrayList<String> rank) {
		super("Ranking");
		this.setSchema(rank);
		this.setSize(400, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public void setSchema(ArrayList<String> rank) {
		JPanel panel = new JPanel();
		GridLayout lay = new GridLayout(rank.size(), 1, 10, 10);
	
		panel.setLayout(lay);
		
		for (int i = 0; i < rank.size(); i++) {
			String line = this.setLine(rank.get(i));
			JLabel tag = new JLabel((i + 1) + ". " + line);
			tag.setHorizontalAlignment(JLabel.CENTER);
			tag.setFont(new Fuente(12).font());
			panel.add(tag);
		}		
		
		this.add(panel);
	}

	private String setLine(String line) {
		String res = "";
		String num = "";
		String nombre = "";
		char[] charArray = line.toCharArray();
		char charFlag = '0';
		int contador = line.length() - 1;
		int i = 0;
		
		while ((contador >= 0) && charFlag != ',') {
			charFlag = charArray[contador];
			contador--;
		}
		
		// Seteo el nombre.
		for (i = 0; i < contador + 1; i++) {
			nombre += charArray[i];
		}
		
		// Seteo el puntaje.
		for (i = contador + 2; i < line.length(); i ++) {
			num += charArray[i];
		}
		
		res = nombre + "- " + num;
		
		return res;
	}
}
