package menhir;

import java.util.Iterator;
import java.util.Scanner;

public class JoueurReel extends Joueur {

	public JoueurReel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie);
	}

	public void jouerSonTour(Saison saisonActuelle, ParametreDePartie parametreDePartie) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Voulez vous voir vos cartes ? (oui ou non)");
		String reponse = sc.next();
		System.out.println(reponse);
		
		if (reponse.contentEquals("y") || reponse.contentEquals("y")
				|| reponse.contentEquals("yes") || reponse.contentEquals("oui")) {
			System.out
					.println(this.getNom()
							+ ", voici vos cartes (col1->P col2->E col3->A col4->H lign1->Geant lign2->Engrais lign3->Farfadet)");
			this.getPaquet().afficherCartes();
			System.out.println(this.getPaquet().getGrainesDeMenhir());
		}

		System.out.println("Quelle carte jouez vous ? (tapez son id) ");
		boolean trouvee = false;
		CarteIngredient carteAJouer = new CarteIngredient(null);
		while (!trouvee) {
			reponse = sc.next();
			for (Iterator<Carte> it = this.getPaquet()
					.getPaquetsDeCartes().get("Cartes Ingredients").iterator(); it
					.hasNext();) {
				Carte tempCarte = it.next();
				if (String.valueOf(tempCarte.getId()).equals(reponse)
						&& !tempCarte.isEstUtilise()) {
					carteAJouer = (CarteIngredient) tempCarte;
					trouvee = true;
					break;
				}
			}
		}

		System.out.println("Quelle action (engrais ou geant ou farfadet) ?");
		while (!reponse.contentEquals("engrais")
				&& !reponse.contentEquals("geant")
				&& !reponse.contentEquals("farfadet")) {
			reponse = sc.next();
			if (reponse.contentEquals("engrais")) {
				carteAJouer.utiliser(TypeAction.engrais, this,
						this, saisonActuelle,
						StatutPartie.rapide); // marche mal on se retrouve avec
												// trop de menhirs adultes
				System.out.println("Vous avez maintenant "
						+ this.getPaquet().getNombreMenhirsAdultes()
						+ " menhirs adultes.");
				System.out.println(this.toString());
			}
			if (reponse.contentEquals("farfadet")) // fonctionne mais revoir le
													// parcourt de la collection
													// joueur
			{
				System.out
						.println("A quel joueur voulez-vous voler des graines");
				// afficher tous les joueurs(IAS) et leurs ressources (graines +
				// menhirs)
				for (int i = 1; i < parametreDePartie.getNombreDeJoueurs(); i++) {
					Joueur tempIA = parametreDePartie.getListeJoueurs().get(i);
					System.out.println(tempIA.toString() + " menhirs : "
							+ tempIA.getPaquet().getNombreMenhirsAdultes());
				}
				System.out
						.println("A quel joueur voulez-vous volez les graines? Entrer id");
				trouvee = false;
				Joueur destinataire = null;
				while (!trouvee) {
					reponse = sc.next();
					for (Iterator<Joueur> it = parametreDePartie.getListeJoueurs()
							.iterator(); it.hasNext();) {
						Joueur tempJoueurVirtuel = it.next();
						if (String.valueOf(tempJoueurVirtuel.getId()).equals(
								reponse)) {
							destinataire = tempJoueurVirtuel;
							trouvee = true;
							break;
						}
					}
				}

				reponse = "farfadet";
				carteAJouer.utiliser(TypeAction.farfadet, destinataire,
						this, saisonActuelle,
						StatutPartie.rapide);
				System.out.println("Vous avez maintenant "
						+ this.getPaquet().getGrainesDeMenhir()
						+ " graines de menhirs.");
				System.out.println(destinataire.toString());
				System.out.println(this.toString());

			}
			if (reponse.contentEquals("geant")) {
				carteAJouer.utiliser(TypeAction.geantGardient, this,
						this, saisonActuelle,
						StatutPartie.rapide);
				System.out.println("Vous avez maintenant "
						+ this.getPaquet().getGrainesDeMenhir()
						+ " graines de menhirs.");

				System.out.println(this.toString());
			}
		}

	}
	
	public int seDefendre(StatutPartie statutPartie, Joueur destinataire, Joueur acteur, Saison saisonActuelle, int puissance){
		int puissanceModifie=puissance;
		if (statutPartie == StatutPartie.avancee){
			if(!(destinataire.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty())){
				System.out.println(acteur.getNom()+" vous lance une attaque de "+puissanceModifie+" "
						+ "graines, Se défendre avec votre carte chien de garde ?");
				Scanner source = new Scanner(System.in);
				if (source.next()=="oui"){
					CarteChiensDeGarde tempCarteChiensDeGarde = (CarteChiensDeGarde) destinataire.getPaquet().getPaquetsDeCartes()
							.get("Cartes ChiensDeGarde").get(0);
					puissanceModifie = tempCarteChiensDeGarde.utiliser(destinataire, saisonActuelle, puissanceModifie);
				}
			}
		}
		return puissanceModifie;
	}
	
	public void attaquer() {
	}
	

}
