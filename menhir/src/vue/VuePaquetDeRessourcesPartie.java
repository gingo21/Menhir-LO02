package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import modele.PaquetDeRessourcesDeJoueur;
import modele.PaquetDeRessourcesDePartie;
	

public class VuePaquetDeRessourcesPartie extends Panneaux implements Observer{
	
	private JLabel nombreDeGraines;
	private JLabel lblCartesAvancee;
	private JLabel lblCartesNormale;
	private int nbreDeCartesNormale;
	private int nbreDeCartesAvancee;
	
	private PaquetDeRessourcesDePartie referencePaquetDeRessourcesDePartie;
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
	
	
	public VuePaquetDeRessourcesPartie(PaquetDeRessourcesDePartie paquetDeRessourcesDePartie, int nbreJoueurs, 
			boolean partieAvancee){
		this.imagesIngredient = new Image[8];
		this.setPreferredSize(new Dimension(480, 250));
		try {
			imageDosGeant = ImageIO.read( new File("src/Ressources/DosCarteNormale.png"));
			imageDosLutin = ImageIO.read( new File("src/Ressources/DosCarteAvancee.png"));
			imageComptageDePoints = ImageIO.read( new File("src/Ressources/CarteComptageDePoints.png"));
			imagesIngredient[1] = ImageIO.read(new File("src/Ressources/CarteIngredientFontaineDEauPure.png"));
			imagesIngredient[4] = ImageIO.read(new File("src/Ressources/CarteIngredientChantDeSirene.png"));
			imagesIngredient[5] = ImageIO.read(new File("src/Ressources/CarteIngredientEspritDeDolmen.png"));
			imagesIngredient[6] = ImageIO.read(new File("src/Ressources/CarteIngredientLarmesDeDryade.png"));
			imagesIngredient[0] = ImageIO.read(new File("src/Ressources/CarteIngredientPoudreDOr.png"));
			imagesIngredient[7] = ImageIO.read(new File("src/Ressources/CarteIngredientRacinesDArcEnCiel.png"));
			imagesIngredient[3] = ImageIO.read(new File("src/Ressources/CarteIngredientRayonDeLune.png"));
			imagesIngredient[2] = ImageIO.read(new File("src/Ressources/CarteIngredientRiresDeFees.png"));
			imageCarteChamp = ImageIO.read( new File("src/Ressources/CarteChamp.png"));
			imageChienDeGarde = ImageIO.read( new File("src/Ressources/CarteChienDeGarde.png"));
			imageTaupesGeantes = ImageIO.read( new File("src/Ressources/CarteTaupeGeante.png"));
			imageGraine = ImageIO.read( new File("src/Ressources/graine.png"));
			imageGeant = ImageIO.read( new File("src/Ressources/geant.png"));



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		referencePaquetDeRessourcesDePartie = paquetDeRessourcesDePartie;
		this.nombreDeGraines = new JLabel();
		
		String tempTexte1 = new String();
		tempTexte1 +="Graines "+paquetDeRessourcesDePartie.getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		vuesGraines = new VueImage[this.referencePaquetDeRessourcesDePartie.getGrainesDeMenhir()];
		for (int i=0; i<this.referencePaquetDeRessourcesDePartie.getGrainesDeMenhir();i++){
			vuesGraines[i]= new VueImage(imageGraine, 24, 10);
			this.ajoutPanneau(vuesGraines[i], 75 + (i%8)*26, 18+ (i/8)*11);
		}
		vueGeant = new VueImage(imageGeant,63,108);
		this.ajoutPanneau(vueGeant, 0, 0);
		this.lblCartesNormale = new JLabel();
		this.nbreDeCartesNormale = 5*nbreJoueurs;
		String tempTexte2 = new String();
		tempTexte2 += "Nombre Cartes: "+ this.nbreDeCartesNormale;
		this.lblCartesNormale.setText(tempTexte2);
		this.ajoutPanneau(lblCartesNormale, 2, 115);
		vueDosNorm = new VueImage(imageDosGeant,70,70);
		this.ajoutPanneau(vueDosNorm, 20, 135);
			if (partieAvancee){
				this.lblCartesAvancee = new JLabel();
				this.nbreDeCartesAvancee = 2*nbreJoueurs;
				String tempTexte3 = new String();
				tempTexte3 += "Nombre Cartes: "+ this.nbreDeCartesAvancee;
				this.lblCartesAvancee.setText(tempTexte3);
				this.ajoutPanneau(lblCartesAvancee, 140, 115);
				this.VueDosAvancee = new VueImage(this.imageDosLutin,70,70);
				this.ajoutPanneau(VueDosAvancee, 175, 135);
			}
		
		
	}
	public JLabel getNombreDeGraines() {
		return nombreDeGraines;
	}
	public PaquetDeRessourcesDePartie getReferencePaquetDeRessourcesDePartie() {
		return referencePaquetDeRessourcesDePartie;
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
	public Image getCarteTaupesGeantes() {
		return imageTaupesGeantes;
	}
	public Image getImageGraine() {
		return imageGraine;
	}
	public Image getImageGeant() {
		return imageGeant;
	}
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}
