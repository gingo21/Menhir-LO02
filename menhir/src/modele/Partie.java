package modele;

import java.util.Iterator;
import java.util.Observable;

public class Partie extends Observable {

	private Saison saisonActuelle;
	private int numeroDeTourActuel;
	private int numeroDeManche;

	public Partie() {
		this.saisonActuelle = Saison.printemps;
		this.numeroDeTourActuel = 0;
		this.numeroDeManche = 0;
	}

	public void changerDeTour(ParametresDePartie parametresDePartie) {
		this.numeroDeTourActuel++;
		if ((this.numeroDeTourActuel % parametresDePartie.getNombreDeJoueurs()) == 0) {
			if (this.saisonActuelle == Saison.hiver) {
				this.saisonActuelle = Saison.printemps;
			} else {
				this.setSaisonActuelle(this.getSaisonActuelle().next());
			}
			this.setNumeroDeTourActuel(0);
			this.hasChanged();
			this.notifyObservers("Changement de saison : " + this.getSaisonActuelle());
		}
	}

	public void changerDeManche(ParametresDePartie parametresDePartie) {
		this.numeroDeManche++;
		this.hasChanged();
		this.notifyObservers("Changement de manche : " + this.numeroDeManche);
		int resteManches = (parametresDePartie.getNombreDeManches() - this.numeroDeManche);
		System.out.println("Il reste " + resteManches + " manche(s) Ã  jouer");
		if (resteManches > 0) {
			parametresDePartie.getPaquetDePartie().reprendreToutesLesCartes(
					parametresDePartie);
		}
	}

	public void finDeJeu(ParametresDePartie parametresDePartie) {
		Joueur JoueurGagnant = null;
		int maxScore = 0;
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
				.iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur.getScore() > maxScore) {
				JoueurGagnant = tempJoueur;
				maxScore = tempJoueur.getScore();
			}
		}
		if (JoueurGagnant instanceof JoueurReel) {
			this.hasChanged();
			this.notifyObservers("Bravo, vous avez gagné, avec :"
					+ JoueurGagnant.getPaquet().getCarteChamp().getMenhirAdultes()
					+ "menhirs et "
					+ JoueurGagnant.getPaquet().getGrainesDeMenhir()
					+ "graines");
		} else {
			this.hasChanged();
			this.notifyObservers("Vous avez perdu :(");
			this.hasChanged();
			this.notifyObservers("Gagnant : " + JoueurGagnant.toString());
		}
	}

	public void wait(int millis) {
		double tempTemp = System.currentTimeMillis() + millis;
		while (tempTemp > System.currentTimeMillis()) {
		}
		System.out.println(" ");
	}
	
	public int getNumeroDeManche() {
		return numeroDeManche;
	}

	public void setNumeroDeManche(int numeroDeManche) {
		this.numeroDeManche = numeroDeManche;
	}

	public int getNumeroDeTourActuel() {
		return numeroDeTourActuel;
	}

	public void setNumeroDeTourActuel(int numeroDeTourActuel) {
		this.numeroDeTourActuel = numeroDeTourActuel;
	}

	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}

	public void setSaisonActuelle(Saison saisonActuelle) {
		this.saisonActuelle = saisonActuelle;
	}
	
}
