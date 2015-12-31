package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modele.Carte;
import modele.CarteIngredient;

public class VueCarteIngredient extends VueCarte{
	private Image[] imagesIngredient;
	private int[][] puissanceActions;
	public VueCarteIngredient(Carte carte, Image dos, Image face, int h, int l ) {
		super(carte, dos, face,h,l);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
		     for (int i = 0; i < this.puissanceActions.length; i++) {
		            for (int j = 0; j < this.puissanceActions[i].length; j++) {
		                JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
//		                force.setPreferredSize(new Dimension(5,5));
		                force.setForeground(Color.white);
		                this.ajoutPanneau(force,70 + i*18,89 + j*17); 
		            }
		     }
		}
		else {
			g.drawImage(imageDos, 0, 0, this);
		}
		
	}

}
