package menhir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Partie {

	/**
	 * @param args
	 * @throws IOException
	 */
	private Saison saisonActuelle;
	private int nombreDeTour;
	private int numeroDeTourActuel;
	private int numeroDeManche;

	public Partie(int nombreDeTour) {
		this.nombreDeTour = nombreDeTour;
		this.saisonActuelle = Saison.printemps;
		this.numeroDeTourActuel = 0;
		this.numeroDeManche = 0;
	}

	public void changerDeTour(ParametreDePartie parametreDePartie) {
		this.numeroDeTourActuel++;
		if ((this.numeroDeTourActuel % parametreDePartie.getNombreDeJoueurs()) == 0) { // on
																						// change
																						// de
																						// saison
																						// tous
																						// les
																						// njoueurs
																						// tours
			if (this.saisonActuelle == Saison.hiver) { // condtion ï¿½ coder plus
														// proprement dans
														// l'enum
				this.saisonActuelle = Saison.printemps;
			} else {
				this.saisonActuelle = this.saisonActuelle.next();
			}
			this.numeroDeTourActuel = 0;
			System.out.println("Changement de saison : " + this.saisonActuelle);
		}
	}
	public void changerDeManche(ParametreDePartie parametreDePartie){ //TODO cas ou le joueur a encore une carte alliée => lui enlever
		System.out.println("Changement de manche : " + this.numeroDeManche);
		//on recupere les cartes des joueurs => on cree un nouveau paquet de ressources (plus simple)
		parametreDePartie.setPaquetDePartie(new PaquetDeRessourcesDePartie(parametreDePartie.getTypePartie(), parametreDePartie.getNombreDeJoueurs()));
		
		//on redistribue
		parametreDePartie.getPaquetDePartie().distribuerRessourcesInitiales(
				parametreDePartie.getListeJoueurs(), parametreDePartie.getTypePartie());
		// comptage des points
		
	}

	public void finDeJeu(ParametreDePartie parametreDePartie) {
		Joueur JoueurGagnant = null;
		int maxScore = 0;
		for (Iterator<Joueur> it = parametreDePartie.getListeJoueurs()
				.iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur.getScore() > maxScore) {
				JoueurGagnant = tempJoueur;
				maxScore = tempJoueur.getScore();
			}
		}
		if (JoueurGagnant instanceof JoueurReel)
			System.out.println("Bravo, vous avez gagnï¿½, avec :"
					+ JoueurGagnant.getPaquet().getNombreMenhirsAdultes()
					+ "menhirs et "
					+ JoueurGagnant.getPaquet().getGrainesDeMenhir()
					+ "graines");
		else {
			System.out.println("Vous avez perdu :(");
			System.out.println("Gagnant : " + JoueurGagnant.toString()); 
		}
	}

	/*
	 * TODO GERER LE TEMPS TODO PARTIE AVANCEE TODO AMELIORER LAFFICHAGE
	 */

	public static void main(String[] args) {

		// 1ere version
		System.out
				.println("Bienvenue dans la version Alpha du jeu du menhir d'apres Francois Reymond"
						+ "et Adrien Wartelle (Partie avancee non disponible)");

		ParametreDePartie test = new ParametreDePartie();
		Partie partie = new Partie(test.getNombreDeJoueurs() * 4);
		Scanner sc = new Scanner(System.in);

		// Distribution des cartes et presentations
		test.getPaquetDePartie().distribuerRessourcesInitiales(
				test.getListeJoueurs(), test.getTypePartie());

		// NE MARCHE QU'EN PARTIE RAPIDE !!

		do {
			int tempIdJoueurActuel = test.getOrdreDesJoueurs()[partie.numeroDeTourActuel];
			int indexJoueurActuel = 0;
			for (Iterator<Joueur> it = test.getListeJoueurs().iterator(); it
					.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur.getId() == tempIdJoueurActuel) {
					indexJoueurActuel = test.getListeJoueurs().indexOf(
							tempJoueur);
					break;
				}
			}
			Joueur joueurActuel = test.getListeJoueurs().get(indexJoueurActuel);
			System.out.println("C'est au tour de " + joueurActuel.getNom());

			joueurActuel.jouerSonTour(partie.saisonActuelle, test);
			joueurActuel.Score(StatutPartie.rapide);
			
			double tempTemp = System.currentTimeMillis() + 2000;
			while (tempTemp > System.currentTimeMillis()) {
				for (Iterator<Joueur> it = test.getListeJoueurs().iterator(); it
						.hasNext();) {
					Joueur tempJoueur = it.next();
					if(!tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty()) {
						System.out.println("TODO");
					}
				}
			}

				partie.changerDeTour(test);
				// test fin de manche
				if (partie.saisonActuelle == Saison.printemps
				&& partie.numeroDeTourActuel == 0){
					partie.numeroDeManche++;
					if(test.getTypePartie()== StatutPartie.avancee){
						partie.changerDeManche(test);
					}
					
				}

		} while (partie.numeroDeManche != test.getNombreDeManches());
		// parcourir les joueurs
		partie.finDeJeu(test);

	}
}
