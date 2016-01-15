package modele;

/**
 * La classe CarteChamp va �tendre la classe Carte pour r�pr�senter la carte o�
 * l'on fait pousser les menhirs.
 * 
 * @see Carte
 */
public class CarteChamp extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -3389589595911788625L;

	/**
	 * Nombre de menhir qui ont pouss� sur la carte.
	 */
	private int menhirAdultes;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            r�cup�re le nom d�sir� de la carte.
	 */
	public CarteChamp(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	/**
	 * Permet de faire pousser plusieurs menhirs sur la carte.
	 * 
	 * @param nombre
	 *            r�cup�re le nombre (de graines) � faire pousser en menhirs.
	 */
	public void rajouterGraines(int nombre) {
		this.menhirAdultes += nombre;
	}

	/**
	 * @return le nombre de menhirs ayant pouss� sur la carte.
	 */
	public int getMenhirAdultes() {
		return menhirAdultes;
	}

	/**
	 * Mise � jour du nombre de menhirs adultes sur la carte.
	 * 
	 * @param menhirAdultes
	 *            r�cup�re le vrai nombre de menhirs adultes.
	 */
	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	/**
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple une carte champ. Elle red�finit celle de la classe �tendue.
	 */
	public String toString() {
		return "CarteChamp [menhirAdultes=" + menhirAdultes + "]";
	}
}
