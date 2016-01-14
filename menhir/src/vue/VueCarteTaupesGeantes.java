package vue;

import Ressources.Ressources;
import modele.Carte;

/**
 * Repr�sentation Graphique d'une Carte Taupes G�antes
 * H�rite de VueCarteAlliee
 */
public class VueCarteTaupesGeantes extends VueCarteAliee {

	private static final long serialVersionUID = 2090021684921520793L;

	/**
	 * Cr�er la vue graphique d'une carte taupe g�ante
	 * @param carte la carte associ�
	 * @param ressources l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteTaupesGeantes(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageTaupesGeantes(), h, l);
	}

}
