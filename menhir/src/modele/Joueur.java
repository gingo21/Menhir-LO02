package modele;

import java.io.Serializable;

/**
 * La classe Joueur va permettre de repr�senter de mani�re g�n�ral un joueur du
 * jeu du Menhir par son nom, son identit�, son score, son paquet de ressource
 * et sa strategie. Cette classe est s�rialisable pour la sauvegarder dans un
 * fichier pour les param�tres. Elle h�rite d'Observable car elle va notifier
 * son utilisation � ses observateurs (instances de Observer). Elle reste
 * abstraite car elle est encore trop g�n�rale.
 * 
 * @see PaquetDeRessourcesDeJoueur
 * @see Strategie
 * @see Partie
 */
public abstract class Joueur implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -2743865683729876772L;

	/**
	 * Nom du joueur
	 */
	private String nom;
	/**
	 * Num�ro d'dentit� unique du joueur
	 */
	private int id;
	/**
	 * Score du joueur o� 1 graine : 1 point, et o� 1 menhir : 100 points
	 */
	private int score;
	/**
	 * Paquet de ressources du joueur
	 */
	private PaquetDeRessourcesDeJoueur paquet;
	/**
	 * Strategie de jeu du joueur qui va d�finir comment le joueur va jouer et
	 * donc interagir avec les entr�es/sorties et la partie
	 */
	private Strategie strategie;
	/**
	 * Indique le num�ro d'identit� du dernier joueur instanci� pour que le
	 * prochain joueur ait un num�ro diff�rent
	 */
	public static int numeroDuDernierID = 0;

	/**
	 * Il s'agit du constructeur de la classe o� l'on r�cup�re toutes les
	 * informations n�cessaires aux attributs.
	 * 
	 * @param nom
	 *            r�cup�re le nom du joueur.
	 * @param referencePaquetPartie
	 *            r�cup�re une r�f�rence du paquet associ�e � la partie
	 *            uniquement pour pouvoir construire le paquet de ressources du
	 *            joueur.
	 * @param strategie
	 *            r�cup�re la strat�gie du joueur.
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
	 * Mise � jour du nom du joueur
	 * 
	 * @param nom
	 *            r�cup�re le nouveau nom du joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return l'identit� unique du joueur.
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
	 * Mise � jour du score du joueur
	 * 
	 * @param score
	 *            r�cup�re le nouveau score du joueur.
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
	 * Mise � jour du paquet de ressources du joueur
	 * 
	 * @param paquet
	 *            r�cup�re le nouveau paquet de ressources du joueur.
	 */
	public void setPaquet(PaquetDeRessourcesDeJoueur paquet) {
		this.paquet = paquet;
	}

	/**
	 * @return la strat�gie du joueur.
	 */
	public Strategie getStrategie() {
		return strategie;
	}

	/**
	 * Mise � jour de la strat�gie du joueur
	 * 
	 * @param strategie
	 *            r�cup�re la nouvelle strat�gie du joueur.
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	/**
	 * Cette m�thode permet en fonction du type (statut) de partie de calculer
	 * et de mettre � jour le score du joueur en comptant le nombre de graines
	 * et de menhirs qu'il a dans son paquet de ressources.
	 * 
	 * @param statutPartie
	 *            r�cup�re le statut de la partie.
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
	 * Cette m�thode permet d'afficher en chaine de caract�res et de mani�re
	 * simple un joueur. Elle red�finit celle de la classe �tendue (ici Object).
	 */
	public String toString() {
		return "Joueur [nom=" + nom + ", id=" + id + ", score=" + score + paquet + "]";
	}
}
