package ar.edu.unlu.trabajofinal.mov;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.JPanel;
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
import ar.edu.unlu.trabajofinal.mov.grafico.Fuente;

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
            	exit();
				System.exit(0);
            }
		});
		
		// Rank.
		menu.getRank().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	rank();
            }
		});
		
		// Config.
		menu.getConfig().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
            	menuConfiguracion();
            }
		});
		
		// Carga.
		menu.getLoad().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ventanaDeCarga();
				controller.cargarPartida();
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

	@Override
	public void menuConfiguracion() {
		Fuente fontTitle = new Fuente(60);
		
		JPanel menuConfig = new JPanel();
		JPanel buttons = new JPanel();
		
		JButton cambiarSkin = new JButton("Cambiar Skin");
		
		JLabel title = new JLabel("Configuracion");
		title.setFont(fontTitle.font());
		title.setHorizontalAlignment(JLabel.CENTER);
		
		menuConfig.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(1,1));
		
		// Cambio de skin de carta
		JPanel skin = new JPanel();
		skin.setLayout(new FlowLayout());
		JLabel as = new JLabel(this.imageManager.imagenCarta("AS_CORAZON"));
		JLabel cubierta = new JLabel(this.imageManager.imagenCarta("CUBIERTA"));
		cambiarSkin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// Estos son para crear la lista de archivos disponibles.
				File dir = new File("files/images/cartas");
				File[] archivos = dir.listFiles();
				String[] strs = new String[archivos.length];
				int contador = 0;				
				
				for (File archivo : archivos) {
					strs[contador] = archivo.getName();
					contador++;
				}

				String choose = (String) JOptionPane.showInputDialog(
						null, 
						"Seleccione un estilo", "Cartas", 
						JOptionPane.QUESTION_MESSAGE, 
						null,
						strs,
						strs[0]
				);
				
				if (choose != null) {
					imageManager.setFolderCarta(choose);
					menuConfiguracion();	
				}
					
			}

		});

		skin.add(cambiarSkin);
		skin.add(as);
		skin.add(cubierta);
		
		menuConfig.add(title, BorderLayout.NORTH);
		menuConfig.add(skin, BorderLayout.CENTER);
		
		this.framePrincipal.add(menuConfig);
		
		menuConfig.updateUI();
	}

	@Override
	public void exit() {
		this.controller.exit();
	}

	@Override
	public void ventanaDeCarga() {
		JPanel clean = new JPanel();
		Fuente fontTitle = new Fuente(30);
		JLabel text = new JLabel("Espera a que lxs demás jugadorxs se conecten!");
		
		text.setFont(fontTitle.font());
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.CENTER);
		
		clean.add(text);
		this.framePrincipal.add(clean);
		clean.updateUI();
	}
}
