package ar.edu.unlu.trabajofinal.mov;

public enum Mensaje {
	FINDEMANO("La mano termino."),
	FINDEJUEGO("Gracias por jugar!");

	String mensaje;
	
	Mensaje(String msj) {
		this.mensaje = msj;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	
}




