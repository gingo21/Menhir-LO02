package vue;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Observable;

import modele.PaquetDeRessourcesDeJoueur;
import Ressources.Ressources;

public class VuePaquetDeRessourcesIA extends VuePaquetDeRessourcesDeJoueur {

	public static int TAILLE_CARTE = 80;

	public VuePaquetDeRessourcesIA(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(250, 640));
		
	}

	public void update(Observable arg0, Object arg1) {
		if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
			if (!tempVueCartes1.isEmpty()){
				for (Iterator <VueCarte>it=tempVueCartes1.iterator();it.hasNext();){
					VuePaquetDeRessourcesIA.this.remove(it.next());
				}
				tempVueCartes1.clear();
			for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i).isEstUtilise()) {
					tempVueCartes1.add(new VueCarteIngredient(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i), referenceRessources, TAILLE_CARTE,
							TAILLE_CARTE, false));
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes1.get(i), (i%2)*TAILLE_CARTE, (i/2)*TAILLE_CARTE);
				}
			}
		}
//		 carte champ
		if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty() && tempVueCartes2.isEmpty()) {
			tempVueCartes2.add(new VueCarteChamp(
					referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes2.get(0), 2*TAILLE_CARTE, 0);
		}
		// Partie Avancee
		if (VuePaquetDeRessourcesIA.this.referenceAvancee) {
			if(!tempVueCartes3.isEmpty()){
				tempVueCartes3.add(new VueCarteComptageDePoints(
						referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), referenceRessources, TAILLE_CARTE,
						TAILLE_CARTE, false));
				VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes3.get(0), 2*TAILLE_CARTE, TAILLE_CARTE);

			}
			tempVueCartes4.clear();
			if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty()) {
				if(!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0).isEstUtilise()){
					tempVueCartes4.add(new VueCarteChiensDeGarde(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), referenceRessources, TAILLE_CARTE,
							TAILLE_CARTE, false));
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes4.get(0), 0, 2*TAILLE_CARTE);
				
				}
			
				tempVueCartes5.clear();
			} else if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty()) {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0).isEstUtilise()){
					tempVueCartes5.add( new VueCarteChamp(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), referenceRessources, TAILLE_CARTE,
							TAILLE_CARTE, false));
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes5.get(0), 220, 5);

				}
			}

		}

		// Graines
		VuePaquetDeRessourcesIA.this.remove(nombreDeGraines);
		tempTexte1="";
		tempTexte1+=referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
		VuePaquetDeRessourcesIA.this.nombreDeGraines.setText(tempTexte1);
		VuePaquetDeRessourcesIA.this.ajoutPanneau(nombreDeGraines, 355, 40);
		graines.clear();
		for (int i = 0; i < VuePaquetDeRessourcesIA.this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
			graines.add(new VueImage(referenceRessources.getImageGraine(), 24, 10));
			VuePaquetDeRessourcesIA.this.ajoutPanneau(graines.get(i), 5 + (i % 3) * 26, 10 + (i / 3) * 11);
		}

		}
	}
}
