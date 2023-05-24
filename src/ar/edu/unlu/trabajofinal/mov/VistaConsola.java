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
		p.printConEspacio("--- Menu principal ---");
		p.print("1. Jugar");
		p.print("2. Ranking");
		p.print("3. Cargar");
		p.printConEspacio("4. Salir");
		
		int eleccion = this.escaner.nextInt();
		p.espacio();
		
		switch(eleccion) {
			case 1:
				this.controlador.startGame();
				break;
				
			case 2:
				p.print("Algun día va a funcar...");
				this.menuPrincipal();
				break;
		
			case 3:
				p.print("Algun día va a funcar...");
				this.menuPrincipal();
				break;
			
			case 4:
				p.print("Hasta luego!");
				System.exit(0);
				break;	
		}			
	}

	@Override
	public void ingresoDeApuesta(String texto) {
		p.print(texto);
		String monto = this.escaner.next();
		p.espacio();

		this.controlador.apostar(monto);
	}

	@Override
	public void preguntaQuieroOtra(String texto) {
		p.print(texto);
		boolean quiere = this.escaner.siONo();
		p.espacio();

		this.controlador.otraCarta(quiere);
	}

	@Override
	public void mostrarMesa(ArrayList<IJugador> mesa) {
		this.printJugadores(mesa);
		p.espacio();
	}

	@Override
	public String formularioDeIngreso() {
		p.print("ingrese su nombre:");
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
	public void mostrarMensaje(String msj) {
		p.justPrint(msj);
		p.espacio();
	}
}
