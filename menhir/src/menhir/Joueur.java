package menhir;


public class Joueur {
	private String nom;
	private int id;
	private int score;
	private PaquetDeRessourcesDeJoueur paquet;
	
	public static int numeroDuDernierID=0;
	
	public Joueur(String nom, PaquetDeRessourcesDePartie referencePaquetPartie){
		this.nom = nom;
		this.id= numeroDuDernierID;
		numeroDuDernierID++;
		this.score = 0;
		
		paquet = new PaquetDeRessourcesDeJoueur(this, referencePaquetPartie);
	}
	
	/*public void utiliserActionIngredient (TypeAction typeAction, CarteIngredient carteIngredient, Joueur Destinataire){
		carte
	}
	
	public void utiliserActionAlliee(Joueur Destinataire){
		
	}*/
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
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
	
	public void Score(StatutPartie statutPartie){
		CarteChamp tempCarteChmp = (CarteChamp)this.getPaquet().getPaquetsDeCartes().get("Cartes Champs").get(0);
		if (statutPartie == StatutPartie.rapide){
			this.score = 100*tempCarteChmp.getMenhirAdultes() + this.getPaquet().getGrainesDeMenhir();
		}
		else{
			CarteComptageDePoint tempComptage = (CarteComptageDePoint)this.getPaquet().getPaquetsDeCartes()
					.get("Cartes Comptage De Points").get(0); 
			this.score = 100*tempComptage.getMenhirAdultes()+ 100*tempCarteChmp.getMenhirAdultes() + this.getPaquet().getGrainesDeMenhir();
		}
		
	}
	public String toString() {
		return "Joueur [nom=" + nom + ", id=" + id + ", score=" + score
				 + "]";
	}
}	
