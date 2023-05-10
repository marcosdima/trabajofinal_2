package ar.edu.unlu.mazo;

import java.util.ArrayList;

public class ConjuntoDeCartas {
	
	private ArrayList<Carta> cartas;
	private int tam;
	
	public ConjuntoDeCartas(int tam) {

		this.setTam(tam);
		this.cartas = new ArrayList<Carta>(tam);
		
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
	
}
