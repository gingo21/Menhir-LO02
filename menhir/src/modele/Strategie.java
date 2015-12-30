package modele;

import launcher.Console;
import java.io.Serializable;
import java.util.Observable;

public abstract class Strategie extends Observable implements Serializable {
	private boolean choixCarteAlliee;
	private Joueur referenceJoueur;

	public Strategie(Joueur referenceJoueur) {
		super();
		choixCarteAlliee = false;
		this.referenceJoueur = referenceJoueur;
	}

	public Joueur getReferenceJoueur() {
		return referenceJoueur;
	}

	public void setReferenceJoueur(Joueur referenceJoueur) {
		this.referenceJoueur = referenceJoueur;
	}

	public boolean isChoixCarteAlliee() {
		return choixCarteAlliee;
	}

	public void setChoixCarteAlliee(boolean choixCarteAlliee) {
		this.choixCarteAlliee = choixCarteAlliee;
	}

	public abstract void jouerSonTour(Saison saisonActuelle, ParametresDePartie parametresDePartie);

	public abstract int seDefendre(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle, int puissance);

	public abstract void attaquer(ParametresDePartie parametresDePartie, Joueur destinataire, Joueur acteur,
			Saison saisonActuelle);

	public abstract void choixDeManche(ParametresDePartie parametresDePartie);
	
	public void addConsoleObserver(Console observer) {
		this.addObserver(observer);
	}
}
