package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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

/**
 * Cette classe permet de d�finir la fen�tre o� le joueur va choisir
 * les param�tres du jeu
 */
public class VueParametres extends JDialog {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = -4191871631149820610L;

	/**
	 * Label texte nombre de joueurs
	 */
	private JLabel labelNombreDeJoueur = new JLabel("Nombre de Joueurs");

	/**
	 * JComboBox qui permet au joueur r�el de sp�cifier le nombre
	 * de joueur total pour la partie
	 */
	private JComboBox<String> choixNombreDeJoueurs;

	/**
	 * Label texte 
	 */
	private JLabel labelTypePartie = new JLabel("Type de partie");

	/**
	 * JComboBox qui permet au joueur r�el de choisir le type de partie
	 */
	private JComboBox<String> typePartie;

	/**
	 * Label texte 
	 */
	private JLabel labelNomDuJoueurReel = new JLabel("Votre Nom ?");

	/**
	 * Champ de texte ou le joueur r�el peut �crire son nome
	 */
	private JTextArea choixNomDuJoueurReel;

	/**
	 * Liste de Label texte des difficult�s des IAs
	 */
	private ArrayList<JLabel> labelsDifficultesIAs;

	/**
	 * JComboBox qui permet au joueur r�el de choisir la difficult�e des IAs
	 */
	private ArrayList<JComboBox<String>> choixDifficultesIAs;

	/**
	 * Boutton appliquer
	 */
	private JButton boutonAppliquer = new JButton("Appliquer");

	/**
	 * Boutton quitter
	 */
	private JButton boutonQuitter = new JButton("Quitter");

	/**
	 * Panneau associ� � la fen�tre des param�tres
	 */
	private JPanel fenetre;

	/**
	 * R�f�rence sur les param�tres de la partie
	 */
	private ParametresDePartie referenceParametresDePartie;

	/**
	 * Constructeur de la fen�tre des param�tres
	 * @param parametresDePartie param�tres de la partie
	 */
	public VueParametres(ParametresDePartie parametresDePartie) {
		this.setTitle("Param�tres");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.referenceParametresDePartie = parametresDePartie;
		this.fenetre = new JPanel();
		this.fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 400));
		String[] chiffres = { "2", "3", "4", "5", "6" };
		this.choixNombreDeJoueurs = new JComboBox<String>(chiffres);
		this.choixNombreDeJoueurs.setSelectedIndex(parametresDePartie.getNombreDeJoueurs() - 2);
		this.choixNombreDeJoueurs.setPreferredSize(new Dimension(50, 20));
		String[] type = { "Rapide", "Avanc�e" };
		this.typePartie = new JComboBox<String>(type);
		if (parametresDePartie.getTypePartie() == StatutPartie.avancee) {
			this.typePartie.setSelectedIndex(1);
		} else {
			this.typePartie.setSelectedIndex(0);
		}
		this.typePartie.setPreferredSize(new Dimension(100, 20));
		this.choixNomDuJoueurReel = new JTextArea();
		this.choixNomDuJoueurReel.setEditable(true);
		this.choixNomDuJoueurReel.setPreferredSize(new Dimension(100, 20));
		this.choixNomDuJoueurReel.setText(parametresDePartie.getListeJoueurs().get(0).getNom());
		this.labelsDifficultesIAs = new ArrayList<JLabel>();
		this.choixDifficultesIAs = new ArrayList<JComboBox<String>>();
		for (Iterator<Joueur> it = parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
			Joueur tempJoueur = it.next();
			if (tempJoueur instanceof JoueurVirtuel) {
				JLabel tempLabel = new JLabel("Difficult� de " + tempJoueur.getNom());
				this.labelsDifficultesIAs.add(tempLabel);
				String[] difficulte = { "Facile", "Normale" };
				JComboBox<String> tempCombo = new JComboBox<String>(difficulte);
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
		for (int i = 0; i < this.labelsDifficultesIAs.size(); i++) {
			this.fenetre.add(this.labelsDifficultesIAs.get(i));
			this.fenetre.add(this.choixDifficultesIAs.get(i));
		}
		fenetre.add(this.boutonAppliquer);
		fenetre.add(this.boutonQuitter);

		this.boutonAppliquer.addActionListener(new AppliquerBoutonListener());
		this.boutonQuitter.addActionListener(new QuitterBoutonListener());

		ActionListener changerNombreDeJoueurs = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < VueParametres.this.labelsDifficultesIAs.size(); i++) {
					VueParametres.this.fenetre.remove(VueParametres.this.labelsDifficultesIAs.get(i));
					VueParametres.this.fenetre.remove(VueParametres.this.choixDifficultesIAs.get(i));
				}
				fenetre.remove(VueParametres.this.boutonAppliquer);
				fenetre.remove(VueParametres.this.boutonQuitter);

				VueParametres.this.choixDifficultesIAs.clear();
				VueParametres.this.labelsDifficultesIAs.clear();
				for (int i = 1; i < VueParametres.this.choixNombreDeJoueurs.getSelectedIndex() + 2; i++) {
					JLabel tempLabel = new JLabel("Difficult� de IA" + i);
					VueParametres.this.labelsDifficultesIAs.add(tempLabel);
					String[] difficulte = { "Facile", "Normale" };
					JComboBox<String> tempCombo = new JComboBox<String>(difficulte);
					tempCombo.setPreferredSize(new Dimension(100, 20));
					VueParametres.this.choixDifficultesIAs.add(tempCombo);
				}

				for (int i = 0; i < VueParametres.this.labelsDifficultesIAs.size(); i++) {
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

		this.setTitle("Param�tres");
		this.getContentPane().add(panel);
		this.pack();

	}

	class AppliquerBoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			VueParametres.this.enregistrerParametres();
			VueParametres.this.setVisible(false);
		}
	}

	class QuitterBoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			VueParametres.this.setVisible(false);
		}
	}

	/**
	 * M�thode qui permet l'enregistrement des param�tres
	 */
	public synchronized void enregistrerParametres() {

		try {
			this.referenceParametresDePartie.setNombreDeJoueurs(this.choixNombreDeJoueurs.getSelectedIndex() + 2);
			if (this.typePartie.getSelectedIndex() == 0) {
				this.referenceParametresDePartie.setTypePartie(StatutPartie.rapide);
				this.referenceParametresDePartie.setNombreDeManches(1);
			} else {
				this.referenceParametresDePartie.setTypePartie(StatutPartie.avancee);
				this.referenceParametresDePartie
				.setNombreDeManches(this.referenceParametresDePartie.getNombreDeJoueurs());
			}
			this.referenceParametresDePartie
			.setPaquetDePartie(new PaquetDeRessourcesDePartie(this.referenceParametresDePartie.getTypePartie(),
					this.referenceParametresDePartie.getNombreDeJoueurs()));
			this.referenceParametresDePartie.miseAJourListeJoueurs(this.choixNomDuJoueurReel.getText());
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
