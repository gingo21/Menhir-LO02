package vue;

import modele.Carte;
import ressources.Ressources;

/**
 * Représentation graphique d'une carte Chien de Garde
 */
public class VueCarteChiensDeGarde extends VueCarteAliee {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -5310218114424495964L;
	
	/**
	 * Crée Vue Graphique d'une carte Chiens de Garde
	 * @param carte la carte associé
	 * @param r largeur'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarteChiensDeGarde(Carte carte, Ressources r, int hauteur, int largeur, boolean IA) {
		super(carte, r, hauteur, largeur, IA);
		imageFaceCarte = r.redimImage(r.getImageChienDeGarde(), hauteur, largeur);
	}
}
