package launcher;

public class Jeu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Console test = new Console();
		Thread toto = new Thread(test);
		toto.start();
	}

}
