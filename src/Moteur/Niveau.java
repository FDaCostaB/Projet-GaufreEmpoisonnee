package Moteur;

import java.util.Observable;

public class Niveau extends Observable {
    boolean [][]grille;
    int largeur,hauteur;

    public Niveau(int l,int c) {
        hauteur=l;largeur=c;
        initNiveau(hauteur,largeur);
    }

    public void initNiveau(int l,int c) {
        grille = new boolean[l][c];
        for(int i=0;i<l;i++){
            for(int j=0;j<c;j++){
                grille[i][j] = false; //grille[i][j] indique si le morceau à été mangé par défaut il ne l'est pas
            }
        }

    }

    public void initNiveau(boolean [][] g) {
        grille = g;

    }

    public void jouerCoup(Coup c) {
        if(!coupValide(c)) return;
        for(int i=c.l;i<hauteur;i++){
            for(int j=c.c;j<largeur;j++){
                if(!estMange(i,j)) c.ajouter(new Couple(i,j));
                manger(i,j); //On met dans l'état mangé tout les morceau qui doivent l'être
            }
        }
        setChanged();
        notifyObservers();
    }
    public boolean estMange(int l, int c){
        return grille[l][c];
    }

    void manger(int l, int c){
        grille[l][c] = true;
    }

    void restaurer(int l, int c){
        grille[l][c] = false;
    }

    public boolean coupValide(Coup c){
        return !grille[c.l][c.c] && (c.l!=0 || c.c!=0);
    }

    public boolean testFin(){
        return grille[0][1]&&grille[1][0];
    }
    public void maj(){
        setChanged();
        notifyObservers();
    }

    public boolean[][] grille(){
        return grille;
    }
}
