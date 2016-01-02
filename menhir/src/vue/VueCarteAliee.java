package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Ressources.Ressources;
import modele.Carte;
import modele.CarteAlliee;
import modele.CarteChiensDeGarde;


public abstract class VueCarteAliee extends VueCarte{
	private int[] puissanceActionsAlliee;
	public VueCarteAliee(Carte carte,Ressources ressources, int h, int l, boolean IA) {
		super(carte, ressources, h, l, IA);
		this.puissanceActionsAlliee = ((CarteAlliee) carte).getPuissanceActions();
		this.imageDos = ressources.redimImage(ressources.getImageDosLutin(), h, l);
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
			g.drawImage(imageDos, 0, 0, this);
		}


	 }
}
