
package vue;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Classe utile pour créer un Panneau(Jpanel) et 
 * ajouter ses composants
 */
public class Panneau extends JPanel {

	public Panneau() {
		super();
		this.setLayout(null);
	}
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = 4985613218494544L;
	
	/**
	 * Ajoute un composant dans le panneau
     * @param c le composant
     * @param x coordonnées X
     * @param y coordonnées Y
	 */
	public void ajoutPanneau(Component c, int x, int y) {
		Dimension size = c.getPreferredSize();
		c.setBounds(x, y, size.width, size.height);
		this.add(c);
	}
}
