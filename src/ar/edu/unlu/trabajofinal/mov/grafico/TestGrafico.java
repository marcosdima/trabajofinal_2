package ar.edu.unlu.trabajofinal.mov.grafico;

import ar.edu.unlu.trabajofinal.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import ar.edu.unlu.mazo.*;
import java.util.ArrayList;

public class TestGrafico {

	public static void main(String[] args) {
	
		Frame f = new Frame("a");
		JugadorBJ ply = new JugadorBJ("Carlos", 11);
		Crupier crup = new Crupier();
		ArrayList<IJugador> jugadores = new ArrayList<IJugador>();
		ImageManager img = new ImageManager("files/images/cartas", "default");
		
		crup.darCarta(ply);
		crup.repartirASiMismo();
		jugadores.add(ply);
		jugadores.add(crup);
		
		PanelMesa panel = new PanelMesa(jugadores, img, new Displayer());
		
		//f.add(panel);
		//f.turnOn();
		Dialogo d = new Dialogo("Test", "Test encabezado", DialogoType.YESORNO);
		d.setVisible(true);
		d.event(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print(d.getContent());
				d.setTitle("cas");
				d.setSchema("Paso", DialogoType.SIMPLEINPUT);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println(d.getX() + " " + d.getY());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		//Dialogo d = new Dialogo("test", "ahora es personal");
		
		/*
		
		b.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					b.setLocation(b.getX() - 1, b.getY());
				}
			}
		});

		ImageIcon icon = new ImageIcon("files/images/icons/button_3.png");
		Image scaledImage = icon.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel label = new JLabel(scaledIcon);
		
		
		Font customFont = null;
		try {
		    // Carga la fuente personalizada desde el archivo
		    customFont = Font.createFont(Font.TRUETYPE_FONT, new File("files/fonts/Blackside.ttf"));
		    // Ajusta el tamaño de la fuente si es necesario
		    customFont = customFont.deriveFont(Font.PLAIN, 180);
		} catch (FontFormatException | IOException e) {
		    e.printStackTrace();
		}

		*/
	}
}
