package vue;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteChiensDeGarde extends VueCarteAliee{

	public VueCarteChiensDeGarde(Carte carte) {
		super(carte);
		try {
			imageFaceCarte = ImageIO.read( new File("src/Ressources/CarteChienDeGarde.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFaceCarte = redimImage(imageFaceCarte, HAUTEUR, LARGEUR);
	}
}
