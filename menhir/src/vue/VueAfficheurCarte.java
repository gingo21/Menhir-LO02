package vue;

import java.util.LinkedList;

public class VueAfficheurCarte extends Panneau implements Runnable {

	private double timeReader = System.currentTimeMillis();
	private LinkedList<VueCarte> listeAttenteAffichage;
	private boolean stop = false;

	public static final double TEMPS_ATTENTE = 1000;

	public VueAfficheurCarte() {
		super();
		this.timeReader = System.currentTimeMillis();
		this.stop = false;
		this.listeAttenteAffichage = new LinkedList<VueCarte>();
	}

	public LinkedList<VueCarte> getListeAttenteAffichage() {
		return listeAttenteAffichage;
	}

	public void setListeAttenteAffichage(LinkedList<VueCarte> listeAttenteAffichage) {
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

	public synchronized void run() {
		while (!stop) {
			if (this.timeReader < System.currentTimeMillis() && !this.listeAttenteAffichage.isEmpty()) {
				this.ajoutPanneau(this.listeAttenteAffichage.pop(), 0, 0);
				this.timeReader = System.currentTimeMillis()+TEMPS_ATTENTE;
			} else if(this.timeReader >= System.currentTimeMillis()){
				try {
					this.wait((long) (this.timeReader - System.currentTimeMillis()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					this.wait(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
