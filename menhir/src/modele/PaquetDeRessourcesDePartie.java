package modele;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import launcher.Console;

/**
 * La classe PaquetDeRessourcesDePartie étend la classe PaquetDeRessources pour
 * définir précisemment ce qu'un paquet de partie doit avoir avec notamment des
 * paquets de cartes
 * 
 * @see PaquetDeRessources
 */
public class PaquetDeRessourcesDePartie extends PaquetDeRessources {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes
	 * implémentant Serializable.
	 */
	private static final long serialVersionUID = 7435385869969841194L;

	/**
	 * Il s'agit des paquets de cartes qui sont rangés dans une Map avec des
	 * noms qui sont associés à leur type. On utilise des paquets car on n'a
	 * uniquement besoin de la dernière carte (à la fois) mis dans le paquet
	 * pour la distribution de celles-ci.
	 */
	private HashMap<String, Stack<Carte>> paquetsDeCartes;

	/**
	 * Il s'agit du constructeur de la classe qui va principalement construire
	 * les paquets de cartes du joueur en plus des autres attributs.
	 * 
	 * @param statutPartie
	 *            récupère le type de partie en cours.
	 * @param nombreDeJoueurs
	 *            récupère le nombre de joueurs dans la partie en cours.
	 */
	public PaquetDeRessourcesDePartie(StatutPartie statutPartie, int nombreDeJoueurs) {
		super(30 * nombreDeJoueurs);
		paquetsDeCartes = new HashMap<String, Stack<Carte>>();
		Stack<Carte> tempCartes1 = new Stack<Carte>();
		Stack<Carte> tempCartes2 = new Stack<Carte>();
		Stack<Carte> tempCartes3 = new Stack<Carte>();
		Stack<Carte> tempCartes4 = new Stack<Carte>();
		Stack<Carte> tempCartes5 = new Stack<Carte>();
		this.paquetsDeCartes.put("Cartes Ingredients", tempCartes1);
		this.paquetsDeCartes.put("Cartes Champs", tempCartes2);
		this.paquetsDeCartes.put("Cartes Comptage De Points", tempCartes3);
		this.paquetsDeCartes.put("Cartes Taupes Geantes", tempCartes4);
		this.paquetsDeCartes.put("Cartes Chiens De Garde", tempCartes5);

		for (int i = 0; i < 4 * nombreDeJoueurs; i++) {
			String tempNames[] = { "Chant de Sirène", "Esprit de Dolmen", "Fontaine Eau Pure", "Larmes De Dryade",
					"Poudre Or", "Racines Arc En Ciel", "Rayon De Lune", "Rires de Fees" };
			int selectName = (int) (Math.random() * (8 - 0));
			Carte tempCarte = new CarteIngredient(tempNames[selectName]);
			this.ajouterUneCarte(tempCarte);
		}
		for (int i = 0; i < nombreDeJoueurs; i++) {
			String tempName = "champ" + i;
			Carte tempCarte = new CarteChamp(tempName);
			this.ajouterUneCarte(tempCarte);
		}
		if (statutPartie == StatutPartie.avancee) {
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempName = "points" + i;
				Carte tempCarte = new CarteComptageDePoints(tempName);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "Taupinators", "Totors", "Taupastort" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteTaupesGeantes(tempNames[selectName]);
				this.ajouterUneCarte(tempCarte);
			}
			for (int i = 0; i < nombreDeJoueurs; i++) {
				String tempNames[] = { "ChiensChiens", "Chiouahahs", "BigBulls" };
				int selectName = (int) (Math.random() * (3 - 0));
				Carte tempCarte = new CarteChiensDeGarde(tempNames[selectName]);
				this.ajouterUneCarte(tempCarte);
			}
		}
	}

	/**
	 * Cette méthode permet de donner à un joueur (à son paquet) une carte de la
	 * map des paquets de carets.
	 * 
	 * @param joueur
	 *            récupère le joueur destiné à recevoir la carte.
	 * @param cleDeTypeDeCarte
	 *            récupère la clé de carte qui permet d'identifier son type dans
	 *            la map.
	 */
	public void donnerUneCarteAuJoueur(Joueur joueur, String cleDeTypeDeCarte) {
		Carte tempCarte = this.paquetsDeCartes.get(cleDeTypeDeCarte).pop();
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempCarte.setEstUtilise(false);
		tempPaquetJoueur.ajouterUneCarte(tempCarte);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Il s'agit de l'implémentation d'une méthode de PaquetDeRessources pour
	 * ajouter une carte dans la map des paquets de cartes.
	 */
	public void ajouterUneCarte(Carte carte) {
		if (carte instanceof CarteIngredient) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Ingredients");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChamp) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Champs");
			tempCarte.add(carte);
		} else if (carte instanceof CarteComptageDePoints) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Comptage De Points");
			tempCarte.add(carte);
		} else if (carte instanceof CarteTaupesGeantes) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Taupes Geantes");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChiensDeGarde) {
			Stack<Carte> tempCarte = (Stack<Carte>) this.paquetsDeCartes.get("Cartes Chiens De Garde");
			tempCarte.add(carte);
		}
	}

	/**
	 * Cette méthode permet, uniquement en mode console, d'afficher les cartes
	 * du paquet.
	 */
	public void afficherCartes() {
		Set<String> tempCles = this.paquetsDeCartes.keySet();
		for (Iterator<String> it = tempCles.iterator(); it.hasNext();) {
			String cle = it.next();
			System.out.println(cle);
			Stack<Carte> tempCartes = this.paquetsDeCartes.get(cle);
			for (Iterator<Carte> yt = tempCartes.iterator(); yt.hasNext();) {
				System.out.println(yt.next().toString());
			}
		}
	}

	/**
	 * Cette méthode est utilisée dans la classe Partie pour distribuer les
	 * ressources aux joueurs à chaque début de manche.
	 * 
	 * @param parametresDePartie
	 *            récupère les paramétres de partie.
	 */
	public void distribuerRessourcesInitiales(ParametresDePartie parametresDePartie) {
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			// on supprime la carte alliée
			tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Chiens De Garde").clear();
			tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Taupes Geantes").clear();
			// et les cartes ingrédients, on ne sait jamais
			tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Ingredients").clear();
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Ingredients");
			if (tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
				this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Champs");
			} else if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
				CarteChamp tempCarteChamp = (CarteChamp) tempJoueur.getPaquet().getPaquetsDeCartes()
						.get("Cartes Champs").get(0);
				CarteComptageDePoints tempCarteComptage = (CarteComptageDePoints) tempJoueur.getPaquet()
						.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0);

				tempCarteComptage.rajouterMenhirs(tempCarteChamp.getMenhirAdultes());
				tempCarteChamp.setMenhirAdultes(0);
			}

			if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
				if (tempJoueur.getPaquet().getPaquetsDeCartes().get("Cartes Comptage De Points").isEmpty()) {
					this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Comptage De Points");
				}
				tempJoueur.getStrategie().choixDeManche(parametresDePartie);
				if (tempJoueur.getStrategie().isChoixCarteAlliee()) {
					int tempAlea = (int) (Math.random() * 2);
					if (tempAlea == 1) {
						this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Taupes Geantes");
					} else {
						this.donnerUneCarteAuJoueur(tempJoueur, "Cartes Chiens De Garde");
					}
					tempJoueur.getStrategie().setChoixCarteAlliee(false);
				} else {
					this.donnerUneGraineDeMenhir(tempJoueur);
					this.donnerUneGraineDeMenhir(tempJoueur);
				}
			} else {
				this.donnerUneGraineDeMenhir(tempJoueur);
				this.donnerUneGraineDeMenhir(tempJoueur);
			}

			this.setChanged();
			this.notifyObservers("Le joueur " + tempJoueur.getNom() + " a recu ses ressources");
		}

	}

	/**
	 * Cette méthode recréée un nouveau paquet de ressources de partie pour une
	 * nouvelle manche car cette méthode est plus pratique étant donné que l'on
	 * créé un paquet qui sera assurément mélangé dès sa création alors que la
	 * récupération des cartes auprès des joueurs nécaissiterait une méthode de
	 * mélange, ce qui n'est pas pratique.
	 * 
	 * @param parametres
	 *            récupère les paramétres de la partie.
	 * @param nouveauPaquet
	 *            récupère le nouveau paquet.
	 */
	public void reprendreToutesLesCartes(ParametresDePartie parametres, PaquetDeRessourcesDePartie nouveauPaquet) {
		this.deleteObservers();
		for (Iterator<Joueur> it = parametres.getListeJoueurs().iterator(); it.hasNext();) {
			it.next().getPaquet().setReferencePaquetPartie(nouveauPaquet);
		}
		parametres.getPaquetDePartie().distribuerRessourcesInitiales(parametres);
	}

	/**
	 * Cette méthode permet à la classe Console de devenir observateur de toutes
	 * les cartes.
	 * 
	 * @param observer
	 *            récupère l'instance de la Console.
	 * 
	 * @see Console
	 */
	public void addConsoleObserver(Console observer) {
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Ingredients").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Champs").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Comptage De Points").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		for (Iterator<Carte> it = this.paquetsDeCartes.get("Cartes Taupes Geantes").iterator(); it.hasNext();) {
			it.next().addObserver(observer);
		}
		this.addObserver(observer);
	}
	/**
	 * @return le nombre de cartes utilisées en uniquement en partie avancée que
	 *         détient le paquet.
	 */
	public int getNombreCartesAvancees() {
		int tempValeur = 0;
		if (this.paquetsDeCartes.get("Cartes Taupes Geantes") != null) {
			tempValeur += this.paquetsDeCartes.get("Cartes Taupes Geantes").size();
		}
		if (this.paquetsDeCartes.get("Cartes Chiens De Garde") != null) {
			tempValeur += this.paquetsDeCartes.get("Cartes Chiens De Garde").size();
		}
		if (this.paquetsDeCartes.get("Cartes Comptage de Points") != null) {
			tempValeur += this.paquetsDeCartes.get("Cartes Comptage de Points").size();
		}
		return tempValeur;
	}

	/**
	 * @return la map des paquets de cartes.
	 */
	public HashMap<String, Stack<Carte>> getPaquetsDeCartes() {
		return paquetsDeCartes;
	}

	/**
	 * Mise à jour de la map des paquets de cartes
	 * 
	 * @param paquetsDeCartes
	 *            récupère la nouvelle ma pdes paquets de cartes.
	 */
	public void setPaquetsDeCartes(HashMap<String, Stack<Carte>> paquetsDeCartes) {
		this.paquetsDeCartes = paquetsDeCartes;
	}

	/**
	 * @return le nombre de cartes utilisées en partie normale que détient le
	 *         paquet.
	 */
	public int getNombreCartesNormales() {
		return (this.paquetsDeCartes.get("Cartes Ingredients").size()
				+ this.paquetsDeCartes.get("Cartes Champs").size());
	}
}
