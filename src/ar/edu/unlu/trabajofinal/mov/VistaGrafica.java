package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.IJugador;
import ar.edu.unlu.trabajofinal.mov.grafico.Frame;
import ar.edu.unlu.trabajofinal.mov.grafico.ImageManager;
import ar.edu.unlu.trabajofinal.mov.grafico.MenuPrincipal;

public class VistaGrafica implements IVista {
	private Controlador controller;
	private Frame framePrincipal = new Frame("Black Jack ♦♥♣♠");
	private ImageManager imageManager;
	
	private MenuPrincipal menuPrincipal;

	public VistaGrafica(Controlador cc) {
		this.setController(cc);
		this.setMenuPrincipal();
	}
	
	@Override
	public void menuPrincipal() {
		
		
	}

	@Override
	public String ingresoDeApuesta(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean preguntaQuieroOtra(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mostrarMesa(ArrayList<IJugador> mesa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String formularioDeIngreso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarMensaje(String msj) {
		// TODO Auto-generated method stub
		
	}
	
	public void setController(Controlador controller) {
		this.controller = controller;
		controller.addVista(this);
	}

	public void setMenuPrincipal() {
		//this.menuPrincipal = new MenuPrincipal();
	}
}
