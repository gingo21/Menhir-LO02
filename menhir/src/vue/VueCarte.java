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
 * Repr�sentation graphique d'une carte
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
	 * Image dos 
	 */
	protected Image imageDos;
	protected Image imageDosLutin;
	protected boolean hidden;
	protected String nomCarte;

	/**
	 * Cr�er la vue graphique d'une carte
	 * @param carte la carte associ�
	 * @param r l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarte(Carte carte, Ressources r, int h, int l,boolean IA) {
		this.carte = carte;
		this.nomCarte = carte.getNom();
		this.setPreferredSize(new Dimension(h, l)); 
		this.hidden = IA;
	} 
	
	/**
	 * Redimensionner une Image
	 * @param image l'image de d�part
	 * @param h sa hauteur souhait�
	 * @param w sa largeur souhait�
	 * 
	 * @return img l'image redimmensionn�
	 */
	public Image redimImage(Image image, int h, int w){
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, w, h, null);
		g.dispose();
		return img;
	}

//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//
//	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

}
