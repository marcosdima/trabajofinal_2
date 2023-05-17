package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.tools.Escaner;
import ar.edu.unlu.tools.Print;
import ar.edu.unlu.trabajofinal.IJugador;

public class VistaConsola implements IVista {
	private Escaner escaner;
	private Controlador controlador;
	private Print p;

	public VistaConsola(Controlador cc) {
		this.escaner = new Escaner();
		this.p = new Print();
		this.controlador = cc;
		this.controlador.addVista(this);
	}

	@Override
	public void menuPrincipal() {
		p.print("--- Menu principal ---");
		p.print("1. Jugar");
		p.print("2. Salir");
		
		int eleccion = this.escaner.nextInt();
		p.espacio();
		
		switch(eleccion) {
		case 1:
			this.controlador.startGame();
			break;
			
		case 2:
			System.out.println("Hasta Luego!");
			System.exit(0);
			break;	
		}
	}

	@Override
	public String ingresoDeApuesta() {
		p.print("Ingresar apuesta: ");
		String monto = this.escaner.next();
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
	public void mostrarMesa(ArrayList<IJugador> mesa) {
		this.printJugadores(mesa);
		p.espacio();
	}

	@Override
	public String formularioDeIngreso() {
		p.print("Ingresa un nombre: ");
		String name = this.escaner.next();
		p.espacio();

		return name;
	}

	private void printJugadores(ArrayList<IJugador> datos) {
		int contador = 0;
		int espacio = 25;
		int size = datos.size();
		String[] conjuntoNombres = new String[size];
		ArrayList<String[]> conjuntoCartas = new ArrayList<>(size);
		String[] conjuntoPuntajes = new String[size];
		String[] dinerillo = new String[size];

		for (IJugador dato : datos) {
			conjuntoNombres[contador] = dato.getNombre();
			conjuntoCartas.add(dato.getArrayCartas());
			conjuntoPuntajes[contador] = "Puntaje: " + String.valueOf(dato.getPuntaje());

			// No le agrega el dinero al crupier.
			if (contador != (size - 1)) {
				dinerillo[contador] = "Dinero: " + String.valueOf(dato.getDinero());
			}
			else {
				dinerillo[contador] = "";
			}
			contador++;
		}

		p.printSeguido(conjuntoNombres, espacio);
		p.printSeguido(conjuntoCartas, espacio);
		p.printSeguido(conjuntoPuntajes, espacio);
		p.printSeguido(dinerillo, espacio);

	}

	@Override
	public void mostrarMensaje(Mensaje msj) {
		p.justPrint(msj.getMensaje());
		p.espacio();
	}
}
