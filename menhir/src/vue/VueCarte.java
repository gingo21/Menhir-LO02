
package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import modele.Carte;
import ressources.Ressources;

/**
 * Repr�sentation graphique d'une carte
 */
public abstract class VueCarte extends Panneau{
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
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
	 * Image dos carte alli�e
	 */
	protected Image imageDosLutin;
	
	/**
	 * Carte cach�e 
	 */
	protected boolean hidden;
	


	/**
	 * Cr�er la vue graphique d'une carte
	 * @param carte la carte associ�
	 * @param r l'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarte(Carte carte, Ressources r, int hauteur, int largeur,boolean IA) {
		this.carte = carte;
		this.setPreferredSize(new Dimension(hauteur, largeur)); 
		this.hidden = IA;
	} 


	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

}
