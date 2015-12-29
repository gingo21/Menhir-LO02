package vue;

import java.awt.Dimension;

import javax.swing.JDialog;

public class FenetrePrincipal extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FenetrePrincipal(){
		 //super(null, "Menhir", Dialog.ModalityType.MODELESS);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setFocusable(true);  
	    this.setSize(new Dimension(1360,725));
	    this.setLocationRelativeTo(null);
	    //this.setLayout(null);
	
	}
}
