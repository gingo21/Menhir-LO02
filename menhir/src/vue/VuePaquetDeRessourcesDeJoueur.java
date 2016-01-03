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
import modele.Joueur;
import modele.PaquetDeRessourcesDeJoueur;
import modele.Carte;
import modele.CarteChamp;
import modele.CarteChiensDeGarde;
import modele.CarteComptageDePoints;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;
import modele.PaquetDeRessourcesDePartie;

public abstract class VuePaquetDeRessourcesDeJoueur extends Panneau implements Observer {

	protected JLabel nombreDeGraines;
	protected JLabel nomDuJoueur;
//	protected VueCarte[] vuesCartes;
	protected PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	protected ArrayList <VueImage> graines;
	protected boolean referenceAvancee;
	protected Ressources referenceRessources;
	protected String tempTexte1;
	protected String tempTexte2;
	protected ArrayList<VueCarte> tempVueCartes1;
	protected ArrayList<VueCarte> tempVueCartes2;
	protected ArrayList<VueCarte> tempVueCartes3;
	protected ArrayList<VueCarte> tempVueCartes4;
	protected ArrayList<VueCarte> tempVueCartes5;
	
//	protected HashMap<String, ArrayList<VueCarte>> vuesCartes;
	
	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r, 
			boolean partieAvancee) {
		
		
		paquetDeRessourcesDeJoueur.addObserver(this);
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
//		this.referencePaquetsDeCartes = paquetDeRessourcesDeJoueur.getPaquetsDeCartes();
		this.referenceAvancee = partieAvancee;
		this.referenceRessources = r;
		this.nombreDeGraines = new JLabel();
		tempTexte1 = new String();
		tempTexte2 = new String();
		this.nomDuJoueur = new JLabel();
		this.setBackground(new Color(70,200,70));
		graines = new ArrayList<VueImage>();
		
//		vuesCartes=new HashMap<String, ArrayList<VueCarte>>();
		tempVueCartes1 = new ArrayList<VueCarte>();
		tempVueCartes2 = new ArrayList<VueCarte>();
		tempVueCartes3 = new ArrayList<VueCarte>();
		tempVueCartes4 = new ArrayList<VueCarte>();
		tempVueCartes5 = new ArrayList<VueCarte>();

}

	public ArrayList<VueCarte> getTempVueCartes1() {
		return tempVueCartes1;
	}

	public void setTempVueCartes1(ArrayList<VueCarte> tempVueCartes1) {
		this.tempVueCartes1 = tempVueCartes1;
	}

	public PaquetDeRessourcesDeJoueur getReferencePaquetDeRessourcesDeJoueur() {
		return referencePaquetDeRessourcesDeJoueur;
	}

	public void setReferencePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur) {
		this.referencePaquetDeRessourcesDeJoueur = referencePaquetDeRessourcesDeJoueur;
	}
	
	
}
