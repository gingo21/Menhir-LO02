package vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.sun.prism.Graphics;

import Ressources.Ressources;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.ParametresDePartie;
import modele.Partie;
import modele.StatutPartie;
import modele.StrategieJoueurReelGraphique;

public class VueJeu extends FenetrePrincipal implements Observer {
	
	private VuePaquetDeRessourcesDePartie vuePaquetDeRessourcesDePartie;
	private VuePaquetDeRessourcesDeJoueurReel vuePaquetDeRessourcesDeJoueurReel;
	private VueStrategieJoueurReelGraphique vueStrategieJoueurReelGraphique;
	private ArrayList<VuePaquetDeRessourcesIA> vuesPaquetDeRessourcesIA;
	private Dimension[] positionsDesIA;
	private Panneau panneau;
	
	public final static Color COULEUR_DE_FOND = new Color(70,200,70);
	
	public VueJeu(ParametresDePartie parametres, Partie partie, Ressources ressources) {
		super();
		
		this.positionsDesIA = new Dimension[5];
		this.positionsDesIA[0] = new Dimension(0,200);
		this.positionsDesIA[1] = new Dimension(0,400);
		this.positionsDesIA[2] = new Dimension(0,600);
		this.positionsDesIA[3] = new Dimension(550,200);
		this.positionsDesIA[4] = new Dimension(550,400);
		
		this.panneau = new Panneau();
		boolean avancee=false;
		if(parametres.getTypePartie() == StatutPartie.avancee) {
			avancee = true;
		}
		
		this.vuePaquetDeRessourcesDePartie = new VuePaquetDeRessourcesDePartie(parametres, ressources);
		this.vuePaquetDeRessourcesDePartie.setBackground(COULEUR_DE_FOND);
		this.vuePaquetDeRessourcesDeJoueurReel = new VuePaquetDeRessourcesDeJoueurReel(parametres.getJoueurReel().getPaquet(), ressources, avancee);
		this.vuePaquetDeRessourcesDeJoueurReel.setBackground(COULEUR_DE_FOND);
		this.vueStrategieJoueurReelGraphique = new VueStrategieJoueurReelGraphique((StrategieJoueurReelGraphique) parametres.getJoueurReel().getStrategie(), ressources, vuePaquetDeRessourcesDeJoueurReel) ;
		this.vueStrategieJoueurReelGraphique.setBackground(COULEUR_DE_FOND);
		int i=0;
		for(Iterator<Joueur> it = parametres.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if(tempJoueur instanceof JoueurVirtuel) {
				VuePaquetDeRessourcesIA tempVuePaquet = new VuePaquetDeRessourcesIA(tempJoueur.getPaquet(), ressources ,avancee);
				if(tempVuePaquet == null) {if(tempVuePaquet == null) {System.out.println("Problème");}}
				else {System.out.println(tempVuePaquet.getClass().getName());}
				this.vuesPaquetDeRessourcesIA.add(tempVuePaquet);
				this.vuesPaquetDeRessourcesIA.get(i).setBackground(COULEUR_DE_FOND);
				this.panneau.ajoutPanneau(tempVuePaquet, this.positionsDesIA[i].width, this.positionsDesIA[i].height);
				i++;
			}
		}
		
		this.panneau.ajoutPanneau(this.vuePaquetDeRessourcesDePartie, 350, 0);
		this.panneau.ajoutPanneau(this.vuePaquetDeRessourcesDeJoueurReel, this.DIMENSION_ECRAN.width - this.vuePaquetDeRessourcesDeJoueurReel.getPreferredSize().width, this.DIMENSION_ECRAN.height - this.vuePaquetDeRessourcesDeJoueurReel.getPreferredSize().height);
		this.panneau.ajoutPanneau(this.vueStrategieJoueurReelGraphique, 350, this.vuePaquetDeRessourcesDePartie.getSize().height);
		this.add(this.panneau);
		
		Container contentframe = this.getContentPane();
		
		//panneaux
		this.panneau.setDoubleBuffered(true);
		this.panneau.setBackground(COULEUR_DE_FOND);
		contentframe.add(panneau);
		contentframe.validate();
		this.setVisible(true);
		
		this.addObserversDuJeu(parametres, partie);
		parametres.getPaquetDePartie().addObserver(vuePaquetDeRessourcesDeJoueurReel);
	}

	public void addObserversDuJeu(ParametresDePartie parametres, Partie partie) {
		partie.addObserver(this.vueStrategieJoueurReelGraphique);
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
