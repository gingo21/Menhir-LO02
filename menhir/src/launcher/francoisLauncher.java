package launcher;

import java.awt.Container;

import Ressources.Ressources;
import modele.CarteTaupesGeantes;
import modele.ParametresDePartie;
import modele.StatutPartie;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneaux;
import vue.VueCarteTaupesGeantes;
import vue.VuePaquetDeRessourcesDeJoueur;
import vue.VuePaquetDeRessourcesDePartie;

public class francoisLauncher {
	public static void main(String[] args) throws InterruptedException {
		//test
		//FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		Ressources ress = new Ressources();
		ParametresDePartie params = new ParametresDePartie();
//		params.getPaquetDePartie().distribuerRessourcesInitiales(params);
		//VuePaquetDeRessourcesDeJoueur vovo = new VuePaquetDeRessourcesDeJoueur(params.getJoueurReel().getPaquet());
		VuePaquetDeRessourcesDePartie vuvu = new VuePaquetDeRessourcesDePartie(params, ress);
		
		FenetrePrincipal fen = new FenetrePrincipal();
		Container contentframe = fen.getContentPane();
		
		//panneaux
		Panneaux conteneur = new Panneaux();
		contentframe.add(conteneur);
		conteneur.ajoutPanneau(vuvu,400,400);
		contentframe.validate();
		fen.setVisible(true);
		
		Thread.sleep(1000);
		params.getPaquetDePartie().distribuerRessourcesInitiales(params);
		fen.update(fen.getGraphics());
		vuvu.update(vuvu.getGraphics());
		}
}
