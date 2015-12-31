package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modele.Carte;

public class VueCarteChiensDeGarde extends VueCarteAliee{

	public VueCarteChiensDeGarde(Carte carte, Image dos, Image face, int h, int l ) {
		super(carte, dos, face, h, l);
		imageFaceCarte = redimImage(imageFaceCarte, h, l);
	}
}
