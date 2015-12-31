package vue;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteTaupesGeantes extends VueCarteAliee{

	public VueCarteTaupesGeantes(Carte carte, Image dos, Image face, int h, int l ) {
		super(carte, dos, face, h, l);
	}

}
