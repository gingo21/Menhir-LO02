package vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JLabel;

import modele.PaquetDeRessourcesDeJoueur;
import ressources.Ressources;

public abstract class VuePaquetDeRessourcesDeJoueur extends Panneau implements Observer {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -6113273243337912263L;
	protected JLabel nombreDeGraines;
	protected JLabel nomDuJoueur;
	// protected VueCarte[] vuesCartes;
	protected PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	protected ArrayList<VueImage> graines;
	protected ArrayList<VueImage> grainesMenhir;
	protected ArrayList<VueImage> grainesMenhirChamp;
	protected boolean referenceAvancee;
	protected Ressources referenceRessources;
	protected String tempTexte1;
	protected String tempTexte2;
	protected ArrayList<VueCarte> tempVueCartes1;
	protected ArrayList<VueCarte> tempVueCartes2;
	protected ArrayList<VueCarte> tempVueCartes3;
	protected ArrayList<VueCarte> tempVueCartes4;
	protected ArrayList<VueCarte> tempVueCartes5;

	// protected HashMap<String, ArrayList<VueCarte>> vuesCartes;

	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean partieAvancee) {

		paquetDeRessourcesDeJoueur.addObserver(this);
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
		// this.referencePaquetsDeCartes =
		// paquetDeRessourcesDeJoueur.getPaquetsDeCartes();
		this.referenceAvancee = partieAvancee;
		this.referenceRessources = r;
		this.nombreDeGraines = new JLabel();
		tempTexte1 = new String();
		tempTexte2 = new String();
		this.nomDuJoueur = new JLabel();
		this.setBackground(new Color(70, 200, 70));
		graines = new ArrayList<VueImage>();
		grainesMenhirChamp = new ArrayList<VueImage>();
		grainesMenhir = new ArrayList<VueImage>();

		// vuesCartes=new HashMap<String, ArrayList<VueCarte>>();
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
