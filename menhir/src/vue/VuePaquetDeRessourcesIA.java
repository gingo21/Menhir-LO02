package vue;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.SwingUtilities;

import modele.CarteChamp;
import modele.CarteComptageDePoints;
import modele.PaquetDeRessourcesDeJoueur;
import Ressources.Ressources;

public class VuePaquetDeRessourcesIA extends VuePaquetDeRessourcesDeJoueur {

	private static final long serialVersionUID = -1925545505936736288L;
	public static int TAILLE_CARTE = 80;

	public VuePaquetDeRessourcesIA(PaquetDeRessourcesDeJoueur paquetDeRessourcesDeJoueur, Ressources r,
			boolean avancee) {
		super(paquetDeRessourcesDeJoueur, r, avancee);
		this.setPreferredSize(new Dimension(330, 180));
		tempTexte2 += referencePaquetDeRessourcesDeJoueur.getJoueur().getNom();
		this.nomDuJoueur.setText(tempTexte2);
		this.ajoutPanneau(nomDuJoueur, 50, 0);

	}

	public void update(Observable arg0, Object arg1) {
		Runnable myRunnable = new Runnable() {
			public void run() {
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Ingredients").isEmpty()) {

					if (!tempVueCartes1.isEmpty()) {
						for (Iterator<VueCarte> it = tempVueCartes1.iterator(); it.hasNext();) {
							VuePaquetDeRessourcesIA.this.remove(it.next());
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
									TAILLE_CARTE, TAILLE_CARTE, true);
							tempVueCartes1.add(tempVue);
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVue, (j % 2) * TAILLE_CARTE,
									20 + (j / 2) * TAILLE_CARTE);
							j++;
						}
					}
				}

				// carte champ
				if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").isEmpty()) {
					if (tempVueCartes2.isEmpty()) {
						tempVueCartes2.add(new VueCarteChamp(
								referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs").get(0),
								referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
						VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes2.get(0), 2 * TAILLE_CARTE, 20);
					}
					if (!grainesMenhirChamp.isEmpty()) {
						for (Iterator<VueImage> it = grainesMenhirChamp.iterator(); it.hasNext();) {
							VueImage tempit = it.next();
							VuePaquetDeRessourcesIA.this.remove(tempit);		
							tempVueCartes2.get(0).remove(tempit);
						}
						grainesMenhirChamp.clear();
						VuePaquetDeRessourcesIA.this.remove(tempVueCartes2.get(0));

					}
					// Affichage graines menhirs
					if (((CarteChamp) referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Champs")
							.get(0)).getMenhirAdultes() != 0) {

						for (int i = 0; i < ((CarteChamp) referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
								.get("Cartes Champs").get(0)).getMenhirAdultes(); i++) {
							VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
							grainesMenhirChamp.add(tempVueImage);
							(tempVueCartes2.get(0)).ajoutPanneau(tempVueImage, 0 + 5 + (i % 3) * 26,
									+10 + (i / 3) * 11);
						}
						
					}
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes2.get(0), 2 * TAILLE_CARTE, 20);
				}
				// Partie Avancee
				if (VuePaquetDeRessourcesIA.this.referenceAvancee) {
					if (!tempVueCartes4.isEmpty()) {
						VuePaquetDeRessourcesIA.this.remove(tempVueCartes4.get(0));
						tempVueCartes4.clear();
					}
					if (!tempVueCartes5.isEmpty()) {
						VuePaquetDeRessourcesIA.this.remove(tempVueCartes5.get(0));
						tempVueCartes5.clear();
					}
					if (  !referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
							.get("Cartes Comptage De Points").isEmpty()) {
						if(tempVueCartes3.isEmpty()){
							tempVueCartes3.add(new VueCarteComptageDePoints(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Comptage De Points").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, false));
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes3.get(0), TAILLE_CARTE * 2,
									20 + TAILLE_CARTE);
						}

						if(!grainesMenhir.isEmpty()){
							for (Iterator<VueImage> it = grainesMenhir.iterator(); it.hasNext();) {
								VueImage tempit = it.next();
								VuePaquetDeRessourcesIA.this.remove(tempit);		
								tempVueCartes3.get(0).remove(tempit);
							}
							grainesMenhir.clear();
							VuePaquetDeRessourcesIA.this.remove(tempVueCartes3.get(0));

						}
						//Affichage graines menhirs
						if (((CarteComptageDePoints)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0)).getMenhirAdultes()!=0) {
							for (int i = 0; i < ((CarteComptageDePoints)referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Comptage De Points").get(0)).getMenhirAdultes(); i++) {
								VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 12, 5);
								grainesMenhir.add(tempVueImage);
								(tempVueCartes3.get(0)).ajoutPanneau(tempVueImage, 2 + (i % 5) * TAILLE_CARTE/(55/10), 4+
										(i / 5) * TAILLE_CARTE/5);
							}
						}
						VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes3.get(0), TAILLE_CARTE * 2, 20+TAILLE_CARTE);

					}

					if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
							.isEmpty()) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Chiens De Garde")
								.get(0).isEstUtilise()) {
							tempVueCartes4.clear();
							tempVueCartes4.add(new VueCarteChiensDeGarde(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
									.get("Cartes Chiens De Garde").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, true));
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes4.get(0), 3 * TAILLE_CARTE, 20);

						}

					} else if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
							.isEmpty()) {
						if (!referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes().get("Cartes Taupes Geantes")
								.get(0).isEstUtilise()) {
							tempVueCartes5.clear();
							tempVueCartes5.add(new VueCarteTaupesGeantes(
									referencePaquetDeRessourcesDeJoueur.getPaquetsDeCartes()
											.get("Cartes Taupes Geantes").get(0),
									referenceRessources, TAILLE_CARTE, TAILLE_CARTE, true));
							VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueCartes5.get(0), 3 * TAILLE_CARTE, 20);

						}
					}

				}

				VuePaquetDeRessourcesIA.this.remove(nombreDeGraines);
				tempTexte1 = "";
				tempTexte1 += referencePaquetDeRessourcesDeJoueur.getGrainesDeMenhir();
				VuePaquetDeRessourcesIA.this.nombreDeGraines.setText(tempTexte1);
				VuePaquetDeRessourcesIA.this.ajoutPanneau(nombreDeGraines, 125, 0);
				if (!graines.isEmpty()) {
					for (Iterator<VueImage> it = graines.iterator(); it.hasNext();) {
						VuePaquetDeRessourcesIA.this.remove(it.next());
					}
					graines.clear();
				}
				for (int i = 0; i < VuePaquetDeRessourcesIA.this.referencePaquetDeRessourcesDeJoueur
						.getGrainesDeMenhir(); i++) {
					VueImage tempVueImage = new VueImage(referenceRessources.getImageGraine(), 24, 10);
					graines.add(tempVueImage);
					VuePaquetDeRessourcesIA.this.ajoutPanneau(tempVueImage, 3 * TAILLE_CARTE + 5 + (i % 3) * 26,
							TAILLE_CARTE + 20 + (i / 3) * 11);
				}

				VuePaquetDeRessourcesIA.this.repaint();
				VuePaquetDeRessourcesIA.this.revalidate();
			}
		};
		SwingUtilities.invokeLater(myRunnable);
	}

}
