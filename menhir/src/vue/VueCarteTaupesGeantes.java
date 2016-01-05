package vue;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteTaupesGeantes extends VueCarteAliee {

	private static final long serialVersionUID = 2090021684921520793L;

	public VueCarteTaupesGeantes(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageTaupesGeantes(), h, l);
	}

}
