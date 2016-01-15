package modele;

import java.util.Iterator;
import java.util.Observable;

/**
 * La classe Partie est la classe centrale du mod�le. En effet, c'est elle qui va
 * r�unir toutes les fonctionnalit�s des classes du mod�le pour pouvoir ...
 */
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
		// On a d�j� tout param�tr�
		this.setChanged();
		this.notifyObservers("Bienvenue dans le jeu du menhir d'apres Francois Reymond" + " et Adrien Wartelle");
		if (modeConsole) {
			this.setChanged();
			this.notifyObservers("Ne r�pondez que par un mot aux questions si vous voulez que cela se passe bien ...");
		}
		// Distribution des cartes et pr�sentations
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
			joueurActuel.getStrategie().jouerSonTour(this.getSaisonActuelle(), this.parametresDePartie);
			joueurActuel.getPaquet().rafraichirLesObservers();
			this.wait(2000);

			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.score(this.parametresDePartie.getTypePartie());
				if (tempJoueur != joueurActuel) {
					tempJoueur.getStrategie().attaquer(this.parametresDePartie, joueurActuel, tempJoueur,
							this.getSaisonActuelle());
					this.parametresDePartie.rafraichirObserversDePaquet();
				}
			}

			this.changerDeTour(this.parametresDePartie);
			// test fin de manche
			if (this.getSaisonActuelle() == Saison.printemps && this.getNumeroDeTourActuel() == 0) {
				if (this.parametresDePartie.getTypePartie() == StatutPartie.avancee) {
					this.changerDeManche();
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
			this.setChanged();
			this.notifyObservers("Changement de saison : " + this.getSaisonActuelle());
		}
	}

	public void changerDeManche() {
		this.numeroDeManche++;
		this.setChanged();
		this.notifyObservers("Changement de manche : " + this.numeroDeManche);
		int resteManches = (this.parametresDePartie.getNombreDeManches() - this.numeroDeManche);
		this.setChanged();
		this.notifyObservers("Il reste " + resteManches + " manche(s) � jouer");
		if (resteManches > 0) {
			PaquetDeRessourcesDePartie tempNewPaquet = new PaquetDeRessourcesDePartie(
					this.parametresDePartie.getTypePartie(), this.parametresDePartie.getNombreDeJoueurs());
			this.parametresDePartie.setPaquetDePartie(tempNewPaquet);
			this.setChanged();
			this.notifyObservers("nouveau paquet");
			this.parametresDePartie.getPaquetDePartie().reprendreToutesLesCartes(this.parametresDePartie,
					tempNewPaquet);
			this.parametresDePartie.rafraichirObserversDePaquet();
		}
	}

	public void finDeJeu(ParametresDePartie parametresDePartie) {
		Joueur JoueurGagnant = null;
		int maxScore = 0;
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur.getScore() > maxScore) {
				JoueurGagnant = tempJoueur;
				maxScore = tempJoueur.getScore();
			}
		}
		if (JoueurGagnant instanceof JoueurReel) {
			this.setChanged();
			this.notifyObservers("Bravo, vous avez gagn�, avec :" + (int) (JoueurGagnant.getScore() / 100)
					+ " menhirs et " + JoueurGagnant.getPaquet().getGrainesDeMenhir() + " graines");
		} else {
			this.setChanged();
			this.notifyObservers("Vous avez perdu :(");
			this.setChanged();
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
