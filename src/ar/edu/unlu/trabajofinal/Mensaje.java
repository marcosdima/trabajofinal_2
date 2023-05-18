package ar.edu.unlu.trabajofinal;

public enum Mensaje {
	FINDEMANO("La mano termino."),
	APUESTAMINIMA("El monto ingresado es menor a la apuesta mínima. Recuerde que la apuesta mínima es de {apuesta_minima}"),
	APUESTASINFONDOS("No podes apostar más de lo que tenes!"),
	APUESTANOVALIDA("El monto ingresado no es válido! Asegurese de ingresar un número."),
	FINDEJUEGO("Gracias por jugar!");

	String mensaje;
	
	Mensaje(String msj) {
		this.mensaje = msj;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	
}




