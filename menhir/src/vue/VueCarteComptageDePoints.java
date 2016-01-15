package vue;

import java.awt.Graphics;

import modele.Carte;
import ressources.Ressources;

/**
 * Repr�sentation graphique d'une carte comptage de points
 * H�rite de VueCarte
 */
public class VueCarteComptageDePoints extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 6388366603442276545L;
	
	/**
	 * Cr�er la vue graphique d'une carte Comptage de Points
	 * @param carte la carte associ�
	 * @param ressources largeur'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteComptageDePoints(Carte carte, Ressources ressources, int hauteur, int largeur, boolean IA) {
		super(carte, ressources, hauteur, largeur, IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageComptageDePoints(), hauteur, largeur);
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), hauteur, largeur);
		this.hidden = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!this.hidden) {
			g.drawImage(imageFaceCarte, 0, 0, this);
		} else {
			g.drawImage(imageDosLutin, 0, 0, this);
		}
	}

}
