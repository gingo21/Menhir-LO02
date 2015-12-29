package vue;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panneaux extends JPanel{
	
	public Panneaux(){
		super();
		this.setLayout(null); 
	}
	
	private static final long serialVersionUID = 1L;

	public void ajoutPanneau(Component c, int x, int y){
		 Dimension size = c.getPreferredSize();
	     c.setBounds(x,y, size.width, size.height);
	     this.add(c);
	}
}
