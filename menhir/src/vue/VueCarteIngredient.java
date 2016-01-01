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

import Ressources.Ressources;
import modele.Carte;
import modele.CarteIngredient;

public class VueCarteIngredient extends VueCarte{
	private Image[] imagesIngredient;
	private int[][] puissanceActions;
	public VueCarteIngredient(Carte carte,Ressources ressources, int h, int l,boolean IA ) {
		super(carte, ressources,h,l,IA);
		this.puissanceActions=((CarteIngredient) carte).getPuissanceActions();
		if (this.nomCarte == "Chant de Sirène"){
			this.imageFaceCarte = ressources.getChantDeSirene();
		}
		else if (this.nomCarte == "Esprit de Dolmen"){
			this.imageFaceCarte = ressources.getEspritDeDolmen();
		}
		else if (this.nomCarte == "Fontaine Eau Pure"){
			this.imageFaceCarte = ressources.getFontaineDEauPure();
		}
		else if (this.nomCarte == "Larmes De Dryade"){
			this.imageFaceCarte = ressources.getLarmesDeDryade();
		}
		else if (this.nomCarte == "Poudre Or"){
			this.imageFaceCarte = ressources.getPoudreDOr();
		}
		else if (this.nomCarte == "Racines Arc En Ciel"){
			this.imageFaceCarte = ressources.getRacinesDArcEnCiel();
		}
		else if (this.nomCarte == "Rayon De Lune"){
			this.imageFaceCarte = ressources.getRayonDeLune();
		}
		else{
			this.imageFaceCarte = ressources.getRiresDeFees();
		}

		this.imageFaceCarte = ressources.redimImage(imageFaceCarte, h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), h, l);
		if (!this.hidden){
			for (int i = 0; i < this.puissanceActions.length; i++) {
				for (int j = 0; j < this.puissanceActions[i].length; j++) {
					JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
//					force.setPreferredSize(new Dimension(5,5));
					force.setForeground(Color.white);
					this.ajoutPanneau(force,70 + i*18,89 + j*17); 
				}
			}
	}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
		}
		else {
			g.drawImage(imageDos, 0, 0, this);
		}

	}

}
