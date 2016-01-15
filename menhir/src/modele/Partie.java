package modele;

import java.util.Iterator;
import java.util.Observable;

/**
 * La classe Partie est la classe centrale du mod�le. En effet, c'est elle qui
 * va r�unir toutes les fonctionnalit�s des classes du mod�le pour pouvoir
 * effectuer une partie du jeu de Menhir
 */
public class Partie extends Observable implements Runnable {

	/**
	 * Saison actuelle de notre partie
	 */
	private Saison saisonActuelle;

	/**
	 * Num�ro de tour
	 */
	private int numeroDeTourActuel;

	/**
	 * Num�ro de manche
	 */
	private int numeroDeManche;

	/**
	 * Param�tres de la partie
	 */
	private ParametresDePartie parametresDePartie;

	/**
	 * Partie lanc� en mode Console ou non
	 */
	private boolean modeConsole;

	/**
	 * Constructeur de la classe
	 * 
	 * @param parametresDePartie
	 * @param modeConsole
	 */
	public Partie(ParametresDePartie parametresDePartie, boolean modeConsole) {
		super();
		this.saisonActuelle = Saison.printemps;
		this.numeroDeTourActuel = 0;
		this.numeroDeManche = 0;
		this.parametresDePartie = parametresDePartie;
		this.modeConsole = modeConsole;
	}

	/**
	 * Lancement de la partie dans un thread
	 */
	public void run() {
		this.setChanged();
		this.notifyObservers("Bienvenue dans le jeu du menhir d'apres Francois Reymond" + " et Adrien Wartelle");
		if (modeConsole) {
			this.setChanged();
			this.notifyObservers("Ne r�pondez que par un mot aux questions si vous voulez que cela se passe bien ...");
		}
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

	/**
	 * M�thode de changement de tour On incr�mente le num�ro de tour, et on
	 * v�rifie s'il y a changement de saison
	 */
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

	/**
	 * M�thode de changement de manche On incr�mente le num�ro de manche, et on
	 * r�d�marre une manche si la partie n'est pas fini
	 */
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

	/**
	 * M�thode de fin du jeu On regarde qui a gagn� la partie
	 */
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

	/**
	 * M�thode qui permet d'attendre le temps donn� en param�tre
	 * 
	 * @param millis
	 */
	public void wait(int millis) {
		double tempTemp = System.currentTimeMillis() + millis;
		while (tempTemp > System.currentTimeMillis()) {
		}
	}

	/**
	 * @return numeroDeManche
	 */
	public int getNumeroDeManche() {
		return numeroDeManche;
	}

	/**
	 * Mise � jour du num�ro de manche
	 * 
	 * @param numeroDeManche
	 */
	public void setNumeroDeManche(int numeroDeManche) {
		this.numeroDeManche = numeroDeManche;
	}

	/**
	 * @return numeroDeTourActuel
	 */
	public int getNumeroDeTourActuel() {
		return numeroDeTourActuel;
	}

	/**
	 * Mise � jour du num�ro de tour actuel
	 * 
	 * @param numeroDeTourActuel
	 */
	public void setNumeroDeTourActuel(int numeroDeTourActuel) {
		this.numeroDeTourActuel = numeroDeTourActuel;
	}

	/**
	 * @return saisonActuelle
	 */
	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}

	/**
	 * Mise � jour de la saison actuelle
	 * 
	 * @param saisonActuelle
	 */
	public void setSaisonActuelle(Saison saisonActuelle) {
		this.saisonActuelle = saisonActuelle;
	}

	/**
	 * @return Param�tres de la partie
	 */
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	/**
	 * Mise � jour des param�tres de la partie
	 * 
	 * @param parametresDePartie
	 */
	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}

}
