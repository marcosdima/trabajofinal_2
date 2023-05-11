package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mazo.*;

public class TestTPFinal {

	public static void main(String[] args) {

		Carta as = new Carta(Palo.PICA, ContenidoDeCarta.AS);
		Carta cartita = new Carta(Palo.PICA, ContenidoDeCarta.NUEVE);
		Crupier crup = new Crupier();
		JugadorBJ player = new JugadorBJ("a", 10000);
			
		// Test de apuestas
		/*
		System.out.println(player.getDinero());
		player.apostar(1000);
		System.out.println(player.getDinero());
		player.gano();
		System.out.println(player.getDinero());
		player.cobrar();
		System.out.println(player.getDinero());
		player.cobrar();
		System.out.println(player.getDinero());
		*/
		
		player.addCarta(as);
		player.addCarta(cartita);
		//player.addCarta(cartita);

		
		System.out.println();
		System.out.println(crup.calcPuntaje(player));
		System.out.println(crup.getEstado(player));
		System.out.println();
	}

}
