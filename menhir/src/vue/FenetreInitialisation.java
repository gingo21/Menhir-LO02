package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Joueur;

public class FenetreInitialisation extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Joueur joueur;
	private JTextArea nom;
	private JPanel fenetre; 
	private JButton ok; 	
	
	private JTextArea nb;//ou combobox?
	private JComboBox typePartie;
	private JLabel label = new JLabel("Type de partie");
	
																			   //TODO ajouter action listener pour combobox
	public FenetreInitialisation(JFrame owner, String title, boolean modal) {  //TODO rajouter texte explicatif au début avant paramétrage
		super(owner, title, modal);
		this.setLocationRelativeTo(null); //null-> centre ecran
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10)); 
		
		JPanel panel = new JPanel();
		
		//Nom 
		JLabel name = new JLabel("Votre nom?");
		nom = new JTextArea();
		nom.setEditable(true);
		
		//nombre de joueurs virtuels
		JLabel nombre = new JLabel("Combien de joueurs virtuels?");
		nb = new JTextArea();
		nb.setText("nb entre 1 et 5");
		nb.setEditable(true);
		
		ok = new JButton("Ok");
		
		//partie rapide ou avancée
		String[] type = {"Rapide", "Avancée"};
		typePartie = new JComboBox(type);
		typePartie.setPreferredSize(new Dimension(100, 20));
		
		fenetre.add(name);
		fenetre.add(nom);
		fenetre.add(nombre);
		fenetre.add(nb);
		fenetre.add(label);
		fenetre.add(typePartie);
		fenetre.add(ok); //TODO centrer le ok
		
		panel.add(fenetre);
		
		this.getContentPane().add(panel);
		this.pack(); 
		
	}
}
	

