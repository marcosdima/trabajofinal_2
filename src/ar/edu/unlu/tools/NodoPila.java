package ar.edu.unlu.tools;

import java.io.Serializable;

public class NodoPila<Cosa> implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cosa elemento;
	private NodoPila<Cosa> anterior;

	public NodoPila(Cosa elemento, NodoPila<Cosa> anterior) {

		this.setCosa(elemento);
		this.setAnterior(anterior);

	}

	public NodoPila(Cosa dato) {

		this (dato, null);

	}

	public NodoPila() {

		this (null, null);

	}

	public Cosa getElemento() {
		return elemento;
	}

	public void setCosa(Cosa dato) {
		this.elemento = dato;
	}

	public NodoPila<Cosa> getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoPila<Cosa> anterior) {
		this.anterior = anterior;
	}

	public boolean esNulo() {

		boolean res = false;

		if (this.elemento == null) {

			res = true;

		}

		return res;

	}

	public boolean esInicial() {

		boolean res = false;

		if (this.anterior == null) {

			res = true;

		}

		return res;

	}

}
