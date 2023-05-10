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
		this.ganancia = (this.monto * porcentajeDeGanancia) / 100;

	}
	
	public float getGanancia() {
		return this.ganancia;
	}

	public String getApostador() {
		return this.apostador;
	}
	
	public int getGananciaTotal() {
		int gananciaTotal = 0;
		
		if (this.ganancia > 0) {
			gananciaTotal = (int) this.ganancia + this.monto;
		}
		
		return gananciaTotal;
	}
}
