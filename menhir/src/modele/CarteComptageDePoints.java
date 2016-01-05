package modele;

public class CarteComptageDePoints extends Carte {

	private static final long serialVersionUID = -2546643344186347017L;
	
	private int menhirAdultes;

	public CarteComptageDePoints(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	public void rajouterGraines(int nombre) {
		this.menhirAdultes +=nombre;
	}

	public int getMenhirAdultes() {
		return menhirAdultes;
	}

	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	public String toString() {
		return "CarteComptageDePoints [menhirAdultes=" + menhirAdultes + "] \n";
	}
}
