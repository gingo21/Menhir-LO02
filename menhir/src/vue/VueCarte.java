package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import Ressources.Ressources;
import modele.Carte;

public abstract class VueCarte extends Panneau{

	private static final long serialVersionUID = 1L; 

	private Carte carte;
	protected  int hauteur = 80;
	protected  int largeur = 80;
	protected Image imageFaceCarte;
	protected Image imageDos;
	protected Image imageDosLutin;
	protected boolean hidden;
	protected String nomCarte;


	public VueCarte(Carte carte, Ressources r, int h, int l,boolean IA) {
		this.carte = carte;
		this.nomCarte = carte.getNom();
		this.setPreferredSize(new Dimension(h, l)); 
		this.hidden = IA;
	} 

	public Image redimImage(Image image, int height, int width){
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return img;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

}
