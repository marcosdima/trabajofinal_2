package ar.edu.unlu.mov;

import ar.edu.unlu.trabajofinal.BlackJack;

public class Controlador {
	private int id = -1; // -1 representa el nulo, ya que los id's siempre son positivos.
	private BlackJack modelo;
	// private ArrayList<IVista> vistas;
	// private IVista vistaPrincipal
	
	public Controlador() {
		this.setVistas();
	}
	
	// Ingresa al jugador al juego, seteando su id.
	public void startGame(String name) {
		if (id < 0) {
			this.id = this.modelo.newPlayer(name);
		}
	}
	
	public void apostar(float monto) {
		this.modelo.registrarApuesta(monto, this.id);
	}
	
	public void quieroOtraCarta() {
		this.modelo.repartir();
	}
	
	public void noQuieroMas() {
		this.modelo.terminarTurnoA(this.id);
	}
	
	private void setVistas() {
		
	}
	
}
