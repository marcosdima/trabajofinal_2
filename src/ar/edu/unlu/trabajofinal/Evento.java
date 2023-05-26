package ar.edu.unlu.trabajofinal;

public enum Evento {
	// Apuestas.
	SOLICITARAPUESTAS("Ingrese su apuesta:"),
	APUESTAMINIMA("El monto ingresado es menor a la apuesta mínima. Recuerde que la apuesta mínima es de {apuesta_minima}"),
	APUESTASINFONDOS("No podes apostar más de lo que tenes!"),
	APUESTANOVALIDA("El monto ingresado no es válido! Asegurese de ingresar un número."),

	// Pregunta otra carta.
	PREGUNTARPORCARTA("¿Quieres otra carta?"),
	
	// Finales.
	FINDEMANO("La mano se terminó! Que arranque la siguiente..."),
	FINDEJUEGO("Gracias por jugar!"),
	
	// Trucos.
	ESOYAM("Ah sh*t, here we go again..."),
	
	// Mensajes.
	MSJ("Mensaje");
	
	private String mensaje;
	
	Evento(String msj) {
		this.mensaje = msj;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	
	public void setMensaje(String msj) {
		this.mensaje = msj;
	}
}
