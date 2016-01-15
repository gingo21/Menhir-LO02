package modele;

/**
 * La classe CarteComptageDePoints est une classe utilisée en cas de partie
 * avancée du jeu du Menhir qui permet de stocker les menhirs adultes des
 * manches précédentes. Elle étend la classe Carte.
 * 
 * @see Carte
 */
public class CarteComptageDePoints extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -2546643344186347017L;

	/**
	 * Nombre de menhirs stockés sur la carte
	 */
	private int menhirAdultes;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            récupère le nom désiré de la carte.
	 */
	public CarteComptageDePoints(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	/**
	 * Rajoute des menhirs sur la carte
	 * 
	 * @param nombre
	 *            récupère le nombre de menhirs à rajouter sur la carte.
	 */
	public void rajouterMenhirs(int nombre) {
		this.menhirAdultes += nombre;
	}

	/**
	 * @return le nombre de menhirs sur la carte.
	 */
	public int getMenhirAdultes() {
		return menhirAdultes;
	}

	/**
	 * Mise à jour du nombre de menhirs sur la carte.
	 * 
	 * @param menhirAdultes
	 *            récupère le vrai nombre de menhirs sur la carte.
	 */
	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple une carte de comptage de points. Elle redéfinit celle de la classe
	 * étendue.
	 */
	public String toString() {
		return "CarteComptageDePoints [menhirAdultes=" + menhirAdultes + "] \n";
	}
}
