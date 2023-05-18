package ar.edu.unlu.trabajofinal;

public enum Evento {
	SOLICITARAPUESTAS("Ingrese su apuesta:"),
	APUESTAMINIMA("El monto ingresado es menor a la apuesta mínima. Recuerde que la apuesta mínima es de {apuesta_minima}"),
	APUESTASINFONDOS("No podes apostar más de lo que tenes!"),
	APUESTANOVALIDA("El monto ingresado no es válido! Asegurese de ingresar un número."),

	PREGUNTARPORCARTA("¿Quieres otra carta?"),
	
	FINDEMANO("La mano se terminó! Que arranque la siguiente..."),
	FINDEJUEGO("Gracias por jugar!"),
	
	ESOYAM("Ah sh*t, here we go again...");
	
	
	private String mensaje;
	
	Evento(String msj) {
		this.mensaje = msj;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
}
