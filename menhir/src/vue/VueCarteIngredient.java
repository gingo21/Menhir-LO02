package vue;

import java.awt.Color;
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
	public VueCarteIngredient(Carte carte) {
		super(carte);
		//chargement toutes les images ingredient, à mettre autre part.
		imagesIngredient = new Image[8];
		this.puissanceActions = ((CarteIngredient) carte).getPuissanceActions();
		try {
			imagesIngredient[1] = ImageIO.read(new File("src/Ressources/CarteIngredientFontaineDEauPure.png"));
			imagesIngredient[4] = ImageIO.read(new File("src/Ressources/CarteIngredientChantDeSirene.png"));
			imagesIngredient[5] = ImageIO.read(new File("src/Ressources/CarteIngredientEspritDeDolmen.png"));
			imagesIngredient[6] = ImageIO.read(new File("src/Ressources/CarteIngredientLarmesDeDryade.png"));
			imagesIngredient[0] = ImageIO.read(new File("src/Ressources/CarteIngredientPoudreDOr.png"));
			imagesIngredient[7] = ImageIO.read(new File("src/Ressources/CarteIngredientRacinesDArcEnCiel.png"));
			imagesIngredient[3] = ImageIO.read(new File("src/Ressources/CarteIngredientRayonDeLune.png"));
			imagesIngredient[2] = ImageIO.read(new File("src/Ressources/CarteIngredientRiresDeFees.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 //sélection aléatoire de la carte
			int v = (int) (Math.random() * (0 + 6));
			imageFaceCarte = imagesIngredient[v];
			imageFaceCarte = VueCarte.redimImage(imageFaceCarte, HAUTEUR, LARGEUR);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
		     for (int i = 0; i < this.puissanceActions.length; i++) {
		            for (int j = 0; j < this.puissanceActions[i].length; j++) {
		                JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
		                force.setForeground(Color.white);
		                this.ajoutPanneau(force,70 + i*18,89 + j*17); 
		            }
		     }
		}
		else {
			g.drawImage(imageDosGeant, 0, 0, this);
		}
		
	}

}
