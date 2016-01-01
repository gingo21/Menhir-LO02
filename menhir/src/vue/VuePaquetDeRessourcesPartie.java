package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import Ressources.Ressources;
import modele.PaquetDeRessourcesDeJoueur;
import modele.PaquetDeRessourcesDePartie;
	

public class VuePaquetDeRessourcesPartie extends Panneaux {
	
	private JLabel nombreDeGraines;
	private JLabel lblCartesAvancee;
	private JLabel lblCartesNormale;
	private int nbreDeCartesNormale;
	private int nbreDeCartesAvancee;
	private PaquetDeRessourcesDePartie referencePaquetDeRessourcesDePartie;
	private Ressources referenceImages;
	private VueImage[] vueGraines;
	private VueImage vueGeant;
	private VueImage vueDosAvancee;
	private VueImage vueDosNorm;
	
	
	public VuePaquetDeRessourcesPartie(PaquetDeRessourcesDePartie paquetDeRessourcesDePartie, int nbreJoueurs, 
			boolean partieAvancee, Ressources ressources){
		this.setPreferredSize(new Dimension(480, 250));
		
		referencePaquetDeRessourcesDePartie = paquetDeRessourcesDePartie;
		referenceImages = ressources;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1 +="Graines "+paquetDeRessourcesDePartie.getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		vueGraines = new VueImage[this.referencePaquetDeRessourcesDePartie.getGrainesDeMenhir()];
		for (int i=0; i<this.referencePaquetDeRessourcesDePartie.getGrainesDeMenhir();i++){
			vueGraines[i]= new VueImage(referenceImages.getImageGraine(), 24, 10);
			this.ajoutPanneau(vueGraines[i], 75 + (i%4)*26, 18+ (i/4)*11);
		}
		vueGeant = new VueImage(referenceImages.getImageGeant(),63,108);
		this.ajoutPanneau(vueGeant, 0, 0);
		this.lblCartesNormale = new JLabel();
		this.nbreDeCartesNormale = 5*nbreJoueurs;
		String tempTexte2 = new String();
		tempTexte2 += "Cartes Normales : "+ this.nbreDeCartesNormale;
		this.lblCartesNormale.setText(tempTexte2);
		this.ajoutPanneau(lblCartesNormale, 200, 0);
		vueDosNorm = new VueImage(referenceImages.getImageDosGeant(),80,80);
		this.ajoutPanneau(vueDosNorm, 220, 80);
			if (partieAvancee){
				this.lblCartesAvancee = new JLabel();
				this.nbreDeCartesAvancee = 2*nbreJoueurs;
				String tempTexte3 = "";
				tempTexte3 = "Cartes Avancée : "+ this.nbreDeCartesAvancee;
				this.lblCartesAvancee.setText(tempTexte3);
				this.ajoutPanneau(lblCartesAvancee, 375, 0);
				this.vueDosAvancee = new VueImage(referenceImages.getImageDosLutin(),80,80);
				this.ajoutPanneau(vueDosAvancee, 350, 80);
			}
//		pour tests
		
		
	}
	public JLabel getNombreDeGraines() {
		return nombreDeGraines;
	}
	public PaquetDeRessourcesDePartie getReferencePaquetDeRessourcesDePartie() {
		return referencePaquetDeRessourcesDePartie;
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}
