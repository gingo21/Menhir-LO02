package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteComptageDePoints extends VueCarte{

	public VueCarteComptageDePoints(Carte carte,Ressources ressources, int h, int l,boolean IA ) {
		super(carte, ressources, h, l,IA);
		this.imageFaceCarte = ressources.redimImage(ressources.getImageComptageDePoints(), h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), h, l);
		this.hidden = false;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
		}
		else{
			g.drawImage(imageDosLutin, 0, 0, this);
		}
	}

}
