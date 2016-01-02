package vue;

import java.awt.Dimension;
import java.util.Observable;

import modele.PaquetDeRessourcesDeJoueur;
import Ressources.Ressources;

public class VuePaquetDeRessourcesIA extends VuePaquetDeRessourcesDeJoueur {

	public VuePaquetDeRessourcesIA(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(250, 640));
		for (int i = 0; i < paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
			vuesCartes[i] = new VueCarteIngredient(
					paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i), r, 80, 80, true);
		}
		// carte champ
		vuesCartes[4] = new VueCarteChamp(paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0),
				r, 80, 80, true);
		// Partie Avancee
		if (avancee) {
			vuesCartes[5] = new VueCarteChamp(
					paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0), r, 80, 80,
					true);
			if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0) != null) {
				vuesCartes[6] = new VueCarteChiensDeGarde(
						paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0), r, 80, 80,
						true);
			} else if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0) != null){
				vuesCartes[7] = new VueCarteChamp(
						paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes").get(0), r, 80, 80,
						true);
			}

		}
		// Affichage du nom en haut droite
		// this.setLayout(new );
		// Font f = new Font("Serif", Font.PLAIN, 1000);
		// this.nomDuJoueur.setPreferredSize(new Dimension(200, 20));
		this.nomDuJoueur.setAlignmentX(0);
		this.nomDuJoueur.setAlignmentY(0);
		// this.setLayout(new FlowLayout());
		this.ajoutPanneau(nomDuJoueur, 85, 170);
		// this.add(nomDuJoueur);

		// Graines
		this.ajoutPanneau(nombreDeGraines, 190, 40);
		for (int i = 0; i < this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
			graines.add(vuesGraines);
			this.ajoutPanneau(graines.get(i), 175 + (i % 3) * 26, 5 + (i / 3) * 11);
		}
		// cartes
		if (vuesCartes[0] != null) {
			this.ajoutPanneau(vuesCartes[0], 0, 0);
		}
		if (vuesCartes[1] != null) {
			this.ajoutPanneau(vuesCartes[1], 0, 85);
		}
		if (vuesCartes[2] != null) {
			this.ajoutPanneau(vuesCartes[2], 85, 0);
		}
		if (vuesCartes[3] != null) {
			this.ajoutPanneau(vuesCartes[3], 85, 85);
		}
		if (vuesCartes[4] != null) {
			this.ajoutPanneau(vuesCartes[4], 170, 0);
		}
		if (vuesCartes[5] != null) {
			this.ajoutPanneau(vuesCartes[5], 170, 85);
		}
		if (vuesCartes[6] != null) {
			this.ajoutPanneau(vuesCartes[6], 0, 170);
		}

	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
