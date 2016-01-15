package modele;

import java.util.Arrays;

/**
 * La carte Alli�e va �tendre la classe carte pour repr�senter de mani�re
 * g�n�rale une carte Alli�e de partie avanc�e. Elle reste abstraite car elle
 * est encore trop g�n�rale.
 *
 * @see Carte
 */
public abstract class CarteAlliee extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 1693346063845949047L;

	/**
	 * Ce tableau (4*1) indique la puissance de la carte en fonction d'un num�ro
	 * qui correspond sa place sur la carte r�el et aussi au num�ro de saison.
	 */
	private int puissanceActions[] = new int[4];

	/**
	 * Il s'agit du constructeur de la classe o� largeur'on sp�cifie, en plus du nom,
	 * les valeurs de puissance.
	 * 
	 * @param nom
	 *            r�cup�re le nom de la carte.
	 * @param valeurs
	 *            r�cup�re la tableau des valeurs de puissance.
	 */
	public CarteAlliee(String nom, int valeurs[]) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = valeurs[j];
	}

	/**
	 * Il s'agit d'une surcharge du constructeur de la classe o� largeur'on sp�cifie
	 * juste le nom. Les valeurs de puissance sont choisies al�atoirement.
	 * 
	 * @param nom
	 *            r�cup�re le nom de la carte.
	 */
	public CarteAlliee(String nom) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = (int) (Math.random() * (5 - 0));
	}

	/**
	 * @return les valeurs de puissance.
	 */
	public int[] getPuissanceActions() {
		return puissanceActions;
	}

	/**
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @return la puissance correspondant � la saison actuelle.
	 */
	public int getPuissanceActions(Saison saisonActuelle) {
		int tempValeur = 2;
		if (saisonActuelle == Saison.automne) {
			tempValeur = 2;
		} else if (saisonActuelle == Saison.hiver) {
			tempValeur = 3;
		} else if (saisonActuelle == Saison.printemps) {
			tempValeur = 0;
		} else if (saisonActuelle == Saison.ete) {
			tempValeur = 1;
		}

		return puissanceActions[tempValeur];
	}

	/**
	 * Mise � jour des valeurs de puissance.
	 * 
	 * @param puissanceActions
	 *            r�cup�re les valeurs de puissance.
	 */
	public void setPuissanceActions(int[] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	/**
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple une carte alli�e. Elle red�finit celle de la classe �tendue.
	 */
	public String toString() {
		return "CarteAlliee [puissanceActions=" + Arrays.toString(puissanceActions) + ", Nom=" + getNom() + ", Classe="
				+ getClass() + "]";
	}

}
