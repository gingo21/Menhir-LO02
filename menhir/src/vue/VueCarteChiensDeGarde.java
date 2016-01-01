package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Ressources.Ressources;
import modele.Carte;

public class VueCarteChiensDeGarde extends VueCarteAliee{

	public VueCarteChiensDeGarde(Carte carte,Ressources ressources, int h, int l,boolean IA) {
		super(carte, ressources, h, l,IA);
		imageFaceCarte = redimImage(ressources.getImageChienDeGarde(), h, l);
	}
}
