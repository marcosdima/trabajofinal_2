package ar.edu.unlu.trabajofinal;

import java.util.ArrayList;

import ar.edu.unlu.mazo.Carta;
import ar.edu.unlu.mazo.ConjuntoDeCartas;

public class Jugador extends Persona implements IJugador{
	private static final long serialVersionUID = 1L;
	private ConjuntoDeCartas mano = new ConjuntoDeCartas(7);
	private int id = 0;
	private int puntaje = 0;

	public Jugador(String nombre, int dinero) {
		super(nombre, dinero);
	}

	public ConjuntoDeCartas getMano() {
		return this.mano;
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

	@Override
	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int cantidadDeCartas() {
		return this.mano.size();
	}

	@Override
	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int pts) {
		this.puntaje = pts;
	}

	@Override
	public String[] getArrayCartas() {
		return this.mano.getArrayCartas();
	}
}
