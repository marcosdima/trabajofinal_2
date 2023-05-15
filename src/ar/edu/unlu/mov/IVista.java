package ar.edu.unlu.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.IJugador;

public interface IVista {
	public void menuPrincipal();
	public int ingresoDeApuesta();
	public boolean preguntaQuieroOtra();
	public void mostrarMesa(ArrayList<IJugador> mesa);
	public String formularioDeIngreso();
}
