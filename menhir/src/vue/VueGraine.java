package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class VueGraine extends Panneaux{
	private Image image; 
	public  VueGraine(Image image, int h, int l){
		image = image;
		this.setPreferredSize(new Dimension(h,l));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
