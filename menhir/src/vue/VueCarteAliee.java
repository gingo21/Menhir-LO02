package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modele.Carte;
import modele.CarteAlliee;
import modele.CarteChiensDeGarde;


public class VueCarteAliee extends VueCarte{
	private int[] puissanceActionsAlliee;
	public VueCarteAliee(Carte carte) {
		super(carte);
		this.puissanceActionsAlliee = ((CarteAlliee) carte).getPuissanceActions();
		//imageDosCarte
		try {
			imageDosCarte = ImageIO.read( new File("src/Ressources/DosCarteAvancee.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageDosCarte = redimImage(imageDosCarte, HAUTEUR, LARGEUR);

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!this.hidden){
			g.drawImage(imageFaceCarte, 0, 0, this);
	         for (int i = 0; i < this.puissanceActionsAlliee.length; i++) {
	              JLabel force = new JLabel(String.valueOf(this.puissanceActionsAlliee[i]));
	              force.setForeground(Color.white);
	              this.ajoutPanneau(force,44 + i*18,124); 
	         }
		}
		else{
			g.drawImage(imageDosCarte, 0, 0, this);
		}


	 }
}