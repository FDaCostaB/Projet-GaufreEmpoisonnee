package Moteur;

import java.util.ArrayList;
import java.util.Iterator;

public class Coup extends Commande{
	public int l,c;
	ArrayList<Couple> mange;
	Jeu jeu;

	public Coup(int l, int c, Jeu j) {
		this.l = l;
		this.c = c;
		jeu=j;
		mange = new ArrayList<>();
	}

	void ajouter(Couple cpl){
		mange.add(cpl);
	}

	@Override
	public void execute() {
		jeu.rejouerCoup(this);
	}

	@Override
	public void desexecute() {
		Iterator<Couple> it = mange.iterator();
		Couple curr;
		while(it.hasNext()){
			curr = it.next();
			jeu.restaurer(curr.l,curr.c);
		}
		jeu.changeJoueur();
		jeu.maj();
	}
}
