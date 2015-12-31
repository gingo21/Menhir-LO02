package vue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.PaquetDeRessourcesDeJoueur;
import modele.Carte;
import modele.CarteIngredient;

public class VuePaquetDeRessourcesDeJoueur extends JPanel implements Observer {
	
	private JLabel nombreDeGraines;
	private JLabel nomDuJoueur;
	private HashMap<String, VueCarte> vuesCartes;
	private PaquetDeRessourcesDeJoueur referencePaquetDeRessourcesDeJoueur;
	
	public VuePaquetDeRessourcesDeJoueur(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur) {
		
		this.setPreferredSize(new Dimension(250, 640));
		
		this.referencePaquetDeRessourcesDeJoueur = paquetDeRessourcesDeJoueur;
		this.nombreDeGraines = new JLabel();
		String tempTexte1 = new String();
		tempTexte1+=paquetDeRessourcesDeJoueur.getGrainesDeMenhir();
		this.nombreDeGraines.setText(tempTexte1);
		this.nomDuJoueur = new JLabel(paquetDeRessourcesDeJoueur.getJoueur().getNom());
		
		Set<String> tempKeys = paquetDeRessourcesDeJoueur.getPaquetsDeCartes().keySet();
		for(Iterator<String> it = tempKeys.iterator(); it.hasNext();) {
			String tempKey = it.next();
			for(Iterator<Carte> yt = paquetDeRessourcesDeJoueur.getPaquetsDeCartes().get(tempKey).iterator(); yt.hasNext();) {
				Carte tempCarte = yt.next();
				VueCarte tempVueCarte;
				if(tempCarte instanceof CarteIngredient) {
					
				} else if(tempCarte instanceof CarteChienDeGardes) {
					
				} else if(tempCarte instanceof CarteIngredient) {
					
				} else if(tempCarte instanceof CarteIngredient) {
					
				}
				vuesCartes
			}
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
