package modele;

import launcher.Jeu;

/**
 * La classe JoueurReel étend la classe Joueur principalement dans le but
 * séparer les joueurs réels et virtuels dont les stratégies différent mais
 * aussi de créer des classes instanciables.
 * 
 * @see Joueur
 */
public class JoueurReel extends Joueur {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -2406747672022623752L;

	/**
	 * Il s'agit du constructeur de la classe qui ne fait que créer un joueur
	 * avec une stratégie de joueur réel (en fonction de MODE_GRAPHIQUE)
	 * 
	 * @param nom
	 *            récupère le nom du joueur
	 * @param referencePaquetPartie
	 *            récupère la référence au paquet de partie
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
