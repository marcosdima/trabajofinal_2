package ar.edu.unlu.trabajofinal.mov.grafico;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
		//this.setBorder(new EmptyBorder(10,350,10,350));
		
		JScrollPane scroll = new JScrollPane(this.chat);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
        
		this.add(escritura);
	}

	public void write(String txt) {
		String history = this.chat.getText();
		
		String espacio = "";
		
		// Harcodeado el 25.
		for (int i = 0; i < 25; i++) {
			espacio += "-";
		}
		
		String contenido = espacio + "\n" + " " + txt + "\n" + espacio + "\n";
		this.chat.setText(history + contenido);
		
		this.chat.setCaretPosition(this.chat.getDocument().getLength());
	}
	
	public void setChat() {
		this.chat = new JTextArea();
		this.chat.setLineWrap(true);
		//this.chat.setEditable(false);
		this.chat.setFont(new Fuente(12).font());
        this.chat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void send(ActionListener action) {
		this.send.addActionListener(action);
	}
	
	public String getInputText() {
		return this.input.getText();
	}
}
