package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import Ressources.Ressources;
import modele.ParametresDePartie;
import modele.Partie;

public class FenetreInitialisation extends JDialog {
	
	private static final long serialVersionUID = 7574373434248L;
	private JButton jouer;
	private JButton parametrer;
	private JButton quitter;
	private VueParametres vueParametres;
	
	public FenetreInitialisation(JFrame owner, String title, boolean modal, final ParametresDePartie parametresDePartie) {  
		super(owner, title, modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setResizable(false);
	    this.setFocusable(true);
		this.setSize(new Dimension(400,200));
		this.setLocationRelativeTo(null); //null-> centre ecran
		this.setLayout(new GridLayout(3,1,0,40));
		
		jouer = new JButton("Jouer");
		jouer.setSize(50, 10);
		parametrer = new JButton("Param�tres");
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
	

