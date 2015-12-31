package launcher;

import java.awt.Container;

import modele.Carte;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;
import vue.FenetreInitialisation;
import vue.FenetrePrincipal;
import vue.Panneaux;
import vue.VueCarte;
import vue.VueCarteIngredient;
import vue.VueCarteTaupesGeantes;

public class francoisLauncher {
	public static void main(String[] args) {
		//test
		FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		// tempCarte = new CarteIngredient("didadidadou");
		//VueCarteIngredient maCarte = new VueCarteIngredient(tempCarte);
		CarteTaupesGeantes tempCarte = new CarteTaupesGeantes("didadidadou");
		VueCarteTaupesGeantes maCarte = new VueCarteTaupesGeantes(tempCarte);

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
