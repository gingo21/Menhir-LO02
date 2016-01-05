package modele;

import launcher.Jeu;

public class JoueurReel extends Joueur {

	private static final long serialVersionUID = -2406747672022623752L;

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
