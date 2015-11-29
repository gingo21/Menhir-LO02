package menhir;

public class CarteTaupesGeantes extends CarteAlliee {

	public CarteTaupesGeantes(String nom) {
		super(nom);
	}

	public void utiliser(Joueur destinataire, Saison saisonActuelle) {
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
		
		int nombreMaximalDeMenhirsAdultesDetruits;
		
		PaquetDeRessourcesDeJoueur tempPaquet = destinataire.getPaquet();
		Carte tempCarte = tempPaquet.getCarteComptageDePoint();
		
		
		
		
	}


}
