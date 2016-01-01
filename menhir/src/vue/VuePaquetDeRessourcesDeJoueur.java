package vue;

import java.awt.Dimension;
import java.awt.Image;
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

public class VuePaquetDeRessourcesDeJoueur extends Panneaux implements Observer {

	protected JLabel nombreDeGraines;
	protected JLabel nomDuJoueur;
	protected VueCarte[] vuesCartes;
	protected PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	
	
	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r, 
			boolean partieAvancee, boolean speciale) {
		
		paquetDeRessourcesDeJoueur.addObserver(this);
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1+=paquetDeRessourcesDeJoueur.getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.nomDuJoueur = new JLabel();
		this.nomDuJoueur.setText(paquetDeRessourcesDeJoueur.getJoueur().getNom());
		System.out.println(paquetDeRessourcesDeJoueur.getJoueur().getNom());
		vuesCartes = new VueCarte[7];
		

	}

	public void update(Observable arg0, Object arg1) {
		

	}

}
