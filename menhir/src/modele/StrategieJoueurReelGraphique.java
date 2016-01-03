package modele;

import java.util.Iterator;
import java.util.Scanner;

public class StrategieJoueurReelGraphique extends Strategie {

	private Carte carteAJouer;
	private TypeAction actionAJouer;
	private Joueur destinataireAAttaquer;
	private boolean seDefendre = false;
	private boolean attaquer = false;

	public StrategieJoueurReelGraphique(Joueur referenceJoueur) {
		super(referenceJoueur);
	}

	public Carte getCarteAJouer() {
		return carteAJouer;
	}

	public void setCarteAJouer(Carte carteAJouer) {
		this.carteAJouer = carteAJouer;
	}

	public TypeAction getActionAJouer() {
		return actionAJouer;
	}

	public void setActionAJouer(TypeAction actionAJouer) {
		this.actionAJouer = actionAJouer;
	}

	public Joueur getDestinataireAAttaquer() {
		return destinataireAAttaquer;
	}

	public void setDestinataireAAttaquer(Joueur destinataireAAttaquer) {
		this.destinataireAAttaquer = destinataireAAttaquer;
	}

	public boolean isSeDefendre() {
		return seDefendre;
	}

	public void setSeDefendre(boolean seDefendre) {
		this.seDefendre = seDefendre;
	}

	public boolean isAttaquer() {
		return attaquer;
	}

	public void setAttaquer(boolean attaquer) {
		this.attaquer = attaquer;
	}

	public synchronized void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		try {
			this.setChanged();
			this.notifyObservers("Quelle carte ingrédient jouez-vous ? (Cliquez dessus) ");
			this.wait();
			// On recoit la carte
			this.setChanged();
			this.notifyObservers("Quelle action ?");
			this.wait();
			// On recoit l'action
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
				// On recoit le destinataire

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

	public int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
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
					// On recoit la reponse
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

	public void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
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
					//On recoit la reponse
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

	public synchronized void choixDeManche(ParametresDePartie parametresDePartie) {
		this.setChanged();
		this.notifyObservers("Voulez-vous une carte Alliee à la place de 2 graines de Menhir ?");
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//On recoit la reponse
	}

}
