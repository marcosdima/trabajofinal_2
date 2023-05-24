package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.IJugador;

public interface IVista {
	public void menuPrincipal();
	public void ingresoDeApuesta(String texto);
	public void preguntaQuieroOtra(String texto);
	public void mostrarMesa(ArrayList<IJugador> mesa);
	public String formularioDeIngreso();
	public void mostrarMensaje(String msj);
}
