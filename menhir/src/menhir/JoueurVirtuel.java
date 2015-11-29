package menhir;

public class JoueurVirtuel extends Joueur {

	private Difficulte difficulte;

	public JoueurVirtuel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie,
			Difficulte difficulte) {
		super(nom, referencePaquetPartie);
		this.difficulte = difficulte;
	}
}
