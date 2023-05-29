package ar.edu.unlu.trabajofinal.mov.grafico;
 
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Dialogo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel encabezado;
	private String content = "";
	private JComponent[] componentes;
	private Component lastComponent;
	
	public Dialogo(String title, String text, DialogoType type) {
		super(title);
		this.setSize(340, 110);
		this.setResizable(false);
		this.setSchema(text, type);
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void event(MouseListener ac) {
		for (JComponent c : this.componentes) {
			c.addMouseListener(ac);
		}
	}
	
	public void setSchema(String text, DialogoType type) {	
		this.content = "";
		this.setEncabezado(text);
		
		switch (type) {
		case SIMPLEINPUT:
			this.setSimpleInput();
			break;
			
		case YESORNO:
			this.setYesOrNo();
			break;

		default:
			break;
		}
	}

	public Component add(Component c) {
		if (this.lastComponent != null) {
			this.remove(this.lastComponent);
		}
		
		super.add(c);
		
		this.lastComponent = c;
		return c;
	}

	private void setEncabezado(String title) {
		this.encabezado = new JLabel(title);
		this.encabezado.setFont(new Fuente(12).font());
	}
	
	// Formato de Simple input.
	private void setSimpleInput() {
		JPanel inputPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		FlowLayout lay = new FlowLayout();
		
		JButton send = new JButton("Aceptar");
		JTextField input = new JTextField(20);
		
		lay.setAlignment(FlowLayout.LEFT);
		mainPanel.setLayout(lay);

		// Set listener del boton de aceptar.
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = input.getText();
			}
		});

		// Appendeo final.
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(input);
		inputPanel.add(send);
		mainPanel.add(this.encabezado);
		mainPanel.add(inputPanel);

		this.add(mainPanel);
		this.setComponentes(new JComponent[]{send});
		mainPanel.updateUI();
	}

	// Formato de Yes or No.
	private void setYesOrNo() {
		JPanel lowPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		
		GridLayout main = new GridLayout(2,1);
		
		JButton yes = new JButton("Si");
		JButton no = new JButton("No");
	
		this.encabezado.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.setLayout(main);
		mainPanel.add(this.encabezado);
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = "1";
			}
			
		});	
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = "0";
			}
		});
	
		lowPanel.add(yes);
		lowPanel.add(no);
		mainPanel.add(lowPanel);
		
		this.add(mainPanel);
		this.setComponentes(new JComponent[]{yes, no});
		mainPanel.updateUI();
	}

	private void setComponentes(JComponent[] components) {
		this.componentes = components;
	}

	
}
