package modele;

public abstract class Joueur {
	private String nom;
	private int id;
	private int score;
	private PaquetDeRessourcesDeJoueur paquet;
	private Strategie strategie;

	public static int numeroDuDernierID = 0;

	public Joueur(String nom, PaquetDeRessourcesDePartie referencePaquetPartie, Strategie strategie) {
		this.nom = nom;
		this.id = numeroDuDernierID;
		numeroDuDernierID++;
		this.score = 0;
		this.strategie=strategie;

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

	public Strategie getStrategie() {
		return strategie;
	}

	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	public void score(StatutPartie statutPartie) {
		CarteChamp tempCarteChmp = (CarteChamp) this.getPaquet()
				.getPaquetsDeCartes().get("Cartes Champs").get(0);
		if (statutPartie == StatutPartie.rapide) {
			this.score = 100 * tempCarteChmp.getMenhirAdultes()
					+ this.getPaquet().getGrainesDeMenhir();
		} else {
			CarteComptageDePoints tempComptage = (CarteComptageDePoints) this
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
}
