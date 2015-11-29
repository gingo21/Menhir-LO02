package menhir;

public abstract class PaquetDeRessources {

	private int grainesDeMenhir;

	public PaquetDeRessources(int nombreDeGraines) {
		this.grainesDeMenhir = nombreDeGraines;
	}

	public abstract void ajouterUneCarte(Carte carte);
	public abstract void afficherCartes();

	public int getGrainesDeMenhir() {
		return grainesDeMenhir;
	}

	public String toString() {
		return " [grainesDeMenhir=" + grainesDeMenhir + "]";
	}

	public void setGrainesDeMenhir(int grainesDeMenhir) {
		this.grainesDeMenhir = grainesDeMenhir;
	}
	
	public void donnerUneGraineDeMenhir(Joueur joueur, int nombre) {
		this.setGrainesDeMenhir(this.getGrainesDeMenhir()-nombre);
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempPaquetJoueur.setGrainesDeMenhir(tempPaquetJoueur.getGrainesDeMenhir()+nombre);
	}
	public void donnerUneGraineDeMenhir(Joueur joueur) {
		this.setGrainesDeMenhir(this.getGrainesDeMenhir()-1);
		PaquetDeRessourcesDeJoueur tempPaquetJoueur = joueur.getPaquet();
		tempPaquetJoueur.setGrainesDeMenhir(tempPaquetJoueur.getGrainesDeMenhir()+1);
	}

}
