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
	
	
	public Partie(int nombreDeTour) {
		this.nombreDeTour = nombreDeTour;
		this.saisonActuelle = Saison.printemps;
		this.numeroDeTourActuel = 0;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * PaquetDeRessourcesDePartie TEst = new
		 * PaquetDeRessourcesDePartie(men
		 * hir.StatutPartie.avancee, 6);
		 * //TEst.afficherCartes(); Joueur testJoueur = new Joueur("Carcarmel");
		 * TEst.donnerUneCarteAuJoueur(testJoueur, "Cartes Ingredients");
		 * testJoueur.getPaquet().afficherCartes();
		 * 
		 * 
		 * // test PaquetDeRessourcesDePartie TEst = new
		 * PaquetDeRessourcesDePartie(menhir.StatutPartie.rapide, 2);
		 * //TEst.afficherCartes(); Joueur testJ1 = new Joueur("J1", TEst );
		 * Joueur testJ2 = new Joueur("J2", TEst );
		 * TEst.donnerUneCarteAuJoueur(testJ1, "Cartes Ingredients");
		 * TEst.donnerUneCarteAuJoueur(testJ2, "Cartes Ingredients");
		 * TEst.donnerUneCarteAuJoueur(testJ1, "Cartes Champs");
		 * TEst.donnerUneGraineDeMenhir(testJ1, 4);
		 * 
		 * testJ1.getPaquet().afficherCartes(); //test geant CarteIngredient
		 * CarteAJouer = (CarteIngredient)
		 * testJ1.getPaquet().getPaquetsDeCartes(
		 * ).get("Cartes Ingredients").get(0);
		 * System.out.println(TEst.toString());
		 * //CarteAJouer.utiliser(TypeAction.geantGardient, testJ2, testJ1,
		 * Saison.hiver, StatutPartie.rapide);
		 * System.out.println(testJ1.getPaquet().toString());
		 * System.out.println(testJ2.getPaquet().toString()); //test farfadet
		 * CarteIngredient CarteAJouer2 = (CarteIngredient)
		 * testJ2.getPaquet().getPaquetsDeCartes
		 * ().get("Cartes Ingredients").get(0);
		 * //CarteAJouer2.utiliser(TypeAction.farfadet, testJ1, testJ2,
		 * Saison.hiver, StatutPartie.rapide);
		 * System.out.println(testJ1.getPaquet().toString());
		 * System.out.println(testJ2.getPaquet().toString()); // test engrais
		 * CarteAJouer.utiliser(TypeAction.engrais, testJ1, testJ1,
		 * Saison.hiver, StatutPartie.rapide);
		 * System.out.println(testJ1.getPaquet().toString());
		 * testJ1.getPaquet().afficherCartes();
		 * System.out.println(TEst.toString());
		 */

		// test 28 novembre
		// 1ère version
		System.out
				.println("Bienvenue dans la version Alpha du jeu du menhir d'apres Francois Reymond"
						+ "et Adrien Wartelle (Partie avancee non disponible)");

		ParametreDePartie test = new ParametreDePartie();
		Partie partie = new Partie (test.getNombreDeJoueurs()*4);
		//Saison saisonActuelle = Saison.printemps;
		//int numeroDeTour = 0;
		Scanner sc = new Scanner(System.in);

		// Distribution des cartes et presentations
		for (Iterator<Joueur> it = test.getListeJoueurs().iterator(); it
				.hasNext();) {
			Joueur tempJoueur = it.next();
			System.out.println("Le joueur " + tempJoueur + "recoit ses ressources");
			test.getPaquetDePartie().donnerUneCarteAuJoueur(tempJoueur,
					"Cartes Ingredients");
			test.getPaquetDePartie().donnerUneCarteAuJoueur(tempJoueur,
					"Cartes Ingredients");
			test.getPaquetDePartie().donnerUneCarteAuJoueur(tempJoueur,
					"Cartes Ingredients");
			test.getPaquetDePartie().donnerUneCarteAuJoueur(tempJoueur,
					"Cartes Ingredients");
			test.getPaquetDePartie().donnerUneCarteAuJoueur(tempJoueur,
					"Cartes Champs");
			test.getPaquetDePartie().donnerUneGraineDeMenhir(tempJoueur);
			test.getPaquetDePartie().donnerUneGraineDeMenhir(tempJoueur);
		}

		// NE MARCHE QU'EN PARTIE RAPIDE !!

		do {
			int tempIdJoueurActuel = test.getOrdreDesJoueurs()[partie.numeroDeTourActuel];
			int indexJoueurActuel = 0;

			// On cherche le bon joueur
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

			// On fait jouer le joueur
			if (joueurActuel instanceof JoueurReel) {

				System.out
						.println("Voulez vous voir vos cartes ? (oui ou non)");
				String reponse = sc.next();
				System.out.println(reponse);
				if (reponse.contentEquals("y") || reponse.contentEquals("y")
						|| reponse.contentEquals("yes")
						|| reponse.contentEquals("oui")) {
					System.out
							.println(joueurActuel.getNom() + ", voici vos cartes (col1->P col2->E col3->A col4->H lign1->Engrais lign2->Geant lign3->Farfadet");
					joueurActuel.getPaquet().afficherCartes();
					System.out.println(joueurActuel.getPaquet().getGrainesDeMenhir());
				}

				System.out.println("Quelle carte jouez vous ? (tapez son id) ");
				boolean trouvee = false;
				CarteIngredient carteAJouer = new CarteIngredient(null);
				while (!trouvee) {
					reponse = sc.next();
					for (Iterator<Carte> it = joueurActuel.getPaquet()
							.getPaquetsDeCartes().get("Cartes Ingredients")
							.iterator(); it.hasNext();) {
						Carte tempCarte = it.next();
						if (String.valueOf(tempCarte.getId()).equals(reponse) && !tempCarte.isEstUtilise()) {
							carteAJouer = (CarteIngredient) tempCarte;
							trouvee = true;
							break;
						}
					}
				}
				
				System.out.println("Quelle action (engrais ou geant ou farfadet) ?");
				while(!reponse.contentEquals("engrais") && !reponse.contentEquals("geant") && !reponse.contentEquals("menhir"))
				{
					reponse = sc.next();
					if(reponse.contentEquals("engrais"))
					{
						carteAJouer.utiliser(TypeAction.engrais, joueurActuel, joueurActuel, partie.saisonActuelle, StatutPartie.rapide); // marche mal on se retrouve avec trop de menhirs adultes
						System.out.println("Vous avez maintenant " + joueurActuel.getPaquet().getNombreMenhirsAdultes() + " menhirs adultes.");
					}
					if(reponse.contentEquals("farfadet"))
					{
						System.out.println("A quel joueur voulez-vous voler des graines");
						// afficher tous les joueurs et leurs ressources (graines + menhirs)
						carteAJouer.utiliser(TypeAction.farfadet, joueurActuel, joueurActuel, partie.saisonActuelle, StatutPartie.rapide); 
						System.out.println("Vous avez maintenant " + joueurActuel.getPaquet().getGrainesDeMenhir() + " graines de menhirs.");
					}
				}

			} else {
				//faire une séléction aléatoire de carte et d'action pour les IA
			}
			partie.numeroDeTourActuel++;

		} while (partie.saisonActuelle != Saison.printemps);

	}
}
