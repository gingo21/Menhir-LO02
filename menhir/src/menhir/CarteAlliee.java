package menhir;

public abstract class CarteAlliee extends Carte {

	private int puissanceActions[] = new int[4];

	public CarteAlliee(String nom, int valeur[]) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = valeur[j];
	}
	
	public CarteAlliee(String nom) {
		super(nom);
		for (int j = 0; j < 4; j++)
			this.puissanceActions[j] = (int) (Math.random() * ( 4 - 1 ));
	}

	public int[] getPuissanceActions() {
		return puissanceActions;
	}

	public void setPuissanceActions(int[] puissanceActions) {
		this.puissanceActions = puissanceActions;
	}

}
