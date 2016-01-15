package modele;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import launcher.Jeu;

/**
 * La classe ParamètresDePartie réunit tous les attributs nécessaire au
 * démarrage d'une partie. Elle hérite d'Observable car elle va notifier son
 * utilisation à ses observateurs (instances de Observer).
 */
public class ParametresDePartie extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -3939993060888077130L;

	/**
	 * Nombre de manches de notre partie
	 */
	private int nombreDeManches;

	/**
	 * Nombre de joueurs de notre partie
	 */
	private int nombreDeJoueurs;

	/**
	 * Type de la partie
	 */
	private StatutPartie typePartie;

	/**
	 * Liste de l'ordre des joueurs
	 */
	private ArrayList<Integer> ordreDesJoueurs;

	/**
	 * Liste des joueurs
	 */
	private ArrayList<Joueur> listeJoueurs;

	/**
	 * Paquet de Ressources de la partie
	 */
	private PaquetDeRessourcesDePartie paquetDePartie;

	/**
	 * Constructeur de la classe
	 */
	public ParametresDePartie() {
		try {
			this.readParametres();
			this.setPaquetDePartie(new PaquetDeRessourcesDePartie(typePartie, nombreDeJoueurs));
			if (Jeu.MODE_GRAPHIQUE) {
				this.getJoueurReel().setStrategie(new StrategieJoueurReelGraphique(this.getJoueurReel()));
			} else {
				this.getJoueurReel().setStrategie(new StrategieJoueurReelConsole(this.getJoueurReel()));
			}
		} catch (ClassNotFoundException e) {
			this.parametresParDefaut();
		}
	}

	/**
	 * Mise à jour de la liste de joueurs
	 * 
	 * @param nomDuJoueur
	 */
	public void miseAJourListeJoueurs(String nomDuJoueur) {
		for (int i = this.listeJoueurs.size() - 1; i >= 0; i--) {
			this.listeJoueurs.remove(i);
		}
		for (int i = this.ordreDesJoueurs.size() - 1; i >= 0; i--) {
			this.ordreDesJoueurs.remove(i);
		}
		JoueurReel tempJoueurReel = new JoueurReel(nomDuJoueur, this.paquetDePartie);
		this.ordreDesJoueurs.add(tempJoueurReel.getId());
		this.listeJoueurs.add(tempJoueurReel);
		for (int i = 1; i < this.nombreDeJoueurs; i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, this.paquetDePartie, Difficulte.facile);
			this.ordreDesJoueurs.add(tempJoueurVirtuel.getId());
			this.listeJoueurs.add(tempJoueurVirtuel);
		}
	}

	/**
	 * Cette méthode établie les paramètres par défaut
	 */
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

	/**
	 * Cette méthode sauvegarde les paramètres dans un fichier parametres.conf
	 */
	public void saveParametres() {

		try {
			FileOutputStream fichier = new FileOutputStream("parametres.conf");
			ObjectOutputStream tempFlux = new ObjectOutputStream(fichier);
			tempFlux.writeObject(this);
			tempFlux.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * Cette méthode lit les paramètres sauvegardé préalablement dans le fichier
	 * parametres.conf
	 * @throws ClassNotFoundException
	 */
	public void readParametres() throws ClassNotFoundException {

		try {
			FileInputStream fichier = new FileInputStream("parametres.conf");
			ObjectInputStream tempFlux = new ObjectInputStream(fichier);
			ParametresDePartie tempParametres = (ParametresDePartie) tempFlux.readObject();
			this.listeJoueurs = tempParametres.getListeJoueurs();
			this.nombreDeJoueurs = tempParametres.getNombreDeJoueurs();
			this.nombreDeManches = tempParametres.getNombreDeManches();
			this.ordreDesJoueurs = tempParametres.getOrdreDesJoueurs();
			this.paquetDePartie = tempParametres.getPaquetDePartie();
			this.typePartie = tempParametres.getTypePartie();
			this.ordreDesJoueurs = tempParametres.getOrdreDesJoueurs();
			tempFlux.close();
		} catch (FileNotFoundException e) {
			this.parametresParDefaut();
		} catch (IOException e) {
			this.parametresParDefaut();
		}
	}

	/**
	 * Cette méthode appelle la méthode rafraichirLesObservers pour chaque
	 * joueur de la partie
	 */
	public void rafraichirObserversDePaquet() {
		for (Iterator<Joueur> it = this.listeJoueurs.iterator(); it.hasNext();) {
			it.next().getPaquet().rafraichirLesObservers();
		}
	}

	/**
	 * @return le nombre de manches
	 */
	public int getNombreDeManches() {
		return nombreDeManches;
	}

	/**
	 * Mise à jour du nombre de manches de notre partie
	 * @param nombreDeManches le nombre de manches
	 */
	public void setNombreDeManches(int nombreDeManches) {
		this.nombreDeManches = nombreDeManches;
	}

	/**
	 * @return nombre de joueurs
	 */
	public int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}

	/**
	 * Mise à jour du nombre de joueurs
	 * 
	 * @param nombreDeJoueurs le nombre de joueurs
	 */
	public void setNombreDeJoueurs(int nombreDeJoueurs) {
		this.nombreDeJoueurs = nombreDeJoueurs;
	}

	/**
	 * @return l'ordre des joueurs
	 */
	public ArrayList<Integer> getOrdreDesJoueurs() {
		return ordreDesJoueurs;
	}

	/**
	 * Mise à jour de l'ordre des joueurs
	 * 
	 * @param ordreDesJoueurs l'ordre des joueurs
	 */
	public void setOrdreDesJoueurs(ArrayList<Integer> ordreDesJoueurs) {
		this.ordreDesJoueurs = ordreDesJoueurs;
	}

	/**
	 * @return le de la Partie
	 */
	public StatutPartie getTypePartie() {
		return typePartie;
	}

	/**
	 * Mise à jour du type de la partie
	 * 
	 * @param typePartie le type de Partie
	 */
	public void setTypePartie(StatutPartie typePartie) {
		this.typePartie = typePartie;
	}

	/**
	 * @return la liste des joueurs
	 */
	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	/**
	 * Mise à jour de la liste des joueurs
	 * 
	 * @param listeJoueurs la liste des joueurs
	 */
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	/**
	 * return paquetDePartie
	 */
	public PaquetDeRessourcesDePartie getPaquetDePartie() {
		return paquetDePartie;
	}

	/**
	 * Mise à jour du paquet de partie
	 * 
	 * @param paquetDePartie le paquet de Partie
	 */
	public void setPaquetDePartie(PaquetDeRessourcesDePartie paquetDePartie) {
		this.paquetDePartie = paquetDePartie;
	}

	/**
	 * @return le joueur réel
	 */
	public JoueurReel getJoueurReel() {
		JoueurReel tempJoueurReel = null;
		for (Iterator<Joueur> it = this.listeJoueurs.iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur instanceof JoueurReel) {
				tempJoueurReel = (JoueurReel) tempJoueur;
			}
		}
		return tempJoueurReel;
	}

	/**
	 * Méthode d'affichage
	 */
	public String toString() {
		return "ParametresDePartie [nombreDeManches=" + nombreDeManches + ", nombreDeJoueurs=" + nombreDeJoueurs
				+ ", typePartie=" + typePartie + ", ordreDesJoueurs=" + ordreDesJoueurs + ", listeJoueurs="
				+ listeJoueurs + ", paquetDePartie=" + paquetDePartie + "]";
	}

}
