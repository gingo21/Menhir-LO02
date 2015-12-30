package launcher;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Scanner;

import modele.Difficulte;
import modele.Joueur;
import modele.JoueurReel;
import modele.JoueurVirtuel;
import modele.PaquetDeRessourcesDePartie;
import modele.ParametresDePartie;
import modele.Partie;
import modele.Saison;
import modele.StatutPartie;

public class Console implements Runnable {

	private ParametresDePartie parametresDePartie;

	public Console(ParametresDePartie parametresDePartie) {
		super();
		this.parametresDePartie = parametresDePartie;
	}

	public void run() {
		// 1ere version
		System.out.println(
				"Bienvenue dans la version Alpha du jeu du menhir d'apres Francois Reymond" + " et Adrien Wartelle");
		System.out.println("Ne répondez que par un mot aux questions si vous voulez que cela se passe bien ...");

		try {
			System.out.println(this.parametresDePartie.toString());
			this.askParametres(this.parametresDePartie);
		} catch (IOException e) {

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		this.parametresDePartie.saveParametres();
		Partie partie = new Partie();

		// Distribution des cartes et présentations
		this.parametresDePartie.getPaquetDePartie().distribuerRessourcesInitiales(this.parametresDePartie);
		partie.wait(1);

		do {
			int tempIdJoueurActuel = this.parametresDePartie.getOrdreDesJoueurs().get(partie.getNumeroDeTourActuel());
			int indexJoueurActuel = 0;
			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				if (tempJoueur.getId() == tempIdJoueurActuel) {
					indexJoueurActuel = this.parametresDePartie.getListeJoueurs().indexOf(tempJoueur);
					break;
				}
			}
			Joueur joueurActuel = this.parametresDePartie.getListeJoueurs().get(indexJoueurActuel);
			System.out.println("C'est au tour de " + joueurActuel.getNom());

			joueurActuel.getStrategie().jouerSonTour(partie.getSaisonActuelle(), this.parametresDePartie);
			partie.wait(10);

			for (Iterator<Joueur> it = this.parametresDePartie.getListeJoueurs().iterator(); it.hasNext();) {
				Joueur tempJoueur = it.next();
				tempJoueur.score(this.parametresDePartie.getTypePartie());
				if (tempJoueur != joueurActuel) {
					tempJoueur.getStrategie().attaquer(this.parametresDePartie, joueurActuel, tempJoueur,
							partie.getSaisonActuelle());
				}
			}

			partie.changerDeTour(this.parametresDePartie);
			// test fin de manche
			if (partie.getSaisonActuelle() == Saison.printemps && partie.getNumeroDeTourActuel() == 0) {
				if (this.parametresDePartie.getTypePartie() == StatutPartie.avancee) {
					partie.changerDeManche(this.parametresDePartie);
				} else {
					partie.setNumeroDeManche(partie.getNumeroDeManche() + 1);
				}

			}

		} while (partie.getNumeroDeManche() < this.parametresDePartie.getNombreDeManches());
		partie.finDeJeu(this.parametresDePartie);

	}

	public ParametresDePartie getParametresDePartie() {
		return this.parametresDePartie;
	}

	public void setParametresDePartie(ParametresDePartie parametresDePartie) {
		this.parametresDePartie = parametresDePartie;
	}

	public synchronized void askParametres(ParametresDePartie parametresDePartie) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);

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
		JoueurReel tempJoueurReel = new JoueurReel(sc.next(), parametresDePartie.getPaquetDePartie());
		parametresDePartie.setOrdreDesJoueurs(new ArrayList<Integer>());
		parametresDePartie.getOrdreDesJoueurs().add(tempJoueurReel.getId());
		parametresDePartie.getListeJoueurs().add(tempJoueurReel);
		for (int i = 1; i < parametresDePartie.getNombreDeJoueurs(); i++) {
			JoueurVirtuel tempJoueurVirtuel = new JoueurVirtuel("IA" + i, parametresDePartie.getPaquetDePartie(),
					Difficulte.facile);
			parametresDePartie.getOrdreDesJoueurs().add(tempJoueurVirtuel.getId());
			parametresDePartie.getListeJoueurs().add(tempJoueurVirtuel);
		}
	}
}
