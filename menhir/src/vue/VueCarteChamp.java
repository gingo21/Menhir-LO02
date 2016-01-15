package vue;

import java.awt.Graphics;

import modele.Carte;
import ressources.Ressources;

/**
 * Repr�sentation graphique d'une carte champ 
 */
public class VueCarteChamp extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -5112939759184291736L;
	
	/**
	 * Cr�e repr�sentation graphique d'une carte champ
	 * @param carte la carte champ
	 * @param ressources les ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteChamp(Carte carte, Ressources ressources, int hauteur, int largeur, boolean IA) {
		super(carte, ressources, hauteur, largeur, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageCarteChamp(), hauteur, largeur);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), hauteur, largeur);
		this.hidden = false;
	}
	
	/**
	 * Dessine la carte champ � partir d'une image
	 */
	public void paintComponent(Graphics g) {
			g.drawImage(imageFaceCarte, 0, 0, this);
	}

}
