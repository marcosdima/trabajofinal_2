package ar.edu.unlu.tools;

import java.io.Serializable;

public class Pila<Cosa> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int tam;
	private int nroNodos;
	private NodoPila<Cosa> tope;

	private final int MAX = 1000;

	public Pila(int tam) {

		this.setTam(tam);
		this.tope = new NodoPila<>();
		this.nroNodos = 0;

	}

	public Pila() {
		this (10);
	}

	// Setea el tamaño.
	private void setTam(int tam) {

		if ((tam <= this.MAX) && (tam > 0)){

			this.tam = tam;

		}

	}

	// Retorna el tamaño.
	public int getTam() {
		return this.tam;
	}

	// Retorna el nodo tope.
 	public Cosa getTope() {
		return this.tope.getElemento();
	}

	// Retorna true si el tope es nulo.
	public boolean estaVacia() {
		return this.nroNodos == 0;
	}

	// Retorna true si esta llena.
	public boolean estaLlena() {

		boolean res = false;

		if (this.nroNodos == this.tam) {

			res = true;

		}

		return res;

	}

	// Retorna la cantidad de nodos.
	public int getCant() {
		return this.nroNodos;
	}

	// Define a tope como tope.anterior (Puede ser nulo, si se desapila el único elemento de la pila).
	public void desapilar() {

		boolean vacia = this.estaVacia();

		if (!vacia) {
			this.tope = this.tope.getAnterior();
			this.nroNodos--;
		}

	}

	// Apila el elemento (Si no esta llena ni el elemento es null).
	public void apilar(Cosa elemento) {

		boolean llena = this.estaLlena();

		NodoPila<Cosa> elementoAux = new NodoPila<>(elemento);

		if ((!llena)){

			elementoAux.setAnterior(this.tope);
			this.tope = elementoAux;
			this.nroNodos++;

		}

	}

	// Pasa el contenido de la pila, a la otra (invirtiendolo).
	public void pasarContenido(Pila<Cosa> pila) {

		boolean vacia = this.estaVacia();
		int auxInt = this.nroNodos;
		pila.vaciar();

		if ((!vacia)) {
			for (int i = 0; i < auxInt; i++) {
				pila.apilar(this.getTope());
				this.desapilar();
			}
		}
	}

	// Retorna true si esta pila y la que pasan por parámetro tienen el mismo tamaño.
	public boolean sameTam(Pila<Cosa> pila) {

		boolean res = (this.tam == pila.getTam());
		return res;

	}

	// Vacia la pila. (Pone en null el tope)
	public void vaciar() {
		this.tope = null;
	}

	// Remueve la primera aparición de c en la Pila.
	public void remove(Cosa c) {
		Pila<Cosa> pilaAux = new Pila<Cosa>(this.nroNodos);
		boolean found = false;
	
		while (this.tope != null && this.tope.getElemento() != null) {

			if ((!found) && (this.getTope().toString().equals(c.toString()))) {
				found = true;
				this.desapilar();
			} 
			else {

				pilaAux.apilar(this.getTope());
				this.desapilar();
			}
		}

		pilaAux.pasarContenido(this);
	}
}
