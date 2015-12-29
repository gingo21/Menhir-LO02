package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Joueur;
import modele.ParametresDePartie;
import modele.StatutPartie;


public class VueParametres extends JDialog implements Observer {
	
	
	private JLabel labelNombreDeJoueur = new JLabel("Nombre de Joueurs");
	private JComboBox choixNombreDeJoueur;
	private JLabel labelTypePartie = new JLabel("Type de partie");
	private JComboBox typePartie;
	private JLabel labelNomDuJoueurReel = new JLabel("Votre Nom ?");
	private JTextArea choixNomDuJoueurReel;
	private ArrayList<JLabel> labelsDifficultesIAs;
	private ArrayList<JTextArea> choixDifficultesIAs;
	private JButton boutonAppliquer = new JButton("Appliquer");
	private JButton boutonAnnuler = new JButton("Annuler");
	private JButton boutonQuitter = new JButton("Quitter");
	private JPanel fenetre;
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
	
																			   //TODO ajouter action listener pour combobox
	public VueParametres(JFrame owner, String title, boolean modal, ParametresDePartie parametresDePartie) {  //TODO rajouter texte explicatif au début avant paramétrage
		super(owner, title, modal);
		this.setLocationRelativeTo(null); //null-> centre ecran
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		this.fenetre = new JPanel();
		this.fenetre.setLayout(new GridLayout(0, 2, 10, 10)); 
		
		JPanel panel = new JPanel();
		
		//Nombre de Joueur
		String[] chiffres = {"2", "3", "4", "5", "6"};
		this.choixNombreDeJoueur = new JComboBox(chiffres);
		this.choixNombreDeJoueur.setSelectedIndex(parametresDePartie.getNombreDeJoueurs()-2);
		this.choixNombreDeJoueur.setPreferredSize(new Dimension(50, 20));
		
		//partie rapide ou avancee
		String[] type = {"Rapide", "Avanc�e"};
		this.typePartie = new JComboBox(type);
		if(parametresDePartie.getTypePartie() == StatutPartie.avancee) {
			this.typePartie.setSelectedIndex(0);
		} else {
			this.typePartie.setSelectedIndex(1);
		}
		this.typePartie.setPreferredSize(new Dimension(100, 20));
		
		//Nom 
		this.choixNomDuJoueurReel = new JTextArea();
		this.choixNomDuJoueurReel.setEditable(true);
		this.choixNomDuJoueurReel.setPreferredSize(new Dimension(100, 20));
		this.choixNomDuJoueurReel.setText(parametresDePartie.getListeJoueurs().get(0).getNom());
		
		//nombre de joueurs virtuels

		
		fenetre.add(this.labelNombreDeJoueur);
		fenetre.add(this.choixNombreDeJoueur);
		fenetre.add(this.labelTypePartie);
		fenetre.add(this.typePartie);
		fenetre.add(this.labelNomDuJoueurReel);
		fenetre.add(this.choixNomDuJoueurReel);
		fenetre.add(this.boutonAppliquer); //TODO centrer le ok
		fenetre.add(this.boutonAnnuler);
		fenetre.add(this.boutonQuitter);
		
		panel.add(fenetre);
		
		this.getContentPane().add(panel);
		this.pack(); 
		
	}
}