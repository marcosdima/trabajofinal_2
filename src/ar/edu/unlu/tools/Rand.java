package ar.edu.unlu.tools;

import java.util.Random;

public class Rand {
	
	public char randomChar() {
		
		// 97 = a; 122 = z;	
		char res = (char) (97 + this.randomNum(25));
		
		return res;
	} 
	
	public String randomStr(int largo) {
		String str = "";
		
		for (int i = largo; i > 0; i--) {
			str = str + this.randomChar();
		}
		
		return str;
	}
	
	public int randomNum(int tope) {
		int num;
		
		Random rand = new Random();
		
		num = rand.nextInt(tope+1);
		
		return num;
	}
	
	public int randomNum(int base, int tope) {
		
		int res = this.randomNum(tope-1) + base;
		return res;
		
	}
	
	public int randomNum() {
		int num;
		
		Random rand = new Random();
		
		num = rand.nextInt();
		
		return num;
	}

	public int[] randomList(int tam) {
		
		int i;
		int numero;
		boolean libre;
		int[] listaDeNumeros = new int[tam];
		boolean[] listaDeAciertos = new boolean[tam];
		
		for (i = 0; i < tam; i++) {
			
			listaDeAciertos[i] = true;
		}
		
		for (i = 0; i < tam; i++) {
			
			numero = this.randomNum(1, tam);
			libre = listaDeAciertos[numero-1];
			
			while (!libre) {

				numero = this.randomNum(1, tam);
				libre = listaDeAciertos[numero-1];
				
			}
			
			listaDeAciertos[numero-1] = false;
			listaDeNumeros[i] = numero;
			
		}
		
		
		return listaDeNumeros;
		
	}
	
}
