package Ressources;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vue.VueImage;

public class Ressources {
	private Image imageDosGeant;
	private Image imageDosLutin;
	private Image imageComptageDePoints;
	private Image[] imagesIngredient;
	private Image imageCarteChamp;
	private Image imageChienDeGarde;
	private Image imageTaupesGeantes;
	private Image imageGraine;
	private Image imageGeant;
	private VueImage[] vuesGraines;
	private VueImage vueGeant;
	private VueImage vueDosNorm;
	private VueImage VueDosAvancee;
	
	private Image fontaineDEauPure;
	private Image chantDeSirene;
	private Image espritDeDolmen;
	private Image larmesDeDryade;
	private Image poudreDOr;
	private Image racinesDArcEnCiel;
	private Image rayonDeLune;
	private Image riresDeFees;
	public Ressources(){
		this.imagesIngredient = new Image[8];
		try {
			imageDosGeant = ImageIO.read( new File("src/Ressources/DosCarteNormale.png"));
			imageDosLutin = ImageIO.read( new File("src/Ressources/DosCarteAvancee.png"));
			imageComptageDePoints = ImageIO.read( new File("src/Ressources/CarteComptageDePoints.png"));
			fontaineDEauPure = ImageIO.read(new File("src/Ressources/CarteIngredientFontaineDEauPure.png"));
			chantDeSirene = ImageIO.read(new File("src/Ressources/CarteIngredientChantDeSirene.png"));
			espritDeDolmen = ImageIO.read(new File("src/Ressources/CarteIngredientEspritDeDolmen.png"));
			larmesDeDryade = ImageIO.read(new File("src/Ressources/CarteIngredientLarmesDeDryade.png"));
			poudreDOr = ImageIO.read(new File("src/Ressources/CarteIngredientPoudreDOr.png"));
			racinesDArcEnCiel = ImageIO.read(new File("src/Ressources/CarteIngredientRacinesDArcEnCiel.png"));
			rayonDeLune = ImageIO.read(new File("src/Ressources/CarteIngredientRayonDeLune.png"));
			riresDeFees = ImageIO.read(new File("src/Ressources/CarteIngredientRiresDeFees.png"));
			imageCarteChamp = ImageIO.read( new File("src/Ressources/CarteChamp.png"));
			imageChienDeGarde = ImageIO.read( new File("src/Ressources/CarteChienDeGarde.png"));
			imageTaupesGeantes = ImageIO.read( new File("src/Ressources/CarteTaupeGeante.png"));
			imageGraine = ImageIO.read( new File("src/Ressources/graine.png"));
			imageGeant = ImageIO.read( new File("src/Ressources/geant.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Image getFontaineDEauPure() {
		return fontaineDEauPure;
	}
	public Image getChantDeSirene() {
		return chantDeSirene;
	}
	public Image getEspritDeDolmen() {
		return espritDeDolmen;
	}
	public Image getLarmesDeDryade() {
		return larmesDeDryade;
	}
	public Image getPoudreDOr() {
		return poudreDOr;
	}
	public Image getRacinesDArcEnCiel() {
		return racinesDArcEnCiel;
	}
	public Image getRayonDeLune() {
		return rayonDeLune;
	}
	public Image getRiresDeFees() {
		return riresDeFees;
	}
	public Image redimImage(Image image, int height, int width){
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return img;
	}
	public Image getImageDosGeant() {
		return imageDosGeant;
	}
	public Image getImageDosLutin() {
		return imageDosLutin;
	}
	public Image getImageComptageDePoints() {
		return imageComptageDePoints;
	}
	public Image[] getImagesIngredient() {
		return imagesIngredient;
	}
	public Image getImageCarteChamp() {
		return imageCarteChamp;
	}
	public Image getImageChienDeGarde() {
		return imageChienDeGarde;
	}
	public Image getImageTaupesGeantes() {
		return imageTaupesGeantes;
	}
	public Image getImageGraine() {
		return imageGraine;
	}
	public Image getImageGeant() {
		return imageGeant;
	}
	public VueImage[] getVuesGraines() {
		return vuesGraines;
	}
	public VueImage getVueGeant() {
		return vueGeant;
	}
	public VueImage getVueDosNorm() {
		return vueDosNorm;
	}
	public VueImage getVueDosAvancee() {
		return VueDosAvancee;
	}
}
