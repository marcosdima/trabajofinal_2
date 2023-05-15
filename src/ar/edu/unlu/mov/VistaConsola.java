package ar.edu.unlu.mov;

import ar.edu.unlu.tools.Escaner;
import ar.edu.unlu.tools.Print;

public class VistaConsola implements IVista {
	private Escaner escaner;
	private Controlador controller;
	private Print p;
	
	
	public VistaConsola() {
		this.escaner = new Escaner();
		this.p = new Print();
	}
	
	@Override
	public void menuPrincipal() {
		p.print("Menu principal");
		p.print("1. Jugar");
		p.print("2. salir");
		int eleccion = this.escaner.nextInt();
		if (eleccion == 1) {
			this.controller.startGame();
		}
	}

	@Override
	public int ingresoDeApuesta() {
		p.print("Ingresar apuesta: ");
		int monto = this.escaner.nextInt();
		p.espacio();
		
		return monto;
	}

	@Override
	public boolean preguntaQuieroOtra() {
		p.print("¿Quieres una carta?");
		boolean quiere = this.escaner.siONo();
		p.espacio();
		
		return quiere;
	}

	@Override
	public void mostrarMesa() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String formularioDeIngreso() {
		p.print("Ingresa un nombre: ");
		String name = this.escaner.next();
		p.espacio();
		
		return name;
	}

}
