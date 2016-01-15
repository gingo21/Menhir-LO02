package vue;

import modele.Carte;
import ressources.Ressources;

/**
 * Repr�sentation Graphique d'une Carte Taupes G�antes
 * H�rite de VueCarteAlliee
 */
public class VueCarteTaupesGeantes extends VueCarteAliee {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 2090021684921520793L;

	/**
	 * Cr�er la vue graphique d'une carte taupe g�ante
	 * @param carte la carte associ�
	 * @param ressources largeur'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteTaupesGeantes(Carte carte, Ressources ressources, int hauteur, int largeur, boolean IA) {
		super(carte, ressources, hauteur, largeur, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageTaupesGeantes(), hauteur, largeur);
	}

}
