package modele;

public class CarteTaupesGeantes extends CarteAlliee {

	public CarteTaupesGeantes(String nom) {
		super(nom);
	}

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
		CarteChamp tempCarte = (CarteChamp) tempPaquet.getPaquetsDeCartes()
				.get("Cartes Champs").get(0);

		int nombreDeMenhirsADetruire = 0;
		nombreDeMenhirsADetruire = Math.min(tempCarte.getMenhirAdultes(), nombreMaximalDeMenhirsAdultesDetruits);
		
		
		this.setChanged();
		this.notifyObservers("utiliser");
		
		this.setEstUtilise(true);
		return nombreDeMenhirsADetruire;
	}

}
