package modele;

import java.util.Iterator;
import java.util.Scanner;

public class StrategieJoueurReelConsole extends Strategie {

	private Joueur referenceJoueur;

	public StrategieJoueurReelConsole(Joueur referenceJoueur) {
		super();
		this.referenceJoueur = referenceJoueur;
	}

	public void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie) {
		Scanner sc = new Scanner(System.in);

		String reponse = null;

		System.out.println("Voici l'état actuel des joueurs :");
		System.out.println(parametresDePartie.getListeJoueurs().toString() + "\n");

		System.out.println(this.referenceJoueur.getNom()
				+ ", voici vos cartes (col1->P col2->E col3->A col4->H lign1->Geant lign2->Engrais lign3->Farfadet) : \n");
		this.referenceJoueur.getPaquet().afficherCartes();
		System.out.println("Graines de menhirs en stock : " + this.referenceJoueur.getPaquet().getGrainesDeMenhir());

		System.out.println("Quelle carte ingrédient jouez-vous ? (tapez son id) ");
		boolean trouvee = false;
		CarteIngredient carteAJouer = new CarteIngredient(null);
		while (!trouvee) {
			reponse = sc.next();
			for (Iterator<Carte> it = this.referenceJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Ingredients")
					.iterator(); it.hasNext();) {
				Carte tempCarte = it.next();
				if (String.valueOf(tempCarte.getId()).equals(reponse) && !tempCarte.isEstUtilise()) {
					carteAJouer = (CarteIngredient) tempCarte;
					trouvee = true;
					break;
				}
			}
		}

		System.out.println("Quelle action (engrais ou geant ou farfadet) ?");
		while (!reponse.contentEquals("engrais") && !reponse.contentEquals("geant")
				&& !reponse.contentEquals("farfadet")) {
			reponse = sc.next();
			if (reponse.contentEquals("engrais")) {
				carteAJouer.utiliser(TypeAction.engrais, this.referenceJoueur, this.referenceJoueur, saisonActuelle,
						parametresDePartie);
				System.out.println("Vous avez maintenant " + this.referenceJoueur.getPaquet().getNombreMenhirsAdultes()
						+ " menhirs adultes sur votre carte champ.");

				this.referenceJoueur.score(parametresDePartie.getTypePartie());
				System.out.println(this.referenceJoueur.toString());
			}
			if (reponse.contentEquals("farfadet")) {
				System.out.println("A quel joueur voulez-vous voler des graines");
				// afficher tous les joueurs(IAS) et leurs ressources (graines +
				// menhirs)
				for (int i = 1; i < parametresDePartie.getNombreDeJoueurs(); i++) {
					Joueur tempIA = parametresDePartie.getListeJoueurs().get(i);
					System.out
							.println(tempIA.toString() + " menhirs : " + tempIA.getPaquet().getNombreMenhirsAdultes());
				}
				System.out.println("A quel joueur voulez-vous volez les graines? Entrer id");
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
				carteAJouer.utiliser(TypeAction.farfadet, destinataire, this.referenceJoueur, saisonActuelle,
						parametresDePartie);
				System.out.println("Vous avez maintenant " + this.referenceJoueur.getPaquet().getGrainesDeMenhir()
						+ " graines de menhirs.");

				destinataire.score(parametresDePartie.getTypePartie());
				this.referenceJoueur.score(parametresDePartie.getTypePartie());
				System.out.println(destinataire.toString());
				System.out.println(this.referenceJoueur.toString());

			}
			if (reponse.contentEquals("geant")) {
				carteAJouer.utiliser(TypeAction.geantGardient, this.referenceJoueur, this.referenceJoueur,
						saisonActuelle, parametresDePartie);
				System.out.println("Vous avez maintenant " + this.referenceJoueur.getPaquet().getGrainesDeMenhir()
						+ " graines de menhirs.");

				this.referenceJoueur.score(parametresDePartie.getTypePartie());
				System.out.println(this.referenceJoueur.toString());
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
				System.out.println(acteur.getNom() + " vous lance une attaque de " + puissanceModifie + " "
						+ "graines, Se défendre avec votre carte chien de garde ?" + "\n( " + tempCarte.toString()
						+ " )");
				if (sc.next().contains("oui")) {
					puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle, puissanceModifie);
					System.out.println(this.referenceJoueur.getNom() + " se défend de " + destinataire.getNom()
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
				System.out.println("Voulez-vous attaquer " + destinataire.getNom()
						+ " avec votre Carte Taupes Géantes ? (oui ou non)" + "\n( " + tempCarte.toString() + " )");
				if (sc.next().contains("oui")) {
					System.out.println(this.referenceJoueur.getNom() + " attaque " + destinataire.getNom()
							+ " avec ses taupes et lui détruit " + tempCarte.utiliser(destinataire, saisonActuelle)
							+ " menhirs adultes sur sa carte champ.");
				}
			}
		}

	}

	public void choixDeManche(ParametresDePartie parametresDePartie) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous une carte Alliee à la place de 2 graines de Menhir ? (oui ou non)");
		if (sc.next().contains("oui")) {
			this.setChoixCarteAlliee(true);
		}
	}

}
