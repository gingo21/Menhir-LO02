package modele;

public class CarteChamp extends Carte {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -3389589595911788625L;

	private int menhirAdultes;

	public CarteChamp(String nom) {
		super(nom);
		this.menhirAdultes = 0;
	}

	public void rajouterGraines(int nombre) {
		this.menhirAdultes += nombre;
	}

	public int getMenhirAdultes() {
		return menhirAdultes;
	}

	public void setMenhirAdultes(int menhirAdultes) {
		this.menhirAdultes = menhirAdultes;
	}

	public String toString() {
		return "CarteChamp [menhirAdultes=" + menhirAdultes + "]";
	}
}
