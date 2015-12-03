package menhir;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Joueur {
	private String nom;
	private int id;
	private int score;
	private PaquetDeRessourcesDeJoueur paquet;
	private boolean choixCarteSpeciale;

	public static int numeroDuDernierID = 0;

	public Joueur(String nom, PaquetDeRessourcesDePartie referencePaquetPartie) {
		this.nom = nom;
		this.id = numeroDuDernierID;
		numeroDuDernierID++;
		this.score = 0;
		choixCarteSpeciale = false;

		paquet = new PaquetDeRessourcesDeJoueur(this, referencePaquetPartie);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PaquetDeRessourcesDeJoueur getPaquet() {
		return paquet;
	}

	public void setPaquet(PaquetDeRessourcesDeJoueur paquet) {
		this.paquet = paquet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isChoixCarteSpeciale() {
		return choixCarteSpeciale;
	}

	public void setChoixCarteSpeciale(boolean choixCarteSpeciale) {
		this.choixCarteSpeciale = choixCarteSpeciale;
	}

	public void Score(StatutPartie statutPartie) {
		CarteChamp tempCarteChmp = (CarteChamp) this.getPaquet()
				.getPaquetsDeCartes().get("Cartes Champs").get(0);
		if (statutPartie == StatutPartie.rapide) {
			this.score = 100 * tempCarteChmp.getMenhirAdultes()
					+ this.getPaquet().getGrainesDeMenhir();
		} else {
			CarteComptageDePoint tempComptage = (CarteComptageDePoint) this
					.getPaquet().getPaquetsDeCartes()
					.get("Cartes Comptage De Points").get(0);
			this.score = 100 * tempComptage.getMenhirAdultes() + 100
					* tempCarteChmp.getMenhirAdultes()
					+ this.getPaquet().getGrainesDeMenhir();
		}

	}

	public String toString() {
		return "Joueur [nom=" + nom + ", id=" + id + ", score=" + score
				+ paquet + "]";
	}
	
	public abstract void jouerSonTour(Saison saisonActuelle, ParametreDePartie parametreDePartie);
	public abstract int seDefendre(StatutPartie statutPartie, Joueur destinataire, Joueur acteur, Saison saisonActuelle, int puissance);
	public abstract void attaquer();
}
