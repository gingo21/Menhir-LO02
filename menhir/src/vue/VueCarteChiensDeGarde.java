package vue;

import Ressources.Ressources;
import modele.Carte;

/**
 * Représentation graphique d'une carte Chien de Garde
 */
public class VueCarteChiensDeGarde extends VueCarteAliee {

	private static final long serialVersionUID = -5310218114424495964L;
	
	/**
	 * Crée Vue Graphique d'une carte Chiens de Garde
	 * @param carte la carte associé
	 * @param r l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarteChiensDeGarde(Carte carte, Ressources r, int h, int l, boolean IA) {
		super(carte, r, h, l, IA);
		imageFaceCarte = r.redimImage(r.getImageChienDeGarde(), h, l);
	}
}
