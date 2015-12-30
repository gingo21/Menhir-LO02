package launcher;

import java.util.Scanner;

import modele.ParametresDePartie;

public class Jeu {
	
	public static final boolean MODE_GRAPHIQUE = true;
	
	public synchronized static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ParametresDePartie parametresDePartie = new ParametresDePartie();
		Console console = new Console(parametresDePartie);
		GraphicLauncher graphicLauncher = new GraphicLauncher(parametresDePartie);
		Thread tutu = new Thread(graphicLauncher);
		Thread toto = new Thread(console);
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous passer en mode graphique ?");
		String reponse = sc.next();*/ // TODO Ne marche pas
		if (MODE_GRAPHIQUE) {
			tutu.start();
		} else {
			toto.start();
		}
	}

}
