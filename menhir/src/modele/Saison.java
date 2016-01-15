package modele;

/**
 * Enum�ration permettant de repr�senter les diff�rentes saisons du jeu du Menhir
 * utilis�es dans la classe Partie.
 * 
 * @see Partie
 */
public enum Saison {
	printemps, ete, automne, hiver;

	public Saison next() {
		return values()[ordinal() + 1];
	}
}
