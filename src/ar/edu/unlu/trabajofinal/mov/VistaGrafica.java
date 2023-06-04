package ar.edu.unlu.trabajofinal.mov;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ar.edu.unlu.trabajofinal.Evento;
import ar.edu.unlu.trabajofinal.IJugador;
import ar.edu.unlu.trabajofinal.mov.grafico.Dialogo;
import ar.edu.unlu.trabajofinal.mov.grafico.DialogoType;
import ar.edu.unlu.trabajofinal.mov.grafico.Displayer;
import ar.edu.unlu.trabajofinal.mov.grafico.Frame;
import ar.edu.unlu.trabajofinal.mov.grafico.ImageManager;
import ar.edu.unlu.trabajofinal.mov.grafico.MenuPrincipal;
import ar.edu.unlu.trabajofinal.mov.grafico.PanelMesa;
import ar.edu.unlu.trabajofinal.mov.grafico.VentanaRank;

public class VistaGrafica implements IVista {
	private Controlador controller;
	private Frame framePrincipal;
	private ImageManager imageManager;
	private Displayer display;
	private Dialogo dialogo;
	private VentanaRank ranking;

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

		this.framePrincipal.add(menu);
		// Jugar.
		menu.getJugar().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	controller.startGame();
            }
		});

		// Salir.
		menu.getSalir().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	System.exit(0);
            }
		});
		
		// Rank.
		menu.getRank().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	rank();
            }
		});
		
		menu.updateUI();

	}

	@Override
	public void ingresoDeApuesta(String texto) {
		this.setDialogo("Apuestas", texto, DialogoType.SIMPLEINPUT);

		this.dialogo.event(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dialogo.setVisible(false);
				controller.apostar(dialogo.getContent());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}

	@Override
	public void siONo(String texto, Evento event) {
		this.setDialogo("Pregunta", texto, DialogoType.YESORNO);

		this.dialogo.event(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dialogo.setVisible(false);
				controller.askSomething(dialogo.getContent(), event);		
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
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
		this.display.send(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMensaje();
			}
			
		});
	}

	@Override
	public void rank() {
		if (this.ranking == null) {
			this.ranking = new VentanaRank(this.controller.getRank());
		}
		else {
			this.ranking.setVisible(true);
		}
	}
	
	private void sendMensaje() {
		SwingUtilities.invokeLater(() -> {
			String text = this.display.getInputText().strip();
			
			// Si el mensaje no esta vacío, lo envía.
			if (text != "") {
				this.controller.sendMensaje(text);
			}
        });
	}

	private void setDialogo(String title, String encabezado, DialogoType type) {
		Dialogo aux = new Dialogo(title, encabezado, type);
		
		int x = 0;
		int y = 0;
		
		if (this.dialogo != null) {
			x = this.dialogo.getX();
			y = this.dialogo.getY();
		}
		
		this.dialogo = aux;
		this.dialogo.setLocation(x, y);
		
		this.dialogo.setVisible(true);
	}
}
