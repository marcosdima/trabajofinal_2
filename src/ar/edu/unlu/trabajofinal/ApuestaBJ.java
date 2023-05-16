package ar.edu.unlu.trabajofinal;

public class ApuestaBJ extends Apuesta {

	public ApuestaBJ(float monto, String apostador) {
		super(monto, apostador);
	}

	public void blackJack() {
		this.gano(100);
	}

	public void manoGanadora() {
		this.gano(50);
	}
}
