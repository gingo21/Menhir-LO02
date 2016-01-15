package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Classe qui permet de dessiner une image
 * dans la taille voulue
 */
public class VueImage extends Panneau {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -6093237545149467189L;
	private Image image;

	/**
	 * Cr�e la vue de l'image
	 * @param image image 
	 * @param largeur largeur souhait�
	 * @param hauteur hauteur souhait�
	 */
	public VueImage(Image image, int largeur, int hauteur) {
		this.image = image;
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.image = redimImage(image, hauteur, largeur);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Redimensionner une Image
	 * @param image l'image de d�part
	 * @param hauteur sa hauteur souhait�
	 * @param w sa largeur souhait�
	 * 
	 * @return l'image redimmensionn�
	 */
	public Image redimImage(Image image, int hauteur, int w) {
		BufferedImage img = new BufferedImage(w, hauteur, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, w, hauteur, null);
		g.dispose();
		return img;
	}
}
