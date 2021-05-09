package Modele;



import javax.swing.*;
import java.util.Observable;

public class Jeu extends Observable {
	boolean [][]grille;
	int height, width;

	public Jeu() {
		this(6,8);
	}
	public Jeu(int l,int c) {
		height=l;width=c;
		creerNiveau(l,c);
	}

	public void creerNiveau(int l,int c) {
		grille = new boolean[l][c];
		for(int i=0;i<l;i++){
			for(int j=0;j<c;j++){
				grille[i][j] = false; //grille[i][j] indique si le morceau à été mangé par défaut il ne l'est pas
			}
		}

	}

	public void jouerCoup(Coup c) {
		if(!coupValide(c)) return;
		for(int i=c.l;i<height;i++){
			for(int j=c.c;j<width;j++){
				grille[i][j] = true; //On met dans l'état mangé tout les morceauqui doivent l'être
			}
		}
		setChanged();
		notifyObservers();
	}

	public boolean coupValide(Coup c){
		return !grille[c.l][c.c];
	}

	public boolean[][] grille(){
		return grille;
	}

	public int longueur(){
		return height;
	}

	public int largeur(){
		return width;
	}

}
