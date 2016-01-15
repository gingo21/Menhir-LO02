package vue;

import modele.Carte;
import ressources.Ressources;

/**
 * Représentation Graphique d'une Carte Taupes Géantes
 * Hérite de VueCarteAlliee
 */
public class VueCarteTaupesGeantes extends VueCarteAliee {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = 2090021684921520793L;

	/**
	 * Créer la vue graphique d'une carte taupe géante
	 * @param carte la carte associé
	 * @param ressources l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarteTaupesGeantes(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageTaupesGeantes(), h, l);
	}

}
