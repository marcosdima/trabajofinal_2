package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.IJugador;

public interface IVista {
	public void menuPrincipal();
	public String ingresoDeApuesta();
	public boolean preguntaQuieroOtra();
	public void mostrarMesa(ArrayList<IJugador> mesa);
	public String formularioDeIngreso();
}
