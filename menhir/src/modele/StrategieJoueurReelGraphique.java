package modele;

/**
 * La classe StratégieJoueurReelGraphique étend la classe Strategie pour définir
 * la stratégie utilisable par le joueur réel en mode graphique. Cette stratégie
 * se traduit par une demande au joueur de l'action qu'il veut faire qui se suit
 * d'une attente que la vue de cette stratégie donnée par la classe
 * VueStrategieJoueurReelGraphique va mettre à terme en réveillant le Thread du
 * modele en utilisant des contrôleurs (avec des boutons).
 * 
 * @see Strategie
 * @see VueStrategieJoueurReelGraphique
 */
public class StrategieJoueurReelGraphique extends Strategie {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -1604537757841635404L;

	/**
	 * Carte que le joueur veut actuellement jouer
	 */
	private Carte carteAJouer;

	/**
	 * Action que le joueur veut actuellement faire (avec une carte ingrédient)
	 */
	private TypeAction actionAJouer;
	/**
	 * Joueur ennemi que le joueur veut actuellement attaquer
	 */
	private Joueur destinataireAAttaquer;
	/**
	 * Volonté ou non du joueur de se défendre avec une carte chiens de garde
	 */
	private boolean seDefendre = false;
	/**
	 * Volonté ou non du joueur d'attaquer avec une carte taupes géantes
	 */
	private boolean attaquer = false;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param referenceJoueur
	 *            récupère le joueur associé à la stratégie.
	 */
	public StrategieJoueurReelGraphique(Joueur referenceJoueur) {
		super(referenceJoueur);
	}

	/**
	 * @return la carte que le joueur veut actuellement jouer.
	 */
	public Carte getCarteAJouer() {
		return carteAJouer;
	}

	/**
	 * Mise à jour de la carte à jouer
	 * 
	 * @param carteAJouer
	 *            récupère la nouvelle carte à jouer.
	 */
	public void setCarteAJouer(Carte carteAJouer) {
		this.carteAJouer = carteAJouer;
	}

	/**
	 * @return l'action que le joueur veut actuellement faire.
	 */
	public TypeAction getActionAJouer() {
		return actionAJouer;
	}

	/**
	 * Mise à jour de l'action à faire
	 * 
	 * @param actionAJouer
	 *            récupère la nouvelle action à faire
	 */
	public void setActionAJouer(TypeAction actionAJouer) {
		this.actionAJouer = actionAJouer;
	}

	/**
	 * @return le joueur ennemi que le joueur veut actuellement attaquer.
	 */
	public Joueur getDestinataireAAttaquer() {
		return destinataireAAttaquer;
	}

	/**
	 * Mise à jour du destinatire à attaquer
	 * 
	 * @param destinataireAAttaquer
	 *            récupère le nouveau destinataire à attaquer.
	 */
	public void setDestinataireAAttaquer(Joueur destinataireAAttaquer) {
		this.destinataireAAttaquer = destinataireAAttaquer;
	}

	/**
	 * @return la volonté ou non du joueur de se défendre avec une carte chiens
	 *         de garde
	 */
	public boolean isSeDefendre() {
		return seDefendre;
	}

	/**
	 * Mise à jour de la volonté de se défendre
	 * 
	 * @param seDefendre
	 *            récupère la nouvelle volonté de se défendre.
	 */
	public void setSeDefendre(boolean seDefendre) {
		this.seDefendre = seDefendre;
	}

	/**
	 * @return la volonté ou non du joueur d'attaquer avec une carte taupes
	 *         géantes
	 */
	public boolean isAttaquer() {
		return attaquer;
	}

	/**
	 * Mise à jour de la volonté d'attaquer
	 * 
	 * @param seDefendre
	 *            récupère la nouvelle volonté d'attaquer.
	 */
	public void setAttaquer(boolean attaquer) {
		this.attaquer = attaquer;
	}

	/**
	 * Implémentation de la façon dont va jouer un tour un joueur réel pour le
	 * choix d'une carte ingrédient et d'une action
	 */
	public synchronized void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		try {
			this.setChanged();
			this.notifyObservers("Quelle carte ingrédient jouez-vous ? (Cliquez dessus) ");
			this.wait();
			this.setChanged();
			this.notifyObservers("Quelle action ?");
			this.wait();
			if (this.actionAJouer == TypeAction.engrais) {
				((CarteIngredient) this.carteAJouer).utiliser(TypeAction.engrais, this.getReferenceJoueur(),
						this.getReferenceJoueur(), saisonActuelle, parametresDePartie);
				this.setChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getCarteChamp().getMenhirAdultes()
						+ " menhirs adultes sur votre carte champ.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
			}
			if (this.actionAJouer == TypeAction.farfadet) {
				this.setChanged();
				this.notifyObservers("A quel joueur voulez-vous voler des graines ? (Cliquez lui-dessus)");
				this.wait();
				((CarteIngredient) this.carteAJouer).utiliser(TypeAction.farfadet, this.destinataireAAttaquer,
						this.getReferenceJoueur(), saisonActuelle, parametresDePartie);
				this.destinataireAAttaquer.score(parametresDePartie.getTypePartie());
				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());

			}
			if (this.actionAJouer == TypeAction.geantGardient) {
				((CarteIngredient) this.carteAJouer).utiliser(TypeAction.geantGardient, this.getReferenceJoueur(),
						this.getReferenceJoueur(), saisonActuelle, parametresDePartie);
				this.setChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getGrainesDeMenhir() + " graines de menhirs.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implémentation de la façon dont va se défendre un joueur réel s'il
	 * possède une carte de chiens de gardes
	 */
	public synchronized int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance) {
		int puissanceModifie = puissance;
		try {
			if (parametresDePartie.getTypePartie() == StatutPartie.avancee
					&& !(destinataire.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty())) {
				CarteChiensDeGarde tempCarte = (CarteChiensDeGarde) destinataire.getPaquet().getPaquetsDeCartes()
						.get("Cartes Chiens De Garde").get(0);
				if (!tempCarte.isEstUtilise()) {
					this.setChanged();
					this.notifyObservers(acteur.getNom() + " vous lance une attaque de " + puissanceModifie + " "
							+ "graines, Se défendre avec votre carte chien de garde ?");
					this.wait();
					// On reçoit la reponse
					if (this.seDefendre) {
						puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle, puissanceModifie);
						this.setChanged();
						this.notifyObservers(this.getReferenceJoueur().getNom() + " se défend de "
								+ destinataire.getNom() + " avec ses chiens de garde et ne perd que " + puissanceModifie
								+ " graines de menhir.");
					}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return puissanceModifie;
	}

	/**
	 * Implémentation de la façon dont va attaquer un joueur réel s'il possède
	 * une carte de taupes géantes
	 */
	public synchronized void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle) {
		try {
			if (parametresDePartie.getTypePartie() == StatutPartie.avancee
					&& !(acteur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty())) {
				CarteTaupesGeantes tempCarte = (CarteTaupesGeantes) acteur.getPaquet().getPaquetsDeCartes()
						.get("Cartes Taupes Geantes").get(0);
				if (!tempCarte.isEstUtilise()) {
					this.setChanged();
					this.notifyObservers(
							"Voulez-vous attaquer " + destinataire.getNom() + " avec votre Carte Taupes Géantes ?");
					this.wait();
					if (this.attaquer) {
						this.setChanged();
						this.notifyObservers(this.getReferenceJoueur().getNom() + " attaque " + destinataire.getNom()
								+ " avec ses taupes et lui détruit " + tempCarte.utiliser(destinataire, saisonActuelle)
								+ " menhirs adultes sur sa carte champ.");
					}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implémentation de la façon dont un joueur réel va choisir si
	 * oui ou non il veut une carte alliée en partie avancée
	 */
	public synchronized void choixDeManche(ParametresDePartie parametresDePartie) {
		this.setChanged();
		this.notifyObservers("Voulez-vous une carte Alliee à la place de 2 graines de Menhir ?");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
