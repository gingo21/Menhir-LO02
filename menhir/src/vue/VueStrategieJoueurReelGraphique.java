package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Ressources.Ressources;
import modele.Carte;
import modele.CarteChiensDeGarde;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;
import modele.ListeAttente;
import modele.Partie;
import modele.StrategieJoueurReelGraphique;
import modele.TypeAction;

public class VueStrategieJoueurReelGraphique extends Panneau implements Observer {

	private JButton boutonAttaqueOui = new JButton("oui");
	private JButton boutonAttaqueNon = new JButton("non");
	private JButton boutonDefenseOui = new JButton("oui");
	private JButton boutonDefenseNon = new JButton("non");
	private JButton boutonChoixMancheOui = new JButton("oui");
	private JButton boutonChoixMancheNon = new JButton("non");
	private JButton boutonEngrais = new JButton("engrais");
	private JButton boutonGeant = new JButton("géant");
	private JButton boutonFarfadet = new JButton("farfadet");
	private JLabel afficheurTexte = new JLabel();
	private JLabel labelCarteEnJeu = new JLabel("Carte en Jeu");
	private VueCarte carteEnJeu;

	public static int TEMPS_DE_REFLEXION = 1000;
	private Ressources referenceRessources;

	public VueStrategieJoueurReelGraphique(StrategieJoueurReelGraphique strategie, Ressources ressources) {
		super();
		strategie.addObserver(this);
		this.referenceRessources = ressources;
		this.setPreferredSize(new Dimension(600, 300));

		this.ajoutPanneau(this.boutonAttaqueOui, 0, 0); // TODO position
		this.ajoutPanneau(this.boutonAttaqueNon, 0, 0);
		this.ajoutPanneau(this.boutonDefenseOui, 0, 0);
		this.ajoutPanneau(this.boutonDefenseNon, 0, 0);
		this.ajoutPanneau(this.boutonChoixMancheOui, 0, 0);
		this.ajoutPanneau(this.boutonChoixMancheNon, 0, 0);
		this.ajoutPanneau(this.boutonEngrais, 0, 0);
		this.ajoutPanneau(this.boutonGeant, 0, 0);
		this.ajoutPanneau(this.boutonFarfadet, 0, 0);
		this.ajoutPanneau(this.afficheurTexte, 0, 0);
		this.ajoutPanneau(this.labelCarteEnJeu, 0, 100);
		this.effacerBoutons();

		ActionListener ouiAttaque = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setAttaquer(true);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonAttaqueOui.addActionListener(ouiAttaque);
		ActionListener nonAttaque = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setAttaquer(false);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonAttaqueNon.addActionListener(nonAttaque);

		ActionListener ouiDefense = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setSeDefendre(true);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonDefenseOui.addActionListener(ouiDefense);
		ActionListener nonDefense = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setSeDefendre(false);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonDefenseNon.addActionListener(nonDefense);

		ActionListener ouiChoixManche = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setChoixCarteAlliee(true);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonChoixMancheOui.addActionListener(ouiChoixManche);
		ActionListener nonChoixManche = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setChoixCarteAlliee(false);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonChoixMancheNon.addActionListener(nonChoixManche);

		ActionListener actionGeant = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.geantGardient);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonGeant.addActionListener(actionGeant);
		ActionListener actionFarfadet = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.farfadet);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonFarfadet.addActionListener(actionFarfadet);
		ActionListener actionEngrais = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.engrais);
				strategie.notify();
				effacerBoutons();
			}
		};
		this.boutonEngrais.addActionListener(actionEngrais);

		this.afficheurTexte.setSize(600, 25);

		this.setVisible(true);
	}

	public void effacerBoutons() {
		this.boutonAttaqueOui.setVisible(false);
		this.boutonAttaqueNon.setVisible(false);
		this.boutonDefenseOui.setVisible(false);
		this.boutonDefenseNon.setVisible(false);
		this.boutonChoixMancheOui.setVisible(false);
		this.boutonChoixMancheNon.setVisible(false);
		this.boutonGeant.setVisible(false);
		this.boutonEngrais.setVisible(false);
		this.boutonFarfadet.setVisible(false);
	}

	public synchronized void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (arg0 instanceof StrategieJoueurReelGraphique) {
					if (arg0.toString().contains("Quelle action ?")) {
						VueStrategieJoueurReelGraphique.this.boutonGeant.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonEngrais.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonGeant.setVisible(true);
					} else if (arg0.toString().contains("Se défendre avec votre carte chien de garde ?")) {
						VueStrategieJoueurReelGraphique.this.boutonDefenseOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonDefenseNon.setVisible(true);
					} else if (arg0.toString().contains("Voulez-vous attaquer")) {
						VueStrategieJoueurReelGraphique.this.boutonAttaqueOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonAttaqueNon.setVisible(true);
					} else if (arg0.toString().contains("Voulez-vous une carte Alliee")) {
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheNon.setVisible(true);
					}
					VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
				} else if (arg0 instanceof Carte) {
					if (arg0 instanceof CarteIngredient) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteIngredient((Carte) arg0,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					} else if (arg0 instanceof CarteChiensDeGarde) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteChiensDeGarde((Carte) arg0,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					} else if (arg0 instanceof CarteTaupesGeantes) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteIngredient((Carte) arg0,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					}
					VueStrategieJoueurReelGraphique.this.ajoutPanneau(VueStrategieJoueurReelGraphique.this.carteEnJeu, 0, 0);
				} else {
					VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
				}
				VueStrategieJoueurReelGraphique.this.repaint();
				VueStrategieJoueurReelGraphique.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);

		try {
			this.wait(TEMPS_DE_REFLEXION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
