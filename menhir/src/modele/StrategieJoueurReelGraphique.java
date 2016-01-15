package modele;

/**
 * La classe Strat�gieJoueurReelGraphique �tend la classe Strategie pour d�finir
 * la strat�gie utilisable par le joueur r�el en mode graphique. Cette strat�gie
 * se traduit par une demande au joueur de l'action qu'il veut faire qui se suit
 * d'une attente que la vue de cette strat�gie donn�e par la classe
 * VueStrategieJoueurReelGraphique va mettre � terme en r�veillant le Thread du
 * modele en utilisant des contr�leurs (avec des boutons).
 * 
 * @see Strategie
 * @see VueStrategieJoueurReelGraphique
 */
public class StrategieJoueurReelGraphique extends Strategie {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -1604537757841635404L;

	/**
	 * Carte que le joueur veut actuellement jouer
	 */
	private Carte carteAJouer;

	/**
	 * Action que le joueur veut actuellement faire (avec une carte ingr�dient)
	 */
	private TypeAction actionAJouer;
	/**
	 * Joueur ennemi que le joueur veut actuellement attaquer
	 */
	private Joueur destinataireAAttaquer;
	/**
	 * Volont� ou non du joueur de se d�fendre avec une carte chiens de garde
	 */
	private boolean seDefendre = false;
	/**
	 * Volont� ou non du joueur d'attaquer avec une carte taupes g�antes
	 */
	private boolean attaquer = false;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param referenceJoueur
	 *            r�cup�re le joueur associ� � la strat�gie.
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
	 * Mise � jour de la carte � jouer
	 * 
	 * @param carteAJouer
	 *            r�cup�re la nouvelle carte � jouer.
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
	 * Mise � jour de l'action � faire
	 * 
	 * @param actionAJouer
	 *            r�cup�re la nouvelle action � faire
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
	 * Mise � jour du destinatire � attaquer
	 * 
	 * @param destinataireAAttaquer
	 *            r�cup�re le nouveau destinataire � attaquer.
	 */
	public void setDestinataireAAttaquer(Joueur destinataireAAttaquer) {
		this.destinataireAAttaquer = destinataireAAttaquer;
	}

	/**
	 * @return la volont� ou non du joueur de se d�fendre avec une carte chiens
	 *         de garde
	 */
	public boolean isSeDefendre() {
		return seDefendre;
	}

	/**
	 * Mise � jour de la volont� de se d�fendre
	 * 
	 * @param seDefendre
	 *            r�cup�re la nouvelle volont� de se d�fendre.
	 */
	public void setSeDefendre(boolean seDefendre) {
		this.seDefendre = seDefendre;
	}

	/**
	 * @return la volont� ou non du joueur d'attaquer avec une carte taupes
	 *         g�antes
	 */
	public boolean isAttaquer() {
		return attaquer;
	}

	/**
	 * Mise � jour de la volont� d'attaquer
	 * 
	 * @param seDefendre
	 *            r�cup�re la nouvelle volont� d'attaquer.
	 */
	public void setAttaquer(boolean attaquer) {
		this.attaquer = attaquer;
	}

	/**
	 * Impl�mentation de la fa�on dont va jouer un tour un joueur r�el pour le
	 * choix d'une carte ingr�dient et d'une action
	 */
	public synchronized void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		try {
			this.setChanged();
			this.notifyObservers("Quelle carte ingr�dient jouez-vous ? (Cliquez dessus) ");
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
	 * Impl�mentation de la fa�on dont va se d�fendre un joueur r�el s'il
	 * poss�de une carte de chiens de gardes
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
							+ "graines, Se d�fendre avec votre carte chien de garde ?");
					this.wait();
					// On re�oit la reponse
					if (this.seDefendre) {
						puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle, puissanceModifie);
						this.setChanged();
						this.notifyObservers(this.getReferenceJoueur().getNom() + " se d�fend de "
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
	 * Impl�mentation de la fa�on dont va attaquer un joueur r�el s'il poss�de
	 * une carte de taupes g�antes
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
							"Voulez-vous attaquer " + destinataire.getNom() + " avec votre Carte Taupes G�antes ?");
					this.wait();
					if (this.attaquer) {
						this.setChanged();
						this.notifyObservers(this.getReferenceJoueur().getNom() + " attaque " + destinataire.getNom()
								+ " avec ses taupes et lui d�truit " + tempCarte.utiliser(destinataire, saisonActuelle)
								+ " menhirs adultes sur sa carte champ.");
					}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Impl�mentation de la fa�on dont un joueur r�el va choisir si
	 * oui ou non il veut une carte alli�e en partie avanc�e
	 */
	public synchronized void choixDeManche(ParametresDePartie parametresDePartie) {
		this.setChanged();
		this.notifyObservers("Voulez-vous une carte Alliee � la place de 2 graines de Menhir ?");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
