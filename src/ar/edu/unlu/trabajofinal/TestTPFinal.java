package ar.edu.unlu.trabajofinal;

import ar.edu.unlu.mov.Controlador;

public class TestTPFinal {

	public static void main(String[] args) {

		BlackJack bj = new BlackJack();
		Controlador c = new Controlador(bj);

		c.startGame();

	}
}
