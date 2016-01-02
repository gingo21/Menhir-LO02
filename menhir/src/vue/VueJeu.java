package vue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import modele.ParametresDePartie;
import modele.Partie;

public class VueJeu extends FenetrePrincipal implements Observer {
	
	VuePaquetDeRessourcesDePartie vuePaquetDeRessourcesDePartie;
	VuePaquetDeRessourcesDeJoueurReel vuePaquetDeRessourcesDeJoueurReel;
	VueStrategieJoueurReelGraphique vueStrategieJoueurReelGraphique;
	ArrayList<VuePaquetDeRessourcesIA> vuesPaquetDeRessourcesIA;
	Dimension[] poisitionsDesIA;
	
	public VueJeu(ParametresDePartie parametres, Partie partie) {
		super();
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
