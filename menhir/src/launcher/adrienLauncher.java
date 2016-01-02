package launcher;

import java.awt.Container;

import Ressources.Ressources;
import modele.CarteTaupesGeantes;
import modele.ParametresDePartie;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneau;
import vue.VueCarteTaupesGeantes;
import vue.VuePaquetDeRessourcesDeJoueurReel;
import vue.VuePaquetDeRessourcesIA;

public class adrienLauncher {
	public static void main(String[] args) {
		
//		FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		ParametresDePartie params = new ParametresDePartie();
		Ressources r = new Ressources();
		params.getPaquetDePartie().distribuerRessourcesInitiales(params);
		VuePaquetDeRessourcesDeJoueurReel vovo = new VuePaquetDeRessourcesDeJoueurReel(params.getJoueurReel().getPaquet(),r,false,false);
		VuePaquetDeRessourcesIA vivi = new VuePaquetDeRessourcesIA(params.getJoueurReel().getPaquet(),r,false,false);

		FenetrePrincipal fen = new FenetrePrincipal();
		Container contentframe = fen.getContentPane();
		
		//panneaux
		Panneau conteneur = new Panneau();
		contentframe.add(conteneur);
		conteneur.ajoutPanneau(vivi,200,200);
		contentframe.validate();
		fen.setVisible(true);
		
		}
}
