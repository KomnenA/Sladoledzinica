package sladoledzinica;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AparatZaTocenje extends Panel {

	MestoZaTocenje mesto = new MestoZaTocenje(this);
	private Panel ukusi = new Panel(new GridLayout());
	private Panel centar = new Panel(new BorderLayout());
	Button prodaj = new Button("Prodaj");
	Label sladoledNatpis = new Label();
	Label sladoledUkusi = new Label("");
	private ArrayList<Button> sadrzaniUkusi = new ArrayList<>();
	private Sladoled sladoled;
	
	public void dodajUkus(String naziv, Color boja) throws GUkus {
		for (Button b : sadrzaniUkusi) {
			if (b.getLabel().equals(naziv))
				throw new GUkus();
		}
		Button ukus = new Button(naziv);
		ukus.setBackground(boja);
		sadrzaniUkusi.add(ukus);
		ukusi.add(ukus);
		if (sadrzaniUkusi.size() == 2) {
			ukusi.setLayout(new GridLayout(2, 1));
		}
		else if (sadrzaniUkusi.size() > 2) {
			ukusi.setLayout(new GridLayout(0, 2));
		}
		ukus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (mesto.dohvSladoled() == null) {
					mesto.postaviUkus(new Ukus(ukus.getLabel(), ukus.getBackground()));
					mesto.pokreni();
				}
				else if (mesto.dohvSladoled() != null && !prodaj.isEnabled()) {
					mesto.postaviUkus(new Ukus(ukus.getLabel(), ukus.getBackground()));
					mesto.nastavi();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mesto.zaustavi();
			}
		});
		revalidate();
	}
	
	private void dodajCentar() {
		ukusi.setSize(centar.getMaximumSize());
		ukusi.setBackground(Color.LIGHT_GRAY);
		centar.add(ukusi, BorderLayout.CENTER);
		Panel panel = new Panel(new GridLayout(0, 1));
		panel.add(prodaj);
		panel.add(mesto);
		panel.setPreferredSize(new Dimension(140, 300));
		centar.add(panel, BorderLayout.EAST);
		this.add(centar, BorderLayout.CENTER);
	}
	private void dodajJug() {
		Panel jug = new Panel(new BorderLayout());
		jug.setBackground(Color.GRAY);
		sladoledNatpis.setFont(new Font(null, Font.BOLD, 16));
		sladoledNatpis.setText("Sladoled: ");
		jug.add(sladoledNatpis, BorderLayout.WEST);
		jug.add(sladoledUkusi, BorderLayout.CENTER);
		this.add(jug, BorderLayout.SOUTH);
	}
	
	void omoguciProdaju() {
		sladoled = mesto.dohvSladoled();
		prodaj.setEnabled(true);
	}
	
	private void dodajOsluskivace() {
		prodaj.addActionListener(ae -> {
			Graphics g = mesto.getGraphics();
			g.clearRect(0, 0, mesto.getWidth(), mesto.getHeight());
			mesto.repaint();
			mesto.azuriraj();
			System.out.println(sladoled);
			prodaj.setEnabled(false);
		});
	}
	
	public AparatZaTocenje() {
		this.setLayout(new BorderLayout());
		prodaj.setEnabled(false);
		
		this.dodajCentar();
		this.dodajJug();
		this.dodajOsluskivace();
		
		this.setVisible(true);
	}
}
