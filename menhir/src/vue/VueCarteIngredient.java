package vue;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

import modele.Carte;
import modele.CarteIngredient;
import ressources.Ressources;

/**
 * Représentation Graphique d'une Carte Ingrédient
 */
public class VueCarteIngredient extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -8695119206359500444L;
	
	/**
	 * Tableau de 2 dimensions qui contient les puissances des 3 
	 * actions (farfadet, engrais, géant) en fonction des saisons
	 */
	private int[][] puissanceActions;
	
	/**
	 * Nom de la carte
	 */
	private String nomCarte;

	/**
	 * Créer la vue graphique d'une carte Ingredient
	 * @param carte la carte associé
	 * @param ressources l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non à IA  
	 */
	public VueCarteIngredient(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.nomCarte = carte.getNom();
		this.puissanceActions = ((CarteIngredient) carte).getPuissanceActions();
		if (this.nomCarte.equals("Chant de Sirène")) {
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

		this.imageFaceCarte = ressources.redimImage(imageFaceCarte, h, l);
		this.imageDos = ressources.redimImage(ressources.getImageDosGeant(), h, l);
		if (!this.hidden) {
			for (int i = 0; i < this.puissanceActions.length; i++) {
				for (int j = 0; j < this.puissanceActions[i].length; j++) {
					JLabel force = new JLabel(String.valueOf(this.puissanceActions[i][j]));
					// force.setPreferredSize(new Dimension(5,5));
					force.setForeground(Color.PINK);
					this.ajoutPanneau(force, (5 * l * 10 / 107) + i * (l * 10 / 82),
							(4 * h * 10 / 67) + j * (h * 100 / 860));
				}
			}
		}
	}

	/**
	 * Dessine la carte Ingredient(de face ou de dos)
	 * à partir d'une image
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
