package Controleur;

import Moteur.Coup;
import Moteur.Jeu;

import java.util.Random;

class IAAleatoire extends IA {

    Random r;
    Jeu j;
    IAAleatoire(Jeu j) {
        r = new Random();
        this.j=j;
    }

    Coup coupIA(Jeu jeu) {
    	int i, j;
        int count = 0;
        i = r.nextInt(jeu.getHeight());
        j = r.nextInt(jeu.getWidth());
        // l'IA essaye de ne pas se suicider et de manger quelque chose
        while (!jeu.coupValide(new Coup(i,j)) && count < 100 || (i<=1 && j<=1)) {
            i = r.nextInt(jeu.getHeight());
            j = r.nextInt(jeu.getWidth());
            count++;
        }
        if (count == 100) {
        	return new Coup(0,0); //si elle n'as rien trouvée elle se suicide
        }
        //plateau.manger(new Couple(i,j));
		if(jeu.grille()[i][j] == false)
			return new Coup(i,j);
		else 
			return new Coup(0,0);
    }
}
