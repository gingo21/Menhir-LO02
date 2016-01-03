package modele;

import java.util.ArrayList;
import java.util.Iterator;
// La strat�gie est compl�tement al�atoire 
public class StrategieFacile extends Strategie {
		
	public StrategieFacile(Joueur referenceJoueur) {
		super(referenceJoueur);
	}

	public void jouerSonTour(Saison saisonActuelle,
			ParametresDePartie parametresDePartie) {
		// faire une selection aleatoire de carte et d'action pour les IA
		CarteIngredient carteIA = new CarteIngredient(null);
		ArrayList<Carte> paquetCartesNonUtilises = new ArrayList<Carte>();
		// cartes ingredients
		for (Iterator<Carte> it = this.getReferenceJoueur().getPaquet().getPaquetsDeCartes()
				.get("Cartes Ingredients").iterator(); it.hasNext();) {
			Carte tempCarte = it.next();
			if (!tempCarte.isEstUtilise())
				paquetCartesNonUtilises.add(tempCarte);
		}

		// selection al�atoire de carte
		int indexCarte = (int) (Math.random() * paquetCartesNonUtilises.size());
		carteIA = (CarteIngredient) paquetCartesNonUtilises.get(indexCarte);
		// s�lection al�atoire action
		int indexAction = (int) (Math.random() * 3);
		// 0=engrais 1=farfadet 2=geant
		if (indexAction == 0) {
			carteIA.utiliser(TypeAction.engrais, this.getReferenceJoueur(), this.getReferenceJoueur(), saisonActuelle,
					parametresDePartie);
			this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
			this.hasChanged();
			this.notifyObservers(this.getReferenceJoueur().toString());
		}
		if (indexAction == 1)
		{
			// generer aleatoirement destinataire
			Joueur destinataire = null;
			ArrayList<Joueur> tempJoueurDest = new ArrayList<Joueur>();
			for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
					.iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur != this.getReferenceJoueur())
					tempJoueurDest.add(tempJoueur);
			}
			int indexJoueurDest = (int) (Math.random() * tempJoueurDest.size());
			destinataire = tempJoueurDest.get(indexJoueurDest);
			carteIA.utiliser(TypeAction.farfadet, destinataire, this.getReferenceJoueur(),
					saisonActuelle, parametresDePartie);
			
			destinataire.score(parametresDePartie.getTypePartie());
			this.hasChanged();
			this.notifyObservers(destinataire.toString());
			
			this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
			this.hasChanged();
			this.notifyObservers(this.getReferenceJoueur().toString());
		}
		if (indexAction == 2) {
			carteIA.utiliser(TypeAction.geantGardient, this.getReferenceJoueur(), this.getReferenceJoueur(),
					saisonActuelle, parametresDePartie);
			
			this.getReferenceJoueur().score(parametresDePartie.getTypePartie());
			this.hasChanged();
			this.notifyObservers(this.getReferenceJoueur().toString());

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
					this.hasChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom()
							+ " se d�fend de "
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
					this.hasChanged();
					this.notifyObservers(this.getReferenceJoueur().getNom() + " attaque "
							+ destinataire.getNom()
							+ " avec ses taupes et lui d�truit "
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
