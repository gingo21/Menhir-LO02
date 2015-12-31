package launcher;

import java.awt.Container;

import modele.CarteTaupesGeantes;
import modele.ParametresDePartie;
import modele.StatutPartie;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneaux;
import vue.VueCarteTaupesGeantes;
import vue.VuePaquetDeRessourcesDeJoueur;
import vue.VuePaquetDeRessourcesPartie;

public class francoisLauncher {
	public static void main(String[] args) {
		//test
		//FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		ParametresDePartie params = new ParametresDePartie();
//		params.getPaquetDePartie().distribuerRessourcesInitiales(params);
		VuePaquetDeRessourcesDeJoueur vovo = new VuePaquetDeRessourcesDeJoueur(params.getJoueurReel().getPaquet());
		VuePaquetDeRessourcesPartie vuvu = new VuePaquetDeRessourcesPartie(params.getPaquetDePartie()
				,params.getNombreDeJoueurs(),params.getTypePartie()==StatutPartie.avancee);
		
		FenetrePrincipal fen = new FenetrePrincipal();
		Container contentframe = fen.getContentPane();
		
		//panneaux
		Panneaux conteneur = new Panneaux();
		contentframe.add(conteneur);
		conteneur.ajoutPanneau(vuvu,200,200);
		contentframe.validate();
		fen.setVisible(true);
		
		}
}
