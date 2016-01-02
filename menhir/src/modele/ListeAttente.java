package modele;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Observable;

public class ListeAttente extends Observable implements Runnable {

	private double timeReader = System.currentTimeMillis();
	private Object currentObject;
	private LinkedList<Object> listeAttenteAffichage;
	private boolean stop = false;

	public static final double TEMPS_ATTENTE = 1000;

	public ListeAttente() {
		super();
		this.timeReader = System.currentTimeMillis();
		this.stop = false;
		this.listeAttenteAffichage = new LinkedList<Object>();
	}

	public LinkedList<Object> getListeAttenteAffichage() {
		return listeAttenteAffichage;
	}

	public void setListeAttenteAffichage(LinkedList<Object> listeAttenteAffichage) {
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

	public Object getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(Object currentObject) {
		this.currentObject = currentObject;
	}

	public synchronized void run() {
		while (!stop) {
			if (this.timeReader <= System.currentTimeMillis() && !this.listeAttenteAffichage.isEmpty()) {
				this.currentObject = this.listeAttenteAffichage.pop();
				this.notifyObservers();
				this.timeReader = System.currentTimeMillis()+TEMPS_ATTENTE;
			}  else if(this.timeReader > System.currentTimeMillis()){
				try {
					this.wait((long) (TEMPS_ATTENTE/2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					this.wait((long) (TEMPS_ATTENTE/2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
