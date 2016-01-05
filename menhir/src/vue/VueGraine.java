package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class VueGraine extends Panneau {

	private static final long serialVersionUID = 8099382905274265094L;
	private Image image;

	public VueGraine(Image image, int h, int l) {
		this.image = image;
		this.setPreferredSize(new Dimension(h, l));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
