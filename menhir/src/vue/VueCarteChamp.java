package vue;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteChamp extends VueCarte{

	public VueCarteChamp(Carte carte) {
		super(carte);
		try {
			imageFaceCarte = ImageIO.read( new File("src/Ressources/CarteChamp.png"));
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
			g.drawImage(imageDosGeant, 0, 0, this);
		}
	}

}
