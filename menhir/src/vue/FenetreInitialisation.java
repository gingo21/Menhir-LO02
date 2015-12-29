package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Joueur;

public class FenetreInitialisation extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Joueur joueur;
	private JTextArea nom;
	private JPanel fenetre; 
	private JButton jouer;
	private JButton parametrer;
	private JButton quitter;
	
	public FenetreInitialisation(JFrame owner, String title, boolean modal) {  
		super(owner, title, modal);
		this.setSize(new Dimension(400,200));
		this.setLocationRelativeTo(null); //null-> centre ecran
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		this.setLayout(new GridLayout(3,1,0,40));
		//this.setLayout(new FlowLayout());
		jouer = new JButton("Jouer");
		jouer.setSize(50, 10);
		parametrer = new JButton("Paramétrer");
		quitter = new JButton("Quitter");
		Container contener = this.getContentPane();
		contener.add(jouer);
		contener.add(parametrer);
		contener.add(quitter);
		//contener.add(jouer,"North");
		//contener.add(parametrer,"Center");
		//contener.add(quitter,"South");
		/* (test d'un gestionnaire de mise en forme)
		Box colonne = Box.createVerticalBox();
		contener.add(colonne);
		colonne.add(jouer);
		colonne.add(Box.createVerticalStrut(30));
		colonne.add(parametrer);
		colonne.add(Box.createVerticalStrut(30));
		colonne.add(Box.createGlue());
		colonne.add(quitter);
		
		jouer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				System.out.println("appui sur jouer");
				FenetrePrincipal gameFen = new FenetrePrincipal();
			}
		});
		parametrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				VueParametres param = new VueParametres();
				
			}
		});
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		//
		*/
		quitter.addActionListener(this);
		jouer.addActionListener(this);
		parametrer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource().equals(jouer)){
			this.dispose();
			FenetrePrincipal gameFen = new FenetrePrincipal();
			
		}
		if (ev.getSource().equals(parametrer)){
			VueParametres param = new VueParametres();
			this.setVisible(false);
		}
		if(ev.getSource().equals(quitter))
			System.exit(0);
				
		
	}
}
	

