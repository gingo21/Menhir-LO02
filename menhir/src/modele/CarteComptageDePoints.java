package modele;

/**
 * La classe CarteComptageDePoints est une classe utilis�e en cas de partie
 * avanc�e du jeu du Menhir qui permet de stocker les menhirs adultes des
 * manches pr�c�dentes. Elle �tend la classe Carte.
 * 
 * @see Carte
 */
public class CarteComptageDePoints extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -2546643344186347017L;

	/**
	 * Nombre de menhirs stock�s sur la carte
	 */
	private int menhirAdultes;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            r�cup�re le nom d�sir� de la carte.
	 */
	public CarteComptageDePoints(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	/**
	 * Rajoute des menhirs sur la carte
	 * 
	 * @param nombre
	 *            r�cup�re le nombre de menhirs � rajouter sur la carte.
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
	 * Mise � jour du nombre de menhirs sur la carte.
	 * 
	 * @param menhirAdultes
	 *            r�cup�re le vrai nombre de menhirs sur la carte.
	 */
	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	/**
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple une carte de comptage de points. Elle red�finit celle de la classe
	 * �tendue.
	 */
	public String toString() {
		return "CarteComptageDePoints [menhirAdultes=" + menhirAdultes + "] \n";
	}
}
