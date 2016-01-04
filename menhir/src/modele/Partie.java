package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Partie extends Observable implements Runnable {

	private Saison saisonActuelle;
	private int numeroDeTourActuel;
	private int numeroDeManche;
	private ParametresDePartie parametresDePartie;
	private boolean modeConsole;

	public Partie(ParametresDePartie parametresDePartie, boolean modeConsole) {
		super();
		this.saisonActuelle = Saison.printemps;
		this.numeroDeTourActuel = 0;
		this.numeroDeManche = 0;
		this.parametresDePartie = parametresDePartie;
		this.modeConsole = modeConsole;
	}

	public void run() {
		//TODO ON A DEJA TOUT PARAMETRE
		this.setChanged();
		this.notifyObservers("Bienvenue dans le jeu du menhir d'apres Francois Reymond" + " et Adrien Wartelle");
		if(modeConsole) {
			this.setChanged();
			this.notifyObservers("Ne répondez que par un mot aux questions si vous voulez que cela se passe bien ...");
		}
		// Distribution des cartes et présentations
		this.parametresDePartie.getPaquetDePartie().distribuerRessourcesInitiales(this.parametresDePartie);
		this.parametresDePartie.rafraichirObserversDePaquet();
		this.wait(500);

		do {
			int tempIdJoueurActuel = this.parametresDePartie.getOrdreDesJoueurs().get(this.getNumeroDeTourActuel());
			int indexJoueurActuel = 0;
			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur.getId() == tempIdJoueurActuel) {
					indexJoueurActuel = this.parametresDePartie.getListeJoueurs().indexOf(tempJoueur);
					break;
				}
			}
			Joueur joueurActuel = this.parametresDePartie.getListeJoueurs().get(indexJoueurActuel);
			this.setChanged();
			this.notifyObservers("C'est au tour de " + joueurActuel.getNom());

			joueurActuel.getPaquet().rafraichirLesObservers();
			joueurActuel.getStrategie().jouerSonTour(this.getSaisonActuelle(), this.parametresDePartie);
			this.wait(2000);

			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.score(this.parametresDePartie.getTypePartie());
				if (tempJoueur != joueurActuel) {
					tempJoueur.getStrategie().attaquer(this.parametresDePartie, joueurActuel, tempJoueur,
							this.getSaisonActuelle());
					tempJoueur.getPaquet().rafraichirLesObservers();
				}
			}

			this.changerDeTour(this.parametresDePartie);
			// test fin de manche
			if (this.getSaisonActuelle() == Saison.printemps && this.getNumeroDeTourActuel() == 0) {
				if (this.parametresDePartie.getTypePartie() == StatutPartie.avancee) {
					this.changerDeManche(this.parametresDePartie);
				} else {
					this.setNumeroDeManche(this.getNumeroDeManche() + 1);
				}

			}

		} while (this.getNumeroDeManche() < this.parametresDePartie.getNombreDeManches());
		this.finDeJeu(this.parametresDePartie);

	}

	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
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
		this.setChanged();
		this.notifyObservers("Il reste " + resteManches + " manche(s) à  jouer");
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
