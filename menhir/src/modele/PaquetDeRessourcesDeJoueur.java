package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * La classe PaquetDeRessourcesDeJoueur étend la classe PaquetDeRessources pour
 * définir précisemment ce qu'un paquet de joueur doit avoir avec notamment des
 * listes de cartes
 * 
 * @see PaquetDeRessources
 */
public class PaquetDeRessourcesDeJoueur extends PaquetDeRessources {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = -4583727989191743754L;

	/**
	 * Il s'agit des listes de cartes qui sont rangés dans une Map avec des noms
	 * qui sont associés à leur type. On utilise des listes car on veut que les
	 * cartes soient ordonnées, et on veut pouvoir accéder à toutes les cartes.
	 */
	private HashMap<String, ArrayList<Carte>> paquetsDeCartes;
	/**
	 * Référence au paquet de partie pour intéragir avec lui
	 */
	private PaquetDeRessourcesDePartie referencePaquetPartie;
	/**
	 * Joueur auquel est associé ce paquet
	 */
	private Joueur joueur;

	/**
	 * Il s'agit du constructeur de la classe qui va principalement construire
	 * les listes de cartes du joueur en plus des autres attributs.
	 * 
	 * @param joueur
	 *            récupère le joueur auquel est associé le paquet.
	 * @param referencePaquetPartie
	 *            récupère la référence au paquet de partie.
	 */
	public PaquetDeRessourcesDeJoueur(Joueur joueur, PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(0);
		setPaquetsDeCartes(new HashMap<String, ArrayList<Carte>>());
		ArrayList<Carte> tempCartes1 = new ArrayList<Carte>();
		ArrayList<Carte> tempCartes2 = new ArrayList<Carte>();
		ArrayList<Carte> tempCartes3 = new ArrayList<Carte>();
		ArrayList<Carte> tempCartes4 = new ArrayList<Carte>();
		ArrayList<Carte> tempCartes5 = new ArrayList<Carte>();
		this.getPaquetsDeCartes().put("Cartes Ingredients", tempCartes1);
		this.getPaquetsDeCartes().put("Cartes Champs", tempCartes2);
		this.getPaquetsDeCartes().put("Cartes Comptage De Points", tempCartes3);
		this.getPaquetsDeCartes().put("Cartes Taupes Geantes", tempCartes4);
		this.getPaquetsDeCartes().put("Cartes Chiens De Garde", tempCartes5);

		this.joueur = joueur;
		this.setReferencePaquetPartie(referencePaquetPartie);
	}

	/**
	 * @return le joueur auquel est associé le paquet.
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Mise à jour du joueur auquel est associé le paquet.
	 * 
	 * @param joueur
	 *            récupère le joueur.
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * @return la carte comptage de point du paquet.
	 */
	public Carte getCarteComptageDePoint() {
		return this.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0);
	}

	/**
	 * @return la carte champ du paquet.
	 */
	public CarteChamp getCarteChamp() {
		return (CarteChamp) this.getPaquetsDeCartes().get("Cartes Champs").get(0);
	}

	/**
	 * @return la référence au paquet de partie.
	 */
	public PaquetDeRessourcesDePartie getReferencePaquetPartie() {
		return referencePaquetPartie;
	}

	/**
	 * Mise à jour de la référence au paquet de partie.
	 * 
	 * @param referencePaquetPartie
	 *            récupère la référence au paquet de partie.
	 */
	public void setReferencePaquetPartie(PaquetDeRessourcesDePartie referencePaquetPartie) {
		this.referencePaquetPartie = referencePaquetPartie;
	}

	/**
	 * @return la map des listes de cartes.
	 */
	public HashMap<String, ArrayList<Carte>> getPaquetsDeCartes() {
		return paquetsDeCartes;
	}

	/**
	 * Mise à jour de la map des listes de cartes.
	 * 
	 * @param paquetsDeCartes
	 *            récupère la map des listes de cartes.
	 */
	public void setPaquetsDeCartes(HashMap<String, ArrayList<Carte>> paquetsDeCartes) {
		this.paquetsDeCartes = paquetsDeCartes;
	}

	/**
	 * Il s'agit de l'implémentation d'une méthode de PaquetDeRessources pour
	 * ajouter une carte dans la map des listes de cartes.
	 */
	public void ajouterUneCarte(Carte carte) {
		if (carte instanceof CarteIngredient) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes().get("Cartes Ingredients");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChamp) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes().get("Cartes Champs");
			tempCarte.add(carte);
		} else if (carte instanceof CarteComptageDePoints) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes().get("Cartes Comptage De Points");
			tempCarte.add(carte);
		} else if (carte instanceof CarteTaupesGeantes) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes().get("Cartes Taupes Geantes");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChiensDeGarde) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes().get("Cartes Chiens De Garde");
			tempCarte.add(carte);
		}
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Cette méthode permet, uniquement en mode console, d'afficher les cartes
	 * du paquet.
	 */
	public void afficherCartes() {
		Set<String> tempCles = this.getPaquetsDeCartes().keySet();
		for (Iterator<String> it = tempCles.iterator(); it.hasNext();) {
			ArrayList<Carte> tempCartes = this.getPaquetsDeCartes().get(it.next());
			for (Iterator<Carte> yt = tempCartes.iterator(); yt.hasNext();) {
				Carte tempCarte = yt.next();
				if (tempCarte.isEstUtilise() == false)
					System.out.println(tempCarte.toString());

			}
		}
	}

}
