package vue;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteChiensDeGarde extends VueCarteAliee{

	public VueCarteChiensDeGarde(Carte carte) {
		super(carte);
		try {
			imageFaceCarte = ImageIO.read( new File("src/Ressources/CarteChiensDeGarde.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageFaceCarte = redimImage(imageFaceCarte, HAUTEUR, LARGEUR);
	}

}
