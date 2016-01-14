
package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import Ressources.Ressources;
import modele.Carte;

/**
 * Représentation graphique d'une carte
 */
public abstract class VueCarte extends Panneau{

	private static final long serialVersionUID = 1L; 

	/**
	 * Carte
	 */
	private Carte carte;
	
	/**
	 * Hauteur de la Carte
	 */
	protected  int hauteur = 80;
	
	/**
	 * Largeur de la Carte
	 */
	protected  int largeur = 80;
	
	/**
	 * Image de la face de la carte 
	 */
	protected Image imageFaceCarte;
	
	/**
	 * Image dos carte normale
	 */
	protected Image imageDos;
	
	/**
	 * Image dos carte alliée
	 */
	protected Image imageDosLutin;
	
	/**
	 * Carte cachée 
	 */
	protected boolean hidden;
	


	/**
	 * Créer la vue graphique d'une carte
	 * @param carte la carte associé
	 * @param r l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarte(Carte carte, Ressources r, int h, int l,boolean IA) {
		this.carte = carte;
		this.setPreferredSize(new Dimension(h, l)); 
		this.hidden = IA;
	} 


	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

}
