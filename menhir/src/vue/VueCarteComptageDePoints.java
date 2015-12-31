package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import modele.Carte;

public class VueCarteComptageDePoints extends VueCarte{

	public VueCarteComptageDePoints(Carte carte) {
		super(carte);
		try {
			imageFaceCarte = ImageIO.read( new File("src/Ressources/CarteComptageDePoints.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
