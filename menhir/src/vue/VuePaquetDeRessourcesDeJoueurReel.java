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
	
	public static int TAILLE_CARTE = 140;
	
	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(600, 300));
	}
	public void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
					for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
						if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i) != null) {
							tempVueCartes1.add(new VueCarteIngredient(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i), referenceRessources, TAILLE_CARTE,
									TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes1.get(i), i*TAILLE_CARTE, 115);
						}
					}
				}
				// carte champ
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
					tempVueCartes2.add(new VueCarteChamp(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes2.get(0), 0, 5);
				}
				// Partie Avancee
				if (VuePaquetDeRessourcesDeJoueurReel.this.referenceAvancee) {
					tempVueCartes3.add(new VueCarteComptageDePoints(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), referenceRessources, TAILLE_CARTE,
							TAILLE_CARTE, false));
					if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0) != null) {
					tempVueCartes4.add(new VueCarteChiensDeGarde(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), referenceRessources, TAILLE_CARTE,
								TAILLE_CARTE, false));
					} else if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0) != null) {
					tempVueCartes5.add( new VueCarteChamp(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), referenceRessources, TAILLE_CARTE,
								TAILLE_CARTE, false));
					}

				}

				VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(nomDuJoueur, 355, 0);

//				// cartes
//				if (tempVueCartes1. != null) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(vuesCartes[0], 0, 115);
//				}
//				if (vuesCartes[1] != null) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(vuesCartes[1], TAILLE_CARTE, 115);
//				}
//				if (vuesCartes[2] != null) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(vuesCartes[2], 2*TAILLE_CARTE, 115);
//				}
//				if (vuesCartes[3] != null) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(vuesCartes[3], 3*TAILLE_CARTE, 115);
//				}
//				if (!tempVueCartes2.isEmpty()) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(vuesCartes[4], 0, 5);
//				}
//				if (!tempVueCartes3.isEmpty()) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes3.get(0), 110, 5);
//				}
//				if (!tempVueCartes4.isEmpty()) {
//					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes4.get(0), 220, 5);
//				}
				// Graines
				tempTexte1="";
				tempTexte1+=referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				VuePaquetDeRessourcesDeJoueurReel.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(nombreDeGraines, 355, 40);
				graines.clear();
				for (int i = 0; i < VuePaquetDeRessourcesDeJoueurReel.this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
					graines.add(new VueImage(referenceRessources.getImageGraine(), 24, 10));
					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(graines.get(i), 5 + (i % 3) * 26, 10 + (i / 3) * 11);
				}
				VuePaquetDeRessourcesDeJoueurReel.this.repaint();
				VuePaquetDeRessourcesDeJoueurReel.this.revalidate();

			}

		};
		SwingUtilities.invokeLater(myRunnable);
	}
}
