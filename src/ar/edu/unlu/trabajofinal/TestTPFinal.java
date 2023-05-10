package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mazo.*;

public class TestTPFinal {

	public static void main(String[] args) {
		MazoDeNaipes m = new MazoDeNaipes();
		Carta cartita = m.agarrarCarta();
		Carta as = new Carta(Palo.PICA, ContenidoDeCarta.AS);
		Crupier crup = new Crupier();
		JugadorBJ player = new JugadorBJ("a", 10000);
		
		player.addCarta(cartita);
		System.out.println(player.getDinero());
		player.apostar(1000);
		System.out.println(player.getDinero());
		player.gano();
		System.out.println(player.getDinero());
		player.cobrar();
		System.out.println(player.getDinero());
		player.cobrar();
		System.out.println(player.getDinero());
		
		player.addCarta(as);
		player.addCarta(as);
		
		System.out.println(crup.calcPuntaje(player));
		System.out.println(cartita.getDesc());
		

	    
	}

}
