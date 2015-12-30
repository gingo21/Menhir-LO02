package modele;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import launcher.Console;

public class PaquetDeRessourcesDePartie extends PaquetDeRessources {

	private HashMap<String, Stack<Carte>> paquetsDeCartes;

	public PaquetDeRessourcesDePartie(modele.StatutPartie statutPartie, int nombreDeJoueurs) {
		super(30 * nombreDeJoueurs);
		paquetsDeCartes = new HashMap<String, Stack<Carte>>();
		Stack<Carte> tempCartes1 = new Stack<Carte>();
		Stack<Carte> tempCartes2 = new Stack<Carte>();
		Stack<Carte> tempCartes3 = new Stack<Carte>();
		Stack<Carte> tempCartes4 = new Stack<Carte>();
		Stack<Carte> tempCartes5 = new Stack<Carte>();
		this.paquetsDeCartes.put("Cartes Ingredients", tempCartes1);
		this.paquetsDeCartes.put("Cartes Champs", tempCartes2);
		this.paquetsDeCartes.put("Cartes Comptage De Points", tempCartes3);
		this.paquetsDeCartes.put("Cartes Taupes Geantes", tempCartes4);
		this.paquetsDeCartes.put("Cartes Chiens De Garde", tempCartes5);

		for (int i = 0; i < 4 * nombreDeJoueurs; i++) {
			String tempNames[] = { "Chant de Sirène", "Esprit de Dolmen", "Fontaine Eau Pure", "Larmes De Dryade",
					"Poudre Or", "Racines Arc En Ciel", "Rayon De Lune", "Rires de Fees" };
			int selectName = (int) (Math.random() * (8 - 0));
			Carte tempCarte = new CarteIngredient(tempNames[selectName]);
			this.ajouterUneCarte(tempCarte);
		}
		for (int i = 0; i < nombreDeJoueurs; i++) {
			String tempName = "champ" + i;
			Carte tempCarte = new CarteChamp(tempName);
			this.ajouterUneCarte(tempCarte);
		}
		if (statutPartie == StatutPartie.avancee) {
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempName = "points" + i;
				Carte tempCarte = new CarteComptageDePoints(tempName);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "Taupinators", "Totors", "Taupastort" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteTaupesGeantes(tempNames[selectName]);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "ChiensChiens", "Chiouahahs", "BigBulls" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteChiensDeGarde(tempNames[selectName]);
				this.ajouterUneCarte(tempCarte);
			}
		}
	}

	public void donnerUneCarteAuJoueur(Joueur joueur, String cleDeTypeDeCarte) {
		Carte tempCarte = this.paquetsDeCartes.get(cleDeTypeDeCarte).pop();
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempCarte.setEstUtilise(false);
		tempPaquetJoueur.ajouterUneCarte(tempCarte);
	}

	public void ajouterUneCarte(Carte carte) {
		if (carte instanceof CarteIngredient) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Ingredients");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChamp) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Champs");
			tempCarte.add(carte);
		} else if (carte instanceof CarteComptageDePoints) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Comptage De Points");
			tempCarte.add(carte);
		} else if (carte instanceof CarteTaupesGeantes) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Taupes Geantes");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChiensDeGarde) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Chiens De Garde");
			tempCarte.add(carte);
		}
	}

	public void afficherCartes() {
		Set<String> tempCles = this.paquetsDeCartes.keySet();
		for (Iterator<String> it = tempCles.iterator(); it.hasNext();) {
			String cle = it.next();
			System.out.println(cle);
			Stack<Carte> tempCartes = this.paquetsDeCartes.get(cle);
			for (Iterator<Carte> yt = tempCartes.iterator(); yt.hasNext();) {
				System.out.println(yt.next().toString());
			}
		}
	}

	public void distribuerRessourcesInitiales(ParametresDePartie parametresDePartie) {
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			// on supprime la carte alliée
			tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").clear();
			tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").clear();

			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			if (tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
				this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Champs");
			} else if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
				CarteChamp tempCarteChamp = (CarteChamp) tempJoueur.getPaquet().getPaquetsDeCartes()
						.get("Cartes Champs").get(0);
				CarteComptageDePoints tempCarteComptage = (CarteComptageDePoints) tempJoueur.getPaquet()
						.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0);

				tempCarteComptage.rajouterGraines(tempCarteChamp.getMenhirAdultes());
				tempCarteChamp.setMenhirAdultes(0);
			}

			if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
				if (tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Comptage De Points").isEmpty()) {
					this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Comptage De Points");
				}
				tempJoueur.getStrategie().choixDeManche(parametresDePartie);
				if (tempJoueur.getStrategie().isChoixCarteAlliee()) {
					int tempAlea = (int) (Math.random() * 2);
					if (tempAlea == 1) {
						this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Taupes Geantes");
					} else {
						this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Chiens De Garde");
					}
					tempJoueur.getStrategie().setChoixCarteAlliee(false);
				} else {
					this.donnerUneGraineDeMenhir(tempJoueur);
					this.donnerUneGraineDeMenhir(tempJoueur);
				}
			} else {
				this.donnerUneGraineDeMenhir(tempJoueur);
				this.donnerUneGraineDeMenhir(tempJoueur);
			}

			this.setChanged();
			this.notifyObservers("Le joueur " + tempJoueur + " a recu ses ressources");
			this.setChanged();
			this.notifyObservers("distribution ressources id" + tempJoueur.getId());
		}

	}

	public void reprendreToutesLesCartes(ParametresDePartie param) {
		param.setPaquetDePartie(new PaquetDeRessourcesDePartie(param.getTypePartie(), param.getNombreDeJoueurs()));
		param.getPaquetDePartie().distribuerRessourcesInitiales(param);
	}

	public void addConsoleObserver(Console observer) {
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Ingredients").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Champs").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Comptage De Points").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Taupes Geantes").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		this.addObserver(observer);
	}
}