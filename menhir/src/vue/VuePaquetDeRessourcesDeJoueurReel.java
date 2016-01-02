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

import Ressources.Ressources;
import modele.PaquetDeRessourcesDeJoueur;
import modele.Carte;
import modele.CarteChamp;
import modele.CarteChiensDeGarde;
import modele.CarteComptageDePoints;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;

public class VuePaquetDeRessourcesDeJoueurReel extends VuePaquetDeRessourcesDeJoueur {

	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
//		referencePaquetDeRessourcesDeJoueur.addObserver(this);

		this.setPreferredSize(new Dimension(450, 240));
//		if (!paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
//			for (int i = 0; i < paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
//				if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i) != null) {
//					vuesCartes[i] = new VueCarteIngredient(
//							paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i), r, 105,
//							105, false);
//				}
//			}
//		}
//		// carte champ
//		if (!paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
//			vuesCartes[4] = new VueCarteChamp(
//					paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), r, 105, 105, false);
//		}
//		// Partie Avancee
//		if (avancee) {
//			vuesCartes[5] = new VueCarteChamp(
//					paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), r, 105,
//					105, false);
//			if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0) != null) {
//				vuesCartes[6] = new VueCarteChiensDeGarde(
//						paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), referenceRessources, 105,
//						105, false);
//			} else if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0) != null) {
//				vuesCartes[7] = new VueCarteChamp(
//						paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), referenceRessources, 105,
//						105, false);
//			}
//
//		}
		// Affichage du nom en haut droite
		// this.setLayout(new );
		// Font f = new Font("Serif", Font.PLAIN, 1000);
		// this.nomDuJoueur.setPreferredSize(new Dimension(200, 20));
		// this.setLayout(new FlowLayout());
//		this.ajoutPanneau(nomDuJoueur, 355, 0);
		// this.add(nomDuJoueur);

//		// Graines
//		this.ajoutPanneau(nombreDeGraines, 355, 40);
//		for (int i = 0; i < this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
//			vuesGraines.add(new VueImage(referenceRessources.getImageGraine(), 24, 10));
//			this.ajoutPanneau(vuesGraines.get(i), 5 + (i % 3) * 26, 10 + (i / 3) * 11);
		}
//		// cartes
//		if (vuesCartes[0] != null) {
//			this.ajoutPanneau(vuesCartes[0], 0, 115);
//		}
//		if (vuesCartes[1] != null) {
//			this.ajoutPanneau(vuesCartes[1], 110, 115);
//		}
//		if (vuesCartes[2] != null) {
//			this.ajoutPanneau(vuesCartes[2], 220, 115);
//		}
//		if (vuesCartes[3] != null) {
//			this.ajoutPanneau(vuesCartes[3], 330, 115);
//		}
//		if (vuesCartes[4] != null) {
//			this.ajoutPanneau(vuesCartes[4], 0, 5);
//		}
//		if (vuesCartes[5] != null) {
//			this.ajoutPanneau(vuesCartes[5], 110, 5);
//		}
//		if (vuesCartes[6] != null) {
//			this.ajoutPanneau(vuesCartes[6], 220, 5);
//		}
//		
//	}

	public void update(Observable arg0, Object arg1) {
//		super.update(arg0, arg1);
		
		if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
			for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
				if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i) != null) {
					vuesCartes[i] = new VueCarteIngredient(
							referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i), referenceRessources, 105,
							105, false);
				}
			}
		}
		// carte champ
		if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
			vuesCartes[4] = new VueCarteChamp(
					referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0), referenceRessources, 105, 105, false);
		}
		// Partie Avancee
		if (this.referenceAvancee) {
			vuesCartes[5] = new VueCarteChamp(
					referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), referenceRessources, 105,
					105, false);
			if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0) != null) {
				vuesCartes[6] = new VueCarteChiensDeGarde(
						referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), referenceRessources, 105,
						105, false);
			} else if (referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0) != null) {
				vuesCartes[7] = new VueCarteChamp(
						referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), referenceRessources, 105,
						105, false);
			}

		}
		
		this.ajoutPanneau(nomDuJoueur, 355, 0);

		// cartes
		if (vuesCartes[0] != null) {
			this.ajoutPanneau(vuesCartes[0], 0, 115);
		}
		if (vuesCartes[1] != null) {
			this.ajoutPanneau(vuesCartes[1], 110, 115);
		}
		if (vuesCartes[2] != null) {
			this.ajoutPanneau(vuesCartes[2], 220, 115);
		}
		if (vuesCartes[3] != null) {
			this.ajoutPanneau(vuesCartes[3], 330, 115);
		}
		if (vuesCartes[4] != null) {
			this.ajoutPanneau(vuesCartes[4], 0, 5);
		}
		if (vuesCartes[5] != null) {
			this.ajoutPanneau(vuesCartes[5], 110, 5);
		}
		if (vuesCartes[6] != null) {
			this.ajoutPanneau(vuesCartes[6], 220, 5);
		}
		// Graines
				tempTexte1="";
				tempTexte1+=referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				this.nombreDeGraines.setText(tempTexte1);
				this.ajoutPanneau(nombreDeGraines, 355, 40);
				graines.clear();;
				System.out.println(graines.size());
				for (int i = 0; i < this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
					graines.add(vuesGraines);
					this.ajoutPanneau(graines.get(i), 5 + (i % 3) * 26, 10 + (i / 3) * 11);
				}
		this.repaint();
		this.revalidate();
		
	}

}
