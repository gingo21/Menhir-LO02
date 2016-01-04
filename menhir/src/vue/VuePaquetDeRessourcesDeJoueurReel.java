package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Ressources.Ressources;
import modele.PaquetDeRessourcesDeJoueur;
import modele.Carte;
import modele.CarteChamp;
import modele.CarteChiensDeGarde;
import modele.CarteComptageDePoints;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;

public class VuePaquetDeRessourcesDeJoueurReel extends VuePaquetDeRessourcesDeJoueur {

	public static int TAILLE_CARTE = 150;

	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(850, 340));
		tempTexte2 += referencePaquetDeRessourcesDeJoueur.getJoueur().getNom();
		VuePaquetDeRessourcesDeJoueurReel.this.nomDuJoueur.setText(tempTexte2);
		this.ajoutPanneau(nomDuJoueur, TAILLE_CARTE/3, 0);

	}

	public void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				//				VuePaquetDeRessourcesDeJoueurReel.this.removeAll();
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
					if (!tempVueCartes1.isEmpty()) {
						for (Iterator<VueCarte> it = tempVueCartes1.iterator(); it.hasNext();) {
							VuePaquetDeRessourcesDeJoueurReel.this.remove(it.next());

						}
						tempVueCartes1.clear();
					}

					for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Ingredients").size(); i++) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i)
								.isEstUtilise()) {
							VueCarteIngredient tempVue = new VueCarteIngredient(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Ingredients").get(i),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false);
							tempVueCartes1.add(tempVue);
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVue, TAILLE_CARTE + i * TAILLE_CARTE,
									TAILLE_CARTE);
							// System.out.println(arg0.getClass().getName());
						}
					}
				}
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
					if( tempVueCartes2.isEmpty()){
						tempVueCartes2.add(new VueCarteChamp(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
						VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes2.get(0), 0, 0);
					}
					if(!grainesMenhir.isEmpty()){
						for (Iterator<VueImage> it = grainesMenhir.iterator(); it.hasNext();) {
							VuePaquetDeRessourcesDeJoueurReel.this.remove(it.next());
						}
						grainesMenhir.clear();
						VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes2.get(0));

					}
					//Affichage graines menhirs
					if (((CarteChamp)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0)).getMenhirAdultes()!=0) {

						for (int i = 0; i < ((CarteChamp)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0)).getMenhirAdultes(); i++) {
							VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
							grainesMenhir.add(tempVueImage);
							(tempVueCartes2.get(0)).ajoutPanneau(tempVueImage, 0+5 + (i % 3) * 26,
									+20 + (i / 3) * 11);
						}
						VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes2.get(0), 0, 0);

					}
				}
				// Partie Avancee
				if (VuePaquetDeRessourcesDeJoueurReel.this.referenceAvancee) {
					if (tempVueCartes3.isEmpty() && !referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Comptage De Points").isEmpty()) {
						tempVueCartes3.add(new VueCarteComptageDePoints(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
								.get("Cartes Comptage De Points").get(0),
								referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
						VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes3.get(0), 0, TAILLE_CARTE);
					}
					if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
							.isEmpty()) {
						if (!tempVueCartes4.isEmpty()) {
							VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes4.get(0));
							tempVueCartes4.clear();
						}
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
								.get(0).isEstUtilise()) {
							tempVueCartes4.add(new VueCarteChiensDeGarde(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Chiens De Garde").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes4.get(0), TAILLE_CARTE*2, 0);

						}

					} else if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
							.isEmpty()) {
						if (!tempVueCartes5.isEmpty()) {
							VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes5.get(0));
							tempVueCartes5.clear();
						}
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
								.get(0).isEstUtilise()) {
							tempVueCartes5.add(new VueCarteTaupesGeantes(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Taupes Geantes").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes5.get(0), TAILLE_CARTE*2, 0);

						}
					}

				}

				// // Graines
				VuePaquetDeRessourcesDeJoueurReel.this.remove(nombreDeGraines);
				tempTexte1 = "";
				tempTexte1 += referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				VuePaquetDeRessourcesDeJoueurReel.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(nombreDeGraines, TAILLE_CARTE*19/10, 40);

				if (!graines.isEmpty()) {
					for (Iterator<VueImage> it = graines.iterator(); it.hasNext();) {
						VuePaquetDeRessourcesDeJoueurReel.this.remove(it.next());
					}
					graines.clear();
				}
				for (int i = 0; i < VuePaquetDeRessourcesDeJoueurReel.this.referencePaquetDeRessourcesDeJoueur
						.getGrainesDeMenhir(); i++) {
					VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
					graines.add(tempVueImage);
					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueImage, TAILLE_CARTE+5 + (i % 3) * 26,
							+10 + (i / 3) * 11);
				}
				VuePaquetDeRessourcesDeJoueurReel.this.repaint();
				VuePaquetDeRessourcesDeJoueurReel.this.revalidate();

			}

		};
		SwingUtilities.invokeLater(myRunnable);
	}
}
