package modele;

import java.io.Serializable;
import java.util.Observable;

/**
 * Le classe PaquetDeRessources permet de définir les aspects généraux d'un
 * paquet de ressources du jeu du Menhir notament avec les graines de Menhir.
 * Cette classe est sérialisable pour la sauvegarder dans un fichier pour les
 * paramétres. Elle hérite d'Observable car elle va notifier son utilisation à
 * ses observateurs (instances de Observer). Elle reste abstraite car elle est
 * encore trop générale.
 * 
 * On ne déclare pas encore de paquet de cartes car les structures des classes
 * héritant celle-ci vont être différentes pour cet aspect.
 * 
 * @see Partie
 */
public abstract class PaquetDeRessources extends Observable implements Serializable {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -6811472892906361246L;

	/**
	 * Nombre de graines de menhir dans le paquet
	 */
	private int grainesDeMenhir;

	/**
	 * Il s'agit du constructeur de la classe qui ne récupère que le nombre de
	 * graines.
	 *
	 * @param nombreDeGraines
	 *            récupère le nombre de graines de menhir dans le paquet.
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
	 * Mise à jour du nombre de graines dans le paquet
	 * 
	 * @param grainesDeMenhir
	 *            récupère le nombre de graines de menhir dans le paquet.
	 */
	public void setGrainesDeMenhir(int grainesDeMenhir) {
		this.grainesDeMenhir = grainesDeMenhir;
	}

	/**
	 * C'est une méthode abstraite qui impose aux classes non abstraites
	 * héritant de celle-ci d'avoir un paquet de cartes où largeur'on peut y ajouter
	 * une carte.
	 * 
	 * @param carte
	 *            récupère la carte à ranger.
	 */
	public abstract void ajouterUneCarte(Carte carte);

	/**
	 * C'est une méthode abstraite qui impose aux classes non abstraites
	 * héritant de celle-ci d'avoir un paquet de cartes que largeur'on peut afficher.
	 */
	public abstract void afficherCartes();

	/**
	 * Cette méthode permet de donner plusieurs graines de menhir à un joueur.
	 * 
	 * @param joueur
	 *            récupère le joueur à qui il faut donner des graines (à travers
	 *            son paquet)
	 * @param nombre
	 *            récupère le nombre de graines de menhir dans le paquet.
	 */
	public void donnerDesGrainesDeMenhir(Joueur joueur, int nombre) {
		this.setGrainesDeMenhir(this.getGrainesDeMenhir() - nombre);
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempPaquetJoueur.setGrainesDeMenhir(tempPaquetJoueur.getGrainesDeMenhir() + nombre);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Cette méthode permet de donner une graine de menhir à la fois à un
	 * joueur.
	 * 
	 * @param joueur
	 *            récupère le joueur à qui il faut donner une graine (à travers
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
	 * Cette méthode permet à la classe Partie de pouvoir rafaîchir les
	 * observateurs du paquet quand elle le souhaite car la classe Partie
	 * contrôle le déroulement de la partie et donc des changements de paquet.
	 * 
	 * @see Partie
	 */
	public void rafraichirLesObservers() {
		this.setChanged();
		this.notifyObservers();
	}

}
