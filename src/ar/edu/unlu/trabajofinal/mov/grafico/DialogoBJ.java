package ar.edu.unlu.trabajofinal.mov.grafico;

import ar.edu.unlu.trabajofinal.mov.Controlador;

public class DialogoBJ extends Dialogo {
	private static final long serialVersionUID = 1L;
	private Controlador controller;

	public DialogoBJ(String title, String text, Controlador cc) {
		super(title, text);
		this.controller = cc;
	}

	public void event() {
		this.apuesta();
	}
	
	private void apuesta() {
		this.controller.apostar(this.getText());
		this.clear();
	}
}
