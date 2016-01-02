package vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.sun.prism.Graphics;

import Ressources.Ressources;
import modele.ParametresDePartie;
import modele.Partie;
import modele.StrategieJoueurReelGraphique;

public class VueJeu extends FenetrePrincipal implements Observer {
	
	VuePaquetDeRessourcesDePartie vuePaquetDeRessourcesDePartie;
	VuePaquetDeRessourcesDeJoueurReel vuePaquetDeRessourcesDeJoueurReel;
	VueStrategieJoueurReelGraphique vueStrategieJoueurReelGraphique;
	ArrayList<VuePaquetDeRessourcesIA> vuesPaquetDeRessourcesIA;
	Dimension[] positionsDesIA;
	Panneau panneau;
	
	public VueJeu(ParametresDePartie parametres, Partie partie, Ressources ressources) {
		super();
		
		this.positionsDesIA = new Dimension[5];
		this.positionsDesIA[0] = new Dimension(0,0);
		this.positionsDesIA[1] = new Dimension(0,0);
		this.positionsDesIA[2] = new Dimension(0,0);
		this.positionsDesIA[3] = new Dimension(0,0);
		this.positionsDesIA[4] = new Dimension(0,0);
		
		this.panneau = new Panneau();
		
		this.vuePaquetDeRessourcesDePartie = new VuePaquetDeRessourcesDePartie(parametres, ressources);
		this.vuePaquetDeRessourcesDeJoueurReel = new VuePaquetDeRessourcesDeJoueurReel(parametres.getJoueurReel().getPaquet(), ressources, false);
		this.vueStrategieJoueurReelGraphique = new VueStrategieJoueurReelGraphique((StrategieJoueurReelGraphique) parametres.getJoueurReel().getStrategie(), ressources) ;
		
		this.panneau.ajoutPanneau(vuePaquetDeRessourcesDePartie, 400, 0);
		this.panneau.ajoutPanneau(vuePaquetDeRessourcesDeJoueurReel, 600, 500);
		this.panneau.ajoutPanneau(vueStrategieJoueurReelGraphique, 400, 300);
		this.add(this.panneau);
		
		this.getContentPane().setBackground(new Color(0,255,0));
		Container contentframe = this.getContentPane();
		
		//panneaux
		this.panneau.setDoubleBuffered(true);
		this.panneau.setBackground(new Color(70,200,70));
		this.vuePaquetDeRessourcesDePartie.setBackground(new Color(70,200,70));
		contentframe.add(panneau);
		contentframe.validate();
		this.setVisible(true);
		
		
		this.addObserversDuJeu(parametres, partie);
	}

	public void addObserversDuJeu(ParametresDePartie parametres, Partie partie) {
		partie.addObserver(this.vueStrategieJoueurReelGraphique);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
