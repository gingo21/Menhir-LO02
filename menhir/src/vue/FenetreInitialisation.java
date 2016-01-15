
package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import modele.ParametresDePartie;
import modele.Partie;
import ressources.Ressources;

/**
 * Fenêtre de démarage du jeu
 */
public class FenetreInitialisation extends JDialog {
	
	private static final long serialVersionUID = 7574373434248L;
	/**
	 * Bouton jouer
	 */
	private JButton jouer;
	
	/**
	 * Bouton paramétrer
	 */
	private JButton parametrer;
	
	/**
	 * Bouton quitter
	 */
	private JButton quitter;
	
	/**
	 * Panneau pour les paramètres
	 */
	private VueParametres vueParametres;
	
	/**
	 * Crée la fenêtre de démarrage du jeu
	 * @param parametresDePartie Paramètres de la Partie
	 */
	public FenetreInitialisation(final ParametresDePartie parametresDePartie) {  
		this.setTitle("Menhir");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setResizable(false);
	    this.setFocusable(true);
		this.setSize(new Dimension(400,200));
		this.setLocationRelativeTo(null); 
		this.setLayout(new GridLayout(3,1,0,40));
		
		jouer = new JButton("Jouer");
		jouer.setSize(50, 10);
		parametrer = new JButton("Paramètres");
		quitter = new JButton("Quitter");
		this.vueParametres = new VueParametres(null, "conf", true, parametresDePartie);
		this.vueParametres.setVisible(false);
		
		Container contener = this.getContentPane();
		contener.add(jouer);
		contener.add(parametrer);
		contener.add(quitter);
		
		
		
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!vueParametres.isVisible()) {
					FenetreInitialisation.this.setVisible(false);
					Partie partie = new Partie(parametresDePartie, false);
					Ressources ressources = new Ressources();
					VueJeu vueJeu = new VueJeu(parametresDePartie, partie, ressources);
					vueJeu.setTitle("Menhir");
					vueJeu.setVisible(true);
					Thread thrPartie = new Thread(partie);
					thrPartie.start();
				}
			}
		});
		parametrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenetreInitialisation.this.vueParametres.setVisible(true);
			}
		});
	}
}
	

