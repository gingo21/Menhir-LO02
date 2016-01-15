package modele;

import java.io.Serializable;
import java.util.Observable;

/**
 * La classe Carte va permettre de réprésenter de la manière la plus générale
 * possible une carte du jeu menhir. Les instances de Carte seront ensuite
 * utilisées dans les paquets de ressources de modèle. Cette classe est
 * sérialisable pour la sauvegarder dans un fichier pour les paramétres. Elle
 * hérite d'Observable car elle va notifier son utilisation à ses observateurs
 * (instances de Observer). Elle reste abstraite car elle est encore trop
 * générale.
 * 
 * @see PaquetDeRessources
 * @see ParametresDePartie
 * @see Joueur
 */
public abstract class Carte extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = 611620216515689L;
	/**
	 * Nom de la carte
	 */
	private String nom;
	/**
	 * Numéro d'identité unique de la carte
	 */
	private int id;
	/**
	 * Cela permet de savoir si la carte est dans un paquet de ressources d'un
	 * joueur ou de la partie.
	 */
	private boolean estDetenuParUnJoueur;
	/**
	 * Cela permet de savoir si la carte a déjà été utilisée par un joueur
	 * (instance de la classe Joueur).
	 */
	private boolean estUtilise;
	/**
	 * Indique le numéro d'identité de la dernière carte instanciée pour que la
	 * prochaine carte ait un numéro différent
	 */
	public static int numeroDuDernierID = 0;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            récupère le nom désiré pour construie une instance de la
	 *            carte.
	 */
	public Carte(String nom) {
		this.estUtilise = false;
		this.estDetenuParUnJoueur = false;
		this.id = numeroDuDernierID;
		numeroDuDernierID++;
		this.nom = nom;
	}

	/**
	 * Permet de réinitialiser l'utilisation et la détention de la carte.
	 */
	public void retourAuPaquet() {
		this.estDetenuParUnJoueur = false;
		this.estUtilise = false;
	}

	/**
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Mise à jour du nom de la carte
	 * 
	 * @param nom
	 *            récupère le nom désiré
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le numéro d'identité de la carte
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return l'attribut qui indique si la carte est détenue par un joueur.
	 */
	public boolean isEstDetenuParUnJoueur() {
		return estDetenuParUnJoueur;
	}

	/**
	 * Mise à jour de la détention ou non de la carte
	 * 
	 * @param estDetenuParUnJoueur
	 *            récupère si oui ou non la carte est détenue.
	 */
	public void setEstDetenuParUnJoueur(boolean estDetenuParUnJoueur) {
		this.estDetenuParUnJoueur = estDetenuParUnJoueur;
	}

	/**
	 * @return l'attribut qui indique si la carte a déjà été utilisée.
	 */
	public boolean isEstUtilise() {
		return estUtilise;
	}

	/**
	 * Mise à jour de l'utilisation ou non de la carte.
	 * 
	 * @param estUtilise
	 *            récupère si oui ou non la carte a déjà été utilisée.
	 */
	public void setEstUtilise(boolean estUtilise) {
		this.estUtilise = estUtilise;
	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple une carte. Elle redéfinit celle de la classe étendue (ici Object).
	 */
	public String toString() {
		return "Carte [nom=" + nom + ", id=" + id + ", estDetenuParUnJoueur=" + estDetenuParUnJoueur + ", estUtilise="
				+ estUtilise + "]";
	}
}
