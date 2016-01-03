package vue;

import java.awt.Color;
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

public abstract class VuePaquetDeRessourcesDeJoueur extends Panneau implements Observer {

	protected JLabel nombreDeGraines;
	protected JLabel nomDuJoueur;
	protected VueCarte[] vuesCartes;
	protected PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	protected ArrayList <VueImage> graines;
	protected boolean referenceAvancee;
	protected Ressources referenceRessources;
	protected String tempTexte1;
	
	
	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r, 
			boolean partieAvancee) {
		
		paquetDeRessourcesDeJoueur.addObserver(this);
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
		this.referenceAvancee = partieAvancee;
		this.referenceRessources = r;
		this.nombreDeGraines = new JLabel();
		tempTexte1 = new String();
		this.nomDuJoueur = new JLabel();
		this.setBackground(new Color(70,200,70));
		vuesCartes = new VueCarte[7];
		graines = new ArrayList<VueImage>();
		
		
		

	}

}
