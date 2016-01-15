package ressources;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import launcher.Console;
import launcher.GraphicLauncher;
import vue.VueImage;

/**
 * La classe Ressources permet de charger une seule fois l'ensemble 
 * des images que nous utilisons pour notre interface graphique
 */
public class Ressources {
	
	/**
	 * Image du dos des cartes normales
	 */
	private Image imageDosGeant;
	
	/**
	 * Image du dos des cartes spéciales
	 */
	private Image imageDosLutin;
	
	/**
	 * Image de la carte comptage de points
	 */
	private Image imageComptageDePoints;
	
	/**
	 * Tableau des images des cartes ingrédients
	 */
	private Image[] imagesIngredient;
	
	/**
	 * Image de la carte champ
	 */
	private Image imageCarteChamp;
	
	/**
	 * Image du chien de garde
	 */
	private Image imageChienDeGarde;
	
	/**
	 * Image de la carte taupes géantes
	 */
	private Image imageTaupesGeantes;
	
	/**
	 * Image d'une graine
	 */
	private Image imageGraine;
	
	/**
	 * Image du géant
	 */
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
	
	/**
	 * Constructeur de la classe
	 */
	public Ressources(){
		this.imagesIngredient = new Image[8];
		try {
			imageDosGeant = ImageIO.read( getClass().getResource("DosCarteNormale.png"));
			imageDosLutin = ImageIO.read( getClass().getResource("DosCarteAvancee.png"));
			imageComptageDePoints = ImageIO.read(getClass().getResource("CarteComptageDePoints.png"));
			fontaineDEauPure = ImageIO.read(getClass().getResource("CarteIngredientFontaineDEauPure.png"));
			chantDeSirene = ImageIO.read(getClass().getResource("CarteIngredientChantDeSirene.png"));
			espritDeDolmen = ImageIO.read(getClass().getResource("CarteIngredientEspritDeDolmen.png"));
			larmesDeDryade = ImageIO.read(getClass().getResource("CarteIngredientLarmesDeDryade.png"));
			poudreDOr = ImageIO.read(getClass().getResource("CarteIngredientPoudreDOr.png"));
			racinesDArcEnCiel = ImageIO.read(getClass().getResource("CarteIngredientRacinesDArcEnCiel.png"));
			rayonDeLune = ImageIO.read(getClass().getResource("CarteIngredientRayonDeLune.png"));
			riresDeFees = ImageIO.read(getClass().getResource("CarteIngredientRiresDeFees.png"));
			imageCarteChamp = ImageIO.read( getClass().getResource("CarteChamp.png"));
			imageChienDeGarde = ImageIO.read(getClass().getResource("CarteChienDeGarde.png"));
			imageTaupesGeantes = ImageIO.read(getClass().getResource("CarteTaupeGeante.png"));
			imageGraine = ImageIO.read( getClass().getResource("graine.png"));
			imageGeant = ImageIO.read( getClass().getResource("geant.png"));

		} catch (IOException e) {
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
	
	/**
	 * Méthode qui permet le redimensionnement d'une image
	 */
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
