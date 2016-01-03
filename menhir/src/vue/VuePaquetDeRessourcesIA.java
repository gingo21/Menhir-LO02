package vue;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.SwingUtilities;

import modele.CarteChamp;
import modele.PaquetDeRessourcesDeJoueur;
import Ressources.Ressources;

public class VuePaquetDeRessourcesIA extends VuePaquetDeRessourcesDeJoueur {

	public static int TAILLE_CARTE = 80;

	public VuePaquetDeRessourcesIA(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(250, 260));
		tempTexte2 += referencePaquetDeRessourcesDeJoueur.getJoueur().getNom();
		this.nomDuJoueur.setText(tempTexte2);
		this.ajoutPanneau(nomDuJoueur, 50, 0);
		
		
		
	}

	public void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {


					if (!tempVueCartes1.isEmpty()){
						for (Iterator <VueCarte>it=tempVueCartes1.iterator();it.hasNext();){
							VuePaquetDeRessourcesIA.this.remove(it.next());
						}
						tempVueCartes1.clear();
					}
					for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i).isEstUtilise()) {
							VueCarteIngredient tempVue = new VueCarteIngredient(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Ingredients").get(i),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, true);
							tempVueCartes1.add(tempVue);
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVue, (i%2)*TAILLE_CARTE, 20+(i/2)*TAILLE_CARTE);
						}
					}
				}

				//		 carte champ
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
					if(tempVueCartes2.isEmpty()){
						tempVueCartes2.add(new VueCarteChamp(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
						VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes2.get(0), 2*TAILLE_CARTE, 20);
					}
					if(!grainesMenhir.isEmpty()){
						for (Iterator<VueImage> it = grainesMenhir.iterator(); it.hasNext();) {
							VuePaquetDeRessourcesIA.this.remove(it.next());
						}
						grainesMenhir.clear();
						VuePaquetDeRessourcesIA.this.remove(tempVueCartes2.get(0));

					}
					//Affichage graines menhirs
					if (((CarteChamp)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0)).getMenhirAdultes()!=0) {

						for (int i = 0; i < ((CarteChamp)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0)).getMenhirAdultes(); i++) {
							VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
							grainesMenhir.add(tempVueImage);
							(tempVueCartes2.get(0)).ajoutPanneau(tempVueImage, 0+5 + (i % 3) * 26,
									+10 + (i / 3) * 11);
						}
						VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes2.get(0), 2*TAILLE_CARTE, 20);

					}
				}
				// Partie Avancee
				if (VuePaquetDeRessourcesIA.this.referenceAvancee) {
					if(tempVueCartes3.isEmpty()){
						tempVueCartes3.add(new VueCarteComptageDePoints(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), referenceRessources, TAILLE_CARTE,
								TAILLE_CARTE, false));
						VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes3.get(0), 2*TAILLE_CARTE, 20+TAILLE_CARTE);

					}
					tempVueCartes4.clear();
					if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").isEmpty()) {
						if(!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0).isEstUtilise()){
							tempVueCartes4.add(new VueCarteChiensDeGarde(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), referenceRessources, TAILLE_CARTE,
									TAILLE_CARTE, true));
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes4.get(0), 0, 20+2*TAILLE_CARTE);

						}

						tempVueCartes5.clear();
					} else if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").isEmpty()) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0).isEstUtilise()){
							tempVueCartes5.add( new VueCarteChamp(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), referenceRessources, TAILLE_CARTE,
									TAILLE_CARTE, true));
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes5.get(0), 0, 2*TAILLE_CARTE);

						}
					}

				}

				VuePaquetDeRessourcesIA.this.remove(nombreDeGraines);
				tempTexte1="";
				tempTexte1+=referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				VuePaquetDeRessourcesIA.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesIA.this.ajoutPanneau(nombreDeGraines, 125, 0);
				if (!graines.isEmpty()) {
					for (Iterator<VueImage> it = graines.iterator(); it.hasNext();) {
						VuePaquetDeRessourcesIA.this.remove(it.next());
					}
					graines.clear();
				}
				for (int i = 0; i < VuePaquetDeRessourcesIA.this.referencePaquetDeRessourcesDeJoueur
						.getGrainesDeMenhir(); i++) {
					VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
					graines.add(tempVueImage);
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueImage, 2*TAILLE_CARTE+5 + (i % 3) * 26,
							TAILLE_CARTE+10 + (i / 3) * 11);
				}


				VuePaquetDeRessourcesIA.this.repaint();
				VuePaquetDeRessourcesIA.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);
	}
			
	
}
