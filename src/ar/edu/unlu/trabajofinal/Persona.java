package ar.edu.unlu.trabajofinal;

import java.io.Serializable;

public class Persona  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private float dinero;

	public Persona() {
		
	}
	
	public Persona(String nombre, float dinero) {
		this.setNombre(nombre);
		this.setDinero(dinero);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getDinero() {
		return dinero;
	}

	public void setDinero(float dinero) {
		this.dinero = dinero;
	}

	public void giveDinero(float monto) {
		this.dinero += monto;
	}

	public boolean pagar(float monto) {
		boolean pago = false;

		if (this.getDinero() >= monto) {
			pago = true;
			this.setDinero(this.getDinero() - monto);
		}

		return pago;
	}
}
