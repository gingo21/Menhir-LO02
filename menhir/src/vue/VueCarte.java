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

import modele.Carte;
import modele.CarteAlliee;
import modele.CarteChiensDeGarde;
import modele.CarteIngredient;

public class VueCarte extends Panneaux implements Observer, MouseListener{

	private static final long serialVersionUID = 1L; 
	
	private Carte carte;
	protected static int HAUTEUR = 150;
	protected static int LARGEUR = 150;
	protected ImageIcon faceCarte;
	protected Image imageFaceCarte;
	protected Image imageDosCarte;
	protected boolean hidden;
	 
	
	public VueCarte(Carte carte) {
		this.carte = carte;
		this.setPreferredSize(new Dimension(150, 150)); 
		//this.hidden = true;
		if (!(carte instanceof CarteAlliee)){
			try {
				imageDosCarte = ImageIO.read( new File("src/Ressources/DosCarteNormale.png"));
				imageDosCarte = redimImage(imageDosCarte, HAUTEUR, LARGEUR);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		addMouseListener(this);
	    } 
	
		
		public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//retourne image en 70*70
	public static Image redimImage(Image image, int height, int width){
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
	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
		System.out.println(ev.getX());
		System.out.println(ev.getY());

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
