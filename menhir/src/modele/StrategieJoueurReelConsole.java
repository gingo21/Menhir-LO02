package modele;

import java.util.Iterator;
import java.util.Scanner;

public class StrategieJoueurReelConsole extends Strategie {

	public StrategieJoueurReelConsole(Joueur referenceJoueur) {
		super(referenceJoueur);
		// TODO Auto-generated constructor stub
	}

	public void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		Scanner sc = new Scanner(System.in);

		String reponse = null;

		this.hasChanged();
		this.notifyObservers("Voici l'état actuel des joueurs :");
		this.hasChanged();
		this.notifyObservers(parametresDePartie.getListeJoueurs().toString() + "\n");

		this.hasChanged();
		this.notifyObservers(this.getReferenceJoueur().getNom()
				+ ", voici vos cartes (col1->P col2->E col3->A col4->H lign1->Geant lign2->Engrais lign3->Farfadet) : \n");
		this.getReferenceJoueur().getPaquet().afficherCartes();
		this.hasChanged();
		this.notifyObservers(
				"Graines de menhirs en stock : " + this.getReferenceJoueur().getPaquet().getGrainesDeMenhir());

		this.hasChanged();
		this.notifyObservers("Quelle carte ingrédient jouez-vous ? (tapez son id) ");
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

		this.hasChanged();
		this.notifyObservers("Quelle action (engrais ou geant ou farfadet) ?");
		while (!reponse.contentEquals("engrais") && !reponse.contentEquals("geant")
				&& !reponse.contentEquals("farfadet")) {
			reponse = sc.next();
			if (reponse.contentEquals("engrais")) {
				carteAJouer.utiliser(TypeAction.engrais, this.getReferenceJoueur(), this.getReferenceJoueur(),
						saisonActuelle, parametresDePartie);
				this.hasChanged();
				this.notifyObservers(
						"Vous avez maintenant " + this.getReferenceJoueur().getPaquet().getNombreMenhirsAdultes()
								+ " menhirs adultes sur votre carte champ.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.hasChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());
			}
			if (reponse.contentEquals("farfadet")) {
				this.hasChanged();
				this.notifyObservers("A quel joueur voulez-vous voler des graines");
				// afficher tous les joueurs(IAS) et leurs ressources (graines +
				// menhirs)
				for (int i = 1; i < parametresDePartie.getNombreDeJoueurs(); i++) {
					Joueur tempIA = parametresDePartie.getListeJoueurs().get(i);
					this.hasChanged();
					this.notifyObservers(
							tempIA.toString() + " menhirs : " + tempIA.getPaquet().getNombreMenhirsAdultes());
				}
				this.hasChanged();
				this.notifyObservers("A quel joueur voulez-vous volez les graines? Entrer id");
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
				this.hasChanged();
				this.notifyObservers("Vous avez maintenant "
						+ this.getReferenceJoueur().getPaquet().getGrainesDeMenhir() + " graines de menhirs.");

				destinataire.score(parametresDePartie.getTypePartie());
				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.hasChanged();
				this.notifyObservers(destinataire.toString());
				this.hasChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());

			}
			if (reponse.contentEquals("geant")) {
				carteAJouer.utiliser(TypeAction.geantGardient, this.getReferenceJoueur(), this.getReferenceJoueur(),
						saisonActuelle, parametresDePartie);
				this.hasChanged();
				this.notifyObservers("Vous avez maintenant " + this.getReferenceJoueur().getPaquet().getGrainesDeMenhir()
						+ " graines de menhirs.");

				this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
				this.hasChanged();
				this.notifyObservers(this.getReferenceJoueur().toString());
			}
		}
	}

	public int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance) {
		Scanner sc = new Scanner(System.in);
		int puissanceModifie = puissance;
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(destinataire.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty())) {
			CarteChiensDeGarde tempCarte = (CarteChiensDeGarde) destinataire.getPaquet().getPaquetsDeCartes()
					.get("Cartes Chiens De Garde").get(0);
			if (!tempCarte.isEstUtilise()) {
				this.hasChanged();
				this.notifyObservers(acteur.getNom() + " vous lance une attaque de " + puissanceModifie + " "
						+ "graines, Se défendre avec votre carte chien de garde ?" + "\n( " + tempCarte.toString()
						+ " )");
				if (sc.next().contains("oui")) {
					puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle, puissanceModifie);
					this.hasChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom() + " se défend de " + destinataire.getNom()
							+ " avec ses chiens de garde et ne perd que " + puissanceModifie + " graines de menhir.");
				}
			}
		}
		return puissanceModifie;
	}

	public void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle) {
		Scanner sc = new Scanner(System.in);
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(acteur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty())) {
			CarteTaupesGeantes tempCarte = (CarteTaupesGeantes) acteur.getPaquet().getPaquetsDeCartes()
					.get("Cartes Taupes Geantes").get(0);
			if (!tempCarte.isEstUtilise()) {
				this.hasChanged();
				this.notifyObservers("Voulez-vous attaquer " + destinataire.getNom()
						+ " avec votre Carte Taupes Géantes ? (oui ou non)" + "\n( " + tempCarte.toString() + " )");
				if (sc.next().contains("oui")) {
					this.hasChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom() + " attaque " + destinataire.getNom()
							+ " avec ses taupes et lui détruit " + tempCarte.utiliser(destinataire, saisonActuelle)
							+ " menhirs adultes sur sa carte champ.");
				}
			}
		}

	}

	public void choixDeManche(ParametresDePartie parametresDePartie) {
		Scanner sc = new Scanner(System.in);
		System.out.println(this.countObservers());
		this.hasChanged();
		this.notifyObservers();
		this.notifyObservers("Voulez-vous une carte Alliee à la place de 2 graines de Menhir ? (oui ou non)");
		if (sc.next().contains("oui")) {
			this.setChoixCarteAlliee(true);
		}
	}

}
