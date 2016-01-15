package modele;

/**
 * La classe JoueurVirtuel �tend la classe Joueur principalement dans le but
 * s�parer les joueurs r�els et virtuels dont les strat�gies diff�rent mais
 * aussi de cr�er des classes instanciables.
 * 
 * @see Joueur
 */
public class JoueurVirtuel extends Joueur {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -942542504972441132L;
	/**
	 * Difficulte du joueur virtuel qui va d�finir sa strat�gie
	 */
	private Difficulte difficulte;

	/**
	 * Il s'agit du constructeur de la classe qui ne fait que r�cup�rer les
	 * param�tres qu'il faut pour ses attributs.
	 * 
	 * @param nom
	 *            r�cup�re le nom du joueur.
	 * @param referencePaquetPartie
	 *            r�cup�re une r�f�rence sur le paquet de partie.
	 * @param difficulte
	 *            r�cup�re la difficult� du joueur virtuel.
	 */
	public JoueurVirtuel(String nom, PaquetDeRessourcesDePartie referencePaquetPartie, Difficulte difficulte) {
		super(nom, referencePaquetPartie, null);
		if (difficulte == Difficulte.facile) {
			this.setStrategie(new StrategieFacile(this));
		} else {
			this.setStrategie(new StrategieNormale(this));
		}
		this.difficulte = difficulte;
	}

	/**
	 * Il s'agit d'une surcharge du constructeur qui a le m�me r�le que le
	 * premier mais qui s�lectionne al�atoirement la difficult� du joueur
	 * virtuel.
	 * 
	 * @param nom
	 *            r�cup�re le nom du joueur.
	 * @param referencePaquetPartie
	 *            r�cup�re une r�f�rence sur le paquet de partie.
	 */
	public JoueurVirtuel(String nom, PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie, null);
		int tempAlea = (int) Math.random() * 2;
		if (tempAlea == 0) {
			this.setStrategie(new StrategieFacile(this));
			this.difficulte = Difficulte.facile;
		} else {
			this.setStrategie(new StrategieNormale(this));
			this.difficulte = Difficulte.normale;
		}
	}

	/**
	 * @return la difficult� du joueur virtuel.
	 */
	public Difficulte getDifficulte() {
		return difficulte;
	}

	/**
	 * Mise � jour de la difficult� du joueur virtuel
	 * 
	 * @param difficulte
	 *            r�cup�re la nouvelle difficult� du joueur.
	 */
	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
}
