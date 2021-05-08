package Modele;

import Global.Configuration;
import Patterns.Observable;
import Structures.Iterateur;

public class Jeu extends Observable {
	LecteurNiveau lecteur;
	boolean [][]grille;
	public Jeu(LecteurNiveau l) {
		lecteur = l;
		creerNiveau();
	}

	public boolean creerNiveau() {
		courant = lecteur.lisProchainNiveau();
		return courant != null;
	}

	public void jouerCoup(Coup c) {
		courant.jouer(c);
		miseAJour();
	}

}
