package launcher;

import java.awt.Container;

import Ressources.Ressources;
import modele.CarteTaupesGeantes;
import modele.ParametresDePartie;
import modele.Partie;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneau;
import vue.VueCarteTaupesGeantes;
import vue.VueJeu;
import vue.VuePaquetDeRessourcesDeJoueurReel;
import vue.VuePaquetDeRessourcesIA;

public class adrienLauncher {
	public static void main(String[] args) {
		
//		FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		ParametresDePartie params = new ParametresDePartie();
		Ressources r = new Ressources();
		Partie partie = new Partie(params, false);
		VueJeu jeu = new VueJeu(params, partie, r);
		
		Thread thrPartie = new Thread(partie);
		thrPartie.start();
		
		}
}
