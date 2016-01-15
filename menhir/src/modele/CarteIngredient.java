package modele;

/**
 * La classe CarteIngrédient va étendre la classe Carte pour ajouter toutes les
 * fonctionnalités des cartes ingrédients avec leur valeurs de puissance et leur
 * utilisation.
 * 
 * @see Carte
 */
public class CarteIngredient extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -7789616796913158493L;

	/**
	 * Ce tableau à deux dimensions (4*3) indique la puissance de la carte en
	 * fonction d'un numéro qui correspond sa place sur la carte réel et aussi
	 * au numéro de saison et du type d'action.
	 */
	private int puissanceActions[][];

	/**
	 * Il s'agit du constructeur de la classe où l'on spécifie, en plus du nom,
	 * les valeurs de puissance.
	 * 
	 * @param nom
	 *            récupère le nom de la carte.
	 * @param puissances
	 *            récupère la tableau des valeurs de puissance.
	 */
	public CarteIngredient(String nom, int puissances[][]) {
		super(nom);
		this.puissanceActions = new int[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.puissanceActions[i][j] = puissances[i][j];
			}
		}
	}

	/**
	 * Il s'agit d'une surcharge du constructeur de la classe où l'on spécifie
	 * juste le nom. Les valeurs de puissance sont choisies aléatoirement.
	 * 
	 * @param nom
	 *            récupère le nom de la carte.
	 */
	public CarteIngredient(String nom) {
		super(nom);
		this.puissanceActions = new int[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.puissanceActions[i][j] = (int) (Math.random() * (5 - 0));
			}
		}
	}

	/**
	 * @return les valeurs de puissance.
	 */
	public int[][] getPuissanceActions() {
		return this.puissanceActions;
	}

	/**
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @param typeAction
	 *            récupère le type d'action (géant, engrais, farfadet).
	 * @return la puissance correspondant à la saison actuelle et à l'action
	 *         demandée.
	 */
	public int getPuissanceActions(Saison saisonActuelle, TypeAction typeAction) {
		int tempValeur1 = 2;
		if (saisonActuelle == Saison.automne) {
			tempValeur1 = 2;
		} else if (saisonActuelle == Saison.hiver) {
			tempValeur1 = 3;
		} else if (saisonActuelle == Saison.printemps) {
			tempValeur1 = 0;
		} else if (saisonActuelle == Saison.ete) {
			tempValeur1 = 1;
		}
		int tempValeur2 = 2;
		if (typeAction == TypeAction.geantGardient) {
			tempValeur2 = 0;
		} else if (typeAction == TypeAction.engrais) {
			tempValeur2 = 1;
		} else if (typeAction == TypeAction.farfadet) {
			tempValeur2 = 2;
		}

		return puissanceActions[tempValeur1][tempValeur2];
	}

	/**
	 * Mise à jour des valeurs de puissance.
	 * 
	 * @param puissanceActions
	 *            récupère les valeurs de puissance.
	 */
	public void setPuissanceActions(int[][] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	/**
	 * La méthode utiliser va permettre d'ajouter la fonctionnalité
	 * d'utilisation de type ingrédient à la carte. Elle permet d'utiliser trois
	 * action : engrais pour faire pousser les graines du paquet de ressources
	 * du joueur ayant la carte, farfadet pour voler des graines à un paquet de
	 * ressources ennemi (de joueur) et géant pour avoir de nouvelles graines.
	 * En fonction de l'action, de la saison actuelle, l'action va avoir une
	 * puissance différente qui dépendra des valeurs de puissance.
	 * 
	 * @param typeaction
	 *            récupère le type d'action.
	 * @param destinataire
	 *            récupère le destinataire (pouvant être le joueur jouant la
	 *            carte) de l'action.
	 * @param acteur
	 *            récupère l'acteur (joueur jouant la carte).
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @param parametresDePartie
	 *            récupère les paramétres de la partie.
	 */
	public void utiliser(TypeAction typeaction, Joueur destinataire, Joueur acteur, Saison saisonActuelle,
			ParametresDePartie parametresDePartie) {
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

		if (typeaction == TypeAction.geantGardient) {
			tempPaquetPartie.donnerDesGrainesDeMenhir(acteur, this.puissanceActions[tempValeur][0]);
			if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " reçoit  " + this.puissanceActions[tempValeur][0]
						+ " graines du geant gardien ");
			} else {
				this.setChanged();
				this.notifyObservers(
						"Vous recevez " + this.puissanceActions[tempValeur][0] + " graines du geant gardien");
			}
			this.setChanged();
			this.notifyObservers("utiliser geantGardient");
		}
		if (typeaction == TypeAction.farfadet) {
			int puissance = this.puissanceActions[tempValeur][2];
			puissance = destinataire.getStrategie().seDefendre(parametresDePartie, destinataire, acteur, saisonActuelle,
					puissance);
			int nombreDeGrainesAVoler = Math.min(puissance, destinataire.getPaquet().getGrainesDeMenhir());
			if (nombreDeGrainesAVoler < 0) {
				nombreDeGrainesAVoler = 0;
			}
			destinataire.getPaquet().donnerDesGrainesDeMenhir(acteur, nombreDeGrainesAVoler);
			if (destinataire instanceof JoueurReel) {
				this.setChanged();
				this.notifyObservers(
						acteur.getNom() + " a envoyé ses farfadets vous voler " + nombreDeGrainesAVoler + " graines");
			} else if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " a envoyé ses farfadets voler " + nombreDeGrainesAVoler
						+ " graines a " + destinataire.getNom());
			} else {
				this.setChanged();
				this.notifyObservers("Vous avez envoyé vos farfadets voler " + nombreDeGrainesAVoler + " graines a "
						+ destinataire.getNom());
			}
			this.setChanged();
			this.notifyObservers(destinataire);

		}
		if (typeaction == TypeAction.engrais) {
			CarteChamp tempCarteChamp = (CarteChamp) acteur.getPaquet().getCarteChamp();
			int nombreDeGrainesPoussees = Math.min(this.puissanceActions[tempValeur][1],
					tempPaquet.getGrainesDeMenhir());
			tempCarteChamp.rajouterGraines(nombreDeGrainesPoussees);
			tempPaquet.setGrainesDeMenhir(tempPaquet.getGrainesDeMenhir() - nombreDeGrainesPoussees);

			if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " fait pousser " + nombreDeGrainesPoussees + " menhirs ");
			} else {
				this.setChanged();
				this.notifyObservers("Vous faites pousser " + nombreDeGrainesPoussees + " menhirs ");
			}
			this.setChanged();
			this.notifyObservers("utiliser engrais");
		}
		this.setEstUtilise(true);
	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple une carte ingrédient. Elle redéfinit celle de la classe étendue.
	 */
	public String toString() {
		String result = "";
		result += "Carte Ingrédient [nom=" + this.getNom() + ", id=" + this.getId() + ", estUtilise="
				+ this.isEstDetenuParUnJoueur() + "] \n";
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				result += +this.puissanceActions[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}
}
