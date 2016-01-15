package modele;

/**
 * La classe CarteIngr�dient va �tendre la classe Carte pour ajouter toutes les
 * fonctionnalit�s des cartes ingr�dients avec leur valeurs de puissance et leur
 * utilisation.
 * 
 * @see Carte
 */
public class CarteIngredient extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -7789616796913158493L;

	/**
	 * Ce tableau � deux dimensions (4*3) indique la puissance de la carte en
	 * fonction d'un num�ro qui correspond sa place sur la carte r�el et aussi
	 * au num�ro de saison et du type d'action.
	 */
	private int puissanceActions[][];

	/**
	 * Il s'agit du constructeur de la classe o� l'on sp�cifie, en plus du nom,
	 * les valeurs de puissance.
	 * 
	 * @param nom
	 *            r�cup�re le nom de la carte.
	 * @param puissances
	 *            r�cup�re la tableau des valeurs de puissance.
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
	 * Il s'agit d'une surcharge du constructeur de la classe o� l'on sp�cifie
	 * juste le nom. Les valeurs de puissance sont choisies al�atoirement.
	 * 
	 * @param nom
	 *            r�cup�re le nom de la carte.
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
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @param typeAction
	 *            r�cup�re le type d'action (g�ant, engrais, farfadet).
	 * @return la puissance correspondant � la saison actuelle et � l'action
	 *         demand�e.
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
	 * Mise � jour des valeurs de puissance.
	 * 
	 * @param puissanceActions
	 *            r�cup�re les valeurs de puissance.
	 */
	public void setPuissanceActions(int[][] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	/**
	 * La m�thode utiliser va permettre d'ajouter la fonctionnalit�
	 * d'utilisation de type ingr�dient � la carte. Elle permet d'utiliser trois
	 * action : engrais pour faire pousser les graines du paquet de ressources
	 * du joueur ayant la carte, farfadet pour voler des graines � un paquet de
	 * ressources ennemi (de joueur) et g�ant pour avoir de nouvelles graines.
	 * En fonction de l'action, de la saison actuelle, l'action va avoir une
	 * puissance diff�rente qui d�pendra des valeurs de puissance.
	 * 
	 * @param typeaction
	 *            r�cup�re le type d'action.
	 * @param destinataire
	 *            r�cup�re le destinataire (pouvant �tre le joueur jouant la
	 *            carte) de l'action.
	 * @param acteur
	 *            r�cup�re l'acteur (joueur jouant la carte).
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @param parametresDePartie
	 *            r�cup�re les param�tres de la partie.
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
				this.notifyObservers(acteur.getNom() + " re�oit  " + this.puissanceActions[tempValeur][0]
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
						acteur.getNom() + " a envoy� ses farfadets vous voler " + nombreDeGrainesAVoler + " graines");
			} else if (acteur instanceof JoueurVirtuel) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " a envoy� ses farfadets voler " + nombreDeGrainesAVoler
						+ " graines a " + destinataire.getNom());
			} else {
				this.setChanged();
				this.notifyObservers("Vous avez envoy� vos farfadets voler " + nombreDeGrainesAVoler + " graines a "
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
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple une carte ingr�dient. Elle red�finit celle de la classe �tendue.
	 */
	public String toString() {
		String result = "";
		result += "Carte Ingr�dient [nom=" + this.getNom() + ", id=" + this.getId() + ", estUtilise="
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
