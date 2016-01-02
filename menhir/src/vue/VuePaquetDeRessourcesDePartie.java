package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Ressources.Ressources;
import modele.PaquetDeRessourcesDeJoueur;
import modele.PaquetDeRessourcesDePartie;
import modele.ParametresDePartie;
import modele.StatutPartie;

public class VuePaquetDeRessourcesDePartie extends Panneau implements Observer {

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

	public VuePaquetDeRessourcesDePartie(ParametresDePartie parametresDePartie, Ressources ressources) {
		this.setPreferredSize(new Dimension(480, 250));
		this.setDoubleBuffered(true);
		parametresDePartie.getPaquetDePartie().addObserver(this);

		this.referenceParametres = parametresDePartie;
		this.referenceImages = ressources;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1 += "Graines " + this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		vueGraines = new VueImage[this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir()];
		for (int i = 0; i < this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir(); i++) {
			vueGraines[i] = new VueImage(referenceImages.getImageGraine(), 24, 10);
			this.ajoutPanneau(vueGraines[i], 75 + (i % 4) * 26, 18 + (i / 4) * 11);
		}
		vueGeant = new VueImage(referenceImages.getImageGeant(), 63, 108);
		this.ajoutPanneau(vueGeant, 0, 0);
		this.lblCartesNormale = new JLabel();
		this.nbreDeCartesNormale = parametresDePartie.getPaquetDePartie().getNombreCartesNormales();
		String tempTexte2 = new String();
		tempTexte2 += "Cartes Normales : " + this.nbreDeCartesNormale;
		this.lblCartesNormale.setText(tempTexte2);
		this.ajoutPanneau(lblCartesNormale, 200, 0);
		vueDosNorm = new VueImage(referenceImages.getImageDosGeant(), 80, 80);
		this.ajoutPanneau(vueDosNorm, 220, 80);
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
			this.lblCartesAvancee = new JLabel();
			this.nbreDeCartesAvancee = parametresDePartie.getPaquetDePartie().getNombreCartesAvancees();
			String tempTexte3 = "";
			tempTexte3 = "Cartes Avancée : " + this.nbreDeCartesAvancee;
			this.lblCartesAvancee.setText(tempTexte3);
			this.ajoutPanneau(lblCartesAvancee, 375, 0);
			this.vueDosAvancee = new VueImage(referenceImages.getImageDosLutin(), 80, 80);
			this.ajoutPanneau(vueDosAvancee, 350, 80);
		}

	}

	public JLabel getNombreDeGraines() {
		return nombreDeGraines;
	}

	public void update(Observable o, Object arg) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				String tempTexte1 = new String();
				tempTexte1 += "Graines " + VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();
				VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.nombreDeGraines);
				VuePaquetDeRessourcesDePartie.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesDePartie.this.ajoutPanneau(nombreDeGraines, 63, 0);
				for (int i = 0; i < vueGraines.length; i++) {
					VuePaquetDeRessourcesDePartie.this.remove(vueGraines[i]);
				}
				vueGraines = new VueImage[VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir()];
				for (int i = 0; i < VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir(); i++) {
					vueGraines[i] = new VueImage(referenceImages.getImageGraine(), 24, 10);
					VuePaquetDeRessourcesDePartie.this.ajoutPanneau(vueGraines[i], 75 + (i % 4) * 26, 18 + (i / 4) * 11);
				}
				VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.lblCartesNormale);
				VuePaquetDeRessourcesDePartie.this.lblCartesNormale = new JLabel();
				VuePaquetDeRessourcesDePartie.this.nbreDeCartesNormale = VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie().getNombreCartesNormales();
				String tempTexte2 = new String();
				tempTexte2 += "Cartes Normales : " + VuePaquetDeRessourcesDePartie.this.nbreDeCartesNormale;
				VuePaquetDeRessourcesDePartie.this.lblCartesNormale.setText(tempTexte2);
				VuePaquetDeRessourcesDePartie.this.ajoutPanneau(lblCartesNormale, 200, 0);
				if (VuePaquetDeRessourcesDePartie.this.referenceParametres.getTypePartie() == StatutPartie.avancee) {
					VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.lblCartesAvancee);
					VuePaquetDeRessourcesDePartie.this.lblCartesAvancee = new JLabel();
					VuePaquetDeRessourcesDePartie.this.nbreDeCartesAvancee = VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie().getNombreCartesAvancees();
					String tempTexte3 = "";
					tempTexte3 = "Cartes Avancée : " + VuePaquetDeRessourcesDePartie.this.nbreDeCartesAvancee;
					VuePaquetDeRessourcesDePartie.this.lblCartesAvancee.setText(tempTexte3);
					VuePaquetDeRessourcesDePartie.this.ajoutPanneau(lblCartesAvancee, 375, 0);
				}
				VuePaquetDeRessourcesDePartie.this.repaint();
				VuePaquetDeRessourcesDePartie.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);
	}

}
