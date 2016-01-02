package vue;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class VueAfficheurDeTexte extends JLabel implements Runnable {

	private double timeReader = System.currentTimeMillis();
	private LinkedList<String> listeAttenteAffichage;
	private boolean stop = false;

	public static final double TEMPS_ATTENTE = 1000;

	public VueAfficheurDeTexte() {
		super();
		this.setFont(new Font("Arial", Font.ITALIC, 14));
		this.setForeground(Color.RED);
		this.timeReader = System.currentTimeMillis();
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

	public synchronized void run() {
		while (!stop) {
			if (this.timeReader < System.currentTimeMillis() && !this.listeAttenteAffichage.isEmpty()) {
				this.setText(this.listeAttenteAffichage.pop());
				this.setSize(600,35);
				this.timeReader = System.currentTimeMillis()+TEMPS_ATTENTE;
				this.repaint();
			}  else if(this.timeReader >= System.currentTimeMillis()){
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
