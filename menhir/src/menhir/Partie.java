package menhir;

import java.util.Iterator;

public class Partie {

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
				this.saisonActuelle = this.saisonActuelle.next();
			}
			this.numeroDeTourActuel = 0;
			System.out.println("Changement de saison : " + this.saisonActuelle);
		}
	}

	public void changerDeManche(ParametresDePartie parametresDePartie) {
		this.numeroDeManche++;
		System.out.println("Changement de manche : " + this.numeroDeManche);
		int resteManches = (parametresDePartie.getNombreDeManches() - this.numeroDeManche);
		System.out.println("Il reste " + resteManches + " manche(s) à jouer");
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
		if (JoueurGagnant instanceof JoueurReel)
			System.out.println("Bravo, vous avez gagné, avec :"
					+ JoueurGagnant.getPaquet().getNombreMenhirsAdultes()
					+ "menhirs et "
					+ JoueurGagnant.getPaquet().getGrainesDeMenhir()
					+ "graines");
		else {
			System.out.println("Vous avez perdu :(");
			System.out.println("Gagnant : " + JoueurGagnant.toString());
		}
	}

	public void wait(int millis) {
		double tempTemp = System.currentTimeMillis() + millis;
		while (tempTemp > System.currentTimeMillis()) {
		}
		System.out.println(" ");
	}

	public static void main(String[] args) {

		// 1ere version
		System.out
				.println("Bienvenue dans la version Alpha du jeu du menhir d'apres Francois Reymond"
						+ " et Adrien Wartelle");
		System.out
				.println("Ne répondez que par un mot aux questions si vous voulez que cela se passe bien ...");

		ParametresDePartie parametresDePartie = new ParametresDePartie();
		Partie partie = new Partie();

		// Distribution des cartes et présentations
		parametresDePartie.getPaquetDePartie().distribuerRessourcesInitiales(
				parametresDePartie);
		partie.wait(1);

		do {
			int tempIdJoueurActuel = parametresDePartie.getOrdreDesJoueurs()[partie.numeroDeTourActuel];
			int indexJoueurActuel = 0;
			for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
					.iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur.getId() == tempIdJoueurActuel) {
					indexJoueurActuel = parametresDePartie.getListeJoueurs()
							.indexOf(tempJoueur);
					break;
				}
			}
			Joueur joueurActuel = parametresDePartie.getListeJoueurs().get(
					indexJoueurActuel);
			System.out.println("C'est au tour de " + joueurActuel.getNom());

			joueurActuel.getStrategie().jouerSonTour(partie.saisonActuelle,
					parametresDePartie);
			partie.wait(10);

			for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
					.iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.score(parametresDePartie.getTypePartie());
				if (tempJoueur != joueurActuel) {
					tempJoueur.getStrategie().attaquer(parametresDePartie,
							joueurActuel, tempJoueur, partie.saisonActuelle);
				}
			}

			partie.changerDeTour(parametresDePartie);
			// test fin de manche
			if (partie.saisonActuelle == Saison.printemps
					&& partie.numeroDeTourActuel == 0) {
				if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
					partie.changerDeManche(parametresDePartie);
				} else {
					partie.numeroDeManche++;
				}

			}

		} while (partie.numeroDeManche < parametresDePartie
				.getNombreDeManches());
		partie.finDeJeu(parametresDePartie);

	}
}
