
package vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import modele.Carte;
import modele.CarteAlliee;
import ressources.Ressources;

/**
 * Repr�sentation graphique d'une carte alli�e 
 * H�rite de VueCarte
 */
public abstract class VueCarteAliee extends VueCarte {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -8372982092938359391L;
	
	/**
	 * Tableau des puissances pour chaque saison 
	 */
	private int[] puissanceActionsAlliee;

	/**
	 * Cr�e Vue Graphique d'une carte Alli�e
	 * @param carte la carte associ�
	 * @param ressources largeur'ensemble des ressources images
	 * @param hauteur hauteur de la carte
	 * @param largeur largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteAliee(Carte carte, Ressources ressources, int hauteur, int largeur, boolean IA) {
		super(carte, ressources, hauteur, largeur, IA);
		this.puissanceActionsAlliee = ((CarteAlliee) carte).getPuissanceActions();
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), hauteur, largeur);
	}
	
	/**
	 * Dessine la carte alli�e cach�e ou non
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!this.hidden) {
			g.drawImage(imageFaceCarte, 0, 0, this);
			for (int i = 0; i < this.puissanceActionsAlliee.length; i++) {
				JLabel force = new JLabel(String.valueOf(this.puissanceActionsAlliee[i]));
				force.setForeground(Color.pink);
				this.ajoutPanneau(force, 44 + i * 18, 124);
			}
		} else {
			g.drawImage(imageDos, 0, 0, this);
		}

	}
}
