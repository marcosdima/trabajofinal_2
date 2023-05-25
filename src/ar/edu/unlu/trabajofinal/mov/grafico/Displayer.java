package ar.edu.unlu.trabajofinal.mov.grafico;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class Displayer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextArea chat;
	private JTextField input = new JTextField(20);
	private JButton send = new JButton("Enviar");
	
	public Displayer() {
		this.setChat();
		this.setSchema();
	}
	
	public void setSchema() {
		JPanel escritura = new JPanel();

		// Setteo de layouts
		escritura.setLayout(new FlowLayout());
		this.setLayout(new GridLayout(2,1));
		
		// Appendeo
		escritura.add(this.input);
		escritura.add(this.send);
		
		this.add(this.chat);
		this.add(escritura);
		//this.setBorder(new EmptyBorder(10,350,10,350));
	}

	public void write(String txt) {
		String history = this.chat.getText();
		
		String espacio = "";
		
		// Harcodeado el 25.
		for (int i = 0; i < 25; i++) {
			espacio += "-";
		}
		
		String contenido = espacio + "\n" + txt + "\n" + espacio + "\n";
		this.chat.setText(history + contenido);
	}
	
	public void setChat() {
		this.chat = new JTextArea();
		this.chat.setLineWrap(true);
		this.chat.setEditable(false);
		this.chat.setFont(new Fuente(12).font());
        this.chat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JScrollPane scrollPane = new JScrollPane(this.chat);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
	}

	public void send(ActionListener action) {
		this.send.addActionListener(action);
	}
	
}
