package vue;

import java.awt.Graphics;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteComptageDePoints extends VueCarte {

	private static final long serialVersionUID = 6388366603442276545L;

	public VueCarteComptageDePoints(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageComptageDePoints(), h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), h, l);
		this.hidden = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!this.hidden) {
			g.drawImage(imageFaceCarte, 0, 0, this);
		} else {
			g.drawImage(imageDosLutin, 0, 0, this);
		}
	}

}
