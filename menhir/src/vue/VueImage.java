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

	private static final long serialVersionUID = -6093237545149467189L;
	private Image image;

	/**
	 * Cr�e la vue de l'image
	 * @param image image 
	 * @param l largeur souhait�
	 * @param h hauteur souhait�
	 */
	public VueImage(Image image, int l, int h) {
		this.image = image;
		this.setPreferredSize(new Dimension(l, h));
		this.image = redimImage(image, h, l);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Redimensionner une Image
	 * @param image l'image de d�part
	 * @param h sa hauteur souhait�
	 * @param w sa largeur souhait�
	 * 
	 * @return l'image redimmensionn�
	 */
	public Image redimImage(Image image, int h, int w) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, w, h, null);
		g.dispose();
		return img;
	}
}
