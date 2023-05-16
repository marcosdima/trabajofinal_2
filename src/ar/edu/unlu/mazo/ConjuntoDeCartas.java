package ar.edu.unlu.mazo;

import java.util.ArrayList;

public class ConjuntoDeCartas {

	private ArrayList<Carta> cartas;
	private int tam;

	public ConjuntoDeCartas(int tam) {
		this.setTam(tam);
		this.cartas = new ArrayList<>(tam);
	}

	public void addCarta(Carta carta) {
		this.cartas.add(carta);
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public void clear() {
		this.cartas.clear();
	}

	public boolean contains(ContenidoDeCarta carta) {
		boolean res = false;

		for (Carta cartita : this.cartas) {
			if (cartita.getLabel() == carta.getLabel()) {
				res = true;
			}
		}

		return res;
	}

	public int size() {
		return this.cartas.size();
	}

	// Retorna una array ordenado de forma descendente con las cartas del conjunto.
	public ArrayList<Carta> sort() {
		ArrayList<Carta> res = this.getCartas();
		int largo = this.size();

		for (int i = 0; i < largo; i++) {
			for (int e = 0; e < (largo - 1); e++) {
				if (res.get(e).getValor() < res.get(e+1).getValor()) {
					this.intercambiar(res, e, e+1);
				}
			}
			largo--;
		}

		return res;
	}

	private void intercambiar(ArrayList<Carta> array, int indexA, int indexB) {
		Carta cartaAux = array.get(indexA);
		array.set(indexA, array.get(indexB));
		array.set(indexB, cartaAux);
	}

	// Retorna un array de strings con la info de las cartas del conjunto.
	public String[] getArrayCartas() {
		int size = this.size();
		int contador = 0;
		String[] cartas = new String[size];

		for (Carta cartita : this.getCartas()) {
			if (cartita.esVisible()) {
				cartas[contador] = cartita.getDesc();
			}
			else {
				cartas[contador] = "Cubierta";
			}

			contador++;
		}

		return cartas;
	}
}
