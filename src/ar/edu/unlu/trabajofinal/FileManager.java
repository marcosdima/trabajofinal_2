package ar.edu.unlu.trabajofinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	
	private final String RANKING = "files/rank/ranking.txt";
	private final String SAVE = "files/save/";
	private final String HELP = "files/help/help.txt";
	
	public void save(String tag, ArrayList<String> guardado) throws IOException {
		File archivo = new File(this.SAVE + tag + ".txt");
		PrintWriter escritor;

		archivo.createNewFile();
		escritor = new PrintWriter(archivo);
		
		for (String player : guardado) {
			escritor.println(player);
		}
		
		escritor.close();
	}
	
	public ArrayList<String> carga(String tag) throws IOException {
		File archivo = new File(tag);
		FileReader fr = new FileReader(archivo);;	
		BufferedReader reader = new BufferedReader(fr);
		ArrayList<String> retorno = new ArrayList<String>();
		String linea = reader.readLine();

		if (archivo.exists()) {
			while ((linea != null)) {
				retorno.add(linea);
				linea = reader.readLine();
			}

			reader.close();
		}
		
		return retorno;
	}
	
	public ArrayList<String> carga() throws IOException {
		File archivo = new File(this.SAVE + "ultimoGuardado.txt");
		FileReader fr = new FileReader(archivo);;	
		BufferedReader reader = new BufferedReader(fr);
		ArrayList<String> retorno = new ArrayList<String>();
		String linea = reader.readLine();

		if (archivo.exists()) {
			while ((linea != null)) {
				retorno.add(linea);
				linea = reader.readLine();
			}

			reader.close();
		}
		
		return retorno;
	}
	
	public ArrayList<String> load(String tag) throws IOException {
		return this.carga(SAVE + tag);
	} 
	
	public ArrayList<String> loadRanking() throws IOException {
		File archivo = new File(this.RANKING);
		ArrayList<String> retorno;
		
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		
		retorno = this.carga(this.RANKING);
		this.ordenarRanking(retorno);
		
		return retorno;
	}

	public ArrayList<String> loadHelp() throws IOException {
		File archivo = new File(this.HELP);
		ArrayList<String> retorno;
		
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		
		retorno = this.carga(this.HELP);

		return retorno;
	}
	
	public void addToRanking(String nombre, float i, int montoInicial) throws IOException {
		ArrayList<String> actual = this.loadRanking();
		File archivo = new File(this.RANKING);
		PrintWriter escritor = new PrintWriter(archivo);
		String nuevo = nombre + "," + (i - montoInicial);

		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		
		if (actual.size() == 1) {
			escritor.println(actual.get(0));
			escritor.write(nuevo);
		}
		else {
			actual.add(nuevo);
			this.ordenarRanking(actual);
			
			while (actual.size() > 10) {
				actual.remove(actual.size() - 1);
			}
			
			for (int e = (actual.size() - 1); e >= 0; e--) {
				escritor.write(actual.get(e) + "\n");
			}
		}
		
		escritor.close();
	}
	
	private void ordenarRanking(ArrayList<String> lista) throws IOException {
		float puntos = 0;
		float puntosAux = 0;
		int largo = lista.size();
		String contenedor;
		String[] linea;
		String[] lineaAux;
		
		for (int i = 0; i < (largo - 1); i++) {
			for (int e = 0; e < (largo - 1); e++) {
				linea = lista.get(e).split(",");
				puntos = Float.valueOf(linea[1].trim());
				
				lineaAux = lista.get(e + 1).split(",");
				puntosAux = Float.valueOf(lineaAux[1].trim());
				
				if (puntos < puntosAux) {
					contenedor = lista.get(e);
					lista.set(e, lista.get(e + 1));
					lista.set(e + 1, contenedor);
				}
			}
		}
	}

}