package modele;

import launcher.Jeu;

/**
 * La classe JoueurReel �tend la classe Joueur principalement dans le but
 * s�parer les joueurs r�els et virtuels dont les strat�gies diff�rent mais
 * aussi de cr�er des classes instanciables.
 * 
 * @see Joueur
 */
public class JoueurReel extends Joueur {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -2406747672022623752L;

	/**
	 * Il s'agit du constructeur de la classe qui ne fait que cr�er un joueur
	 * avec une strat�gie de joueur r�el (en fonction de MODE_GRAPHIQUE)
	 * 
	 * @param nom
	 *            r�cup�re le nom du joueur
	 * @param referencePaquetPartie
	 *            r�cup�re la r�f�rence au paquet de partie
	 */
	public JoueurReel(String nom, PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie, null);
		if (Jeu.MODE_GRAPHIQUE) {
			this.setStrategie(new StrategieJoueurReelGraphique(this));
		} else {
			this.setStrategie(new StrategieJoueurReelConsole(this));
		}
	}
}
