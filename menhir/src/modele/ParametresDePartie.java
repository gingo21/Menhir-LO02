package modele;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import launcher.Jeu;

public class ParametresDePartie extends Observable implements Serializable {
	private int nombreDeManches;
	private int nombreDeJoueurs;
	private StatutPartie typePartie;
	private ArrayList<Integer> ordreDesJoueurs;
	private ArrayList<Joueur> listeJoueurs;
	private PaquetDeRessourcesDePartie paquetDePartie;

	public ParametresDePartie() { 
		try {
			this.readParametres();
			if(Jeu.MODE_GRAPHIQUE) {
				this.getJoueurReel().setStrategie(new StrategieJoueurReelGraphique(this.getJoueurReel()));
			} else {
				this.getJoueurReel().setStrategie(new StrategieJoueurReelConsole(this.getJoueurReel()));
			}
			this.saveParametres();
		} catch (ClassNotFoundException e){
			this.parametresParDefaut();
		}
	}

	public int getNombreDeManches() {
		return nombreDeManches;
	}

	public void setNombreDeManches(int nombreDeManches) {
		this.nombreDeManches = nombreDeManches;
	}

	public int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}

	public void setNombreDeJoueurs(int nombreDeJoueurs) {
		this.nombreDeJoueurs = nombreDeJoueurs;
	}


	public ArrayList<Integer> getOrdreDesJoueurs() {
		return ordreDesJoueurs;
	}

	public void setOrdreDesJoueurs(ArrayList<Integer> ordreDesJoueurs) {
		this.ordreDesJoueurs = ordreDesJoueurs;
	}

	public StatutPartie getTypePartie() {
		return typePartie;
	}

	public void setTypePartie(StatutPartie typePartie) {
		this.typePartie = typePartie;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public PaquetDeRessourcesDePartie getPaquetDePartie() {
		return paquetDePartie;
	}

	public void setPaquetDePartie(PaquetDeRessourcesDePartie paquetDePartie) {
		this.paquetDePartie = paquetDePartie;
	}
	
	public JoueurReel getJoueurReel() {
		JoueurReel tempJoueurReel = null;
		for(Iterator<Joueur> it=this.listeJoueurs.iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if(tempJoueur instanceof JoueurReel) {
				tempJoueurReel = (JoueurReel) tempJoueur;
			}
		}
		return tempJoueurReel;
	}
	
	public void miseAJourListeJoueurs() {
		for (int i = this.listeJoueurs.size()-1; i >=1; i--) {
			this.listeJoueurs.remove(i);
		}
		for (int i = this.ordreDesJoueurs.size()-1; i >=1; i--) {
		this.ordreDesJoueurs.remove(i);
		}
		for (int i = 1; i < this.nombreDeJoueurs; i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, this.paquetDePartie, Difficulte.facile);
			this.ordreDesJoueurs.add(tempJoueurVirtuel.getId());
			this.listeJoueurs.add(tempJoueurVirtuel);
		}
	}
	
	public void parametresParDefaut() {
		this.nombreDeJoueurs = 2;
		this.typePartie = StatutPartie.rapide;
		this.nombreDeManches = 1;
		this.paquetDePartie = new PaquetDeRessourcesDePartie(this.typePartie, this.nombreDeJoueurs);
		this.listeJoueurs = new ArrayList<Joueur>();
		JoueurReel tempJoueurReel = new JoueurReel("Joueur", this.paquetDePartie);
		ordreDesJoueurs = new ArrayList<Integer>();
		this.ordreDesJoueurs.add(tempJoueurReel.getId());
		
		this.listeJoueurs.add(tempJoueurReel);
		for (int i = 1; i < this.nombreDeJoueurs; i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, this.paquetDePartie, Difficulte.facile);
			this.ordreDesJoueurs.add(tempJoueurVirtuel.getId());
			this.listeJoueurs.add(tempJoueurVirtuel);
		}
	}
	
public void saveParametres() {
		
		try {
			FileOutputStream fichier = new FileOutputStream("parametres.conf");
			ObjectOutputStream temp = new ObjectOutputStream(fichier);
			temp.writeObject(this);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
public void readParametres() throws ClassNotFoundException {
		
		try {
			FileInputStream fichier = new FileInputStream("parametres.conf");
			ObjectInputStream temp = new ObjectInputStream(fichier);
			ParametresDePartie tempParametres = (ParametresDePartie) temp.readObject();
			this.listeJoueurs=tempParametres.getListeJoueurs();
			this.nombreDeJoueurs=tempParametres.getNombreDeJoueurs();
			this.nombreDeManches=tempParametres.getNombreDeManches();
			this.ordreDesJoueurs=tempParametres.getOrdreDesJoueurs();
			this.paquetDePartie=tempParametres.getPaquetDePartie();
			this.typePartie=tempParametres.getTypePartie();
			this.ordreDesJoueurs=tempParametres.getOrdreDesJoueurs();
			
		} catch (FileNotFoundException e) {
			this.parametresParDefaut();
		} catch (IOException e) {
			this.parametresParDefaut();
		}
	}

@Override
public String toString() {
	return "ParametresDePartie [nombreDeManches=" + nombreDeManches + ", nombreDeJoueurs=" + nombreDeJoueurs
			+ ", typePartie=" + typePartie + ", ordreDesJoueurs=" + ordreDesJoueurs + ", listeJoueurs=" + listeJoueurs
			+ ", paquetDePartie=" + paquetDePartie + "]";
}




	
}
