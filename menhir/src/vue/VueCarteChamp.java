package vue;

import java.awt.Graphics;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteChamp extends VueCarte {

	private static final long serialVersionUID = -5112939759184291736L;

	public VueCarteChamp(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageCarteChamp(), h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), h, l);
		this.hidden = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!this.hidden) {
			g.drawImage(imageFaceCarte, 0, 0, this);
		} else {
			g.drawImage(imageDos, 0, 0, this);
		}
	}

}
