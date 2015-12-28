package launcher;

import java.awt.Container;

import modèle.Carte;
import modèle.CarteIngredient;
import vue.FenetreInitialisation;
import vue.VueCarte;

public class GraphicLauncher {
	public static void main(String[] args) {
		//test
		FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
		
		Carte tempCarte = new CarteIngredient("didadidadou");
		VueCarte maCarte = new VueCarte("/Ressources/bleu.jpg", tempCarte);
		Container contentframe = dialog.getContentPane();
		contentframe.add(maCarte);
		contentframe.validate();
		dialog.setVisible(true);
		
		

		
	}

}
