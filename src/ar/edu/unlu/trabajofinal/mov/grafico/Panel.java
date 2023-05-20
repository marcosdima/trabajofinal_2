package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.Graphics;
//import java.awt.Image;

//import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	//private Image backgroundImage;

	public Panel() {
		super();
		//backgroundImage = new ImageIcon("files/images/backgrounds/fondo.jpg").getImage();
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        //g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
	
}
