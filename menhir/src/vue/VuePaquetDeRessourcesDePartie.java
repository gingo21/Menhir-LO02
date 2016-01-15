package vue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import modele.ParametresDePartie;
import modele.StatutPartie;
import ressources.Ressources;

/**
 *Classe représentant les ressources graphiques d'une Partie
 *C'est un panneau(Jlabel) qui va contenir cartes, graines, labels textes et le géant
 *Cette classe observe le paquet de Ressource de cette partie
 */
public class VuePaquetDeRessourcesDePartie extends Panneau implements Observer {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = 4458122718657188781L;
	
	/**
	 * Label texte du nombre de Graines
	 */
	private JLabel nombreDeGraines;
	
	/**
	 * Label texte du nombre de cartes avancées
	 */
	private JLabel lblCartesAvancee;
	
	/**
	 * Label texte du nombre de cartes normales
	 */
	private JLabel lblCartesNormale;
	
	/**
	 *  Nombre de cartes normales
	 */
	private int nbreDeCartesNormale;
	
	/**
	 *  Nombre de cartes avancées
	 */
	private int nbreDeCartesAvancee;
	
	/**
	 *  Référence sur les paramètres de la partie
	 */
	private ParametresDePartie referenceParametres;
	
	/**
	 *  Référence sur les images chargées dans la classe ressource
	 */
	private Ressources referenceImages;
	
	/**
	 *  Liste de Graines(graphique)
	 */
	protected ArrayList<VueImage> graines;
	
	/**
	 *  Vue du géant
	 */
	private VueImage vueGeant;
	
	/**
	 *  Vue du dos d'une carte avancée
	 */
	private VueImage vueDosAvancee;
	
	/**
	 *  Vue du dos d'une carte normale
	 */
	private VueImage vueDosNorm;

	/**
	 *  Constructeur de la vue des Ressources de la partie.
	 */
	public VuePaquetDeRessourcesDePartie(ParametresDePartie parametresDePartie, Ressources ressources) {
		this.setPreferredSize(new Dimension(460, 130));
		this.setDoubleBuffered(true);
		parametresDePartie.getPaquetDePartie().addObserver(this);

		this.referenceParametres = parametresDePartie;
		this.referenceImages = ressources;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1 += "Graines " + this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.ajoutPanneau(nombreDeGraines, 63, 0);
		graines = new ArrayList<VueImage>();
		for (int i = 0; i < this.referenceParametres.getPaquetDePartie().getGrainesDeMenhir(); i++) {
			graines.add(new VueImage(referenceImages.getImageGraine(), 24, 10));
			this.ajoutPanneau(graines.get(i), 75 + (i % 8) * 13, 18 + (i / 8) * 6);
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
		this.ajoutPanneau(vueDosNorm, 220, 40);
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
			this.lblCartesAvancee = new JLabel();
			this.nbreDeCartesAvancee = parametresDePartie.getPaquetDePartie().getNombreCartesAvancees();
			String tempTexte3 = "";
			tempTexte3 = "Cartes Avancées : " + this.nbreDeCartesAvancee;
			this.lblCartesAvancee.setText(tempTexte3);
			this.ajoutPanneau(lblCartesAvancee, 347, 0);
			this.vueDosAvancee = new VueImage(referenceImages.getImageDosLutin(), 80, 80);
			this.ajoutPanneau(vueDosAvancee, 370, 40);
		}

	}
	
	/**
	 * @return nombreDeGraines
	 */
	public JLabel getNombreDeGraines() {
		return nombreDeGraines;
	}

	/**
	 *Rafraichissement graphique selon le modèle Observer/Observable
	 */
	public void update(Observable o, Object arg) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				String tempTexte1 = new String();
				tempTexte1 += "Graines " + VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie()
						.getGrainesDeMenhir();
				VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.nombreDeGraines);
				VuePaquetDeRessourcesDePartie.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesDePartie.this.ajoutPanneau(nombreDeGraines, 63, 0);
				for (Iterator<VueImage> it = graines.iterator(); it.hasNext();) {
					VuePaquetDeRessourcesDePartie.this.remove(it.next());
				}
				graines.clear();
				for (int i = 0; i < VuePaquetDeRessourcesDePartie.this.referenceParametres.getPaquetDePartie()
						.getGrainesDeMenhir(); i++) {
					graines.add(new VueImage(referenceImages.getImageGraine(), 24, 10));
					VuePaquetDeRessourcesDePartie.this.ajoutPanneau(graines.get(i), 75 + (i % 8) * 13,
							18 + (i / 8) * 6);
				}
				VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.lblCartesNormale);
				VuePaquetDeRessourcesDePartie.this.lblCartesNormale = new JLabel();
				VuePaquetDeRessourcesDePartie.this.nbreDeCartesNormale = VuePaquetDeRessourcesDePartie.this.referenceParametres
						.getPaquetDePartie().getNombreCartesNormales();
				String tempTexte2 = new String();
				tempTexte2 += "Cartes Normales : " + VuePaquetDeRessourcesDePartie.this.nbreDeCartesNormale;
				VuePaquetDeRessourcesDePartie.this.lblCartesNormale.setText(tempTexte2);
				VuePaquetDeRessourcesDePartie.this.ajoutPanneau(lblCartesNormale, 200, 0);
				if (VuePaquetDeRessourcesDePartie.this.referenceParametres.getTypePartie() == StatutPartie.avancee) {
					VuePaquetDeRessourcesDePartie.this.remove(VuePaquetDeRessourcesDePartie.this.lblCartesAvancee);
					VuePaquetDeRessourcesDePartie.this.lblCartesAvancee = new JLabel();
					VuePaquetDeRessourcesDePartie.this.nbreDeCartesAvancee = VuePaquetDeRessourcesDePartie.this.referenceParametres
							.getPaquetDePartie().getNombreCartesAvancees();
					String tempTexte3 = "";
					tempTexte3 = "Cartes Avancées : " + VuePaquetDeRessourcesDePartie.this.nbreDeCartesAvancee;
					VuePaquetDeRessourcesDePartie.this.lblCartesAvancee.setText(tempTexte3);
					VuePaquetDeRessourcesDePartie.this.ajoutPanneau(lblCartesAvancee, 347, 0);
				}
				VuePaquetDeRessourcesDePartie.this.repaint();
				VuePaquetDeRessourcesDePartie.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);
	}
	
	/**
	 *  Méthode apellée quand il y a un changement de paquet, et donc
	 *  notre vue graphique doit observer ce nouveau paquet
	 */
	public void changementDePaquet() {
		this.referenceParametres.getPaquetDePartie().addObserver(this);
	}

}
