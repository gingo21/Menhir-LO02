package modele;

public class CarteChiensDeGarde extends CarteAlliee {

	public CarteChiensDeGarde(String nom) {
		super(nom);
	}

	public int utiliser(Joueur destinataire, Saison saisonActuelle,
			int puissanceAttaqueFarfadet) {
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

		int puissanceFinale = puissanceAttaqueFarfadet
				- tempPuissance[tempValeur];
		if (puissanceFinale < 0) {
			puissanceFinale = 0;
		}
		
		this.setChanged();
		this.notifyObservers("utiliser");
		
		return puissanceFinale;
	}

}
