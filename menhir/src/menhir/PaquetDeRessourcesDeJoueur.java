package menhir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PaquetDeRessourcesDeJoueur extends PaquetDeRessources {

	private HashMap<String, ArrayList<Carte>> paquetsDeCartes;
	//private HashMap<String, ArrayList<Carte>> paquetsDeCartesUtilises; // TODO (je pense l'enlevé AW)
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
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	/*
	public HashMap<String, ArrayList<Carte>> getPaquetsDeCartesUtilises() {
		return paquetsDeCartesUtilises;
	}

	public void setPaquetsDeCartesUtilises(HashMap<String, ArrayList<Carte>> paquetsDeCartesUtilises) {
		this.paquetsDeCartesUtilises = paquetsDeCartesUtilises;
	}
	*/
	public Carte getCarteComptageDePoint() {
		return this.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0);
	}
	
	public Carte getCarteChamp() {
		return this.getPaquetsDeCartes().get("Cartes Champs").get(0);
	}
	public int getNombreMenhirsAdultes()
	{
		CarteChamp tempCarteChamp = (CarteChamp) this.paquetsDeCartes.get("Cartes Champs").get(0);
		return tempCarteChamp.getMenhirAdultes();
	}

	public PaquetDeRessourcesDePartie getReferencePaquetPartie() {
		return referencePaquetPartie;
	}

	public void setReferencePaquetPartie(PaquetDeRessourcesDePartie referencePaquetPartie) {
		this.referencePaquetPartie = referencePaquetPartie;
	}

	public HashMap<String, ArrayList<Carte>> getPaquetsDeCartes() {
		return paquetsDeCartes;
	}

	public void setPaquetsDeCartes(HashMap<String, ArrayList<Carte>> paquetsDeCartes) {
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
		} else if (carte instanceof CarteComptageDePoint) {
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

	}

	public void afficherCartes() {
		Set<String> tempCles = this.getPaquetsDeCartes().keySet();
		for (Iterator<String> it = tempCles.iterator(); it.hasNext();) {
			ArrayList<Carte> tempCartes = this.getPaquetsDeCartes().get(it.next());
			for (Iterator<Carte> yt = tempCartes.iterator(); yt.hasNext();) {
				System.out.println(yt.next().toString());
			}
		}

	}
	
}
