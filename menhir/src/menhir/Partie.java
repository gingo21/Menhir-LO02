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
	
		// 1Ã¨re version
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

			// On cherche le bon joueur //FR pas besoin? on commence à 0 on incrémente à chaque tour
			//et remise à 0 à chaque chgt de saison?
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
				while(!reponse.contentEquals("engrais") && !reponse.contentEquals("geant") && !reponse.contentEquals("farfadet"))
				{
					reponse = sc.next();
					if(reponse.contentEquals("engrais"))
					{
						carteAJouer.utiliser(TypeAction.engrais, joueurActuel, joueurActuel, partie.saisonActuelle, StatutPartie.rapide); // marche mal on se retrouve avec trop de menhirs adultes
						System.out.println("Vous avez maintenant " + joueurActuel.getPaquet().getNombreMenhirsAdultes() + " menhirs adultes.");
					}
					if(reponse.contentEquals("farfadet")) //fonctionne mais revoir le parcourt de la collection joueur
					{
						System.out.println("A quel joueur voulez-vous voler des graines");
						// afficher tous les joueurs(IAS) et leurs ressources (graines + menhirs)
						for (int  i=1; i< test.getNombreDeJoueurs(); i++){
							Joueur tempIA = test.getListeJoueurs().get(i);
							System.out.println(tempIA.toString()+" menhirs : "+ tempIA.getPaquet().getNombreMenhirsAdultes());
						}
							System.out.println("A quel joueur voulez-vous volez les graines? Entrer id");
							trouvee = false;
							Joueur destinataire = new Joueur(null, null);
							while (!trouvee) {
								reponse = sc.next();
								for (Iterator<Joueur> it = test.getListeJoueurs().iterator(); it
										.hasNext();){
									Joueur tempJoueurVirtuel = it.next();
									if (String.valueOf(tempJoueurVirtuel.getId()).equals(reponse)) {
										destinataire =  tempJoueurVirtuel;
										trouvee = true;
										break;
									}
								}
							}
							
						reponse = "farfadet";
						carteAJouer.utiliser(TypeAction.farfadet, destinataire,joueurActuel , partie.saisonActuelle, StatutPartie.rapide); 
						System.out.println("Vous avez maintenant " + joueurActuel.getPaquet().getGrainesDeMenhir() + " graines de menhirs.");

					}
					if(reponse.contentEquals("geant")){
						carteAJouer.utiliser(TypeAction.geantGardient, joueurActuel, joueurActuel, partie.saisonActuelle, StatutPartie.rapide); 
						System.out.println("Vous avez maintenant " + joueurActuel.getPaquet().getGrainesDeMenhir() + " graines de menhirs.");
					}
				}

			} else {
				//faire une selection aleatoire de carte et d'action pour les IA
			}
			System.out.println(partie.saisonActuelle);
			joueurActuel.Score(StatutPartie.rapide);
			partie.numeroDeTourActuel++;
			indexJoueurActuel++;
			if ((partie.numeroDeTourActuel % test.getNombreDeJoueurs()) == 0){ //on change de saison tous les njoueurs tours
				if(partie.saisonActuelle == Saison.hiver){						//condtion à coder plus proprement dans l'enum
					partie.saisonActuelle = Saison.printemps;
				}
				else partie.saisonActuelle = partie.saisonActuelle.next();
				indexJoueurActuel = 0; 
				partie.numeroDeTourActuel=0;
			}
			System.out.println(partie.saisonActuelle);

		} while (partie.saisonActuelle != Saison.printemps || partie.numeroDeTourActuel ==1 );
		// parcourir les joueurs 
		int indexJoueurActuel = 0;

		// On cherche le bon joueur //FR pas besoin? on commence à 0 on incrémente à chaque tour
		//et remise à 0 à chaque chgt de saison?
		Joueur JoueurGagnant = new Joueur(null,null);
		int maxScore = 0;
		for (Iterator<Joueur> it = test.getListeJoueurs().iterator(); it
				.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur.getScore() > maxScore )
				JoueurGagnant = tempJoueur;
			}
		System.out.println("Gagnant : " + JoueurGagnant.toString()); //+ rajouter un test sur joueur reel, avec message personnalisé
		
		
	}
}
