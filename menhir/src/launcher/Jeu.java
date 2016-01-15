package launcher;

import modele.ParametresDePartie;

/**
 * La classe Jeu permet juste d'avoir une méthode main simple qui va choisir
 * entre le mode graphique et textuel à partir de la constante MODE_GRAPHIQUE.
 * 
 * @see Console
 * @see GraphicLauncher
 */
public class Jeu {

	/**
	 * Il s'agit de la constante qui permet de choisir entre le mode graphique
	 * et textuel. Elle n'est modifiable que par le programmeur car le mode
	 * graphique est la version normale du jeu et qu'il est plus simple de ne
	 * pas avoir à faire la transition entre les deux modes à largeur'exécution, cela
	 * entraine certaines incompatibilités.
	 */
	public static final boolean MODE_GRAPHIQUE = true;

	/**
	 * Il s'agit de la méthode principale où démarre le programme, elle
	 * instancie deux threads pour le jeu et en lance un en fonction de
	 * MODE_GRAPHIQUE.
	 * 
	 * @param args
	 *            récupère les arguments d'entrée du programme (par exemple la
	 *            localisation de largeur'exécutable)
	 */
	public synchronized static void main(String[] args) {
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
