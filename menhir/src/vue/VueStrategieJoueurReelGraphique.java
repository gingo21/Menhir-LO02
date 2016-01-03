package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
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
	private JLabel afficheurSaison = new JLabel("Saison : automne");
	private JLabel labelCarteEnJeu = new JLabel("Carte en Jeu");
	private VueCarte carteEnJeu;

	boolean attenteChoixCarte = false;
	boolean attenteChoixDestinataire = false;

	public static int TEMPS_DE_REFLEXION = 1000;
	private Ressources referenceRessources;
	private StrategieJoueurReelGraphique referenceStrategie;
	private VuePaquetDeRessourcesDeJoueur referenceVuePaquetDeRessourcesDeJoueur;

	public VueStrategieJoueurReelGraphique(final StrategieJoueurReelGraphique strategie, Ressources ressources,
			VuePaquetDeRessourcesDeJoueur vuePaquetDeRessourcesDeJoueur/*
																		 * ,
																		 * VueDePaquetDeRessourcesIA
																		 * ...
																		 */) {
		super();
		strategie.addObserver(this);
		this.referenceStrategie = strategie;
		this.referenceRessources = ressources;
		this.referenceVuePaquetDeRessourcesDeJoueur = vuePaquetDeRessourcesDeJoueur;
		this.setPreferredSize(new Dimension(500, 250));

		this.ajoutPanneau(this.boutonAttaqueOui, 250, 125);
		this.ajoutPanneau(this.boutonAttaqueNon, 325, 125);
		this.ajoutPanneau(this.boutonDefenseOui, 250, 125);
		this.ajoutPanneau(this.boutonDefenseNon, 325, 125);
		this.ajoutPanneau(this.boutonChoixMancheOui, 250, 125);
		this.ajoutPanneau(this.boutonChoixMancheNon, 325, 125);
		this.ajoutPanneau(this.boutonEngrais, 250, 125);
		this.ajoutPanneau(this.boutonGeant, 325, 125);
		this.ajoutPanneau(this.boutonFarfadet, 400, 125);
		this.ajoutPanneau(this.afficheurTexte, 0, 0);
		this.ajoutPanneau(this.afficheurSaison, 400, 225);
		this.ajoutPanneau(this.labelCarteEnJeu, 0, 40);
		this.effacerBoutons();

		ActionListener ouiAttaque = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setAttaquer(true);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonAttaqueOui.addActionListener(ouiAttaque);
		ActionListener nonAttaque = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setAttaquer(false);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonAttaqueNon.addActionListener(nonAttaque);

		ActionListener ouiDefense = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setSeDefendre(true);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonDefenseOui.addActionListener(ouiDefense);
		ActionListener nonDefense = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setSeDefendre(false);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonDefenseNon.addActionListener(nonDefense);

		ActionListener ouiChoixManche = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setChoixCarteAlliee(true);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonChoixMancheOui.addActionListener(ouiChoixManche);
		ActionListener nonChoixManche = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setChoixCarteAlliee(false);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonChoixMancheNon.addActionListener(nonChoixManche);

		ActionListener actionGeant = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.geantGardient);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonGeant.addActionListener(actionGeant);
		ActionListener actionFarfadet = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.farfadet);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonFarfadet.addActionListener(actionFarfadet);
		ActionListener actionEngrais = new ActionListener() {
			public synchronized void actionPerformed(ActionEvent event) {
				strategie.setActionAJouer(TypeAction.engrais);
				synchronized (strategie) {
					strategie.notify();
				}
				effacerBoutons();
			}
		};
		this.boutonEngrais.addActionListener(actionEngrais);

		this.afficheurTexte.setSize(500, 25);

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

	public void ajouterMouseListeners() {
		for (Iterator<VueCarte> it = VueStrategieJoueurReelGraphique.this.referenceVuePaquetDeRessourcesDeJoueur
				.getTempVueCartes1().iterator(); it.hasNext();) {
			final VueCarte  tempVueCarte = it.next();
			if (tempVueCarte.getMouseListeners().length == 0) {
				tempVueCarte.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if (VueStrategieJoueurReelGraphique.this.attenteChoixCarte) {
							VueStrategieJoueurReelGraphique.this.referenceStrategie
									.setCarteAJouer(tempVueCarte.getCarte());
							synchronized (VueStrategieJoueurReelGraphique.this.referenceStrategie) {
								VueStrategieJoueurReelGraphique.this.referenceStrategie.notify();
							}
						}
					}

					public void mouseEntered(MouseEvent arg0) {
					}

					public void mouseExited(MouseEvent arg0) {
					}

					public void mousePressed(MouseEvent arg0) {
					}

					public void mouseReleased(MouseEvent arg0) {
					}
				});
			}
		}
	}

	public synchronized void update(final Observable arg0, final Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (arg0 instanceof StrategieJoueurReelGraphique) {
					if (arg1.toString().contains("Quelle action ?")) {
						VueStrategieJoueurReelGraphique.this.boutonGeant.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonEngrais.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonFarfadet.setVisible(true);
					} else if (arg1.toString().contains("Se défendre avec votre carte chien de garde ?")) {
						VueStrategieJoueurReelGraphique.this.boutonDefenseOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonDefenseNon.setVisible(true);
					} else if (arg1.toString().contains("Voulez-vous attaquer")) {
						VueStrategieJoueurReelGraphique.this.boutonAttaqueOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonAttaqueNon.setVisible(true);
					} else if (arg1.toString().contains("Voulez-vous une carte Alliee")) {
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheNon.setVisible(true);
					} else if (arg1.toString().contains("Quelle carte ingrédient jouez-vous ?")) {
						VueStrategieJoueurReelGraphique.this.attenteChoixCarte = true;
						VueStrategieJoueurReelGraphique.this.ajouterMouseListeners();
					} else if (arg1.toString().contains("A quel joueur voulez-vous voler des graines ?")) {
						VueStrategieJoueurReelGraphique.this.attenteChoixDestinataire = true;
						VueStrategieJoueurReelGraphique.this.ajouterMouseListeners();
					}
					VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
				} else if (arg0 instanceof Carte && arg1.toString().contains("utiliser")) {
					Carte tempCarte = (Carte) arg0;
					System.out.println("Trol");
					if (arg0 instanceof CarteIngredient) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteIngredient(tempCarte,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					} else if (arg0 instanceof CarteChiensDeGarde) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteChiensDeGarde(tempCarte,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					} else if (arg0 instanceof CarteTaupesGeantes) {
						VueStrategieJoueurReelGraphique.this.carteEnJeu = new VueCarteIngredient(tempCarte,
								VueStrategieJoueurReelGraphique.this.referenceRessources, 200, 200, false);
					}
					VueStrategieJoueurReelGraphique.this.ajoutPanneau(VueStrategieJoueurReelGraphique.this.carteEnJeu,
							0, 60);
				} else if (arg1 != null) {
					if (arg1.toString().contains("saison")) {
						if (arg1.toString().contains("omne")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : automne");
						} else if (arg1.toString().contains("rin")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : printemps");
						} else if (arg1.toString().contains("ete")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : été");
						} else if (arg1.toString().contains("hiver")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : hiver");
						}
					}
				VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
				}

				VueStrategieJoueurReelGraphique.this.repaint();
				VueStrategieJoueurReelGraphique.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);

		try {
			if (arg1 != null) {
				if (!arg1.toString().contains("saison")) {
					this.wait(TEMPS_DE_REFLEXION);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAttenteChoixCarte() {
		return attenteChoixCarte;
	}

	public void setAttenteChoixCarte(boolean attenteChoixCarte) {
		this.attenteChoixCarte = attenteChoixCarte;
	}

	public boolean isAttenteChoixDestinataire() {
		return attenteChoixDestinataire;
	}

	public void setAttenteChoixDestinataire(boolean attenteChoixDestinataire) {
		this.attenteChoixDestinataire = attenteChoixDestinataire;
	}
}
