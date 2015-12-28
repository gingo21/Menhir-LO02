package launcher;

import java.util.Iterator;

import modèle.Joueur;
import modèle.ParametresDePartie;
import modèle.Partie;
import modèle.Saison;
import modèle.StatutPartie;

public class Console {

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
			int tempIdJoueurActuel = parametresDePartie.getOrdreDesJoueurs()[partie.getNumeroDeTourActuel()];
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

			joueurActuel.getStrategie().jouerSonTour(partie.getSaisonActuelle(),
					parametresDePartie);
			partie.wait(10);

			for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs()
					.iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.score(parametresDePartie.getTypePartie());
				if (tempJoueur != joueurActuel) {
					tempJoueur.getStrategie().attaquer(parametresDePartie,
							joueurActuel, tempJoueur, partie.getSaisonActuelle());
				}
			}

			partie.changerDeTour(parametresDePartie);
			// test fin de manche
			if (partie.getSaisonActuelle() == Saison.printemps
					&& partie.getNumeroDeTourActuel() == 0) {
				if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
					partie.changerDeManche(parametresDePartie);
				} else {
					partie.setNumeroDeManche(partie.getNumeroDeManche() + 1);
				}

			}

		} while (partie.getNumeroDeManche() < parametresDePartie
				.getNombreDeManches());
		partie.finDeJeu(parametresDePartie);

	}
}
