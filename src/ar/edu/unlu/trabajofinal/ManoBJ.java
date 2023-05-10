package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mazo.Carta;
import ar.edu.unlu.mazo.ConjuntoDeCartas;
import ar.edu.unlu.mazo.ContenidoDeCarta;

public class ManoBJ extends ConjuntoDeCartas {
	
	public ManoBJ(int tam) {
		super(tam);	
	}
	
	// Retorna el puntaje de la mano.
	public int getPuntaje() {
		int puntaje = 0;
		ContenidoDeCarta content = null;
		
		for (Carta carta : this.getCartas()) {
			content = carta.getContenido();
			
			if (content == ContenidoDeCarta.AS) {
				if (puntaje >= 11) {
					puntaje += 1;
				}
				else {
					puntaje += 11;
				}
			}
			else if (content == ContenidoDeCarta.CABALLERO || content == ContenidoDeCarta.REY || content == ContenidoDeCarta.REINA) {
				puntaje += 10;
			}
			else {
				puntaje += carta.getValor();
			}
		}
		
		return puntaje;
	}

	// Retorna true si la mano ya se no puede seguir jugando,
	public boolean noMore() {
		boolean res = false;
				
		if (this.getPuntaje() >= 21) {
			res = true;
		}
		
		return res;
	}
	
}
