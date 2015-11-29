package menhir;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class PaquetDeRessourcesDePartie extends PaquetDeRessources {

	private HashMap<String, Stack<Carte>> paquetsDeCartes;

	public PaquetDeRessourcesDePartie(menhir.StatutPartie statutPartie,
			int nombreDeJoueurs) {
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
			String tempNames[] = { "Trululu", "tarlatata", "trabidu" };
			int selectName = (int) (Math.random() * (3 - 0));
			Carte tempCarte = new CarteIngredient(
					tempNames[selectName]);
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
				Carte tempCarte = new CarteComptageDePoint(tempName);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "Taupinators", "Totors", "Taupastort" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteTaupesGeantes(
						tempNames[selectName]);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "ChiensChiens", "Chiouahahs", "BigBulls" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteChiensDeGarde(
						tempNames[selectName]);
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
	
	/*public void reprendreToutesLesCartes() {

	}*/

	/*public void reprendreTousLesMenhirs() {

	}*/

	public void ajouterUneCarte(Carte carte) {
		if (carte instanceof CarteIngredient) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes
					.get("Cartes Ingredients");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChamp) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes
					.get("Cartes Champs");
			tempCarte.add(carte);
		} else if (carte instanceof CarteComptageDePoint) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes
					.get("Cartes Comptage De Points");
			tempCarte.add(carte);
		} else if (carte instanceof CarteTaupesGeantes) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes
					.get("Cartes Taupes Geantes");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChiensDeGarde) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes
					.get("Cartes Chiens De Garde");
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
}