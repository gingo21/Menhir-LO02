package vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JLabel;

import modele.PaquetDeRessourcesDeJoueur;
import ressources.Ressources;

/**
 *Classe abstraite qui définit les ressources graphiques communes d'un joueur réel et une IA
 *C'est un panneau(Jlabel)
 */
public abstract class VuePaquetDeRessourcesDeJoueur extends Panneau implements Observer {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -6113273243337912263L;
	
	/**
	 *Attribut pour affichage du nombre de graines
	 */
	protected JLabel nombreDeGraines;
	
	/**
	 *Attribut pour affichage du nom du joueur
	 */
	protected JLabel nomDuJoueur;
	
	/**
	 *Attribut référence du Paquet de Ressources du joueur
	 */
	protected PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	
	/**
	 *Liste des graines(graphique)
	 */
	protected ArrayList<VueImage> graines;
	
	/**
	 *Liste des graines de Menhir(graphique)
	 */
	protected ArrayList<VueImage> grainesMenhir;
	
	/**
	 *Liste des graines de Menhir(graphique)
	 */
	protected ArrayList<VueImage> grainesMenhirChamp;
	
	/**
	 *Référence partie avancée 
	 */
	protected boolean referenceAvancee;
	
	/**
	 *Attribut référence des ressources
	 */
	protected Ressources referenceRessources;
	
	/**
	 *Texte pour affichage
	 */
	protected String tempTexte1;
	
	/**
	 *Texte pour affichage
	 */
	protected String tempTexte2;
	
	/**
	 *Liste des Vues graphiques des cartes ingredient
	 */
	protected ArrayList<VueCarte> tempVueCartes1;
	
	/**
	 *Liste des Vues graphiques des cartes Champs
	 */
	protected ArrayList<VueCarte> tempVueCartes2;
	
	/**
	 *Liste des Vues graphiques des cartes Comptage de Points
	 */
	protected ArrayList<VueCarte> tempVueCartes3;
	
	/**
	 *Liste des Vues graphiques des cartes Chiens de Gardes
	 */
	protected ArrayList<VueCarte> tempVueCartes4;
	
	/**
	 *Liste des Vues graphiques des cartes taupes géantes
	 */
	protected ArrayList<VueCarte> tempVueCartes5;

	/**
	 *Constructeur de la vue graphique des ressources de notre joueur.
	 *Seront affiché ses cartes et graines
	 */
	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean partieAvancee) {

		paquetDeRessourcesDeJoueur.addObserver(this);
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
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
		tempVueCartes1 = new ArrayList<VueCarte>();
		tempVueCartes2 = new ArrayList<VueCarte>();
		tempVueCartes3 = new ArrayList<VueCarte>();
		tempVueCartes4 = new ArrayList<VueCarte>();
		tempVueCartes5 = new ArrayList<VueCarte>();

	}

	/**
	 * @return tempVueCartes1
	 */
	public ArrayList<VueCarte> getTempVueCartes1() {
		return tempVueCartes1;
	}
	
	/**
	 * Mise à jour de tempVueCartes1
	 * 
	 * @param tempVueCartes1
	 */
	public void setTempVueCartes1(ArrayList<VueCarte> tempVueCartes1) {
		this.tempVueCartes1 = tempVueCartes1;
	}

	/**
	 * @return referencePaquetDeRessourcesDeJoueur
	 */
	public PaquetDeRessourcesDeJoueur getReferencePaquetDeRessourcesDeJoueur() {
		return referencePaquetDeRessourcesDeJoueur;
	}

	/**
	 * Mise à jour de referencePaquetDeRessourcesDeJoueur
	 * 
	 * @param referencePaquetDeRessourcesDeJoueur
	 */
	public void setReferencePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur) {
		this.referencePaquetDeRessourcesDeJoueur = referencePaquetDeRessourcesDeJoueur;
	}

}
