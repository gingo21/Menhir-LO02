package menhir;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;


public class ParametreDePartie {
	private int nombreDeManches;
	private int nombreDeJoueurs;
	private StatutPartie typePartie;
	private int[] ordreDesJoueurs;
	private ArrayList<Joueur> listeJoueurs;
	private PaquetDeRessourcesDePartie paquetDePartie;
	
	public ParametreDePartie()  { // ce parametrage se fera avec un fichier par la suite
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Combien de joueurs? (entre 2 et 6)");
		this.nombreDeJoueurs = sc.nextInt();
		while(!(this.nombreDeJoueurs<=6 && this.nombreDeJoueurs>=2))
		{
			System.out.println("Combien de joueurs? (entre 2 et 6)");
			this.nombreDeJoueurs = sc.nextInt();
		}
		
		System.out.println("Type de Partie ? rapide = 1 avancee = 2 ");
		if (sc.nextInt()==2){
			this.nombreDeManches = this.nombreDeJoueurs;
			this.typePartie = StatutPartie.avancee;
			this.nombreDeManches = 3;
		}
		else{
			this.typePartie = StatutPartie.rapide;
			this.nombreDeManches = 1;
		}
		
		this.paquetDePartie = new PaquetDeRessourcesDePartie(this.typePartie, this.nombreDeJoueurs);
		this.listeJoueurs = new ArrayList<Joueur>();
		
		// creer direct joueurs ici? -- edit oui
		System.out.println("Votre nom ?");
		JoueurReel tempJoueurReel = new JoueurReel(sc.next(), this.paquetDePartie);
		ordreDesJoueurs = new int[this.nombreDeJoueurs];
		this.ordreDesJoueurs[0]=tempJoueurReel.getId(); //si on definie joueur 1 comme joueur reel
		this.listeJoueurs.add(tempJoueurReel);
		for(int i=1; i<this.nombreDeJoueurs; i++)
		{
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA"+i,this.paquetDePartie,Difficulte.facile);
			this.ordreDesJoueurs[i]= tempJoueurVirtuel.getId();
			this.listeJoueurs.add(tempJoueurVirtuel);
		}
	}

	public int getNombreDeManches() {
		return nombreDeManches;
	}

	public void setNombreDeManches(int nombreDeManches) {
		this.nombreDeManches = nombreDeManches;
	}

	public int getNombreDeJoueurs() {
		return nombreDeJoueurs;
	}

	public void setNombreDeJoueurs(int nombreDeJoueurs) {
		this.nombreDeJoueurs = nombreDeJoueurs;
	}

	public int[] getOrdreDesJoueurs() {
		return ordreDesJoueurs;
	}

	public void setOrdreDesJoueurs(int[] ordreDesJoueurs) {
		this.ordreDesJoueurs = ordreDesJoueurs;
	}

	public StatutPartie getTypePartie() {
		return typePartie;
	}

	public void setTypePartie(StatutPartie typePartie) {
		this.typePartie = typePartie;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public PaquetDeRessourcesDePartie getPaquetDePartie() {
		return paquetDePartie;
	}

	public void setPaquetDePartie(PaquetDeRessourcesDePartie paquetDePartie) {
		this.paquetDePartie = paquetDePartie;
	}
	
}
