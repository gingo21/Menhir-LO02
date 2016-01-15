package vue;

import java.awt.Graphics;

import modele.Carte;
import ressources.Ressources;

/**
 * Représentation graphique d'une carte champ 
 */
public class VueCarteChamp extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -5112939759184291736L;
	
	/**
	 * Crée représentation graphique d'une carte champ
	 * @param carte la carte champ
	 * @param ressources les ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarteChamp(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageCarteChamp(), h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), h, l);
		this.hidden = false;
	}
	
	/**
	 * Dessine la carte champ à partir d'une image
	 */
	public void paintComponent(Graphics g) {
			g.drawImage(imageFaceCarte, 0, 0, this);
	}

}
