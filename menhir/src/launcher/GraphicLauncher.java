package launcher;

import java.awt.Container;
import java.io.FileWriter;
import java.io.IOException;

import modele.Carte;
import modele.CarteIngredient;
import vue.FenetreInitialisation;
import vue.VueCarte;

public class GraphicLauncher implements Runnable{

	public void run() {
				FenetreInitialisation dialog = new FenetreInitialisation(null,"conf",true);
				
				Carte tempCarte = new CarteIngredient("didadidadou");
				VueCarte maCarte = new VueCarte("/Ressources/bleu.jpg", tempCarte);
				Container contentframe = dialog.getContentPane();
				contentframe.add(maCarte);
				contentframe.validate();
				dialog.setVisible(true);
	}

}
