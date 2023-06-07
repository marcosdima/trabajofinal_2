package ar.edu.unlu.trabajofinal.mov.grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BotonCarta extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean onClick = false;
	private JLabel textLabel;
	private String content;
	private ImageManager imageManager;
	private Image carta;
	
	public BotonCarta(String text, ImageManager imageManager) {
		Fuente fuente = new Fuente("files/fonts/Pokemon Classic.ttf", 21);
		
		this.content = text;
		this.imageManager = imageManager;
		this.carta = imageManager.randCard().getImage(); 
		
		this.setLayout(new BorderLayout());
		
		// Seteo el texto del botón.
		this.textLabel = new JLabel(this.content);
        this.textLabel.setHorizontalAlignment(JLabel.CENTER);
        this.textLabel.setFont(fuente.font());
        textLabel.setForeground(Color.BLACK);
        this.textLabel.setText("");
        
        
        this.add(this.textLabel, BorderLayout.CENTER);
        
        this.setBasicListeners();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image;
        
		if (this.onClick) {
			image = imageManager.imagenCarta("CUBIERTA").getImage();
	        //g.fillRect( 0, 0, this.getWidth(), this.getHeight());
	        textLabel.setText(content);
		}
		else {
			image = carta;
			textLabel.setText("");
		}
		
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);	
	}
	
	// Setea los listeners del mouse para que se vea como un botón.
	private void setBasicListeners() {
		//Timer time = new Timer(100);
		this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	cambiazo();
            }
            public void mouseExited(MouseEvent e) {
            	cambiazo();
            }
        });
	}
	
	// Cambia el valor de 'onClick' a su opuesto.
	private void cambiazo() {
		this.onClick = !this.onClick;
		this.updateUI();
	}
}
