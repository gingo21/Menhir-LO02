package modele;

import launcher.Console;
import java.io.Serializable;
import java.util.Observable;

/**
 * La classe Strategie est la classe qui d�finit de mani�re g�n�ral ce que peut
 * et doit savoir faire un joueur au cour d'une partie durant et en dehors de
 * son tour. Cette classe est s�rialisable pour la sauvegarder dans un fichier
 * pour les param�tres. Elle h�rite d'Observable car elle va notifier son
 * utilisation � ses observateurs (instances de Observer). Elle reste abstraite
 * car elle est encore trop g�n�rale.
 */
public abstract class Strategie extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -871033957513334014L;

	/**
	 * Cette attribut indique si le joueur veut une carte alli�e (en partie
	 * avanc�e) au d�but de manche, par d�faut la valeur est fausse.
	 */
	private boolean choixCarteAlliee;
	/**
	 * R�f�rence au joueur utilisant cette strat�gie.
	 */
	private Joueur referenceJoueur;

	/**
	 * Il s'agit du constructeur de la classe.
	 * 
	 * @param referenceJoueur
	 *            r�cup�re une r�f�rence sur le joueur utilisant cette
	 *            strat�gie.
	 */
	public Strategie(Joueur referenceJoueur) {
		super();
		choixCarteAlliee = false;
		this.referenceJoueur = referenceJoueur;
	}

	/**
	 * @return la r�f�rence sur le joueur utilisant cette strat�gie.
	 */
	public Joueur getReferenceJoueur() {
		return referenceJoueur;
	}

	/**
	 * Mise � jour du joueur utilisant cette strat�gie.
	 * 
	 * @param referenceJoueur
	 *            r�cup�re la r�f�rence du joueur.
	 */
	public void setReferenceJoueur(Joueur referenceJoueur) {
		this.referenceJoueur = referenceJoueur;
	}

	/**
	 * @return si oui ou non le joueur utilisant la strat�gie veut une carte
	 *         alli�e.
	 */
	public boolean isChoixCarteAlliee() {
		return choixCarteAlliee;
	}

	/**
	 * Mise � jour de si oui ou non le joueur utilisant la strat�gie veut une
	 * carte alli�e.
	 * 
	 * @param choixCarteAlliee
	 *            r�cup�re le choix.
	 */
	public void setChoixCarteAlliee(boolean choixCarteAlliee) {
		this.choixCarteAlliee = choixCarteAlliee;
	}

	/**
	 * Cette m�thode abstraite impose aux strat�gies instanciables de permettre
	 * au joueur (de la strat�gie) de savoir jouer son tour.
	 * 
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @param parametresDePartie
	 *            r�cup�re les param�tres de la partie.
	 */
	public abstract void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie);

	/**
	 * Cette m�thode abstraite impose aux strat�gies instanciables de permettre
	 * au joueur (de la strat�gie) de savoir se d�fendre contre une attaque.
	 * 
	 * @param parametresDePartie
	 *            r�cup�re les param�tres de la partie.
	 * @param destinataire
	 *            r�cup�re le joueur destinataire de la d�fense (joueur associ�
	 *            � cette strat�gie).
	 * @param acteur
	 *            r�cup�re le joueur acteur de la d�fense (joueur associ� �
	 *            cette strat�gie).
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 * @param puissance
	 *            r�cup�re la puissance de largeur'attaque subie.
	 * @return la puissance modifi�e par le d�fense de largeur'attaque.
	 */
	public abstract int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance);

	/**
	 * Cette m�thode abstraite impose aux strat�gies instanciables de permettre
	 * au joueur (de la strat�gie) de savoir attaquer un autre joueur.
	 * 
	 * @param parametresDePartie
	 *            r�cup�re les param�tres de la partie.
	 * @param destinataire
	 *            r�cup�re le joueur destinataire de la d�fense (joueur associ�
	 *            � cette strat�gie).
	 * @param acteur
	 *            r�cup�re le joueur acteur de la d�fense (joueur associ� �
	 *            cette strat�gie).
	 * @param saisonActuelle
	 *            r�cup�re la saison actuelle de la partie en cours.
	 */
	public abstract void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle);

	/**
	 * Cette m�thode abstraite impose aux strat�gies instanciables de permettre
	 * au joueur (de la strat�gie) de choisir si oui ou non il veut une carte
	 * alli�e.
	 * 
	 * @param parametresDePartie
	 *            r�cup�re les param�tres de la partie.
	 */
	public abstract void choixDeManche(ParametresDePartie parametresDePartie);

	/**
	 * Cette m�thode permet � la classe Console de devenir observateur de la
	 * strat�gie.
	 * 
	 * @param observer
	 *            r�cup�re largeur'instance de la Console.
	 * 
	 * @see Console
	 */
	public void addConsoleObserver(Console observer) {
		this.addObserver(observer);
	}
}
