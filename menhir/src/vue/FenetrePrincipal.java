package vue;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class FenetrePrincipal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FenetrePrincipal(){
		 //super(null, "Menhir", Dialog.ModalityType.MODELESS);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setFocusable(true);  
	    this.setSize(new Dimension(1200,725));
	    this.setLocationRelativeTo(null);
	    //this.setLayout(null);
	
	}
}
