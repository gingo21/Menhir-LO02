package launcher;

import modele.ParametresDePartie;

public class Jeu {

	public static final boolean MODE_GRAPHIQUE = true;

	public synchronized static void main(String[] args) throws InterruptedException {
		ParametresDePartie parametresDePartie = new ParametresDePartie();
		Console console = new Console(parametresDePartie);
		GraphicLauncher graphicLauncher = new GraphicLauncher(parametresDePartie);
		Thread thrGaphicLauncher = new Thread(graphicLauncher);
		Thread thrConsole = new Thread(console);
		if (MODE_GRAPHIQUE) {
			thrGaphicLauncher.start();
		} else {
			thrConsole.start();
		}
	}

}
