package vue;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.SwingUtilities;

import modele.CarteChamp;
import modele.CarteComptageDePoints;
import modele.PaquetDeRessourcesDeJoueur;
import ressources.Ressources;

/**
 *Classe représentant les ressources graphiques du joueur réel
 *C'est un panneau(Jlabel)
 *Cette classe observe le paquet de Ressource du joueur réel
 *Elle hérite de la classe abstraite VuePaquetDeRessourcesDeJoueur
 */
public class VuePaquetDeRessourcesDeJoueurReel extends VuePaquetDeRessourcesDeJoueur {

	/**
	 * Il s'agit d'un attribut pour la gestion de version des classes implémentant Serializable.
	 */
	private static final long serialVersionUID = -5883957509956446697L;

	/**
	 *Constante pour la taille des cartes du joueur réel
	 */
	public static int TAILLE_CARTE = 150;

	/**
	 *Constructeur de la vue graphique des ressources de notre joueur réel.
	 *Seront affiché ses cartes et graines
	 */
	public VuePaquetDeRessourcesDeJoueurReel(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(850, 340));
		tempTexte2 += referencePaquetDeRessourcesDeJoueur.getJoueur().getNom();
		VuePaquetDeRessourcesDeJoueurReel.this.nomDuJoueur.setText(tempTexte2);
		this.ajoutPanneau(nomDuJoueur, TAILLE_CARTE / 3, 0);

	}

	/**
	 *Rafraichissement graphique selon le modèle Observer/Observable
	 */
	public void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {
					if (!tempVueCartes1.isEmpty()) {
						for (Iterator<VueCarte> it = tempVueCartes1.iterator(); it.hasNext();) {
							VuePaquetDeRessourcesDeJoueurReel.this.remove(it.next());

						}
						tempVueCartes1.clear();
					}

					int j = 0;
					for (int i = 0; i < referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Ingredients").size(); i++) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").get(i)
								.isEstUtilise()) {
							VueCarteIngredient tempVue = new VueCarteIngredient(referencePaquetDeRessourcesDeJoueur
									.getPaquetsDeCartes().get("Cartes Ingredients").get(i), referenceRessources,
									TAILLE_CARTE, TAILLE_CARTE, false);
							tempVueCartes1.add(tempVue);
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVue,
									TAILLE_CARTE + j * TAILLE_CARTE, TAILLE_CARTE);
							j++;
						}
					}
				}
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
					if (tempVueCartes2.isEmpty()) {
						tempVueCartes2.add(new VueCarteChamp(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0),
								referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
					}
					if (!grainesMenhirChamp.isEmpty()) {

						for (Iterator<VueImage> it = grainesMenhirChamp.iterator(); it.hasNext();) {
							VueImage tempit = it.next();
							VuePaquetDeRessourcesDeJoueurReel.this.remove(tempit);
							tempVueCartes2.get(0).remove(tempit);
						}
						grainesMenhirChamp.clear();
						VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes2.get(0));

					}
					if (((CarteChamp) referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs")
							.get(0)).getMenhirAdultes() != 0) {
						for (int i = 0; i < ((CarteChamp) referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
								.get("Cartes Champs").get(0)).getMenhirAdultes(); i++) {
							VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
							grainesMenhirChamp.add(tempVueImage);
							(tempVueCartes2.get(0)).ajoutPanneau(tempVueImage, 0 + 5 + (i % 3) * 26,
									+20 + (i / 3) * 11);
						}

					}
					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes2.get(0), 0, 0);
				}
				if (VuePaquetDeRessourcesDeJoueurReel.this.referenceAvancee) {
					if (!tempVueCartes4.isEmpty()) {
						VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes4.get(0));
						tempVueCartes4.clear();
					}
					if (!tempVueCartes5.isEmpty()) {
						VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes5.get(0));
						tempVueCartes5.clear();
					}
					if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points")
							.isEmpty()) {
						if (tempVueCartes3.isEmpty()) {
							tempVueCartes3.add(new VueCarteComptageDePoints(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Comptage De Points").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes3.get(0), 0, TAILLE_CARTE);
						}
						if (!grainesMenhir.isEmpty()) {
							for (Iterator<VueImage> it = grainesMenhir.iterator(); it.hasNext();) {
								VueImage tempit = it.next();
								VuePaquetDeRessourcesDeJoueurReel.this.remove(tempit);
								tempVueCartes3.get(0).remove(tempit);
							}
							grainesMenhir.clear();
							VuePaquetDeRessourcesDeJoueurReel.this.remove(tempVueCartes3.get(0));

						}
						if (((CarteComptageDePoints) referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
								.get("Cartes Comptage De Points").get(0)).getMenhirAdultes() != 0) {
							for (int i = 0; i < ((CarteComptageDePoints) referencePaquetDeRessourcesDeJoueur
									.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0))
									.getMenhirAdultes(); i++) {
								VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
								grainesMenhir.add(tempVueImage);
								(tempVueCartes3.get(0)).ajoutPanneau(tempVueImage,
										3 + (i % 5) * TAILLE_CARTE / (55 / 10), 9 + (i / 5) * TAILLE_CARTE / 5);
							}
						}
						VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes3.get(0), 0, TAILLE_CARTE);

					}
					if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
							.isEmpty()) {

						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
								.get(0).isEstUtilise()) {
							tempVueCartes4.add(new VueCarteChiensDeGarde(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Chiens De Garde").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes4.get(0), TAILLE_CARTE * 2,
									0);

						}

					} else if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
							.isEmpty()) {

						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
								.get(0).isEstUtilise()) {
							tempVueCartes5.add(new VueCarteTaupesGeantes(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Taupes Geantes").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueCartes5.get(0), TAILLE_CARTE * 2,
									0);

						}
					}

				}
				VuePaquetDeRessourcesDeJoueurReel.this.remove(nombreDeGraines);
				tempTexte1 = "";
				tempTexte1 += referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				VuePaquetDeRessourcesDeJoueurReel.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(nombreDeGraines, TAILLE_CARTE * 19 / 10, 40);

				if (!graines.isEmpty()) {
					for (Iterator<VueImage> it = graines.iterator(); it.hasNext();) {
						VuePaquetDeRessourcesDeJoueurReel.this.remove(it.next());
					}
					graines.clear();
				}
				for (int i = 0; i < VuePaquetDeRessourcesDeJoueurReel.this.referencePaquetDeRessourcesDeJoueur
						.getGrainesDeMenhir(); i++) {
					VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
					graines.add(tempVueImage);
					VuePaquetDeRessourcesDeJoueurReel.this.ajoutPanneau(tempVueImage, TAILLE_CARTE + 5 + (i % 3) * 26,
							+10 + (i / 3) * 11);
				}
				VuePaquetDeRessourcesDeJoueurReel.this.repaint();
				VuePaquetDeRessourcesDeJoueurReel.this.revalidate();

			}

		};
		SwingUtilities.invokeLater(myRunnable);
	}
}
