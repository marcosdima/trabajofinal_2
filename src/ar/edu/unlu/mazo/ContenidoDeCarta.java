package ar.edu.unlu.mazo;

import java.util.HashMap;
import java.util.Map;

public enum ContenidoDeCarta {

	AS(1, "As"),
	DOS(2, "2"),
	TRES(3, "3"),
	CUATRO(4, "4"),
	CINCO(5, "5"),
	SEIS(6, "6"),
	SIETE(7, "7"),
	OCHO(8, "8"),
	NUEVE(9, "9"),
	DIEZ(10, "10"),
	CABALLERO(11, "Caballero"),
	REINA(12, "Reina"),
	REY(13, "Rey");

	private int valor;
	private String key;

	ContenidoDeCarta(int valor, String key) {
		this.valor = valor;
		this.key = key;
	}

	public int getValor() {
		return this.valor;
	}

	public String getLabel() {
		return this.key;
	}

	static public ContenidoDeCarta fromString(String contenido) {
		ContenidoDeCarta contenidoAux = null;
		contenido = contenido.toUpperCase();
		
		Map<String, ContenidoDeCarta> values = new HashMap<String, ContenidoDeCarta>();
		values.put("AS", ContenidoDeCarta.AS);
		values.put("DOS", ContenidoDeCarta.DOS);
		values.put("TRES", ContenidoDeCarta.TRES);
		values.put("CUATRO", ContenidoDeCarta.CUATRO);
		values.put("CINCO", ContenidoDeCarta.CINCO);
		values.put("SEIS", ContenidoDeCarta.SEIS);
		values.put("SIETE", ContenidoDeCarta.SIETE);
		values.put("OCHO", ContenidoDeCarta.OCHO);
		values.put("NUEVE", ContenidoDeCarta.NUEVE);
		values.put("DIEZ", ContenidoDeCarta.DIEZ);
		values.put("CABALLERO", ContenidoDeCarta.CABALLERO);
		values.put("REINA", ContenidoDeCarta.REINA);
		values.put("REY", ContenidoDeCarta.REY);	
		
		contenidoAux = values.get(contenido);
		
		return contenidoAux;
	}
}