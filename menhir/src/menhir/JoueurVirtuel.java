package menhir;

import java.util.ArrayList;
import java.util.Iterator;

public class JoueurVirtuel extends Joueur {

	private Difficulte difficulte;

	public JoueurVirtuel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie,
			Difficulte difficulte) {
		super(nom, referencePaquetPartie);
		this.difficulte = difficulte;
	}

	public void jouerSonTour(Saison saisonActuelle,
			ParametreDePartie parametreDePartie) {
		//faire une selection aleatoire de carte et d'action pour les IA
		CarteIngredient carteIA = new CarteIngredient(null);
		ArrayList <Carte> paquetCartesNonUtilises = new ArrayList<Carte>();
		//cartes ingredients
		for (Iterator<Carte> it = this.getPaquet().getPaquetsDeCartes().get("Cartes Ingredients")
				.iterator(); it.hasNext();) {
			Carte tempCarte = it.next();
			if (!tempCarte.isEstUtilise())
				paquetCartesNonUtilises.add(tempCarte);
		}
		
		//selection al�atoire de carte
		int indexCarte = (int) (Math.random() * paquetCartesNonUtilises.size());
		carteIA = (CarteIngredient) paquetCartesNonUtilises.get(indexCarte);
		//s�lection al�atoire action
		int indexAction = (int) (Math.random() * 3);
		// 0=engrais 1=farfadet 2=geant
		if(indexAction==0)
		{
			carteIA.utiliser(TypeAction.engrais, this, this, saisonActuelle, StatutPartie.rapide); // marche mal on se retrouve avec trop de menhirs adultes
			System.out.println(this.toString());
		}
		if(indexAction==1) //fonctionne mais revoir le parcourt de la collection joueur
		{
				//generer aleatoirement destinataire
			Joueur destinataire = null;
			ArrayList<Joueur> tempJoueurDest = new ArrayList<Joueur>();
			for (Iterator<Joueur> it = parametreDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur!=this)
					tempJoueurDest.add(tempJoueur);
			}
			int indexJoueurDest = (int) (Math.random() * tempJoueurDest.size());
			destinataire = tempJoueurDest.get(indexJoueurDest);
			carteIA.utiliser(TypeAction.farfadet, destinataire, this , saisonActuelle, StatutPartie.rapide); 
			System.out.println(destinataire.toString());
			System.out.println(this.toString());
		}
		if(indexAction==2){
			carteIA.utiliser(TypeAction.geantGardient, this, this, saisonActuelle, StatutPartie.rapide); 
			System.out.println(toString());

		}
	}

	public int seDefendre(StatutPartie statutPartie, Joueur destinataire,
			Joueur acteur, Saison saisonActuelle, int puissance) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void attaquer() {
		// TODO Auto-generated method stub
		
	}
}
