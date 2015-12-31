package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modele.Carte;
import modele.CarteIngredient;

public class VueCarteIngredient extends VueCarte{
	private ImageIcon[] imagesIngredient = new ImageIcon[8];
	private int[][] puissanceActions;
	public VueCarteIngredient(Carte carte) {
		super(carte);

		//chargement toutes les images ingredient, à mettre autre part.
		imagesIngredient[1] = new ImageIcon("src/Ressources/CarteIngredientFontaineDEauPure.png");
		imagesIngredient[4] = new ImageIcon("src/Ressources/CarteIngredientChantDeSirene.png");
		imagesIngredient[5] = new ImageIcon("src/Ressources/CarteIngredientEspritDeDolmen.png");
		imagesIngredient[6] = new ImageIcon("src/Ressources/CarteIngredientLarmesDeDryade.png");
		imagesIngredient[0] = new ImageIcon("src/Ressources/CarteIngredientPoudreDOr.png");
		imagesIngredient[7] = new ImageIcon("src/Ressources/CarteIngredientRacinesDArcEnCiel.png");
		imagesIngredient[3] = new ImageIcon("src/Ressources/CarteIngredientRayonDeLune.png");
		imagesIngredient[2] = new ImageIcon("src/Ressources/CarteIngredientRiresDeFees.png");
		
		this.puissanceActions = ((CarteIngredient) carte).getPuissanceActions();
		 
	     
	   //sélection aléatoire de la carte
			int v = (int) (Math.random() * (0 + 6));
			imageFaceCarte = imagesIngredient[v].getImage();
			imageFaceCarte = VueCarte.redimImage(imageFaceCarte, HAUTEUR, LARGEUR);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
			int debutX = 30;
		     int pasX  = 15;
		     int debutY = 38;
		     int pasY  = 20;
		     for (int i = 0; i < this.puissanceActions.length; i++) {
		            for (int j = 0; j < this.puissanceActions[i].length; j++) {
		                JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
		                force.setForeground(Color.white);
		                this.ajoutPanneau(force,70 + i*18,89 + j*17); 
		            }
		     }
		}
		else {
			g.drawImage(imageDosCarte, 0, 0, this);
		}
		
	}

}
