package launcher;

import modele.ParametresDePartie;

public class Jeu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParametresDePartie parametresDePartie = new ParametresDePartie();
		//Console test = new Console();
		GraphicLauncher test = new GraphicLauncher();
		Thread toto = new Thread(test);
		toto.start();
	}

}
