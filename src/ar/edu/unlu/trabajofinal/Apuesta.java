package ar.edu.unlu.trabajofinal;

public class Apuesta {
	private int monto = 0;
	private float ganancia = 0;
	private String apostador = "";
	
	public Apuesta(int monto, String apostador) {
		this.monto = monto;
		this.apostador = apostador;
	}
	
	public void gano(int porcentajeDeGanancia) {
		this.ganancia = ((this.monto * porcentajeDeGanancia) / 100) + this.monto;
	}
	
	public void empato() {
		this.ganancia = this.monto;
	}
	
	public float getGanancia() {
		return this.ganancia;
	}

	public String getApostador() {
		return this.apostador;
	}
}
