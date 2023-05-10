package ar.edu.unlu.trabajofinal;

import java.util.ArrayList;

import ar.edu.unlu.mazo.Carta;
import ar.edu.unlu.mazo.ConjuntoDeCartas;

public class Jugador extends Persona {
	private ConjuntoDeCartas mano = new ConjuntoDeCartas(7);
	private int id = 0;
	
	public Jugador(String nombre, int dinero) {
		super(nombre, dinero);
		
	}
	
	public ArrayList<Carta> getCartas() {
		return this.mano.getCartas();
	}
	
	public void addCarta(Carta c) {
		this.mano.addCarta(c);
	}
	
	public void vaciarMano() {
		this.mano.clear();
	}

	public int getID() {
		return this.id;
	}
	
	public int cantidadDeCartas() {
		return this.mano.getTam();
	}
}
