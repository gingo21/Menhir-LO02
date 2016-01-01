package vue;

import java.awt.Dimension;
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

public class VuePaquetDeRessourcesDeJoueurReel extends JPanel implements Observer {

	private JLabel nombreDeGraines;
	private JLabel nomDuJoueur;
	private VueCarte[] vuesCartes;
	private PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;

	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r) {
		
		paquetDeRessourcesDeJoueur.addObserver(this);
		
		this.setPreferredSize(new Dimension(250, 640));
		
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1+=paquetDeRessourcesDeJoueur.getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.nomDuJoueur = new JLabel();
		this.nomDuJoueur.setText(paquetDeRessourcesDeJoueur.getJoueur().getNom());
		System.out.println(paquetDeRessourcesDeJoueur.getJoueur().getNom());
		vuesCartes = new VueCarte[7];
		for(int i=0; i<paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").size(); i++) {
			vuesCartes[i] = new VueCarteIngredient(paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i),r,80,80);
		}
		if(vuesCartes[0] != null){
			vuesCartes[0].setAlignmentX(0);
			vuesCartes[0].setAlignmentY(0);
			this.add(vuesCartes[0]);
		}
		if(vuesCartes[1] != null){
			vuesCartes[1].setAlignmentX(0);
			vuesCartes[1].setAlignmentY(125);
			this.add(vuesCartes[1]);
		}
		if(vuesCartes[2] != null){
			vuesCartes[2].setAlignmentX(80);
			vuesCartes[2].setAlignmentY(0);
			this.add(vuesCartes[2]);
		}
		if(vuesCartes[3] != null){
		vuesCartes[3].setAlignmentX(80);
		vuesCartes[3].setAlignmentY(125);
		this.add(vuesCartes[3]);
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
		
		//this.nombreDeGraines.setPreferredSize(new Dimension(75, 30));
		this.nombreDeGraines.setAlignmentX(241);
		this.nombreDeGraines.setAlignmentY(155);
		
		//this.nomDuJoueur.setPreferredSize(new Dimension(75, 30));
		this.nomDuJoueur.setAlignmentX(241);
		this.nomDuJoueur.setAlignmentX(250);
		
		
		this.add(this.nombreDeGraines);
		this.add(this.nomDuJoueur);
	}

	public void update(Observable arg0, Object arg1) {
		

	}

}
