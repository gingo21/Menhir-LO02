package menhir;

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
				this.puissanceActions[i][j] = (int) (Math.random() * (5 - 1)+1);
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
		boolean condition;
		if (typeaction == TypeAction.farfadet){
			int puissance = this.puissanceActions[tempValeur][2];
			if (statutPartie == StatutPartie.avancee){
				if(!(tempPaquetDest.getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty()))
					System.out.println("Possibilite de jouer carte chien de garde");
				// possibilite de jouer carte chien de garde -> different si joueur virtuel ou joueur reel
				// si joue chien de garde
				CarteChiensDeGarde tempCarteChiensDeGarde = (CarteChiensDeGarde) tempPaquetDest.getPaquetsDeCartes()
						.get("Cartes ChiensDeGarde").get(0);
				puissance = tempCarteChiensDeGarde.utiliser(destinataire, saisonActuelle, puissance);
				
			}
			for (int i=0; i< puissance;i++){
				boolean condition1;
				if (condition1 =(destinataire.getPaquet().getGrainesDeMenhir() > 0 && puissance > 0)){
				destinataire.getPaquet().donnerUneGraineDeMenhir(acteur);
				}
				if (!condition1)
					break;
		}
		}
		if (typeaction == TypeAction.engrais){
			CarteChamp tempCarteChamp = (CarteChamp) acteur.getPaquet().getCarteChamp();
			for (int i = 0; i< this.puissanceActions[tempValeur][1]; i++){
				if ((tempPaquet.getGrainesDeMenhir() > 0)){
					tempCarteChamp.rajouterGraines(1);
					tempPaquet.setGrainesDeMenhir(tempPaquet.getGrainesDeMenhir()-1);
				}
			}
			
			
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
		result += "Carte [nom=" + this.getNom() + ", id=" + this.getId() + ", estUtilise=" + this.isEstDetenuParUnJoueur() + "] \n";
		for (int j = 0; j < 3; j++){
			for (int i = 0; i < 4; i++){
				result += + this.puissanceActions[i][j]+ " ";
			}
			result += "\n";
		}
		return result;
	}
}
