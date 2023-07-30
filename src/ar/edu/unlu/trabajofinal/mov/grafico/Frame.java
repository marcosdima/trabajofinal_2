package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private Component lastComponent;
	
	public Frame(String title) {
		super(title);
		this.setSize(1240, 900);
		this.setBackground(new Color(10,10,10));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Activar.
	public void turnOn() {
		this.setVisible(true);
	}
	
	// Apagar
	public void turnOff() {
		this.setVisible(false);
	}

	public Component add(Component c) {
		if (this.lastComponent != null) {
			this.remove(this.lastComponent);
		}
		
		super.add(c);
		
		this.lastComponent = c;
		return c;
	}
}
