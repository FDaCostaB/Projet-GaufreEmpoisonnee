package Modele;

import Global.Configuration;
import Patterns.Commande;
import Structures.Iterateur;
import Structures.Sequence;

public class Coup extends Commande implements Cloneable {
	List<Deplacement> mouvements;
	List<Marque> marques;

	public Coup(Niveau n) {

	}

	public void deplace(int l, int c) {

	}


	@Override
	public void execute() {
		applique(1);
	}


	@Override
	public void desexecute() {
		applique(-1);
	}

	public List<Deplacement> deplacements() {
		return mouvements;
	}

	public void marque(int l, int c, int val) {
		marques.insereQueue(new Marque(l, c, val));
	}
}
