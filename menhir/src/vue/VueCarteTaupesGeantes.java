package vue;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteTaupesGeantes extends VueCarteAliee{

	public VueCarteTaupesGeantes(Carte carte) {
		super(carte);
		try {
			imageFaceCarte = ImageIO.read( new File("src/Ressources/CarteTaupeGeante.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageFaceCarte = redimImage(imageFaceCarte, HAUTEUR, LARGEUR);
	}

}
