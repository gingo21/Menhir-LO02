package launcher;

import java.awt.Container;
import java.io.FileWriter;
import java.io.IOException;

import modele.Carte;
import modele.CarteIngredient;
import modele.ParametresDePartie;
import vue.FenetreInitialisation;
import vue.VueCarte;
import vue.VueParametres;

public class GraphicLauncher implements Runnable {

	private ParametresDePartie parametresDePartie;

	public GraphicLauncher(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	public void run() {
		VueParametres dialog = new VueParametres(null, "conf", true, parametresDePartie);

		Carte tempCarte = new CarteIngredient("didadidadou");
		VueCarte maCarte = new VueCarte("/Ressources/bleu.jpg", tempCarte);
		Container contentframe = dialog.getContentPane();
		contentframe.add(maCarte);
		contentframe.validate();
		dialog.setVisible(true);
	}
	
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}


	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}
}
