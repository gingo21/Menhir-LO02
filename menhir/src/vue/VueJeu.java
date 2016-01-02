package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Ressources.Ressources;
import modele.ParametresDePartie;
import modele.Partie;

public class VueJeu extends FenetrePrincipal implements Observer {
	
	VuePaquetDeRessourcesDePartie vuePaquetDeRessourcesDePartie;
	VuePaquetDeRessourcesDeJoueurReel vuePaquetDeRessourcesDeJoueurReel;
	VueStrategieJoueurReelGraphique vueStrategieJoueurReelGraphique;
	ArrayList<VuePaquetDeRessourcesIA> vuesPaquetDeRessourcesIA;
	Dimension[] positionsDesIA;
	Panneau panneau;
	
	public VueJeu(ParametresDePartie parametres, Partie partie, Ressources ressources) {
		super();
		
		
		positionsDesIA = new Dimension[5];
		positionsDesIA[0] = new Dimension(0,0);
		positionsDesIA[1] = new Dimension(0,0);
		positionsDesIA[2] = new Dimension(0,0);
		positionsDesIA[3] = new Dimension(0,0);
		positionsDesIA[4] = new Dimension(0,0);
		
		this.panneau = new Panneau();
		
		vuePaquetDeRessourcesDePartie = new VuePaquetDeRessourcesDePartie(parametres, ressources);
		vuePaquetDeRessourcesDeJoueurReel = new VuePaquetDeRessourcesDeJoueurReel(parametres.getJoueurReel().getPaquet(), ressources, false);
		
		this.panneau.ajoutPanneau(vuePaquetDeRessourcesDePartie, 0, 0);
		this.panneau.ajoutPanneau(vuePaquetDeRessourcesDeJoueurReel, 300, 0);
		this.add(this.panneau);
		
		Container contentframe = this.getContentPane();
		
		//panneaux
		panneau.setDoubleBuffered(true);
		contentframe.add(panneau);
		contentframe.validate();
		this.setVisible(true);
		
		
		//parametres.getPaquetDePartie().addObserver(vuePaquetDeRessourcesDePartie);
		//this.addObserversDuJeu(parametres, partie);
	}

	public void addObserversDuJeu(ParametresDePartie parametres, Partie partie) {
		partie.addObserver(this.vueStrategieJoueurReelGraphique);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
