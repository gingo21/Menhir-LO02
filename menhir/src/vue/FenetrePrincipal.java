package vue;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Classe qui va permettre de créer la vueJeu
 * 
 *
 */
public class FenetrePrincipal extends JFrame {
	
	private static final long serialVersionUID = 213498785226L;
	public static final Dimension DIMENSION_ECRAN = new Dimension(1200,725);
	
	
	public FenetrePrincipal(){
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setFocusable(true);  
	    this.setSize(DIMENSION_ECRAN);
	    this.setLocationRelativeTo(null);
	
	}
}
