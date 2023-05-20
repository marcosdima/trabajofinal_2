package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Frame(String title) {
		super(title);
		this.setSize(1024, 768);
		this.setBackground(new Color(10,10,10));
	}

	// Activar.
	public void turnOn() {
		this.setVisible(true);
	}
	
	// Apagar
	public void turnOff() {
		this.setVisible(false);
	}

}
