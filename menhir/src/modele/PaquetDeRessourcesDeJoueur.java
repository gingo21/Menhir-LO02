package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PaquetDeRessourcesDeJoueur extends PaquetDeRessources {

	private HashMap<String, ArrayList<Carte>> paquetsDeCartes;
	private PaquetDeRessourcesDePartie referencePaquetPartie;
	private Joueur joueur;

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

	public Joueur getJoueur() {
		this.setChanged();
		this.notifyObservers();
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.setChanged();
		this.notifyObservers();
		this.joueur = joueur;
	}
	
	public Carte getCarteComptageDePoint() {
		this.setChanged();
		this.notifyObservers();
		return this.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0);
	}
	
	public CarteChamp getCarteChamp() {
		this.setChanged();
		this.notifyObservers();
		return (CarteChamp) this.getPaquetsDeCartes().get("Cartes Champs").get(0);
	}

	public PaquetDeRessourcesDePartie getReferencePaquetPartie() {
		this.setChanged();
		this.notifyObservers();
		return referencePaquetPartie;
	}

	public void setReferencePaquetPartie(PaquetDeRessourcesDePartie referencePaquetPartie) {
		this.setChanged();
		this.notifyObservers();
		this.referencePaquetPartie = referencePaquetPartie;
	}

	public HashMap<String, ArrayList<Carte>> getPaquetsDeCartes() {
		this.setChanged();
		this.notifyObservers();
		return paquetsDeCartes;
	}

	public void setPaquetsDeCartes(HashMap<String, ArrayList<Carte>> paquetsDeCartes) {
		this.setChanged();
		this.notifyObservers();
		this.paquetsDeCartes = paquetsDeCartes;
	}

	public void ajouterUneCarte(Carte carte) {
		if (carte instanceof CarteIngredient) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes()
					.get("Cartes Ingredients");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChamp) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes()
					.get("Cartes Champs");
			tempCarte.add(carte);
		} else if (carte instanceof CarteComptageDePoints) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes()
					.get("Cartes Comptage De Points");
			tempCarte.add(carte);
		} else if (carte instanceof CarteTaupesGeantes) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes()
					.get("Cartes Taupes Geantes");
			tempCarte.add(carte);
		} else if (carte instanceof CarteChiensDeGarde) {
			ArrayList<Carte> tempCarte = (ArrayList<Carte>) this.getPaquetsDeCartes()
					.get("Cartes Chiens De Garde");
			tempCarte.add(carte);
		}
		this.setChanged();
		this.notifyObservers("ajout carte");
	}

	public void afficherCartes() { //MODE CONSOLE
		Set<String> tempCles = this.getPaquetsDeCartes().keySet();
		for (Iterator<String> it = tempCles.iterator(); it.hasNext();) {
			ArrayList<Carte> tempCartes = this.getPaquetsDeCartes().get(it.next());
			for (Iterator<Carte> yt = tempCartes.iterator(); yt.hasNext();) {
				Carte tempCarte = yt.next();
				if (tempCarte.isEstUtilise()==false)
				System.out.println(tempCarte.toString());
				
			}
		}
	}
	
	
	
}
