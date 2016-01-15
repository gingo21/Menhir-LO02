package launcher;

import modele.ParametresDePartie;
import vue.FenetreInitialisation;

/**
 * La classe GraphicLauncher permet de lancer le jeu du menhir en mode graphique
 * avec la librairie Swing. Elle se lance avec un Thread et ne fait que créer la
 * partie et la fenêtre d'initialisation.
 * 
 * @see Jeu
 */
public class GraphicLauncher implements Runnable {

	/**
	 * Cet attribut récupère les parametres de la partie.
	 * 
	 * @see modele.ParametresDePartie
	 */
	private ParametresDePartie parametresDePartie;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param parametresDePartie
	 *            récupère une référence sur les paramétres de partie.
	 */
	public GraphicLauncher(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	/**
	 * Il s'agit de la méthode permettant de lancer le jeu du menhir en mode
	 * graphique, elle ne fait que lancer la fenêtre d'initialisation.
	 * 
	 * @see vue.FenetreInitialisation
	 */
	public void run() {
		FenetreInitialisation fenetreInitialisation = new FenetreInitialisation(this.parametresDePartie);
		fenetreInitialisation.setVisible(true);
	}

	/**
	 * @return la référence aux paramétres de partie.
	 */
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	/**
	 * Cette méthode permet une mise à jour de la référence sur les paramétres
	 * de partie.
	 * 
	 * @param parametresDePartie
	 *            récupère une référence sur les paramétres de partie.
	 */
	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}
}
