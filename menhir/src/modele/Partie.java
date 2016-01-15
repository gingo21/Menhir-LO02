package modele;

import java.util.Iterator;
import java.util.Observable;

/**
 * La classe Partie est la classe centrale du modèle. En effet, c'est elle qui
 * va réunir toutes les fonctionnalités des classes du modèle pour pouvoir
 * effectuer une partie du jeu de Menhir
 */
public class Partie extends Observable implements Runnable {

	/**
	 * Saison actuelle de notre partie
	 */
	private Saison saisonActuelle;

	/**
	 * Numéro de tour
	 */
	private int numeroDeTourActuel;

	/**
	 * Numéro de manche
	 */
	private int numeroDeManche;

	/**
	 * Paramètres de la partie
	 */
	private ParametresDePartie parametresDePartie;

	/**
	 * Partie lancé en mode Console ou non
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
			this.notifyObservers("Ne répondez que par un mot aux questions si vous voulez que cela se passe bien ...");
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
	 * Méthode de changement de tour On incrémente le numéro de tour, et on
	 * vérifie s'il y a changement de saison
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
	 * Méthode de changement de manche On incrémente le numéro de manche, et on
	 * rédémarre une manche si la partie n'est pas fini
	 */
	public void changerDeManche() {
		this.numeroDeManche++;
		this.setChanged();
		this.notifyObservers("Changement de manche : " + this.numeroDeManche);
		int resteManches = (this.parametresDePartie.getNombreDeManches() - this.numeroDeManche);
		this.setChanged();
		this.notifyObservers("Il reste " + resteManches + " manche(s) à  jouer");
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
	 * Méthode de fin du jeu On regarde qui a gagné la partie
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
			this.notifyObservers("Bravo, vous avez gagné, avec :" + (int) (JoueurGagnant.getScore() / 100)
					+ " menhirs et " + JoueurGagnant.getPaquet().getGrainesDeMenhir() + " graines");
		} else {
			this.setChanged();
			this.notifyObservers("Vous avez perdu :(");
			this.setChanged();
			this.notifyObservers("Gagnant : " + JoueurGagnant.toString());
		}
	}

	/**
	 * Méthode qui permet d'attendre le temps donné en paramètre
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
	 * Mise à jour du numéro de manche
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
	 * Mise à jour du numéro de tour actuel
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
	 * Mise à jour de la saison actuelle
	 * 
	 * @param saisonActuelle
	 */
	public void setSaisonActuelle(Saison saisonActuelle) {
		this.saisonActuelle = saisonActuelle;
	}

	/**
	 * @return Paramètres de la partie
	 */
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	/**
	 * Mise à jour des paramètres de la partie
	 * 
	 * @param parametresDePartie
	 */
	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}

}
