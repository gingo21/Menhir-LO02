package vue;

import modele.Carte;
import ressources.Ressources;

/**
 * Repr�sentation graphique d'une carte Chien de Garde
 */
public class VueCarteChiensDeGarde extends VueCarteAliee {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -5310218114424495964L;
	
	/**
	 * Cr�e Vue Graphique d'une carte Chiens de Garde
	 * @param carte la carte associ�
	 * @param r l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteChiensDeGarde(Carte carte, Ressources r, int h, int l, boolean IA) {
		super(carte, r, h, l, IA);
		imageFaceCarte = r.redimImage(r.getImageChienDeGarde(), h, l);
	}
}
