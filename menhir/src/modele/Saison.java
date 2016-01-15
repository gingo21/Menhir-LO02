package modele;

/**
 * Enumération permettant de représenter les différentes saisons du jeu du Menhir
 * utilisées dans la classe Partie.
 * 
 * @see Partie
 */
public enum Saison {
	printemps, ete, automne, hiver;

	public Saison next() {
		return values()[ordinal() + 1];
	}
}
