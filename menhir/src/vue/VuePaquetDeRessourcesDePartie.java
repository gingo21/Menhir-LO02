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
import modele.ParametresDePartie;
import modele.StatutPartie;
	

public class VuePaquetDeRessourcesDePartie extends Panneaux implements Observer {
	
	private JLabel nombreDeGraines;
	private JLabel lblCartesAvancee;
	private JLabel lblCartesNormale;
	private int nbreDeCartesNormale;
	private int nbreDeCartesAvancee;
	private ParametresDePartie referenceParametres;
	private Ressources referenceImages;
	private VueImage[] vueGraines;
	private VueImage vueGeant;
	private VueImage vueDosAvancee;
	private VueImage vueDosNorm;
	
	public VuePaquetDeRessourcesDePartie(ParametresDePartie parametresDePartie, Ressources ressources){
		this.setPreferredSize(new Dimension(480, 250));
		parametresDePartie.getPaquetDePartie().addObserver(this);
		
		this.referenceParametres = parametresDePartie;
		this.referenceImages = ressources;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1 +="Graines "+ this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		vueGraines = new VueImage[this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir()];
		for (int i=0; i<this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();i++){
			vueGraines[i]= new VueImage(referenceImages.getImageGraine(), 24, 10);
			this.ajoutPanneau(vueGraines[i], 75 + (i%4)*26, 18+ (i/4)*11);
		}
		vueGeant = new VueImage(referenceImages.getImageGeant(),63,108);
		this.ajoutPanneau(vueGeant, 0, 0);
		this.lblCartesNormale = new JLabel();
		this.nbreDeCartesNormale = parametresDePartie.getPaquetDePartie().getNombreCartesNormales();
		String tempTexte2 = new String();
		tempTexte2 += "Cartes Normales : "+ this.nbreDeCartesNormale;
		this.lblCartesNormale.setText(tempTexte2);
		this.ajoutPanneau(lblCartesNormale, 200, 0);
		vueDosNorm = new VueImage(referenceImages.getImageDosGeant(),80,80);
		this.ajoutPanneau(vueDosNorm, 220, 80);
			if (parametresDePartie.getTypePartie() == StatutPartie.avancee){
				this.lblCartesAvancee = new JLabel();
				this.nbreDeCartesAvancee = parametresDePartie.getPaquetDePartie().getNombreCartesAvancees();
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

	public void update(Observable o, Object arg) {
		String tempTexte1 = new String();
		tempTexte1 +="Graines "+ this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();
		this.remove(this.nombreDeGraines);
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		for (int i=0; i<vueGraines.length;i++){
			this.remove(vueGraines[i]);
		}
		vueGraines = new VueImage[this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir()];
		for (int i=0; i<this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();i++){
			vueGraines[i]= new VueImage(referenceImages.getImageGraine(), 24, 10);
			this.ajoutPanneau(vueGraines[i], 75 + (i%4)*26, 18+ (i/4)*11);
		}
		this.remove(this.lblCartesNormale);
		this.lblCartesNormale = new JLabel();
		this.nbreDeCartesNormale = this.referenceParametres.getPaquetDePartie().getNombreCartesNormales();
		String tempTexte2 = new String();
		tempTexte2 += "Cartes Normales : "+ this.nbreDeCartesNormale;
		this.lblCartesNormale.setText(tempTexte2);
		this.ajoutPanneau(lblCartesNormale, 200, 0);
			if (this.referenceParametres.getTypePartie() == StatutPartie.avancee){
				this.remove(this.lblCartesAvancee);
				this.lblCartesAvancee = new JLabel();
				this.nbreDeCartesAvancee = this.referenceParametres.getPaquetDePartie().getNombreCartesAvancees();
				String tempTexte3 = "";
				tempTexte3 = "Cartes Avancée : "+ this.nbreDeCartesAvancee;
				this.lblCartesAvancee.setText(tempTexte3);
				this.ajoutPanneau(lblCartesAvancee, 375, 0);
			}
		
		
	}
	

}
