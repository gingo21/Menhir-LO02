package modele;

/**
 * La classe CarteTaupesGeantes étend la Carte Alliee pour lui associer une
 * méthode permettant d'utiliser la carte en tant que CarteTaupesGeantes.
 * 
 * @see CarteAlliee
 */
public class CarteTaupesGeantes extends CarteAlliee {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -6868316408969678573L;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param nom
	 *            récupère le nom désiré de la carte.
	 */
	public CarteTaupesGeantes(String nom) {
		super(nom);
	}

	/**
	 * La méthode utiliser permet au joueur ayant cette d'attaquer les menhirs
	 * se situant sur une carte champ ennemie. La puissance de largeur'action dépendra
	 * de la saison actuelle.
	 * 
	 * @param destinataire
	 *            récupère le destinataire de largeur'attaque.
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @return la valeur de la puissance de largeur'attaque (le nombre de menhirs à
	 *         détruire).
	 */
	public int utiliser(Joueur destinataire, Saison saisonActuelle) {
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

		int nombreMaximalDeMenhirsAdultesDetruits = this.getPuissanceActions()[tempValeur];

		PaquetDeRessourcesDeJoueur tempPaquet = destinataire.getPaquet();
		CarteChamp tempCarte = (CarteChamp) tempPaquet.getPaquetsDeCartes().get("Cartes Champs").get(0);

		int nombreDeMenhirsADetruire = 0;
		nombreDeMenhirsADetruire = Math.min(tempCarte.getMenhirAdultes(), nombreMaximalDeMenhirsAdultesDetruits);
		tempCarte.setMenhirAdultes(tempCarte.getMenhirAdultes() - nombreDeMenhirsADetruire);

		this.setChanged();
		this.notifyObservers(destinataire);

		this.setChanged();
		this.notifyObservers("utiliser");

		this.setEstUtilise(true);
		return nombreDeMenhirsADetruire;
	}

}
