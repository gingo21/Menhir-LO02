package vue;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

import modele.Carte;
import modele.CarteIngredient;
import ressources.Ressources;

/**
 * Repr�sentation Graphique d'une Carte Ingr�dient
 */
public class VueCarteIngredient extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -8695119206359500444L;
	
	/**
	 * Tableau de 2 dimensions qui contient les puissances des 3 
	 * actions (farfadet, engrais, g�ant) en fonction des saisons
	 */
	private int[][] puissanceActions;
	
	/**
	 * Nom de la carte
	 */
	private String nomCarte;

	/**
	 * Cr�er la vue graphique d'une carte Ingredient
	 * @param carte la carte associ�
	 * @param ressources l'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteIngredient(Carte carte, Ressources ressources, int hauteur, int largeur, boolean IA) {
		super(carte, ressources, hauteur, largeur, IA);
		this.nomCarte = carte.getNom();
		this.puissanceActions = ((CarteIngredient) carte).getPuissanceActions();
		if (this.nomCarte.equals("Chant de Sir�ne")) {
			this.imageFaceCarte = ressources.getChantDeSirene();
		} else if (this.nomCarte.equals("Esprit de Dolmen")) {
			this.imageFaceCarte = ressources.getEspritDeDolmen();
		} else if (this.nomCarte.equals("Fontaine Eau Pure")) {
			this.imageFaceCarte = ressources.getFontaineDEauPure();
		} else if (this.nomCarte.equals("Larmes De Dryade")) {
			this.imageFaceCarte = ressources.getLarmesDeDryade();
		} else if (this.nomCarte.equals("Poudre Or")) {
			this.imageFaceCarte = ressources.getPoudreDOr();
		} else if (this.nomCarte.equals("Racines Arc En Ciel")) {
			this.imageFaceCarte = ressources.getRacinesDArcEnCiel();
		} else if (this.nomCarte.equals("Rayon De Lune")) {
			this.imageFaceCarte = ressources.getRayonDeLune();
		} else {
			this.imageFaceCarte = ressources.getRiresDeFees();
		}

		this.imageFaceCarte = ressources.redimImage(imageFaceCarte, hauteur, largeur);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), hauteur, largeur);
		if (!this.hidden) {
			for (int i = 0; i < this.puissanceActions.length; i++) {
				for (int j = 0; j < this.puissanceActions[i].length; j++) {
					JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
					// force.setPreferredSize(new Dimension(5,5));
					force.setForeground(Color.PINK);
					this.ajoutPanneau(force, (5 * largeur * 10 / 107) + i * (largeur * 10 / 82),
							(4 * hauteur * 10 / 67) + j * (hauteur * 100 / 860));
				}
			}
		}
	}

	/**
	 * Dessine la carte Ingredient(de face ou de dos)
	 * � partir d'une image
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!this.hidden) {
			g.drawImage(imageFaceCarte, 0, 0, this);
		} else {
			g.drawImage(imageDos, 0, 0, this);
		}

	}

}
