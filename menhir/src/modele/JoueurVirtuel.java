package modele;

public class JoueurVirtuel extends Joueur {

	private Difficulte difficulte;

	public JoueurVirtuel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie, Difficulte difficulte) {
		super(nom, referencePaquetPartie, null);
		if(difficulte == Difficulte.facile) {
			this.setStrategie(new StrategieFacile(this));
		} else {
			this.setStrategie(new StrategieNormale(this));
		}
		this.difficulte = difficulte;
	}
	
	public JoueurVirtuel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie, null);
		int tempAlea = (int) Math.random() * 2;
		if(tempAlea==0) {
			this.setStrategie(new StrategieFacile(this));
			this.difficulte = Difficulte.facile;
		} else {
			this.setStrategie(new StrategieNormale(this));
			this.difficulte = Difficulte.normale;
		}
	}
	
	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
}
