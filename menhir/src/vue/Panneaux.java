package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panneaux extends JPanel implements MouseListener{
	
	public Panneaux(){
		super();
		this.setLayout(null); 
	}
	
	private static final long serialVersionUID = 1L;

	public void ajoutPanneau(Component c, int x, int y){
		 Dimension size = c.getPreferredSize();
	     c.setBounds(x,y, size.width, size.height);
	     this.add(c);
	     this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent ev) {
		// TODO Auto-generated method stub
				System.out.println(ev.getX());
				System.out.println(ev.getY());
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
