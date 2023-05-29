package ar.edu.unlu.trabajofinal;

public class JugadorBJ extends Jugador {
	private static final long serialVersionUID = 1L;
	private boolean yaAposto = false;
	private boolean yaJugo = false;
	private boolean sigueJugando = false;
	private ApuestaBJ apuesta;
	
	public JugadorBJ(String nombre, int dinero) {
		super(nombre, dinero);
	}

	// Si puede, realiza una apuesta.
	public void apostar(float monto) {
		boolean flagPago = this.pagar(monto);

		if (flagPago) {
			this.apuesta = new ApuestaBJ(monto, this.getNombre());
			this.yaAposto = true;
		}
	}

	// El jugador gano con BJ.
	public void blackjack() {
		this.apuesta.blackJack();
	}

	// El jugador gano la mano.
	public void gano() {
		this.apuesta.manoGanadora();
	}

	// El jugador empato con el crupier.
	public void empate() {
		this.apuesta.empato();
	}

	// Cobra la apuesta.
	public void cobrar() {
		this.giveDinero(this.apuesta.getGanancia());
		this.apostar(0);
	}

	// Pone los flags en false.
	public void reset() {
		this.yaAposto = false;
		this.yaJugo = false;
		this.sigueJugando = false;
		this.setPuntaje(0);
		this.vaciarMano();
	}

	// Setea en true 'yaJugo'
	public void terminoTurno() {
		this.yaJugo = true;
	}

	// Setea en true 'sigueJugando'
	public void sigue() {
		this.sigueJugando = true;
	}
	
	public boolean yaAposto() {
		return this.yaAposto;
	}

	public boolean yaJugo() {
		return this.yaJugo;
	}

	public boolean sigueJugando() {
		return this.sigueJugando;
	}
}
