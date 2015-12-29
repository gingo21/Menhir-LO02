package modele;

import java.io.Serializable;

public abstract class Strategie implements Serializable {
	private boolean choixCarteAlliee;
	
	
	public Strategie() {
		super();
		choixCarteAlliee=false;
	}
	
	public boolean isChoixCarteAlliee() {
		return choixCarteAlliee;
	}

	public void setChoixCarteAlliee(boolean choixCarteAlliee) {
		this.choixCarteAlliee = choixCarteAlliee;
	}

	public abstract void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie);
	
	public abstract int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur, Saison saisonActuelle, int puissance);
	
	public abstract void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire,
			Joueur acteur, Saison saisonActuelle);
	
	public abstract void choixDeManche(ParametresDePartie parametresDePartie);
}
