
package vue;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Fenetre principal de notre jeu
 */
public class FenetrePrincipal extends JFrame {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = 21316163498785226L;
	public static final Dimension DIMENSION_ECRAN = new Dimension(1200, 725);

	public FenetrePrincipal() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setFocusable(true);
		this.setSize(DIMENSION_ECRAN);
		this.setLocationRelativeTo(null);

	}
}
