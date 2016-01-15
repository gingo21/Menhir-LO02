package modele;

import java.io.Serializable;
import java.util.Observable;

/**
 * Le classe PaquetDeRessources permet de d�finir les aspects g�n�raux d'un
 * paquet de ressources du jeu du Menhir notament avec les graines de Menhir.
 * Cette classe est s�rialisable pour la sauvegarder dans un fichier pour les
 * param�tres. Elle h�rite d'Observable car elle va notifier son utilisation �
 * ses observateurs (instances de Observer). Elle reste abstraite car elle est
 * encore trop g�n�rale.
 * 
 * On ne d�clare pas encore de paquet de cartes car les structures des classes
 * h�ritant celle-ci vont �tre diff�rentes pour cet aspect.
 * 
 * @see Partie
 */
public abstract class PaquetDeRessources extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -6811472892906361246L;

	/**
	 * Nombre de graines de menhir dans le paquet
	 */
	private int grainesDeMenhir;

	/**
	 * Il s'agit du constructeur de la classe qui ne r�cup�re que le nombre de
	 * graines.
	 *
	 * @param nombreDeGraines
	 *            r�cup�re le nombre de graines de menhir dans le paquet.
	 */
	public PaquetDeRessources(int nombreDeGraines) {
		super();
		this.grainesDeMenhir = nombreDeGraines;
	}

	/**
	 * @return le nombre de graines de menhir dans le paquet.
	 */
	public int getGrainesDeMenhir() {
		return grainesDeMenhir;
	}

	/**
	 * Mise � jour du nombre de graines dans le paquet
	 * 
	 * @param grainesDeMenhir
	 *            r�cup�re le nombre de graines de menhir dans le paquet.
	 */
	public void setGrainesDeMenhir(int grainesDeMenhir) {
		this.grainesDeMenhir = grainesDeMenhir;
	}

	/**
	 * C'est une m�thode abstraite qui impose aux classes non abstraites
	 * h�ritant de celle-ci d'avoir un paquet de cartes o� largeur'on peut y ajouter
	 * une carte.
	 * 
	 * @param carte
	 *            r�cup�re la carte � ranger.
	 */
	public abstract void ajouterUneCarte(Carte carte);

	/**
	 * C'est une m�thode abstraite qui impose aux classes non abstraites
	 * h�ritant de celle-ci d'avoir un paquet de cartes que largeur'on peut afficher.
	 */
	public abstract void afficherCartes();

	/**
	 * Cette m�thode permet de donner plusieurs graines de menhir � un joueur.
	 * 
	 * @param joueur
	 *            r�cup�re le joueur � qui il faut donner des graines (� travers
	 *            son paquet)
	 * @param nombre
	 *            r�cup�re le nombre de graines de menhir dans le paquet.
	 */
	public void donnerDesGrainesDeMenhir(Joueur joueur, int nombre) {
		this.setGrainesDeMenhir(this.getGrainesDeMenhir() - nombre);
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempPaquetJoueur.setGrainesDeMenhir(tempPaquetJoueur.getGrainesDeMenhir() + nombre);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Cette m�thode permet de donner une graine de menhir � la fois � un
	 * joueur.
	 * 
	 * @param joueur
	 *            r�cup�re le joueur � qui il faut donner une graine (� travers
	 *            son paquet)
	 */
	public void donnerUneGraineDeMenhir(Joueur joueur) {
		this.setGrainesDeMenhir(this.getGrainesDeMenhir() - 1);
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempPaquetJoueur.setGrainesDeMenhir(tempPaquetJoueur.getGrainesDeMenhir() + 1);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Cette m�thode permet � la classe Partie de pouvoir rafa�chir les
	 * observateurs du paquet quand elle le souhaite car la classe Partie
	 * contr�le le d�roulement de la partie et donc des changements de paquet.
	 * 
	 * @see Partie
	 */
	public void rafraichirLesObservers() {
		this.setChanged();
		this.notifyObservers();
	}

}
