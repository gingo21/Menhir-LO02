package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
import modele.CarteIngredient;

public class VueCarte extends Panneaux implements Observer, MouseListener{

	private static final long serialVersionUID = 1L; 
	
	private Carte carte;
	private BufferedImage bf;
	private boolean isAlliee;
	private int[][] puissanceActions;
	private Image image;
	private int hauteurImage;
	private int largImage;
	private ImageIcon[] imagesIngredient = new ImageIcon[8];
	int x = 0+1;
	int[] tab = new int[5];
	//atribut pour action 
	
	
	public VueCarte(String path, Carte carte) {
		this.carte = carte;
		this.setPreferredSize(new Dimension(250, 250));
		//this.setSize(new Dimension(250, 250));
		
		//chargement toutes les images, à mettre autre part.
		imagesIngredient[1] = new ImageIcon("src/Ressources/CarteIngredientFontaineDEauPure.png");
		imagesIngredient[4] = new ImageIcon("src/Ressources/CarteIngredientChantDeSirene.png");
		imagesIngredient[5] = new ImageIcon("src/Ressources/CarteIngredientEspritDeDolmen.png");
		imagesIngredient[6] = new ImageIcon("src/Ressources/CarteIngredientLarmesDeDryade.png");
		imagesIngredient[0] = new ImageIcon("src/Ressources/CarteIngredientPoudreDOr.png");
		imagesIngredient[7] = new ImageIcon("src/Ressources/CarteIngredientRacinesDArcEnCiel.png");
		imagesIngredient[3] = new ImageIcon("src/Ressources/CarteIngredientRayonDeLune.png");
		imagesIngredient[2] = new ImageIcon("src/Ressources/CarteIngredientRiresDeFees.png");
		
		//sélection aléatoire de la carte
		int v = (int) (Math.random() * (0 + 6));
		image = imagesIngredient[v].getImage();

		
		/*  fonctionne pas (utilisation imageIcon pour le moment)
		try {
			//Image image = ImageIO.read( new File("CarteIngredientFontaineDEauPure.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		//allié ou ingredient
		if (carte instanceof CarteIngredient){
			this.puissanceActions = ((CarteIngredient) carte).getPuissanceActions();
		}
		/*
		if (carte instanceof CarteAlliee) {
			this.puissanceActions = carte.g
		*/
		
		//Affichage des valeurs 
		 int debutX = 30;
	     int pasX  = 15;
	     int debutY = 38;
	     int pasY  = 20;
	     for (int i = 0; i < this.puissanceActions.length; i++) {
	            for (int j = 0; j < this.puissanceActions[i].length; j++) {
	                JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
	                force.setForeground(Color.white);
	                this.ajoutPanneau(force,120 + i*29,154 + j*28); 
	            }
	     }
	     //mouseListener
	     addMouseListener(this);
	    } 
	
		
		public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		
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
