package ar.edu.unlu.mazo;

import ar.edu.unlu.tools.Pila;
import ar.edu.unlu.tools.Rand;

public abstract class Mazo extends ConjuntoDeCartas {

	private static final long serialVersionUID = 1L;
	private Pila<Carta> baraja;
	private Pila<Carta> descarte;
	public Rand random;

	public Mazo(int tam) {
		super(tam);
		this.random = new Rand();
		this.setDescarte();
	}

	public void barajar() {
		int tamanio = this.getTam();
		int[] lista = random.randomList(tamanio);
		Pila<Carta> contenedor = new Pila<>(tamanio);
		Carta cartaAux;

		for (int numero : lista) {
			cartaAux = this.getCartas().get(numero - 1);
			cartaAux.setVisibilidad(false);
			contenedor.apilar(cartaAux);
		}

		this.setBaraja(contenedor);
		this.descarte.vaciar();
	}

	public Pila<Carta> getBaraja() {
		return baraja;
	}

	public Pila<Carta> getDescarte() {
		return this.descarte;
	}

	public void setBaraja(Pila<Carta> baraja) {
		this.baraja = baraja;
	}

	public Carta agarrarCarta() {

		if (this.baraja == null) {
			this.barajar();
		}

		Carta res = this.baraja.getTope();
		this.descarte.apilar(res);
		this.baraja.desapilar();

		return res;
	}

	public void setDescarte() {
		this.descarte = new Pila<>(this.getTam());
	}
}
