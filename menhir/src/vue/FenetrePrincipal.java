package vue;

import javax.swing.JDialog;

public class FenetrePrincipal extends JDialog {
	
	public FenetrePrincipal(){
		 //super(null, "Menhir", Dialog.ModalityType.MODELESS);

		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setFocusable(true);    
	
	}
}
