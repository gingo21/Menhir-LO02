package modele;

import launcher.Console;
import java.io.Serializable;
import java.util.Observable;

/**
 * La classe Strategie est la classe qui définit de manière général ce que peut
 * et doit savoir faire un joueur au cour d'une partie durant et en dehors de
 * son tour. Cette classe est sérialisable pour la sauvegarder dans un fichier
 * pour les paramétres. Elle hérite d'Observable car elle va notifier son
 * utilisation à ses observateurs (instances de Observer). Elle reste abstraite
 * car elle est encore trop générale.
 */
public abstract class Strategie extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -871033957513334014L;

	/**
	 * Cette attribut indique si le joueur veut une carte alliée (en partie
	 * avancée) au début de manche, par défaut la valeur est fausse.
	 */
	private boolean choixCarteAlliee;
	/**
	 * Référence au joueur utilisant cette stratégie.
	 */
	private Joueur referenceJoueur;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param referenceJoueur
	 *            récupère une référence sur le joueur utilisant cette
	 *            stratégie.
	 */
	public Strategie(Joueur referenceJoueur) {
		super();
		choixCarteAlliee = false;
		this.referenceJoueur = referenceJoueur;
	}

	/**
	 * @return la référence sur le joueur utilisant cette stratégie.
	 */
	public Joueur getReferenceJoueur() {
		return referenceJoueur;
	}

	/**
	 * Mise à jour du joueur utilisant cette stratégie.
	 * 
	 * @param referenceJoueur
	 *            récupère la référence du joueur.
	 */
	public void setReferenceJoueur(Joueur referenceJoueur) {
		this.referenceJoueur = referenceJoueur;
	}

	/**
	 * @return si oui ou non le joueur utilisant la stratégie veut une carte
	 *         alliée.
	 */
	public boolean isChoixCarteAlliee() {
		return choixCarteAlliee;
	}

	/**
	 * Mise à jour de si oui ou non le joueur utilisant la stratégie veut une
	 * carte alliée.
	 * 
	 * @param choixCarteAlliee
	 *            récupère le choix.
	 */
	public void setChoixCarteAlliee(boolean choixCarteAlliee) {
		this.choixCarteAlliee = choixCarteAlliee;
	}

	/**
	 * Cette méthode abstraite impose aux stratégies instanciables de permettre
	 * au joueur (de la stratégie) de savoir jouer son tour.
	 * 
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @param parametresDePartie
	 *            récupère les paramétres de la partie.
	 */
	public abstract void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie);

	/**
	 * Cette méthode abstraite impose aux stratégies instanciables de permettre
	 * au joueur (de la stratégie) de savoir se défendre contre une attaque.
	 * 
	 * @param parametresDePartie
	 *            récupère les paramétres de la partie.
	 * @param destinataire
	 *            récupère le joueur destinataire de la défense (joueur associé
	 *            à cette stratégie).
	 * @param acteur
	 *            récupère le joueur acteur de la défense (joueur associé à
	 *            cette stratégie).
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 * @param puissance
	 *            récupère la puissance de largeur'attaque subie.
	 * @return la puissance modifiée par le défense de largeur'attaque.
	 */
	public abstract int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance);

	/**
	 * Cette méthode abstraite impose aux stratégies instanciables de permettre
	 * au joueur (de la stratégie) de savoir attaquer un autre joueur.
	 * 
	 * @param parametresDePartie
	 *            récupère les paramétres de la partie.
	 * @param destinataire
	 *            récupère le joueur destinataire de la défense (joueur associé
	 *            à cette stratégie).
	 * @param acteur
	 *            récupère le joueur acteur de la défense (joueur associé à
	 *            cette stratégie).
	 * @param saisonActuelle
	 *            récupère la saison actuelle de la partie en cours.
	 */
	public abstract void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle);

	/**
	 * Cette méthode abstraite impose aux stratégies instanciables de permettre
	 * au joueur (de la stratégie) de choisir si oui ou non il veut une carte
	 * alliée.
	 * 
	 * @param parametresDePartie
	 *            récupère les paramétres de la partie.
	 */
	public abstract void choixDeManche(ParametresDePartie parametresDePartie);

	/**
	 * Cette méthode permet à la classe Console de devenir observateur de la
	 * stratégie.
	 * 
	 * @param observer
	 *            récupère largeur'instance de la Console.
	 * 
	 * @see Console
	 */
	public void addConsoleObserver(Console observer) {
		this.addObserver(observer);
	}
}
