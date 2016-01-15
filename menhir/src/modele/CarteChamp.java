package modele;

/**
 * La classe CarteChamp va étendre la classe Carte pour réprésenter la carte où
 * l'on fait pousser les menhirs.
 * 
 * @see Carte
 */
public class CarteChamp extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -3389589595911788625L;

	/**
	 * Nombre de menhir qui ont poussé sur la carte.
	 */
	private int menhirAdultes;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            récupère le nom désiré de la carte.
	 */
	public CarteChamp(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	/**
	 * Permet de faire pousser plusieurs menhirs sur la carte.
	 * 
	 * @param nombre
	 *            récupère le nombre (de graines) à faire pousser en menhirs.
	 */
	public void rajouterGraines(int nombre) {
		this.menhirAdultes += nombre;
	}

	/**
	 * @return le nombre de menhirs ayant poussé sur la carte.
	 */
	public int getMenhirAdultes() {
		return menhirAdultes;
	}

	/**
	 * Mise à jour du nombre de menhirs adultes sur la carte.
	 * 
	 * @param menhirAdultes
	 *            récupère le vrai nombre de menhirs adultes.
	 */
	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple une carte champ. Elle redéfinit celle de la classe étendue.
	 */
	public String toString() {
		return "CarteChamp [menhirAdultes=" + menhirAdultes + "]";
	}
}
