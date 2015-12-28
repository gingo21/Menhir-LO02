package modele;

public class JoueurReel extends Joueur {

	public JoueurReel(String nom,
			PaquetDeRessourcesDePartie referencePaquetPartie) {
		super(nom, referencePaquetPartie,null);
		this.setStrategie(new StrategieJoueurReelConsole(this));
		}
}
