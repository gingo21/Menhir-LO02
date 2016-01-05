package launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import modele.Difficulte;
import modele.Joueur;
import modele.JoueurReel;
import modele.JoueurVirtuel;
import modele.PaquetDeRessourcesDePartie;
import modele.ParametresDePartie;
import modele.Partie;
import modele.StatutPartie;

public class Console implements Runnable, Observer {

	private ParametresDePartie parametresDePartie;
	public final static Scanner SCANNER_PUBLIC = new Scanner(System.in);

	public Console(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	public void run() {
		try {
			this.askParametres(this.parametresDePartie);
			this.parametresDePartie.getPaquetDePartie().addConsoleObserver(this);
			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.getStrategie().addConsoleObserver(this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.parametresDePartie.saveParametres();
		Partie partie = new Partie(parametresDePartie, true);
		partie.addObserver(this);
		partie.run();

	}

	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}

	public synchronized void askParametres(ParametresDePartie parametresDePartie)
			throws IOException, InterruptedException {
		Scanner sc = Console.SCANNER_PUBLIC;

		System.out.println("Combien de joueurs? (entre 2 et 6)");
		parametresDePartie.setNombreDeJoueurs(sc.nextInt());
		while (!(parametresDePartie.getNombreDeJoueurs() <= 6 && parametresDePartie.getNombreDeJoueurs() >= 2)) {
			System.out.println("Combien de joueurs? (entre 2 et 6)");
			parametresDePartie.setNombreDeJoueurs(sc.nextInt());
		}

		System.out.println("Type de Partie ? rapide = 1 avancee = 2 ");
		if (sc.nextInt() == 2) {
			parametresDePartie.setNombreDeManches(parametresDePartie.getNombreDeJoueurs());
			parametresDePartie.setTypePartie(StatutPartie.avancee);
		} else {
			parametresDePartie.setTypePartie(StatutPartie.rapide);
			parametresDePartie.setNombreDeManches(1);
		}

		parametresDePartie.setPaquetDePartie(new PaquetDeRessourcesDePartie(parametresDePartie.getTypePartie(),
				parametresDePartie.getNombreDeJoueurs()));
		parametresDePartie.setListeJoueurs(new ArrayList<Joueur>());

		System.out.println("Votre nom ?");
		String tempReponse = sc.next();
		JoueurReel tempJoueurReel = new JoueurReel(tempReponse, parametresDePartie.getPaquetDePartie());
		parametresDePartie.setOrdreDesJoueurs(new ArrayList<Integer>());
		parametresDePartie.getOrdreDesJoueurs().add(tempJoueurReel.getId());
		parametresDePartie.getListeJoueurs().add(tempJoueurReel);
		for (int i = 1; i < parametresDePartie.getNombreDeJoueurs(); i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, parametresDePartie.getPaquetDePartie(),
					Difficulte.normale);
			parametresDePartie.getOrdreDesJoueurs().add(tempJoueurVirtuel.getId());
			parametresDePartie.getListeJoueurs().add(tempJoueurVirtuel);
		}
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 != null) {
			if (!(arg1.toString().contains("utiliser") || arg1.toString().contains("don")
					|| arg1.toString().contains("distribution") || arg1.toString().contains("nouveau paquet"))) {
				System.out.println(arg1.toString());
			}
		}
	}
}
