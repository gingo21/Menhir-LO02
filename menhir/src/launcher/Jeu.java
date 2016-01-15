package launcher;

import modele.ParametresDePartie;

/**
 * La classe Jeu permet juste d'avoir une m�thode main simple qui va choisir
 * entre le mode graphique et textuel � partir de la constante MODE_GRAPHIQUE.
 * 
 * @see Console
 * @see GraphicLauncher
 */
public class Jeu {

	/**
	 * Il s'agit de la constante qui permet de choisir entre le mode graphique
	 * et textuel. Elle n'est modifiable que par le programmeur car le mode
	 * graphique est la version normale du jeu et qu'il est plus simple de ne
	 * pas avoir � faire la transition entre les deux modes � largeur'ex�cution, cela
	 * entraine certaines incompatibilit�s.
	 */
	public static final boolean MODE_GRAPHIQUE = true;

	/**
	 * Il s'agit de la m�thode principale o� d�marre le programme, elle
	 * instancie deux threads pour le jeu et en lance un en fonction de
	 * MODE_GRAPHIQUE.
	 * 
	 * @param args
	 *            r�cup�re les arguments d'entr�e du programme (par exemple la
	 *            localisation de largeur'ex�cutable)
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
