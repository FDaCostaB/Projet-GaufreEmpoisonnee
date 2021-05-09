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

    Coup coupIA(Jeu j) {
        int l;
        int c;
        Coup cp ;

        l = r.nextInt(j.longueur());
        c = r.nextInt(j.largeur());
        cp = new Coup(l,c);

        return cp;
    }
}
