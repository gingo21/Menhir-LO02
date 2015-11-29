package menhir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class CarteIngredient extends Carte {
	private int puissanceActions[][];

	public CarteIngredient(String nom, int puissance[][]) {
		super(nom);
		this.puissanceActions = new int[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.puissanceActions[i][j] = puissance[i][j];
			}
		}
	}

	public CarteIngredient(String nom) {
		super(nom);
		this.puissanceActions = new int[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.puissanceActions[i][j] = (int) (Math.random() * (4 - 1));
			}
		}
	}

	public int[][] getPuissanceActions() {
		return this.puissanceActions;
	}

	public void setPuissanceActions(int[][] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	public void utiliser(TypeAction typeaction, Joueur destinataire, Joueur acteur, Saison saisonActuelle, StatutPartie statutPartie) {
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
		PaquetDeRessourcesDeJoueur tempPaquet = acteur.getPaquet();
		PaquetDeRessourcesDePartie tempPaquetPartie = tempPaquet.getReferencePaquetPartie();
		PaquetDeRessourcesDeJoueur tempPaquetDest = destinataire.getPaquet();

		if (typeaction == TypeAction.geantGardient){
			tempPaquetPartie.donnerUneGraineDeMenhir(acteur, this.puissanceActions[tempValeur][0]);
		}
		if (typeaction == TypeAction.farfadet){
			int puissance = this.puissanceActions[tempValeur][2];
			if (statutPartie == StatutPartie.avancee){
				if(!(tempPaquetDest.getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty()))
					System.out.println("Possibilité de jouer carte chien de garde");
				// possibiilité de jouer carte chien de garde -> différent si joueur virtuel ou joueur reel
				// si joue chien de garde
				CarteChiensDeGarde tempCarteChiensDeGarde = (CarteChiensDeGarde) tempPaquetDest.getPaquetsDeCartes()
						.get("Cartes ChiensDeGarde").get(0);
				puissance = tempCarteChiensDeGarde.utiliser(destinataire, saisonActuelle, puissance);
				
			}
			while (destinataire.getPaquet().getGrainesDeMenhir() > 0 && puissance > 0){
				for (int i=0; i< puissance;i++)
				destinataire.getPaquet().donnerUneGraineDeMenhir(acteur);
			}
		}
		if (typeaction == TypeAction.engrais){
			CarteChamp tempCarteChamp = (CarteChamp) tempPaquet.getPaquetsDeCartes().get("Cartes Champs")
					.get(0);
			tempCarteChamp.rajouterGraines(this.puissanceActions[tempValeur][1]);
			tempPaquet.setGrainesDeMenhir(tempPaquet.getGrainesDeMenhir()-this.puissanceActions[tempValeur][1]);
			//fin de la manche ? 
			/*
			if (statutPartie == StatutPartie.avancee){
				CarteComptageDePoint tempComptage = (CarteComptageDePoint)tempPaquet.getPaquetsDeCartes()
						.get("Cartes Comptage De Points").get(0);
				tempCarteChamp.rajouterGraines(this.puissanceActions[2][tempValeur]);
			} */
		}
		this.setEstUtilise(true);
	}

	public String toString() {
		String result = "";
		for (int j = 0; j < 3; j++){
			for (int i = 0; i < 4; i++){
				result += + this.puissanceActions[i][j]+ " ";
			}
			result += "\n";
		}
		return result;
	}
}
