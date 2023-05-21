package ar.edu.unlu.trabajofinal.mov.grafico;

import java.io.File;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TestGrafico {

	public static void main(String[] args) {
		Frame f = new Frame("Ventana ♦    ♥    ♣    ♠");
		Panel p = new Panel();
		
    	p.setLayout(new GridLayout(3,3));
    	
    	String base = "files/images/cartas/default/";
        
		MenuPrincipal menu = new MenuPrincipal(new ImageManager("files/images/cartas", "simple"));
    	
		f.add(menu);
		
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
