package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mov.*;

public class TestTPFinal {

	public static void main(String[] args) {
		
		BlackJack bj = new BlackJack();
		Controlador c = new Controlador(bj);
	
		c.startGame();
		
	}
}
