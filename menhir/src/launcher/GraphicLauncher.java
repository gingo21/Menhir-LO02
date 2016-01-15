package launcher;

import modele.ParametresDePartie;
import vue.FenetreInitialisation;

/**
 * La classe GraphicLauncher permet de lancer le jeu du menhir en mode graphique
 * avec la librairie Swing. Elle se lance avec un Thread et ne fait que cr�er la
 * partie et la fen�tre d'initialisation.
 * 
 * @see Jeu
 */
public class GraphicLauncher implements Runnable {

	/**
	 * Cet attribut r�cup�re les parametres de la partie.
	 * 
	 * @see modele.ParametresDePartie
	 */
	private ParametresDePartie parametresDePartie;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param parametresDePartie
	 *            r�cup�re une r�f�rence sur les param�tres de partie.
	 */
	public GraphicLauncher(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	/**
	 * Il s'agit de la m�thode permettant de lancer le jeu du menhir en mode
	 * graphique, elle ne fait que lancer la fen�tre d'initialisation.
	 * 
	 * @see vue.FenetreInitialisation
	 */
	public void run() {
		FenetreInitialisation fenetreInitialisation = new FenetreInitialisation(this.parametresDePartie);
		fenetreInitialisation.setVisible(true);
	}

	/**
	 * @return la r�f�rence aux param�tres de partie.
	 */
	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	/**
	 * Cette m�thode permet une mise � jour de la r�f�rence sur les param�tres
	 * de partie.
	 * 
	 * @param parametresDePartie
	 *            r�cup�re une r�f�rence sur les param�tres de partie.
	 */
	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}
}
