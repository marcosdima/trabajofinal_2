package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mazo.Carta;
import ar.edu.unlu.mazo.ContenidoDeCarta;
import ar.edu.unlu.mazo.MazoDeNaipes;

public class Crupier extends Jugador {
	private MazoDeNaipes mazo = new MazoDeNaipes();
	// private BlackJack mesa;
	
	public Crupier() {
		super("Crupier", 0);	
	}
	
	// Calcula el puntaje de un jugador.
	public int calcPuntaje(Jugador player) {
		int puntaje = 0;
		ContenidoDeCarta content = null;
		
		for (Carta carta : player.getMano().sort()) {
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
	
	// Le da una carta a un jugador.
	public void darCarta(Jugador player) {
		Carta cartita = this.mazo.agarrarCarta();
		player.addCarta(cartita);
	}

	// Mezcla el mazo.
	public void barajar() {
		this.mazo.barajar();
	}
	
	// Retorna el estado de la mano de un jugador.
	public EstadoDeMano getEstado(Jugador player) {
		EstadoDeMano res = null;
		int puntaje = this.calcPuntaje(player);
		boolean primeraMano = (player.cantidadDeCartas() == 2);
		boolean puntaje21 = (puntaje == 21);
		
		if (primeraMano && puntaje21) {
			res = EstadoDeMano.BLACKJACK;
		}
		else if (puntaje21) {
			res = EstadoDeMano.IGUALA21;
		}
		else if (puntaje < 21) {
			res = EstadoDeMano.MENORA21;
		}
		else {
			res = EstadoDeMano.MAYORA21;
		}
		
		return res;
	}
}
