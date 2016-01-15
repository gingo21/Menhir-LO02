package modele;

/**
 * La classe CarteChiensDeGarde �tend la Carte Alliee pour lui associer une
 * m�thode permettant d'utiliser la carte en tant que CarteChiensDeGarde.
 * 
 * @see CarteAlliee
 */
public class CarteChiensDeGarde extends CarteAlliee {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 6228607201064829046L;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            r�cup�re le nom d�sir� de la carte.
	 */
	public CarteChiensDeGarde(String nom) {
		super(nom);
	}

	/**
	 * La m�thode utiliser de cette carte est appel�e lorsque le paquet de
	 * ressources de joueur qui lui est associ� est attaqu� par une carte
	 * ingr�dient avec l'action farfadet et que le joueur d�cide de se d�fendre.
	 * Elle va permettre en fonction de la saisonActuelle (avec la puissance de
	 * la carte)et de la puissance de l'attaque de diminuer ou compl�ment
	 * contrer la tentative de vol en cours sur la carte champ du paquet de
	 * ressources du joueur poss�dant cette carte.
	 * 
	 * @param destinataire
	 *            r�cup�re le joueur attaquant.
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @param puissanceAttaqueFarfadet
	 *            r�cup�re la puissance de l'attaque effectu�e par l'attaquant.
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
