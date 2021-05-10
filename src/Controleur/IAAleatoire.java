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

    public Coup coupIA(Jeu j) {
        int l;
        int c;
        Coup cp ;

        l = j.longueur();
        c = j.largeur();

        do{
            l = r.nextInt(l>0?l:1);
            c = r.nextInt(c>0?c:1);
            cp = new Coup(l,c,j);
            if(!j.coupValide(cp) && l==0 && c==0){
                l = j.longueur();
                c = j.largeur();
            }
        }while(!j.coupValide(cp));

        System.out.println("IA a cliqué sur ( l : " +cp.l+" , c : "+cp.c+" )" );
        return cp;
    }
}
