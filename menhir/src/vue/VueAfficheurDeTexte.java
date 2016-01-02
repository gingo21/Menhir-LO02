package vue;

import java.util.LinkedList;

import javax.swing.JTextArea;

public class VueAfficheurDeTexte extends JTextArea implements Runnable {

	private double timeReader = 0;
	private LinkedList<String> listeAttenteAffichage;
	private boolean stop = false;

	public static final double TEMPS_ATTENTE = 1000;

	public VueAfficheurDeTexte(double timeReader) {
		super();
		this.timeReader = timeReader;
		this.stop = false;
		this.listeAttenteAffichage = new LinkedList<String>();
	}

	public LinkedList<String> getListeAttenteAffichage() {
		return listeAttenteAffichage;
	}

	public void setListeAttenteAffichage(LinkedList<String> listeAttenteAffichage) {
		this.listeAttenteAffichage = listeAttenteAffichage;
	}

	public double getTimeReader() {
		return timeReader;
	}

	public void setTimeReader(double timeReader) {
		this.timeReader = timeReader;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void run() {
		while (!stop) {
			if (this.timeReader < System.currentTimeMillis()) {
				this.setText(this.listeAttenteAffichage.pop());
				this.timeReader += TEMPS_ATTENTE;
			}
		}
	}

}
