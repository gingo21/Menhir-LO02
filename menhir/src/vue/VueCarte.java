package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JPanel;

import modele.Carte;

public class VueCarte extends JPanel implements Observer{

	private static final long serialVersionUID = 1L; 
	
	private Carte carte;
	private BufferedImage bf;
	
	
	public VueCarte(String path, Carte carte) {
		this.carte = carte;
		
		//préférence du Jpanel
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setBackground(Color.yellow);
		
		

	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
