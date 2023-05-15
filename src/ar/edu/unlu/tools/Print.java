package ar.edu.unlu.tools;

import java.util.ArrayList;

public class Print {
	
	private char separador;
	private static final int MAXIMO = 3;
	private static final int MAXIMOLINEA = 50;
	
	public Print(char caracter) {
		this.setSeparador(caracter);
	}
	
	// Constructor que no recibe nada de parametro, setea por defecto como separador '-'.
	public Print() {
		this ('-');
	}
	
	// Setea el separador.
	public void setSeparador(char caracter) {
		this.separador = caracter;
	}
	
	// Muestra en pantalla lo que se le pase por parametro.
	public void print(String algo) {
		System.out.println(algo);
	}

	// Hace un print vacio.
	public void print() {
		System.out.println("");
	}
	
	// Printea sin salto de linea.
	public void justPrint(String c) {
		System.out.print(c);
	}
	
	// Printea un char sin salto de linea.
	public void justPrint(char c) {
		System.out.print(c);
	}
	
	// Printea sin salto de linea n veces.
	public void justPrint(char c, int n) {
		for (int i = 0; i < n; i++) {
			this.justPrint(c);
		}
	}
	
	// Hace prints vacios tantas veces como se le indique por parametro.
	public void printsEnBlanco(int espacios) {
		for (int i = 0; i < Print.MAXIMO; i++) {
			this.print();	
		}
	}
	
	// Hace un print de algo y luego hace un print vacio.
	public void printConEspacio(String algo) {
		this.print(algo);
		this.print();
	}
	
	// Hace un print vacio y luego printea algo.
	public void printConEspacioAlto(String algo) {
		this.print();
		this.print(algo);
	}
	
	// Hace MAXIMO veces prints vacios, para luego agregar una linea divisoria y volver a hacer prints vacios.
	public void espacio() {
		this.printsEnBlanco(Print.MAXIMO);
		
		this.justPrint(this.separador, Print.MAXIMOLINEA);
		
		this.printsEnBlanco(Print.MAXIMO);
	}

	public void printSeguido(String[] printeable, int size, String espacio) {
		String linea = "";
		int agregado;
		
		for (String str : printeable) {
			linea += str;
			agregado = size;
			
			if (str != null) {	
				agregado = size - str.length();
			}
			
			for (int i = 0; i < agregado; i++) {
				linea += espacio;	
			}
		}
		
		this.printConEspacio(linea);
	}
	
	public void printSeguido(String[] printeable, int size) {
		this.printSeguido(printeable, size, " ");
	}

	public void printSeguido(ArrayList<String[]> printeable, int size) {		
		String espacio = "";
		String[] arregloAuxiliar;

		int i;
		int o;
		int a;
		int mayor = 0;
		int largo = printeable.size();
		
		// Setea el tamaño del array más grande.
		for (String[] arreglo : printeable) {
			if (arreglo.length > mayor) {
				mayor = arreglo.length;	
			}
		}
		
		// Setea la variable 'espacio'.
		for (a = 0; a < size; a++) {
			espacio += " ";
		}
		
		for (i = 0; i < mayor; i++) {
			// Creo un array que va a contener las cartas, para lugo ser printeadas.
			arregloAuxiliar = new String[largo];
			
			for (o = 0; o < (largo); o++) {
				if (printeable.get(o).length <= (i)) {
					arregloAuxiliar[o] = espacio;
				}
				else {
					arregloAuxiliar[o] = printeable.get(o)[i];
				}			
			}
			
			this.printSeguido(arregloAuxiliar, size);
		}	
	}
}
