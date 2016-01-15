package modele;

import java.util.Iterator;
import java.util.Scanner;

import launcher.Console;

/**
 * La classe Strat�gieJoueurReelGraphique �tend la classe Strategie pour d�finir
 * la strat�gie utilisable par le joueur r�el en mode textuel. Cette strat�gie
 * se traduit par une demande au joueur de l'action qu'il veut faire qui se suit
 * d'une attente que le SCANNER_PUBLIC de la console re�oit la r�ponse �crite du
 * joueur.
 * 
 * @see Strategie
 * @see Console
 */
public class StrategieJoueurReelConsole extends Strategie {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 4427005288814710556L;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param referenceJoueur
	 *            r�cup�re le joueur associ� � la strat�gie.
	 */
	public StrategieJoueurReelConsole(Joueur referenceJoueur) {
		super(referenceJoueur);
	}

	/**
	 * Impl�mentation de la fa�on dont va jouer un tour un joueur r�el pour le
	 * choix d'une carte ingr�dient et d'une action
	 */
	public void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		Scanner sc = Console.SCANNER_PUBLIC;

		String reponse = null;

		this.setChanged();
		this.notifyObservers("Voici l'�tat actuel des joueurs :");
		this.setChanged();
		this.notifyObservers(parametresDePartie.getListeJoueurs().toString() + "\n");

		this.setChanged();
		this.notifyObservers(this.getReferenceJoueur().getNom()
				+ ", voici vos cartes (col1->P col2->E col3->A col4->H lign1->Geant lign2->Engrais lign3->Farfadet) : \n");
		this.getReferenceJoueur().getPaquet().afficherCartes();
		this.setChanged();
		this.notifyObservers(
				"Graines de menhirs en stock : " + this.getReferenceJoueur().getPaquet().getGrainesDeMenhir());

		this.setChanged();
		this.notifyObservers("Quelle carte ingr�dient jouez-vous ? (tapez son id) ");
		boolean trouvee = false;
		CarteIngredient carteAJouer = new CarteIngredient(null);
		while (!trouvee) {
			reponse = sc.next();
			for (Iterator<Carte> it = this.getReferenceJoueur().getPaquet().getPaquetsDeCartes()
					.get("Cartes Ingredients").iterator(); it.hasNext();) {
				Carte tempCarte = it.next();
				if (String.valueOf(tempCarte.getId()).equals(reponse) && !tempCarte.isEstUtilise()) {
					carteAJouer = (CarteIngredient) tempCarte;
					trouvee = true;
					break;
				}
			}
		}

		this.setChanged();
		this.notifyObservers("Quelle action (engrais ou geant ou farfadet) ?");
		while (!reponse.contentEquals("engrais") && !reponse.contentEquals("geant")
				&& !reponse.contentEquals("farfadet")) {
			reponse = sc.next();
			if (reponse.contentEquals("engrais")) {
				carteAJouer.utiliser(TypeAction.engrais, this.getReferenceJoueur(), this.getReferenceJoueur(),
						saisonActuelle, parametresDePartie);
				this.setChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getCarteChamp().getMenhirAdultes()
						+ " menhirs adultes sur votre carte champ.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.setChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());
			}
			if (reponse.contentEquals("farfadet")) {
				this.setChanged();
				this.notifyObservers("A quel joueur voulez-vous voler des graines ? Entrer id");
				for (int i = 1; i < parametresDePartie.getNombreDeJoueurs(); i++) {
					Joueur tempIA = parametresDePartie.getListeJoueurs().get(i);
					this.setChanged();
					this.notifyObservers(
							tempIA.toString() + " menhirs : " + tempIA.getPaquet().getCarteChamp().getMenhirAdultes());
				}
				trouvee = false;
				Joueur destinataire = null;
				while (!trouvee) {
					reponse = sc.next();
					for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
						Joueur tempJoueurVirtuel = it.next();
						if (String.valueOf(tempJoueurVirtuel.getId()).equals(reponse)) {
							destinataire = tempJoueurVirtuel;
							trouvee = true;
							break;
						}
					}
				}

				reponse = "farfadet";
				carteAJouer.utiliser(TypeAction.farfadet, destinataire, this.getReferenceJoueur(), saisonActuelle,
						parametresDePartie);
				this.setChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getGrainesDeMenhir() + " graines de menhirs.");

				destinataire.score(parametresDePartie.getTypePartie());
				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.setChanged();
				this.notifyObservers(destinataire.toString());
				this.setChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());

			}
			if (reponse.contentEquals("geant")) {
				carteAJouer.utiliser(TypeAction.geantGardient, this.getReferenceJoueur(), this.getReferenceJoueur(),
						saisonActuelle, parametresDePartie);
				this.setChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getGrainesDeMenhir() + " graines de menhirs.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.setChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());
			}
		}
	}

	/**
	 * Impl�mentation de la fa�on dont va se d�fendre un joueur r�el s'il
	 * poss�de une carte de chiens de gardes
	 */
	public int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance) {
		Scanner sc = Console.SCANNER_PUBLIC;
		int puissanceModifie = puissance;
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(destinataire.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty())) {
			CarteChiensDeGarde tempCarte = (CarteChiensDeGarde) destinataire.getPaquet().getPaquetsDeCartes()
					.get("Cartes Chiens De Garde").get(0);
			if (!tempCarte.isEstUtilise()) {
				this.setChanged();
				this.notifyObservers(acteur.getNom() + " vous lance une attaque de " + puissanceModifie + " "
						+ "graines, Se d�fendre avec votre carte chien de garde ?" + "\n( " + tempCarte.toString()
						+ " )");
				if (sc.next().contains("oui")) {
					puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle, puissanceModifie);
					this.setChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom() + " se d�fend de " + destinataire.getNom()
							+ " avec ses chiens de garde et ne perd que " + puissanceModifie + " graines de menhir.");
				}
			}
		}
		return puissanceModifie;
	}

	/**
	 * Impl�mentation de la fa�on dont va attaquer un joueur r�el s'il poss�de
	 * une carte de taupes g�antes
	 */
	public void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle) {
		Scanner sc = Console.SCANNER_PUBLIC;
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(acteur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty())) {
			CarteTaupesGeantes tempCarte = (CarteTaupesGeantes) acteur.getPaquet().getPaquetsDeCartes()
					.get("Cartes Taupes Geantes").get(0);
			if (!tempCarte.isEstUtilise()) {
				this.setChanged();
				this.notifyObservers("Voulez-vous attaquer " + destinataire.getNom()
						+ " avec votre Carte Taupes G�antes ? (oui ou non)" + "\n( " + tempCarte.toString() + " )");
				if (sc.next().contains("oui")) {
					this.setChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom() + " attaque " + destinataire.getNom()
							+ " avec ses taupes et lui d�truit " + tempCarte.utiliser(destinataire, saisonActuelle)
							+ " menhirs adultes sur sa carte champ.");
				}
			}
		}

	}

	/**
	 * Impl�mentation de la fa�on dont un joueur r�el va choisir si oui ou non
	 * il veut une carte alli�e en partie avanc�e
	 */
	public void choixDeManche(ParametresDePartie parametresDePartie) {
		Scanner sc = Console.SCANNER_PUBLIC;
		this.setChanged();
		this.notifyObservers("Voulez-vous une carte Alliee � la place de 2 graines de Menhir ? (oui ou non)");
		if (sc.next().contains("oui")) {
			this.setChoixCarteAlliee(true);
		}
	}

}
