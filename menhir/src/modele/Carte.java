package modele;

import java.io.Serializable;
import java.util.Observable;

/**
 * La classe Carte va permettre de r�pr�senter de la mani�re la plus g�n�rale
 * possible une carte du jeu menhir. Les instances de Carte seront ensuite
 * utilis�es dans les paquets de ressources de mod�le. Cette classe est
 * s�rialisable pour la sauvegarder dans un fichier pour les param�tres. Elle
 * h�rite d'Observable car elle va notifier son utilisation � ses observateurs
 * (instances de Observer). Elle reste abstraite car elle est encore trop
 * g�n�rale.
 * 
 * @see PaquetDeRessources
 * @see ParametresDePartie
 * @see Joueur
 */
public abstract class Carte extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 611620216515689L;
	/**
	 * Nom de la carte
	 */
	private String nom;
	/**
	 * Num�ro d'identit� unique de la carte
	 */
	private int id;
	/**
	 * Cela permet de savoir si la carte est dans un paquet de ressources d'un
	 * joueur ou de la partie.
	 */
	private boolean estDetenuParUnJoueur;
	/**
	 * Cela permet de savoir si la carte a d�j� �t� utilis�e par un joueur
	 * (instance de la classe Joueur).
	 */
	private boolean estUtilise;
	/**
	 * Indique le num�ro d'identit� de la derni�re carte instanci�e pour que la
	 * prochaine carte ait un num�ro diff�rent
	 */
	public static int numeroDuDernierID = 0;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            r�cup�re le nom d�sir� pour construie une instance de la
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
	 * Permet de r�initialiser l'utilisation et la d�tention de la carte.
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
	 * Mise � jour du nom de la carte
	 * 
	 * @param nom
	 *            r�cup�re le nom d�sir�
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le num�ro d'identit� de la carte
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return l'attribut qui indique si la carte est d�tenue par un joueur.
	 */
	public boolean isEstDetenuParUnJoueur() {
		return estDetenuParUnJoueur;
	}

	/**
	 * Mise � jour de la d�tention ou non de la carte
	 * 
	 * @param estDetenuParUnJoueur
	 *            r�cup�re si oui ou non la carte est d�tenue.
	 */
	public void setEstDetenuParUnJoueur(boolean estDetenuParUnJoueur) {
		this.estDetenuParUnJoueur = estDetenuParUnJoueur;
	}

	/**
	 * @return l'attribut qui indique si la carte a d�j� �t� utilis�e.
	 */
	public boolean isEstUtilise() {
		return estUtilise;
	}

	/**
	 * Mise � jour de l'utilisation ou non de la carte.
	 * 
	 * @param estUtilise
	 *            r�cup�re si oui ou non la carte a d�j� �t� utilis�e.
	 */
	public void setEstUtilise(boolean estUtilise) {
		this.estUtilise = estUtilise;
	}

	/**
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple une carte. Elle red�finit celle de la classe �tendue (ici Object).
	 */
	public String toString() {
		return "Carte [nom=" + nom + ", id=" + id + ", estDetenuParUnJoueur=" + estDetenuParUnJoueur + ", estUtilise="
				+ estUtilise + "]";
	}
}
