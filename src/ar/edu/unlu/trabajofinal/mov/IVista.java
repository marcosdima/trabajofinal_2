package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.IJugador;

public interface IVista {
	public void menuPrincipal();
	public String ingresoDeApuesta(String texto);
	public boolean preguntaQuieroOtra(String texto);
	public void mostrarMesa(ArrayList<IJugador> mesa);
	public String formularioDeIngreso();
	public void mostrarMensaje(String msj);
}
