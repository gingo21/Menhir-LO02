package launcher;

import java.awt.Container;

import modele.Carte;
import modele.CarteIngredient;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneaux;
import vue.VueCarte;

public class francoisLauncher {
	public static void main(String[] args) {
		//test
		FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		Carte tempCarte = new CarteIngredient("didadidadou");
		VueCarte maCarte = new VueCarte(tempCarte);
		FenetrePrincipal fen = new FenetrePrincipal();
		Container contentframe = fen.getContentPane();
		
		//panneaux
		Panneaux conteneur = new Panneaux();
		contentframe.add(conteneur);
		conteneur.ajoutPanneau(maCarte,200,200);
		contentframe.validate();
		fen.setVisible(true);
		
		}

}
