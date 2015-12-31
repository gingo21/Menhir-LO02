package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import modele.Carte;

public class VueCarteComptageDePoints extends VueCarte{

	public VueCarteComptageDePoints(Carte carte, Image dos, Image face, int h, int l ) {
		super(carte, dos, face, h, l);
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
