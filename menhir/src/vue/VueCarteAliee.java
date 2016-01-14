package vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import Ressources.Ressources;
import modele.Carte;
import modele.CarteAlliee;

/**
 * Repr�sentation graphique d'une carte alli�e 
 * H�rite de VueCarte
 */
public abstract class VueCarteAliee extends VueCarte {
	private static final long serialVersionUID = -8372982092938359391L;
	
	/**
	 * Tableau des puissances pour chaque saison 
	 */
	private int[] puissanceActionsAlliee;

	/**
	 * Cr�e Vue Graphique d'une carte Alli�e
	 * @param carte la carte associ�
	 * @param ressources l'ensemble des ressources images
	 * @param h hauteur de la carte
	 * @param l largeur de la carte
	 * @param IA carte appartenant ou non � IA  
	 */
	public VueCarteAliee(Carte carte, Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.puissanceActionsAlliee = ((CarteAlliee) carte).getPuissanceActions();
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), h, l);
	}
	
	/**
	 * Dessine la carte alli�e de face ou de dos
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
