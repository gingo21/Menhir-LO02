package menhir;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class ParametreDePartie {
	private int nombreDeManches;
	private int nombreDeJoueurs;
	private StatutPartie typePartie;
	private int[] ordreDesJoueurs;
	
	public ParametreDePartie() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien de joueurs?");
		this.nombreDeJoueurs = sc.nextInt();
		ordreDesJoueurs = new int[this.nombreDeJoueurs];
		// cr�er direct joueurs ici?
		System.out.println("Type de Partie ? rapide = 1 avancee = 2 ");
		if (sc.nextInt()==1){
			this.typePartie = StatutPartie.rapide;
			this.nombreDeManches = 1;
		}
		else{
			this.typePartie = StatutPartie.avancee;
			this.nombreDeManches = 3;
		}
		System.out.println("Voulez-vous commencer la partie ?");
			if(sc.nextLine()== "y" || sc.nextLine()=="o" || sc.nextLine()=="oui" || sc.nextLine()=="yes")
				this.ordreDesJoueurs[0]=1; //si on définie joueur 1 comme joueur reel
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
}
