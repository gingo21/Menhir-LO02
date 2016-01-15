package modele;

/**
 * La classe CarteChiensDeGarde étend la Carte Alliee pour lui associer une
 * méthode permettant d'utiliser la carte en tant que CarteChiensDeGarde.
 * 
 * @see CarteAlliee
 */
public class CarteChiensDeGarde extends CarteAlliee {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = 6228607201064829046L;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            récupère le nom désiré de la carte.
	 */
	public CarteChiensDeGarde(String nom) {
		super(nom);
	}

	/**
	 * La méthode utiliser de cette carte est appelée lorsque le paquet de
	 * ressources de joueur qui lui est associé est attaqué par une carte
	 * ingrédient avec l'action farfadet et que le joueur décide de se défendre.
	 * Elle va permettre en fonction de la saisonActuelle (avec la puissance de
	 * la carte)et de la puissance de l'attaque de diminuer ou complément
	 * contrer la tentative de vol en cours sur la carte champ du paquet de
	 * ressources du joueur possédant cette carte.
	 * 
	 * @param destinataire
	 *            récupère le joueur attaquant.
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @param puissanceAttaqueFarfadet
	 *            récupère la puissance de l'attaque effectuée par l'attaquant.
	 * @return la puissance finale qu'aura l'attaque.
	 */
	public int utiliser(Joueur destinataire, Saison saisonActuelle, int puissanceAttaqueFarfadet) {
		int tempValeur = 0;
		if (saisonActuelle == Saison.automne) {
			tempValeur = 2;
		} else if (saisonActuelle == Saison.hiver) {
			tempValeur = 3;
		} else if (saisonActuelle == Saison.printemps) {
			tempValeur = 0;
		} else if (saisonActuelle == Saison.ete) {
			tempValeur = 1;
		}
		int[] tempPuissance = this.getPuissanceActions();
		this.setEstUtilise(true);

		int puissanceFinale = puissanceAttaqueFarfadet - tempPuissance[tempValeur];
		if (puissanceFinale < 0) {
			puissanceFinale = 0;
		}

		this.setChanged();
		this.notifyObservers(destinataire);

		this.setChanged();
		this.notifyObservers("utiliser");

		return puissanceFinale;
	}

}
