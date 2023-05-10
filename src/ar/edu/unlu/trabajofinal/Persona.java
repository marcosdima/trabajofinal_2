package ar.edu.unlu.trabajofinal;

public class Persona {

	private String nombre;
	private int dinero;

	public Persona(String nombre, int dinero) {
		this.setNombre(nombre);
		this.setDinero(dinero);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public void giveDinero(int monto) {
		this.dinero += monto;
	}
	
	public boolean pagar(int monto) {
		boolean pago = false;
		
		if (this.getDinero() > monto) {
			pago = true;
			this.setDinero(this.getDinero() - monto);
		}
		
		return pago;
	}
}
