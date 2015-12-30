package modele;

import launcher.Jeu;

public class JoueurReel extends Joueur {

	public JoueurReel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie,null);
		if(Jeu.MODE_GRAPHIQUE) {
			this.setStrategie(new StrategieJoueurReelGraphique(this));
		} else {
			this.setStrategie(new StrategieJoueurReelConsole(this));
		}
		}
}
