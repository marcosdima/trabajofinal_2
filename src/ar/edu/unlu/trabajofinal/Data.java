package ar.edu.unlu.trabajofinal;

import java.io.Serializable;

public class Data<Info> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private Info informacion;
	private int remitente;

	public Data(Evento evento, Info info, int remitente) {
		this.informacion = info;
		this.evento = evento;
		this.remitente = remitente;
	}

	public Evento evento() {
		return this.evento;
	}

	public Info info() {
		return this.informacion;
	}

	public int remitente() {
		return this.remitente;
	}

}
