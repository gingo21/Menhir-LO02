package menhir;

public class StrategieDifficile extends Strategie {
	//TODO Prévu pour la version finale
	
	private Joueur referenceJoueur;
	
	public StrategieDifficile(Joueur referenceJoueur) {
		super();
		this.referenceJoueur = referenceJoueur;
	}
	
	
	@Override
	public void jouerSonTour(Saison saisonActuelle,
			ParametresDePartie parametresDePartie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int seDefendre(ParametresDePartie parametresDePartie,
			Joueur destinataire, Joueur acteur, Saison saisonActuelle,
			int puissance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void attaquer(ParametresDePartie parametresDePartie,
			Joueur destinataire, Joueur acteur, Saison saisonActuelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choixDeManche(ParametresDePartie parametresDePartie) {
		// TODO Auto-generated method stub
		
	}
}
