package sladoledzinica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoledzinica extends Frame {
	
	private Panel dodajUkus = new Panel();
	private AparatZaTocenje aparat = new AparatZaTocenje();
	private TextField naziv = new TextField(10), boja = new TextField(7);
	private Button dodaj = new Button("Dodaj ukus");
	
	private void dodajCentar() {
		aparat.setVisible(true);
		this.add(aparat, BorderLayout.CENTER);
	}
	private void dodajJug() {
		dodajUkus.setBackground(Color.CYAN);
		Label l1 = new Label("Naziv: ");
		l1.setFont(new Font(null, Font.BOLD, 14));
		Label l2 = new Label("Boja: ");
		l2.setFont(new Font(null, Font.BOLD, 14));
		dodajUkus.add(l1);
		dodajUkus.add(naziv);
		dodajUkus.add(l2);
		dodajUkus.add(boja);
		dodajUkus.add(dodaj);
		dodajUkus.setVisible(true);
		this.add(dodajUkus, BorderLayout.SOUTH);
	}
	
	private void dodajOsluskivace() {
		dodaj.addActionListener(ae -> {
			String nazivUkusa = naziv.getText();
			Color bojaUkusa = new Color(Integer.parseInt(boja.getText(), 16));
			try {
				aparat.dodajUkus(nazivUkusa, bojaUkusa);
			} catch (GUkus e) {}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				aparat.mesto.zavrsi();
				dispose();
			}
		});
	}
	
	public Sladoledzinica() {
		this.setBounds(700, 300, 400, 380);
		this.setResizable(false);
		this.setTitle("Sladoledzinica");
		
		this.dodajCentar();
		this.dodajJug();
		this.dodajOsluskivace();
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Sladoledzinica();
	}
}
