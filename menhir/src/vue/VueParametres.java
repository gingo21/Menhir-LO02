package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Difficulte;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.PaquetDeRessourcesDePartie;
import modele.ParametresDePartie;
import modele.StatutPartie;

public class VueParametres extends JDialog implements Observer {

	private JLabel labelNombreDeJoueur = new JLabel("Nombre de Joueurs");
	private JComboBox choixNombreDeJoueurs;
	private JLabel labelTypePartie = new JLabel("Type de partie");
	private JComboBox typePartie;
	private JLabel labelNomDuJoueurReel = new JLabel("Votre Nom ?");
	private JTextArea choixNomDuJoueurReel;
	private ArrayList<JLabel> labelsDifficultesIAs;
	private ArrayList<JComboBox> choixDifficultesIAs;
	private JButton boutonAppliquer = new JButton("Appliquer");
	private JButton boutonQuitter = new JButton("Quitter");
	private JPanel fenetre;

	private ParametresDePartie referenceParametresDePartie;

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	// TODO ajouter action listener pour combobox
	public VueParametres(JFrame owner, String title, boolean modal, ParametresDePartie parametresDePartie) { // TODO
																												// rajouter
																												// texte
																												// explicatif
																												// au
																												// début
																												// avant
																												// paramétrage
		super(owner, title, modal);
		this.setLocationRelativeTo(null); // null-> centre ecran
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.referenceParametresDePartie = parametresDePartie;
		this.fenetre = new JPanel();
		this.fenetre.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 400));

		// Nombre de Joueur
		String[] chiffres = { "2", "3", "4", "5", "6" };
		this.choixNombreDeJoueurs = new JComboBox(chiffres);
		this.choixNombreDeJoueurs.setSelectedIndex(parametresDePartie.getNombreDeJoueurs() - 2);
		this.choixNombreDeJoueurs.setPreferredSize(new Dimension(50, 20));

		// partie rapide ou avancee
		String[] type = { "Rapide", "Avancée" };
		this.typePartie = new JComboBox(type);
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
			this.typePartie.setSelectedIndex(1);
		} else {
			this.typePartie.setSelectedIndex(0);
		}
		this.typePartie.setPreferredSize(new Dimension(100, 20));

		// Nom
		this.choixNomDuJoueurReel = new JTextArea();
		this.choixNomDuJoueurReel.setEditable(true);
		this.choixNomDuJoueurReel.setPreferredSize(new Dimension(100, 20));
		this.choixNomDuJoueurReel.setText(parametresDePartie.getListeJoueurs().get(0).getNom());

		// Joueurs virtuels
		this.labelsDifficultesIAs = new ArrayList<JLabel>();
		this.choixDifficultesIAs = new ArrayList<JComboBox>();
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur instanceof JoueurVirtuel) {
				JLabel tempLabel = new JLabel("Difficulté de " + tempJoueur.getNom());
				this.labelsDifficultesIAs.add(tempLabel);
				String[] difficulte = { "Facile", "Normale" };
				JComboBox tempCombo = new JComboBox(difficulte);
				if (((JoueurVirtuel) tempJoueur).getDifficulte() == Difficulte.facile) {
					tempCombo.setSelectedIndex(0);
				} else if (((JoueurVirtuel) tempJoueur).getDifficulte() == Difficulte.normale) {
					tempCombo.setSelectedIndex(1);
				} else {
					tempCombo.setSelectedIndex(2);
				}
				tempCombo.setPreferredSize(new Dimension(100, 20));
				this.choixDifficultesIAs.add(tempCombo);
			}
		}

		this.fenetre.add(this.labelNombreDeJoueur);
		this.fenetre.add(this.choixNombreDeJoueurs);
		this.fenetre.add(this.labelTypePartie);
		this.fenetre.add(this.typePartie);
		this.fenetre.add(this.labelNomDuJoueurReel);
		this.fenetre.add(this.choixNomDuJoueurReel);
		for(int i=0; i<this.labelsDifficultesIAs.size(); i++)
		{
			this.fenetre.add(this.labelsDifficultesIAs.get(i));
			this.fenetre.add(this.choixDifficultesIAs.get(i));
		}
		fenetre.add(this.boutonAppliquer);
		fenetre.add(this.boutonQuitter);

		this.boutonAppliquer.addActionListener(new AppliquerBoutonListener());
		this.boutonQuitter.addActionListener(new QuitterBoutonListener());

		ActionListener changerNombreDeJoueurs = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(int i=0; i<VueParametres.this.labelsDifficultesIAs.size(); i++)
				{
					VueParametres.this.fenetre.remove(VueParametres.this.labelsDifficultesIAs.get(i));
					VueParametres.this.fenetre.remove(VueParametres.this.choixDifficultesIAs.get(i));
				}
				fenetre.remove(VueParametres.this.boutonAppliquer);
				fenetre.remove(VueParametres.this.boutonQuitter);
				
				VueParametres.this.choixDifficultesIAs.clear();
				VueParametres.this.labelsDifficultesIAs.clear();
				for (int i=1; i<VueParametres.this.choixNombreDeJoueurs.getSelectedIndex() + 2; i++) {
					JLabel tempLabel = new JLabel("Difficulté de IA" + i);
					VueParametres.this.labelsDifficultesIAs.add(tempLabel);
					String[] difficulte = {"Facile", "Normale"};
					JComboBox tempCombo = new JComboBox(difficulte);	
					tempCombo.setPreferredSize(new Dimension(100, 20));
					VueParametres.this.choixDifficultesIAs.add(tempCombo);
					}
				
				for(int i=0; i<VueParametres.this.labelsDifficultesIAs.size(); i++)
				{
					VueParametres.this.fenetre.add(VueParametres.this.labelsDifficultesIAs.get(i));
					VueParametres.this.fenetre.add(VueParametres.this.choixDifficultesIAs.get(i));
				}
				fenetre.add(VueParametres.this.boutonAppliquer);
				fenetre.add(VueParametres.this.boutonQuitter);
				VueParametres.this.fenetre.updateUI();
			}
		};

		choixNombreDeJoueurs.addActionListener(changerNombreDeJoueurs);

		panel.add(this.fenetre);

		this.getContentPane().add(panel);
		this.pack();

	}

	class AppliquerBoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			VueParametres.this.enregistrerParametres();
		}
	}

	class QuitterBoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			VueParametres.this.setVisible(false);
		}
	}

	public synchronized void enregistrerParametres() {

		try {
			this.referenceParametresDePartie.setNombreDeJoueurs(this.choixNombreDeJoueurs.getSelectedIndex() + 2);
			if (this.typePartie.getSelectedIndex() == 0) {
				this.referenceParametresDePartie.setTypePartie(StatutPartie.rapide);
			} else {
				this.referenceParametresDePartie.setTypePartie(StatutPartie.avancee);
			}
			this.referenceParametresDePartie
					.setPaquetDePartie(new PaquetDeRessourcesDePartie(this.referenceParametresDePartie.getTypePartie(),
							this.referenceParametresDePartie.getNombreDeJoueurs()));
			this.referenceParametresDePartie.miseAJourListeJoueurs();
			this.referenceParametresDePartie.getJoueurReel().setNom(this.choixNomDuJoueurReel.getText());
			for (Iterator<Joueur> it = this.referenceParametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur instanceof JoueurVirtuel) {
					if (tempJoueur.getNom().contains("IA1")) {
						if (this.choixDifficultesIAs.get(0).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					} else if (tempJoueur.getNom().contains("IA2")) {
						if (this.choixDifficultesIAs.get(1).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					} else if (tempJoueur.getNom().contains("IA3")) {
						if (this.choixDifficultesIAs.get(2).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					} else if (tempJoueur.getNom().contains("IA4")) {
						if (this.choixDifficultesIAs.get(3).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					} else if (tempJoueur.getNom().contains("IA5")) {
						if (this.choixDifficultesIAs.get(4).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					} else if (tempJoueur.getNom().contains("IA6")) {
						if (this.choixDifficultesIAs.get(5).getSelectedItem().toString().contains("Facile")) {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.facile);
						} else {
							((JoueurVirtuel) tempJoueur).setDifficulte(Difficulte.normale);
						}
					}
				}
			}
			this.referenceParametresDePartie.saveParametres();

		} catch (NullPointerException e) {
			this.referenceParametresDePartie.parametresParDefaut();
			e.printStackTrace();
		}
	}
}
