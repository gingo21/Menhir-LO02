package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ressources.Ressources;
import modele.Carte;
import modele.CarteAlliee;
import modele.CarteChiensDeGarde;
import modele.CarteIngredient;

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
		addMouseListener(this);
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

}
