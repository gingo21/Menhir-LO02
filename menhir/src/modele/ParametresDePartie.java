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
import java.util.Observable;
import java.util.Scanner;

public class ParametresDePartie extends Observable implements Serializable {
	private int nombreDeManches;
	private int nombreDeJoueurs;
	private StatutPartie typePartie;
	private int[] ordreDesJoueurs;
	private ArrayList<Joueur> listeJoueurs;
	private PaquetDeRessourcesDePartie paquetDePartie;
	private boolean saisieConsole;

	public ParametresDePartie() { 
		try {
			readParametres();
		} catch (ClassNotFoundException e){
			this.parametresParDefaut();
		} finally {
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

	public int[] getOrdreDesJoueurs() {
		return ordreDesJoueurs;
	}

	public void setOrdreDesJoueurs(int[] ordreDesJoueurs) {
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

	public boolean isSaisieConsole() {
		return saisieConsole;
	}

	public void setSaisieConsole(boolean saisieConsole) {
		this.saisieConsole = saisieConsole;
	}
	
	public void parametresParDefaut() {
		this.nombreDeJoueurs = 2;
		this.typePartie = StatutPartie.rapide;
		this.nombreDeManches = 1;
		this.paquetDePartie = new PaquetDeRessourcesDePartie(this.typePartie, this.nombreDeJoueurs);
		this.listeJoueurs = new ArrayList<Joueur>();
		JoueurReel tempJoueurReel = new JoueurReel("Joueur", this.paquetDePartie);
		ordreDesJoueurs = new int[this.nombreDeJoueurs];
		this.ordreDesJoueurs[0] = tempJoueurReel.getId();
		
		this.listeJoueurs.add(tempJoueurReel);
		for (int i = 1; i < this.nombreDeJoueurs; i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, this.paquetDePartie, Difficulte.facile);
			this.ordreDesJoueurs[i] = tempJoueurVirtuel.getId();
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
			this.saisieConsole=tempParametres.isSaisieConsole();
			
		} catch (FileNotFoundException e) {
			this.parametresParDefaut();
		} catch (IOException e) {
			this.parametresParDefaut();
		} finally {
			this.parametresParDefaut();
		}
	}
}
