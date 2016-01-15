package modele;

/**
 * La classe JoueurVirtuel étend la classe Joueur principalement dans le but
 * séparer les joueurs réels et virtuels dont les stratégies différent mais
 * aussi de créer des classes instanciables.
 * 
 * @see Joueur
 */
public class JoueurVirtuel extends Joueur {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -942542504972441132L;
	/**
	 * Difficulte du joueur virtuel qui va définir sa stratégie
	 */
	private Difficulte difficulte;

	/**
	 * Il s'agit du constructeur de la classe qui ne fait que récupérer les
	 * paramétres qu'il faut pour ses attributs.
	 * 
	 * @param nom
	 *            récupère le nom du joueur.
	 * @param referencePaquetPartie
	 *            récupère une référence sur le paquet de partie.
	 * @param difficulte
	 *            récupère la difficulté du joueur virtuel.
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
	 * Il s'agit d'une surcharge du constructeur qui a le même rôle que le
	 * premier mais qui sélectionne aléatoirement la difficulté du joueur
	 * virtuel.
	 * 
	 * @param nom
	 *            récupère le nom du joueur.
	 * @param referencePaquetPartie
	 *            récupère une référence sur le paquet de partie.
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
	 * @return la difficulté du joueur virtuel.
	 */
	public Difficulte getDifficulte() {
		return difficulte;
	}

	/**
	 * Mise à jour de la difficulté du joueur virtuel
	 * 
	 * @param difficulte
	 *            récupère la nouvelle difficulté du joueur.
	 */
	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
}
