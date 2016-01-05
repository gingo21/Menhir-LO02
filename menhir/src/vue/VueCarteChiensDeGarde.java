package vue;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteChiensDeGarde extends VueCarteAliee {

	private static final long serialVersionUID = -5310218114424495964L;

	public VueCarteChiensDeGarde(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		imageFaceCarte = redimImage(ressources.getImageChienDeGarde(), h, l);
	}
}
