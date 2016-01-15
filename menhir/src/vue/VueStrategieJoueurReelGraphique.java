package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import modele.Carte;
import modele.CarteChiensDeGarde;
import modele.CarteIngredient;
import modele.CarteTaupesGeantes;
import modele.PaquetDeRessourcesDePartie;
import modele.StrategieJoueurReelGraphique;
import modele.TypeAction;
import ressources.Ressources;

/**
 * Classe qui d�crit la zone graphique o� le joueur r�el choisit
 * ses actions et o� est affich� la carte en jeu
 */
public class VueStrategieJoueurReelGraphique extends Panneau implements Observer {
	
	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes impl�mentant Serializable.
	 */
	private static final long serialVersionUID = 7126927330980873763L;
	
	/**
	 * Boutton oui pour attaquer
	 */
	private JButton boutonAttaqueOui = new JButton("oui");
	
	/**
	 * Boutton non pour attaquer
	 */
	private JButton boutonAttaqueNon = new JButton("non");
	
	/**
	 * Boutton oui pour se d�fendre
	 */
	private JButton boutonDefenseOui = new JButton("oui");
	
	/**
	 * Boutton non pour se d�fendre
	 */
	private JButton boutonDefenseNon = new JButton("non");
	
	/**
	 * Boutton oui pour le choix de la manche
	 */
	private JButton boutonChoixMancheOui = new JButton("oui");
	
	/**
	 * Boutton non pour le choix de la manche
	 */
	private JButton boutonChoixMancheNon = new JButton("non");
	
	/**
	 * Boutton engrais
	 */
	private JButton boutonEngrais = new JButton("engrais");
	
	/**
	 * Boutton g�ant
	 */
	private JButton boutonGeant = new JButton("g�ant");
	
	/**
	 * Boutton farfadet
	 */
	private JButton boutonFarfadet = new JButton("farfadet");
	
	/**
	 * Afficheur des informations sur le d�roulement du jeu
	 */
	private JLabel afficheurTexte = new JLabel();
	
	/**
	 * Afficheur des informations sur la saison
	 */
	private JLabel afficheurSaison = new JLabel("Saison : printemps");
	
	/**
	 * Label texte 
	 */
	private JLabel labelCarteEnJeu = new JLabel("Carte en Jeu");
	
	/**
	 * Vue graphique de la carte en jeu 
	 */
	private VueCarte carteEnJeu;

	boolean attenteChoixCarte = false;
	boolean attenteChoixDestinataire = false;

	/**
	 * Temps laiss� au joueur pour choisir son action
	 */
	public static int TEMPS_DE_REFLEXION = 1000;
	
	/**
	 * R�f�rence des ressources images 
	 */
	private Ressources referenceRessources;
	
	/**
	 * R�f�rence de la strat�gie du joueur r�el
	 */
	private StrategieJoueurReelGraphique referenceStrategie;
	
	/**
	 * R�f�rence de la strat�gie du joueur r�el
	 */
	private VuePaquetDeRessourcesDeJoueur referenceVuePaquetDeRessourcesDeJoueur;
	
	/**
	 * R�f�rence du panneau des ressources graphiques de l'IA
	 */
	private ArrayList<VuePaquetDeRessourcesIA> referenceVuesPaquetDeRessourcesIA;
	
	/**
	 * Constructeur de la Vue Strat�gie du joueur r�el
	 * @param strategie strat�gie du joueur
	 * @param ressources ensemble des images d�j� charg�es
	 */
	public VueStrategieJoueurReelGraphique(final StrategieJoueurReelGraphique strategie, Ressources ressources,
			VuePaquetDeRessourcesDeJoueur vuePaquetDeRessourcesDeJoueur,
			ArrayList<VuePaquetDeRessourcesIA> vuesPaquetDeRessourcesIA) {
		super();
		strategie.addObserver(this);
		this.referenceStrategie = strategie;
		this.referenceRessources = ressources;
		this.referenceVuePaquetDeRessourcesDeJoueur = vuePaquetDeRessourcesDeJoueur;
		this.referenceVuesPaquetDeRessourcesIA = vuesPaquetDeRessourcesIA;
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
		this.ajoutPanneau(this.afficheurSaison, 375, 225);
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
	/**
	 * M�thode qui efface les boutons
	 */
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
			final VueCarte tempVueCarte = it.next();
			if (tempVueCarte.getMouseListeners().length == 0) {
				tempVueCarte.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (VueStrategieJoueurReelGraphique.this.attenteChoixCarte) {
							VueStrategieJoueurReelGraphique.this.referenceStrategie
									.setCarteAJouer(tempVueCarte.getCarte());
							synchronized (VueStrategieJoueurReelGraphique.this.referenceStrategie) {
								VueStrategieJoueurReelGraphique.this.referenceStrategie.notify();
							}
						}
					}
				});
			}
		}
		for (Iterator<VuePaquetDeRessourcesIA> it = VueStrategieJoueurReelGraphique.this.referenceVuesPaquetDeRessourcesIA
				.iterator(); it.hasNext();) {
			final VuePaquetDeRessourcesIA tempVue = it.next();
			if (tempVue.getMouseListeners().length == 0) {
				tempVue.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (VueStrategieJoueurReelGraphique.this.attenteChoixDestinataire) {
							VueStrategieJoueurReelGraphique.this.referenceStrategie.setDestinataireAAttaquer(
									tempVue.getReferencePaquetDeRessourcesDeJoueur().getJoueur());
							;
							synchronized (VueStrategieJoueurReelGraphique.this.referenceStrategie) {
								VueStrategieJoueurReelGraphique.this.referenceStrategie.notify();
							}
						}
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
					} else if (arg1.toString().contains("Se d�fendre avec votre carte chien de garde ?")) {
						VueStrategieJoueurReelGraphique.this.boutonDefenseOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonDefenseNon.setVisible(true);
					} else if (arg1.toString().contains("Voulez-vous attaquer")) {
						VueStrategieJoueurReelGraphique.this.boutonAttaqueOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonAttaqueNon.setVisible(true);
					} else if (arg1.toString().contains("Voulez-vous une carte Alliee")) {
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheOui.setVisible(true);
						VueStrategieJoueurReelGraphique.this.boutonChoixMancheNon.setVisible(true);
					} else if (arg1.toString().contains("Quelle carte ingr�dient jouez-vous ?")) {
						VueStrategieJoueurReelGraphique.this.attenteChoixCarte = true;
						VueStrategieJoueurReelGraphique.this.ajouterMouseListeners();
					} else if (arg1.toString().contains("A quel joueur voulez-vous voler des graines ?")) {
						VueStrategieJoueurReelGraphique.this.attenteChoixDestinataire = true;
						VueStrategieJoueurReelGraphique.this.ajouterMouseListeners();
					}
					VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
				} else if (arg0 instanceof Carte && arg1.toString().contains("utiliser")) {
					Carte tempCarte = (Carte) arg0;
					if (VueStrategieJoueurReelGraphique.this.carteEnJeu != null) {
						VueStrategieJoueurReelGraphique.this.remove(VueStrategieJoueurReelGraphique.this.carteEnJeu);
					}
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
						VueStrategieJoueurReelGraphique.this
								.remove(VueStrategieJoueurReelGraphique.this.afficheurSaison);
						if (VueStrategieJoueurReelGraphique.this.afficheurSaison.getText().contains("omne")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : hiver");
						} else if (VueStrategieJoueurReelGraphique.this.afficheurSaison.getText().contains("rin")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : �t�");
						} else if (VueStrategieJoueurReelGraphique.this.afficheurSaison.getText().contains("�t�")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : automne");
						} else if (VueStrategieJoueurReelGraphique.this.afficheurSaison.getText().contains("hiver")) {
							VueStrategieJoueurReelGraphique.this.afficheurSaison = new JLabel("Saison : printemps");
						}
						VueStrategieJoueurReelGraphique.this
								.ajoutPanneau(VueStrategieJoueurReelGraphique.this.afficheurSaison, 375, 225);
						VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
					} else {
						VueStrategieJoueurReelGraphique.this.afficheurTexte.setText(arg1.toString());
					}
				}

				VueStrategieJoueurReelGraphique.this.repaint();
				VueStrategieJoueurReelGraphique.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);

		try {
			if (arg1 != null) {
				if (!arg1.toString().contains("[") && !arg1.toString().contains("nouveau paquet")) {
					this.wait(TEMPS_DE_REFLEXION);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  M�thode apell�e quand il y a un changement de paquet, et donc
	 *  notre vue graphique doit observer ce nouveau paquet
	 */
	public void changementDePaquet(PaquetDeRessourcesDePartie paquet) {
		paquet.addObserver(this);
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
