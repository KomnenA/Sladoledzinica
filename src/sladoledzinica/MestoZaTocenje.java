package sladoledzinica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class MestoZaTocenje extends Canvas implements Runnable {
	
	private static final int vremeTocenja = 500, kolicinaUkusa = 20, velicinaCase = 200;
	private Thread nit = new Thread(this);
	private AparatZaTocenje aparat;
	private Sladoled sladoled;
	private boolean radi;
	private Ukus trUkus;
	private int progres, trPozicija, visina, broj;
	private HashMap<Integer, Color> ukusi = new HashMap<>();
	
	public MestoZaTocenje(AparatZaTocenje aparat) {
		this.aparat = aparat;
		nit.start();
	}
	
	public synchronized Sladoled dohvSladoled() {
		return sladoled;
	}
	
	public synchronized void nastavi() {
		radi = true;
		notify();
	}
	public synchronized void zaustavi() {
		radi = false;
	}
	public synchronized void zavrsi() {
		if (nit != null)
			nit.interrupt();
	}
	public synchronized void pokreni() {
		sladoled = new Sladoled(velicinaCase);
		radi = true;
		notify();
	}
	
	public synchronized boolean toci() {
		return radi;
	}
	public synchronized void postaviUkus(Ukus u) {
		trUkus = u;
	}
	
	void azuriraj() {
		trPozicija = 0;
		progres = 0;
		sladoled = null;
		broj = 0;
		ukusi.clear();
	}
	
	@Override
	public void paint(Graphics g) {
		if (sladoled != null) {
			visina = (((kolicinaUkusa * 100) / velicinaCase) * this.getHeight()) / 100 + 1;
			for (int i = 0; i<broj; i++) {
				g.setColor(ukusi.get(i));
				g.fillRect(0, this.getHeight() - trPozicija - this.getHeight() % visina, this.getWidth(), visina);
				trPozicija += visina;
			}
			trPozicija = 0;
		}
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!radi)
						wait();
				}
				sladoled.dodajUkus(trUkus, kolicinaUkusa);
				ukusi.put(broj++, trUkus.dohvBoju());
				repaint();
				aparat.sladoledUkusi.setText("" + sladoled);
				progres += kolicinaUkusa;
				if (progres == velicinaCase) {
					radi = false;
					aparat.omoguciProdaju();
				}
				Thread.sleep(vremeTocenja);
			}
		} catch (InterruptedException e) {}
	}
}
