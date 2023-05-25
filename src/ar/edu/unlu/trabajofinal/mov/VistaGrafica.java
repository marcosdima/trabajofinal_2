package ar.edu.unlu.trabajofinal.mov;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ar.edu.unlu.trabajofinal.IJugador;
import ar.edu.unlu.trabajofinal.mov.grafico.Displayer;
import ar.edu.unlu.trabajofinal.mov.grafico.Frame;
import ar.edu.unlu.trabajofinal.mov.grafico.ImageManager;
import ar.edu.unlu.trabajofinal.mov.grafico.MenuPrincipal;
import ar.edu.unlu.trabajofinal.mov.grafico.PanelMesa;

public class VistaGrafica implements IVista {
	private Controlador controller;
	private Frame framePrincipal;
	private ImageManager imageManager;
	private PanelMesa panelMesa;
	private Displayer display;

	public VistaGrafica(Controlador cc) {
		this.setController(cc);
		this.setFramePrincipal();
		this.setImageManager("files/images/cartas", "default");
		this.setDisplay();
		this.framePrincipal.turnOn();
	}
	
	@Override
	public void menuPrincipal() {
		MenuPrincipal menu = new MenuPrincipal(this.imageManager);
		//this.framePrincipal.add(this.menuPrincipal);
		this.framePrincipal.add(menu);
		// Jugar
		menu.getJugar().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	controller.startGame();
            }
		});

		// Salir
		menu.getSalir().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	System.exit(0);
            }
		});
		
		menu.updateUI();

	}

	@Override
	public void ingresoDeApuesta(String texto) {
		SwingUtilities.invokeLater(() -> {
			String stringAux = "";
            stringAux = JOptionPane.showInputDialog(null, texto, "Apuesta", JOptionPane.INFORMATION_MESSAGE);	
            this.controller.apostar(stringAux);
        });
	}

	@Override
	public void preguntaQuieroOtra(String texto) {
		SwingUtilities.invokeLater(() -> {
			boolean retorno = false;
			
			// 0 = Si; 1 = No
			int respuesta = JOptionPane.showConfirmDialog(null, texto, "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (respuesta == 0) {
				retorno = true;
			}
			
			controller.otraCarta(retorno);
        });
	}

	@Override
	public void mostrarMesa(ArrayList<IJugador> mesa) {
		PanelMesa panelMesa = new PanelMesa(mesa, this.imageManager, this.display);
		this.framePrincipal.add(panelMesa);
		panelMesa.updateUI();
	}

	@Override
	public String formularioDeIngreso() {
		String res = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Nombre", JOptionPane.INFORMATION_MESSAGE);	
		
		if (res == null) {
			res = "a";
		}
		
		return res;
	}

	@Override
	public void mostrarMensaje(String msj) {
		this.display.write(msj);
	}
	
	public void setController(Controlador controller) {
		this.controller = controller;
		controller.addVista(this);
	}

	public void setFramePrincipal() {
		this.framePrincipal = new Frame("Black Jack ♦♥♣♠");
	}
	
	public void setImageManager(String directorio, String carpeta) {
		this.imageManager = new ImageManager(directorio, carpeta);
	}
	
	public void setDisplay() {
		this.display = new Displayer();
	}
}
