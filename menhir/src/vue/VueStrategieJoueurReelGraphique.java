package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Carte;
import modele.StrategieJoueurReelGraphique;

public class VueStrategieJoueurReelGraphique extends Panneaux implements Observer {
	
	private JButton boutonAttaqueOui = new JButton("oui");
	private JButton boutonAttaqueNon = new JButton("non");
	private JButton boutonDefenseOui = new JButton("oui");
	private JButton boutonDefenseNon = new JButton("non");
	private JButton boutonChoixMancheOui = new JButton("oui");
	private JButton boutonChoixMancheNon = new JButton("non");
	private JButton boutonEngrais = new JButton("engrais");
	private JButton boutonGeant = new JButton("géant");
	private JButton boutonFarfadet = new JButton("farfadet");
	private JTextArea afficheurTexte = new JTextArea();
	private JLabel labelCarteEnJeu = new JLabel("Carte en Jeu");
	private VueImage carteEnJeu;// TODO à finir
	
	public VueStrategieJoueurReelGraphique(StrategieJoueurReelGraphique strategie) {
		super();
		strategie.addObserver(this);
		
		this.add(this.boutonAttaqueOui);
		this.add(this.boutonAttaqueNon);
		this.add(this.boutonDefenseOui);
		this.add(this.boutonDefenseNon);
		this.add(this.boutonChoixMancheOui);
		this.add(this.boutonChoixMancheNon);
		this.add(this.boutonEngrais);
		this.add(this.boutonGeant);
		this.add(this.boutonFarfadet);
		this.add(this.afficheurTexte);
		this.add(this.labelCarteEnJeu);
		//this.add(this.carteEnJeu;// TODO à finir
		
	}
	
	public void effacerBoutons () {
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
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof StrategieJoueurReelGraphique) {
			if(arg0.toString().contains("Quelle action ?")) {
				this.boutonGeant.setVisible(true);
				this.boutonEngrais.setVisible(true);
				this.boutonGeant.setVisible(true);
			} else if(arg0.toString().contains("Se défendre avec votre carte chien de garde ?")) {
				this.boutonDefenseOui.setVisible(true);
				this.boutonDefenseNon.setVisible(true);
			} else if(arg0.toString().contains("Voulez-vous attaquer")) {
				this.boutonAttaqueOui.setVisible(false);
				this.boutonAttaqueNon.setVisible(false);
			} else if(arg0.toString().contains("Voulez-vous une carte Alliee")) {
				this.boutonChoixMancheOui.setVisible(false);
				this.boutonChoixMancheNon.setVisible(false);
			}
			try {
				this.wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.afficheurTexte.setText(arg1.toString());
		} else if (arg0 instanceof Carte) {
			//VueCarteImage = new 
			// TODO à finir
			
		}

	}
}
