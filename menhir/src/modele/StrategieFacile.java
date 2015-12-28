package modele;

import java.util.ArrayList;
import java.util.Iterator;
// La stratégie est complétement aléatoire 
public class StrategieFacile extends Strategie {
	
	private Joueur referenceJoueur;
	
	public StrategieFacile(Joueur referenceJoueur) {
		super();
		this.referenceJoueur = referenceJoueur;
	}
	
	public void jouerSonTour(Saison saisonActuelle,
			ParametresDePartie parametresDePartie) {
		// faire une selection aleatoire de carte et d'action pour les IA
		CarteIngredient carteIA = new CarteIngredient(null);
		ArrayList<Carte> paquetCartesNonUtilises = new ArrayList<Carte>();
		// cartes ingredients
		for (Iterator<Carte> it = this.referenceJoueur.getPaquet().getPaquetsDeCartes()
				.get("Cartes Ingredients").iterator(); it.hasNext();) {
			Carte tempCarte = it.next();
			if (!tempCarte.isEstUtilise())
				paquetCartesNonUtilises.add(tempCarte);
		}

		// selection aléatoire de carte
		int indexCarte = (int) (Math.random() * paquetCartesNonUtilises.size());
		carteIA = (CarteIngredient) paquetCartesNonUtilises.get(indexCarte);
		// sélection aléatoire action
		int indexAction = (int) (Math.random() * 3);
		// 0=engrais 1=farfadet 2=geant
		if (indexAction == 0) {
			carteIA.utiliser(TypeAction.engrais, this.referenceJoueur, this.referenceJoueur, saisonActuelle,
					parametresDePartie);
			this.referenceJoueur.score(parametresDePartie.getTypePartie());
			System.out.println(this.referenceJoueur.toString());
		}
		if (indexAction == 1)
		{
			// generer aleatoirement destinataire
			Joueur destinataire = null;
			ArrayList<Joueur> tempJoueurDest = new ArrayList<Joueur>();
			for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
					.iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur != this.referenceJoueur)
					tempJoueurDest.add(tempJoueur);
			}
			int indexJoueurDest = (int) (Math.random() * tempJoueurDest.size());
			destinataire = tempJoueurDest.get(indexJoueurDest);
			carteIA.utiliser(TypeAction.farfadet, destinataire, this.referenceJoueur,
					saisonActuelle, parametresDePartie);
			
			destinataire.score(parametresDePartie.getTypePartie());
			System.out.println(destinataire.toString());
			
			this.referenceJoueur.score(parametresDePartie.getTypePartie());
			System.out.println(this.referenceJoueur.toString());
		}
		if (indexAction == 2) {
			carteIA.utiliser(TypeAction.geantGardient, this.referenceJoueur, this.referenceJoueur,
					saisonActuelle, parametresDePartie);
			
			this.referenceJoueur.score(parametresDePartie.getTypePartie());
			System.out.println(this.referenceJoueur.toString());

		}
	}

	public int seDefendre(ParametresDePartie parametresDePartie,
			Joueur destinataire, Joueur acteur, Saison saisonActuelle,
			int puissance) {
		int puissanceModifie = puissance;
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(destinataire.getPaquet().getPaquetsDeCartes()
						.get("Cartes Chiens De Garde").isEmpty())) {
			CarteChiensDeGarde tempCarte = (CarteChiensDeGarde) destinataire
					.getPaquet().getPaquetsDeCartes()
					.get("Cartes Chiens De Garde").get(0);
			if (!tempCarte.isEstUtilise()) {
				int tempAlea = (int) Math.random() * 4;
				if (tempAlea == 0) {
					puissanceModifie = tempCarte.utiliser(destinataire, saisonActuelle,
							puissanceModifie);
					System.out.println(this.referenceJoueur.getNom()
							+ " se défend de "
							+ destinataire.getNom()
							+ " avec ses chiens de garde et ne perd que "
							+ puissanceModifie
							+ " graines de menhir.");
				}
			}
		}
		return puissanceModifie;
	}

	public void attaquer(ParametresDePartie parametresDePartie,
			Joueur destinataire, Joueur acteur, Saison saisonActuelle) {
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee
				&& !(acteur.getPaquet().getPaquetsDeCartes()
						.get("Cartes Taupes Geantes").isEmpty())) {
			CarteTaupesGeantes tempCarte = (CarteTaupesGeantes) acteur
					.getPaquet().getPaquetsDeCartes()
					.get("Cartes Taupes Geantes").get(0);
			if (!tempCarte.isEstUtilise()) {
				int tempAlea = (int) Math.random() * 4;
				if (tempAlea == 0) {
					tempCarte.utiliser(destinataire, saisonActuelle);
					System.out.println(this.referenceJoueur.getNom() + " attaque "
							+ destinataire.getNom()
							+ " avec ses taupes et lui détruit "
							+ tempCarte.utiliser(destinataire, saisonActuelle)
							+ " menhirs adultes sur sa carte champ.");
				}
			}
		}
	}
	
	public void choixDeManche(ParametresDePartie parametresDePartie) {
		int tempAlea = (int) (Math.random() * 2);
		if (tempAlea == 1) {
			this.setChoixCarteAlliee(true);
		}
	}
}
