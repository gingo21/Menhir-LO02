package vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.sun.prism.Graphics;

import Ressources.Ressources;
import modele.Carte;
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
	
	private ParametresDePartie referenceParametres;

	public final static Color COULEUR_DE_FOND = new Color(80, 190, 80);

	public VueJeu(ParametresDePartie parametres, Partie partie, Ressources ressources) {
		super();
		partie.addObserver(this);
		referenceParametres = parametres;
		this.positionsDesIA = new Dimension[5];
		this.positionsDesIA[0] = new Dimension(0, 0);
		this.positionsDesIA[1] = new Dimension(0, 250);
		this.positionsDesIA[2] = new Dimension(0, 500);
		this.positionsDesIA[3] = new Dimension(870, 0);
		this.positionsDesIA[4] = new Dimension(870, 190);

		this.panneau = new Panneau();
		boolean avancee = false;
		if (parametres.getTypePartie() == StatutPartie.avancee) {
			avancee = true;
		}

		this.vuePaquetDeRessourcesDePartie = new VuePaquetDeRessourcesDePartie(parametres, ressources);
		this.vuePaquetDeRessourcesDePartie.setBackground(COULEUR_DE_FOND);
		this.vuePaquetDeRessourcesDeJoueurReel = new VuePaquetDeRessourcesDeJoueurReel(
				parametres.getJoueurReel().getPaquet(), ressources, avancee);
		this.vuePaquetDeRessourcesDeJoueurReel.setBackground(COULEUR_DE_FOND);
		this.vuesPaquetDeRessourcesIA = new ArrayList<VuePaquetDeRessourcesIA>();
		int i = 0;
		for (Iterator<Joueur> it = parametres.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur instanceof JoueurVirtuel) {
				VuePaquetDeRessourcesIA tempVuePaquet = new VuePaquetDeRessourcesIA(tempJoueur.getPaquet(), ressources,
						avancee);
				tempVuePaquet.setBackground(COULEUR_DE_FOND);
				tempVuePaquet.setBorder(BorderFactory.createLineBorder(Color.black));
				this.vuesPaquetDeRessourcesIA.add(tempVuePaquet);
				this.panneau.ajoutPanneau(tempVuePaquet, this.positionsDesIA[i].width, this.positionsDesIA[i].height);
				i++;
			}
		}
		this.vueStrategieJoueurReelGraphique = new VueStrategieJoueurReelGraphique(
				(StrategieJoueurReelGraphique) parametres.getJoueurReel().getStrategie(), ressources,
				this.vuePaquetDeRessourcesDeJoueurReel, this.vuesPaquetDeRessourcesIA);
		this.vueStrategieJoueurReelGraphique.setBackground(COULEUR_DE_FOND);
		this.vueStrategieJoueurReelGraphique.setBorder(BorderFactory.createLineBorder(Color.black));

		this.panneau.ajoutPanneau(this.vuePaquetDeRessourcesDePartie, 350, 0);
		this.panneau.ajoutPanneau(this.vuePaquetDeRessourcesDeJoueurReel,
				this.DIMENSION_ECRAN.width - this.vuePaquetDeRessourcesDeJoueurReel.getPreferredSize().width,
				this.DIMENSION_ECRAN.height - this.vuePaquetDeRessourcesDeJoueurReel.getPreferredSize().height);
		this.panneau.ajoutPanneau(this.vueStrategieJoueurReelGraphique, 350,
				this.vuePaquetDeRessourcesDePartie.getSize().height);
		this.add(this.panneau);

		Container contentframe = this.getContentPane();

		// panneaux
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
		for (Iterator<Joueur> it = parametres.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur instanceof JoueurVirtuel) {
				tempJoueur.getStrategie().addObserver(this.vueStrategieJoueurReelGraphique);
			}
		}
		for (Iterator<Carte> it = parametres.getPaquetDePartie().getPaquetsDeCartes().get("Cartes Ingredients")
				.iterator(); it.hasNext();) {
			it.next().addObserver(this.vueStrategieJoueurReelGraphique);
		}
		parametres.getPaquetDePartie().addObserver(this.vueStrategieJoueurReelGraphique);
	}

	public void update(Observable o, Object arg) {
		if(arg != null) {
			if(arg.toString().contains("Changement de manche : ")) {
				for (Iterator<Carte> it = this.referenceParametres.getPaquetDePartie().getPaquetsDeCartes().get("Cartes Ingredients")
						.iterator(); it.hasNext();) {
					it.next().deleteObservers();
				}
			} else if (arg.toString().contains("nouveau paquet")) {
				this.vuePaquetDeRessourcesDePartie.changementDePaquet();
				this.vueStrategieJoueurReelGraphique.changementDePaquet(this.referenceParametres.getPaquetDePartie());
				for (Iterator<Carte> it = this.referenceParametres.getPaquetDePartie().getPaquetsDeCartes().get("Cartes Ingredients")
						.iterator(); it.hasNext();) {
					it.next().addObserver(this.vueStrategieJoueurReelGraphique);
					System.out.println("John");
				}
			}
		}

	}

}
