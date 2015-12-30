package modele;

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
				this.puissanceActions[i][j] = (int) (Math.random() * (5 - 0));
			}
		}
	}

	public int[][] getPuissanceActions() {
		return this.puissanceActions;
	}

	public void setPuissanceActions(int[][] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	public void utiliser(TypeAction typeaction, Joueur destinataire,
			Joueur acteur, Saison saisonActuelle, ParametresDePartie parametresDePartie) {
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
		PaquetDeRessourcesDePartie tempPaquetPartie = tempPaquet
				.getReferencePaquetPartie();

		if (typeaction == TypeAction.geantGardient) {
			tempPaquetPartie.donnerDesGrainesDeMenhir(acteur,
					this.puissanceActions[tempValeur][0]);
			if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " reçoit  "
						+ this.puissanceActions[tempValeur][0]
						+ " graines du geant gardien ");
			} else {
				this.setChanged();
				this.notifyObservers("Vous recevez "
						+ this.puissanceActions[tempValeur][0]
						+ " graines du geant gardien");
			}
			this.setChanged();
			this.notifyObservers("utiliser geantGardient");
		}
		if (typeaction == TypeAction.farfadet) {
			int puissance = this.puissanceActions[tempValeur][2];
			puissance = destinataire.getStrategie().seDefendre(parametresDePartie, destinataire,
					acteur, saisonActuelle, puissance);
			int nombreDeGrainesAVoler = Math.min(puissance, destinataire
					.getPaquet().getGrainesDeMenhir());
			if(nombreDeGrainesAVoler<0)
			{
				nombreDeGrainesAVoler=0;	
			}
			destinataire.getPaquet().donnerDesGrainesDeMenhir(acteur,
					nombreDeGrainesAVoler);
			if (destinataire instanceof JoueurReel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom()
						+ " a envoyé ses farfadets vous voler "
						+ nombreDeGrainesAVoler + " graines");
			} else if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom()
						+ " a envoyé ses farfadets voler "
						+ nombreDeGrainesAVoler + " graines a "
						+ destinataire.getNom());
			} else {
				this.setChanged();
				this.notifyObservers("Vous avez envoyé vos farfadets voler "
						+ nombreDeGrainesAVoler + " graines a "
						+ destinataire.getNom());
			}
			this.setChanged();
			this.notifyObservers("utiliser farfadets id" + destinataire.getId());

		}
		if (typeaction == TypeAction.engrais) {
			CarteChamp tempCarteChamp = (CarteChamp) acteur.getPaquet()
					.getCarteChamp();
			int nombreDeGrainesPoussees = Math.min(
					this.puissanceActions[tempValeur][1],
					tempPaquet.getGrainesDeMenhir());
			tempCarteChamp.rajouterGraines(nombreDeGrainesPoussees);
			tempPaquet.setGrainesDeMenhir(tempPaquet.getGrainesDeMenhir()
					- nombreDeGrainesPoussees);

			if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " fait pousser "
						+ nombreDeGrainesPoussees + " menhirs ");
			} else {
				this.setChanged();
				this.notifyObservers("Vous faites pousser "
						+ nombreDeGrainesPoussees + " menhirs ");
			}
			this.setChanged();
			this.notifyObservers("utiliser engrais");
		}
		this.setEstUtilise(true);
	}

	public String toString() {
		String result = "";
		result += "Carte Ingrédient [nom=" + this.getNom() + ", id=" + this.getId()
				+ ", estUtilise=" + this.isEstDetenuParUnJoueur() + "] \n";
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				result += +this.puissanceActions[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}
}
