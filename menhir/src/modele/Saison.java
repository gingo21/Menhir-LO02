package modele;

public enum Saison {
	printemps,ete,automne,hiver;
	
	public Saison next() {
        // No bounds checking required here, because the last instance overrides
        return values()[ordinal() + 1];
    }
}



