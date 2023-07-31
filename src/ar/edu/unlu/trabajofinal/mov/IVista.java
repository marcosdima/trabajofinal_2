package ar.edu.unlu.trabajofinal.mov;

import java.util.ArrayList;

import ar.edu.unlu.trabajofinal.Evento;
import ar.edu.unlu.trabajofinal.IJugador;

public interface IVista {
	public void menuPrincipal();
	
	public void ingresoDeApuesta(String texto);
	
	public void siONo(String texto, Evento event);
	
	public void mostrarMesa(ArrayList<IJugador> mesa);
	
	public String formularioDeIngreso();
	
	public void mostrarMensaje(String msj);
	
	public void rank();
	
	public void ventanaDeCarga();
	
	public void exit();
	
	public void help();
}
