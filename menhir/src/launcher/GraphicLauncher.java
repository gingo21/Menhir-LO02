package launcher;

import modele.ParametresDePartie;
import vue.FenetreInitialisation;

public class GraphicLauncher implements Runnable {

	private ParametresDePartie parametresDePartie;

	public GraphicLauncher(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	public void run() {
		ParametresDePartie parametresDePartie = new ParametresDePartie();
		FenetreInitialisation fenetreInitialisation = new FenetreInitialisation(null, "Menhir", true, parametresDePartie);
		fenetreInitialisation.setVisible(true);
	}
	
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}


	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}
}
