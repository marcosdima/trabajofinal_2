package ar.edu.unlu.trabajofinal;

public class JugadorBJ extends Jugador {
	private ApuestaBJ apuesta;
	
	public JugadorBJ(String nombre, int dinero) {
		super(nombre, dinero);
		this.apostar(0);
	}
	
	// Si puede, realiza una apuesta.
	public void apostar(int monto) {
		boolean flagPago = this.pagar(monto);
		
		if (flagPago) {
			this.apuesta = new ApuestaBJ(monto, this.getNombre());
		}
	}
	
	// El jugador gano con BJ. FALTA VER DARLE LAS GANANCIAS!
	public void blackjack() {
		this.apuesta.blackJack();
	}
	
	// El jugador gano la mano.
	public void gano() {
		this.apuesta.manoGanadora();
	}
	
	// Cobra la apuesta.
	public void cobrar() {
		this.giveDinero(this.apuesta.getGananciaTotal());
		this.apostar(0);
	}
}
