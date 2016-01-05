package modele;

import java.util.Arrays;

public abstract class CarteAlliee extends Carte {

	private static final long serialVersionUID = 1693346063845949047L;
	
	private int puissanceActions[] = new int[4];

	public CarteAlliee(String nom, int valeur[]) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = valeur[j];
	}

	public CarteAlliee(String nom) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = (int) (Math.random() * (5 - 0));
	}

	public int[] getPuissanceActions() {
		return puissanceActions;
	}

	public int getPuissanceActions(Saison saisonActuelle) {
		int tempValeur = 2;
		if (saisonActuelle == Saison.automne) {
			tempValeur = 2;
		} else if (saisonActuelle == Saison.hiver) {
			tempValeur = 3;
		} else if (saisonActuelle == Saison.printemps) {
			tempValeur = 0;
		} else if (saisonActuelle == Saison.ete) {
			tempValeur = 1;
		}

		return puissanceActions[tempValeur];
	}

	public void setPuissanceActions(int[] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

	public String toString() {
		return "CarteAlliee [puissanceActions=" + Arrays.toString(puissanceActions) + ", Nom=" + getNom() + ", Classe="
				+ getClass() + "]";
	}

}
