package modele;

import java.io.Serializable;
import java.util.Observable;

/**
 * La classe Carte va permettre de réprésenter de la manière la plus générale
 * possible une carte du jeu menhir. Les instances de Carte seront ensuite
 * utilisées dans les paquets de ressources de modèle. Cette classe est
 * sérialisable pour la sauvegarder dans un fichier pour les paramétres.
 * 
 * @see PaquetDeRessources
 * @see ParametresDePartie
 */
public abstract class Carte extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = 611620216515689L;
	private String nom;
	private int id;
	private boolean estDetenuParUnJoueur;
	private boolean estUtilise;
	public static int numeroDuDernierID = 0;

	public Carte(String nom) {
		this.estUtilise = false;
		this.estDetenuParUnJoueur = false;
		this.id = numeroDuDernierID;
		numeroDuDernierID++;
		this.nom = nom;
	}

	public void retourAuPaquet() {
		this.estDetenuParUnJoueur = false;
		this.estUtilise = false;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public boolean isEstDetenuParUnJoueur() {
		return estDetenuParUnJoueur;
	}

	public void setEstDetenuParUnJoueur(boolean estDetenuParUnJoueur) {
		this.estDetenuParUnJoueur = estDetenuParUnJoueur;
	}

	public boolean isEstUtilise() {
		return estUtilise;
	}

	public void setEstUtilise(boolean estUtilise) {
		this.estUtilise = estUtilise;
	}

	public String toString() {
		return "Carte [nom=" + nom + ", id=" + id + ", estDetenuParUnJoueur=" + estDetenuParUnJoueur + ", estUtilise="
				+ estUtilise + "]";
	}
}
