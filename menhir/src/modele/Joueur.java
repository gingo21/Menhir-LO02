package modele;

import java.io.Serializable;

/**
 * La classe Joueur va permettre de représenter de manière général un joueur du
 * jeu du Menhir par son nom, son identité, son score, son paquet de ressource
 * et sa strategie. Cette classe est sérialisable pour la sauvegarder dans un
 * fichier pour les paramétres. Elle hérite d'Observable car elle va notifier
 * son utilisation à ses observateurs (instances de Observer). Elle reste
 * abstraite car elle est encore trop générale.
 * 
 * @see PaquetDeRessourcesDeJoueur
 * @see Strategie
 * @see Partie
 */
public abstract class Joueur implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -2743865683729876772L;

	/**
	 * Nom du joueur
	 */
	private String nom;
	/**
	 * Numéro d'dentité unique du joueur
	 */
	private int id;
	/**
	 * Score du joueur où 1 graine : 1 point, et où 1 menhir : 100 points
	 */
	private int score;
	/**
	 * Paquet de ressources du joueur
	 */
	private PaquetDeRessourcesDeJoueur paquet;
	/**
	 * Strategie de jeu du joueur qui va définir comment le joueur va jouer et
	 * donc interagir avec les entrées/sorties et la partie
	 */
	private Strategie strategie;
	/**
	 * Indique le numéro d'identité du dernier joueur instancié pour que le
	 * prochain joueur ait un numéro différent
	 */
	public static int numeroDuDernierID = 0;

	/**
	 * Il s'agit du constructeur de la classe où l'on récupère toutes les
	 * informations nécessaires aux attributs.
	 * 
	 * @param nom
	 *            récupère le nom du joueur.
	 * @param referencePaquetPartie
	 *            récupère une référence du paquet associée à la partie
	 *            uniquement pour pouvoir construire le paquet de ressources du
	 *            joueur.
	 * @param strategie
	 *            récupère la stratégie du joueur.
	 */
	public Joueur(String nom, PaquetDeRessourcesDePartie referencePaquetPartie, Strategie strategie) {
		this.nom = nom;
		this.id = numeroDuDernierID;
		numeroDuDernierID++;
		this.score = 0;
		this.strategie = strategie;

		paquet = new PaquetDeRessourcesDeJoueur(this, referencePaquetPartie);
	}

	/**
	 * @return le nom du joueur.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Mise à jour du nom du joueur
	 * 
	 * @param nom
	 *            récupère le nouveau nom du joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return l'identité unique du joueur.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return le score du joueur.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Mise à jour du score du joueur
	 * 
	 * @param score
	 *            récupère le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return le paquet de ressources du joueur.
	 */
	public PaquetDeRessourcesDeJoueur getPaquet() {
		return paquet;
	}

	/**
	 * Mise à jour du paquet de ressources du joueur
	 * 
	 * @param paquet
	 *            récupère le nouveau paquet de ressources du joueur.
	 */
	public void setPaquet(PaquetDeRessourcesDeJoueur paquet) {
		this.paquet = paquet;
	}

	/**
	 * @return la stratégie du joueur.
	 */
	public Strategie getStrategie() {
		return strategie;
	}

	/**
	 * Mise à jour de la stratégie du joueur
	 * 
	 * @param strategie
	 *            récupère la nouvelle stratégie du joueur.
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	/**
	 * Cette méthode permet en fonction du type (statut) de partie de calculer
	 * et de mettre à jour le score du joueur en comptant le nombre de graines
	 * et de menhirs qu'il a dans son paquet de ressources.
	 * 
	 * @param statutPartie
	 *            récupère le statut de la partie.
	 */
	public void score(StatutPartie statutPartie) {
		CarteChamp tempCarteChamp = (CarteChamp) this.getPaquet().getPaquetsDeCartes().get("Cartes Champs").get(0);
		if (statutPartie == StatutPartie.rapide) {
			this.score = 100 * tempCarteChamp.getMenhirAdultes() + this.getPaquet().getGrainesDeMenhir();
		} else {
			CarteComptageDePoints tempComptage = (CarteComptageDePoints) this.getPaquet().getPaquetsDeCartes()
					.get("Cartes Comptage De Points").get(0);
			this.score = 100 * tempComptage.getMenhirAdultes() + 100 * tempCarteChamp.getMenhirAdultes()
					+ this.getPaquet().getGrainesDeMenhir();
		}

	}

	/**
	 * Cette méthode permet d'afficher en chaine de caractères et de manière
	 * simple un joueur. Elle redéfinit celle de la classe étendue (ici Object).
	 */
	public String toString() {
		return "Joueur [nom=" + nom + ", id=" + id + ", score=" + score + paquet + "]";
	}
}
