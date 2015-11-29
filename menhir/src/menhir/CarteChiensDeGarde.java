package menhir;

public class CarteChiensDeGarde extends CarteAlliee {

	public CarteChiensDeGarde(String nom) {
		super(nom);
	}

	public int utiliser(Joueur destinataire, Saison saisonActuelle, int puissance) {
		int tempValeur = 0;
		if (saisonActuelle == Saison.automne) {
			tempValeur = 2;
		} else if (saisonActuelle == Saison.hiver) {
			tempValeur = 3;
		} else if (saisonActuelle == Saison.printemps) {
			tempValeur = 0;
		} else if (saisonActuelle == Saison.ete) {
			tempValeur = 1;
		}
		int[] tempPuissance = this.getPuissanceActions();
		this.setEstUtilise(true);
		return puissance - tempPuissance[tempValeur];
		
	}

	@Override
	public void utiliser(Joueur destinataire, Saison saisonActuelle) {
		// TODO Auto-generated method stub
	
	}

	

}
