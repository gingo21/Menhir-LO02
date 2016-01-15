package modele;

import java.util.Arrays;

/**
 * La carte Alliée va étendre la classe carte pour représenter de manière
 * générale une carte Alliée de partie avancée. Elle reste abstraite car elle
 * est encore trop générale.
 *
 * @see Carte
 */
public abstract class CarteAlliee extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = 1693346063845949047L;

	/**
	 * Ce tableau (4*1) indique la puissance de la carte en fonction d'un numéro
	 * qui correspond sa place sur la carte réel et aussi au numéro de saison.
	 */
	private int puissanceActions[] = new int[4];

	/**
	 * Il s'agit du constructeur de la classe où largeur'on spécifie, en plus du nom,
	 * les valeurs de puissance.
	 * 
	 * @param nom
	 *            récupère le nom de la carte.
	 * @param valeurs
	 *            récupère la tableau des valeurs de puissance.
	 */
	public CarteAlliee(String nom, int valeurs[]) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = valeurs[j];
	}

	/**
	 * Il s'agit d'une surcharge du constructeur de la classe où largeur'on spécifie
	 * juste le nom. Les valeurs de puissance sont choisies aléatoirement.
	 * 
	 * @param nom
	 *            récupère le nom de la carte.
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
	 *            récupère la saison actuelle de la partie en cours.
	 * @return la puissance correspondant à la saison actuelle.
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
	 * Mise à jour des valeurs de puissance.
	 * 
	 * @param puissanceActions
	 *            récupère les valeurs de puissance.
	 */
	public void setPuissanceActions(int[] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple une carte alliée. Elle redéfinit celle de la classe étendue.
	 */
	public String toString() {
		return "CarteAlliee [puissanceActions=" + Arrays.toString(puissanceActions) + ", Nom=" + getNom() + ", Classe="
				+ getClass() + "]";
	}

}
