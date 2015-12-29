package vue;

import java.awt.Dimension;

import javax.swing.JDialog;

public class FenetrePrincipal extends JDialog {
	
	public FenetrePrincipal(){
		 //super(null, "Menhir", Dialog.ModalityType.MODELESS);

		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setFocusable(true);  
	    this.setSize(new Dimension(800,600));
	    //this.setLayout(null);
	
	}
}
