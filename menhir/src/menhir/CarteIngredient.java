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
			if (acteur instanceof JoueurVirtuel) {
				System.out.println(acteur.getNom() + " reçoit  "+this.puissanceActions[tempValeur][0]  +" graines du geant gardien ");
			}
			else {
				System.out.println("Vous recevez "+ this.puissanceActions[tempValeur][0] +" graines du geant gardien");
			}
		}
		if (typeaction == TypeAction.farfadet){ 
			int puissance = this.puissanceActions[tempValeur][2];
			puissance = destinataire.seDefendre(statutPartie,destinataire,acteur,saisonActuelle,puissance);
			int nombreDeGrainesAVoler = Math.min(puissance, destinataire.getPaquet().getGrainesDeMenhir());
			destinataire.getPaquet().donnerUneGraineDeMenhir(acteur,nombreDeGrainesAVoler);
		
			if (destinataire instanceof JoueurReel)
				System.out.println(acteur.getNom()+"a envoye ses farfadets vous voler "+ nombreDeGrainesAVoler+ " graines");
			else if (acteur instanceof JoueurVirtuel)
				System.out.println(acteur.getNom()+"a envoye ses farfadets voler "+ nombreDeGrainesAVoler+ " graines a "+
							destinataire.getNom());
			else System.out.println("Vous avez envoye vos farfadets voler "+ nombreDeGrainesAVoler+ " graines a "+
					destinataire.getNom());
			
		}
		if (typeaction == TypeAction.engrais){
			CarteChamp tempCarteChamp = (CarteChamp) acteur.getPaquet().getCarteChamp();
			int nombreDeGrainesPoussees = Math.min(this.puissanceActions[tempValeur][1], tempPaquet.getGrainesDeMenhir());
			tempCarteChamp.rajouterGraines(nombreDeGrainesPoussees);
			tempPaquet.setGrainesDeMenhir(tempPaquet.getGrainesDeMenhir()-nombreDeGrainesPoussees);
			
			if (acteur instanceof JoueurVirtuel) {
				System.out.println(acteur.getNom() + " fait pousser "+ nombreDeGrainesPoussees +" menhirs ");
			}
			else {
				System.out.println("Vous faites pousser "+ nombreDeGrainesPoussees +" menhirs ");
			}
			
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
