package ar.edu.unlu.trabajofinal.mov.grafico;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Dialogo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel encabezado;
	private JTextField input;
	private JButton send;
	private String content = "";
	
	public Dialogo(String title, String text) {
		super(title);
		this.setEncabezado(text);
		this.setLayout();
		this.setButton();
		this.setInput();
		this.setSchema();
	}
	
	public String getText() {
		return this.content;
	}

	public void clear() {
		this.input.setText("");
		this.content = "";
	}
	
	public abstract void event();
	
	private void setSchema() {
		GridLayout main = new GridLayout(2,1);
		JPanel mainPanel = new JPanel();
		JPanel inputPanel = new JPanel();
		
		mainPanel.setLayout(main);
		inputPanel.setLayout(new FlowLayout());
		
		inputPanel.add(this.input);
		inputPanel.add(this.send);
		mainPanel.add(this.encabezado);
		mainPanel.add(inputPanel);
		
		this.add(mainPanel);
		
		this.setSize(340, 140);
	}

	private void setEncabezado(String title) {
		this.encabezado = new JLabel(title);
		this.encabezado.setFont(new Fuente(10).font());
	}
	
	private void setLayout() {
		FlowLayout lay = new FlowLayout();
		lay.setAlignment(FlowLayout.LEFT);
		this.setLayout(lay);
	}
	
	private void setButton() {
		this.send = new JButton("Aceptar");
		this.send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				content = input.getText();
				event();
			}
			
		});
	}
	
	private void setInput() {
		this.input = new JTextField(20);
	}
}
