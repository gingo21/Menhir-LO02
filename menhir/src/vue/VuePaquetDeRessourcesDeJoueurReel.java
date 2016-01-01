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

public class VuePaquetDeRessourcesDeJoueurReel extends VuePaquetDeRessourcesDeJoueur implements Observer {

	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee, boolean speciale) {
		super(paquetDeRessourcesDeJoueur,r,avancee,speciale);
		paquetDeRessourcesDeJoueur.addObserver(this);
		
		this.setPreferredSize(new Dimension(450, 240));
		for(int i=0; i<paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
			vuesCartes[i] = new VueCarteIngredient(paquetDeRessourcesDeJoueur.getPaquetsDeCartes()
					.get("Cartes Ingredients").get(i),r,105,105,false);
		}
//		carte champ
		vuesCartes[4] = new VueCarteChamp(paquetDeRessourcesDeJoueur.getPaquetsDeCartes()
				.get("Cartes Champs").get(0),r,105,105,false);
//		Partie Avancee
		if (avancee){
			vuesCartes[5]= new VueCarteChamp(paquetDeRessourcesDeJoueur.getPaquetsDeCartes()
					.get("Cartes Comptage De Points").get(0),r,105,105,false);
			if(speciale){
				if (paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde").get(0)!=null){
					vuesCartes[6]= new VueCarteChiensDeGarde(paquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Chiens De Garde").get(0),r,105,105,false);
				}
				else{
					vuesCartes[7]= new VueCarteChamp(paquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Taupes Geantes").get(0),r,105,105,false);
				}
			}
			
		}
//		Affichage du nom en haut droite
//		this.setLayout(new );
//		Font f = new Font("Serif", Font.PLAIN, 1000);
//		this.nomDuJoueur.setPreferredSize(new Dimension(200, 20));
		this.nomDuJoueur.setAlignmentX(0);
		this.nomDuJoueur.setAlignmentY(0);
//		this.setLayout(new FlowLayout());
		this.ajoutPanneau(nomDuJoueur, 355, 0);
//		this.add(nomDuJoueur);
		
//		Graines 
		this.ajoutPanneau(nombreDeGraines, 355, 40);
		for (int i = 0; i < this.referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir(); i++) {
			vuesGraines.add(new VueImage(r.getImageGraine(), 24, 10));
			this.ajoutPanneau(vuesGraines.get(i), 5 + (i % 3) * 26, 10 + (i / 3) * 11);
		}
//		cartes
		if(vuesCartes[0] != null){
			this.ajoutPanneau(vuesCartes[0], 0, 115);
		}
		if(vuesCartes[1] != null){
			this.ajoutPanneau(vuesCartes[1], 110, 115);
		}
		if(vuesCartes[2] != null){
			this.ajoutPanneau(vuesCartes[2], 220, 115);
		}
		if(vuesCartes[3] != null){
			this.ajoutPanneau(vuesCartes[3], 330, 115);
		}
		if(vuesCartes[4] != null){
			this.ajoutPanneau(vuesCartes[4], 0, 5);
		}
		if(vuesCartes[5] != null){
			this.ajoutPanneau(vuesCartes[5], 110, 5);
		}
		if(vuesCartes[6] != null){
			this.ajoutPanneau(vuesCartes[6], 220, 5);
		}
		/*Set<String> tempKeys = paquetDeRessourcesDeJoueur.getPaquetsDeCartes().keySet();
		for(Iterator<String> it = tempKeys.iterator(); it.hasNext();) {
			String tempKey = it.next();
			for(Iterator<Carte> yt = paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get(tempKey).iterator(); yt.hasNext();) {
				Carte tempCarte = yt.next();
				VueCarte tempVueCarte = null;
				if(tempCarte instanceof CarteIngredient) {
					tempVueCarte = new VueCarteIngredient(tempCarte);
				} else if(tempCarte instanceof CarteChiensDeGarde) {
					tempVueCarte = new VueCarteChiensDeGarde(tempCarte);
				} else if(tempCarte instanceof CarteTaupesGeantes) {
					tempVueCarte = new VueCarteComptageDePoints(tempCarte);
				} else if(tempCarte instanceof CarteChamp) {
					tempVueCarte = new VueCarteChamp(tempCarte);
				} else {
					tempVueCarte = new VueCarteComptageDePoints(tempCarte);
				}
				vuesCartes.put(tempKey, tempVueCarte);
			}
		}*/
	}

	public void update(Observable arg0, Object arg1) {
		

	}

}
