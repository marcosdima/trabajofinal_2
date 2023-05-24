package ar.edu.unlu.trabajofinal.mov.grafico;

import ar.edu.unlu.trabajofinal.*;
import ar.edu.unlu.mazo.*;

public class TestGrafico {

	public static void main(String[] args) {
	
		JugadorBJ test = new JugadorBJ("Carlos", 111);
		
		Carta c1 = new Carta(Palo.CORAZON, ContenidoDeCarta.AS);
		Carta c2 = new Carta(Palo.CORAZON, ContenidoDeCarta.DOS);
		
		c1.setVisibilidad(true);
		c2.setVisibilidad(true);
		
		test.addCarta(c2);
		test.addCarta(c1);
		
		Frame f = new Frame("");
		ImageManager manager = new ImageManager("files/images/cartas", "default");
		
		ModuloJugador mj = new ModuloJugador(test, manager);
		f.add(mj);
		f.turnOn();
		
		
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
