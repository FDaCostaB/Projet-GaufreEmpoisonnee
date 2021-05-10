package Moteur;



import java.util.Observable;

public class Jeu extends Observable {
	boolean [][]grille;
	private int height;
	private int width;
	public int joueur;

	public Jeu() {
		this(6,8);
	}
	public Jeu(int l,int c) {
		setHeight(l);setWidth(c);
		creerNiveau(getHeight(),getWidth());
		joueur = 0;
	}
	
	public Jeu( boolean [][] tab,int hauteur, int largeur) {
		this.grille =tab;
		this.height = hauteur;
		this.width = largeur;
	}

	public void creerNiveau(int l,int c) {
		grille = new boolean[l][c];
		for(int i=0;i<l;i++){
			for(int j=0;j<c;j++){
				grille[i][j] = false; //grille[i][j] indique si le morceau à été mangé par défaut il ne l'est pas
			}
		}

	}

	public void reinitialiser(){
		creerNiveau(getHeight(),getWidth());
		joueur = 0;
	}

	public void jouerCoup(Coup c) {
		if(!coupValide(c)) return;
		for(int i=c.l;i<getHeight();i++){
			for(int j=c.c;j<getWidth();j++){
				grille[i][j] = true; //On met dans l'état mangé tout les morceau qui doivent l'être
			}
		}
		joueur = (joueur+1)%2;
		setChanged();
		notifyObservers();
	}
    
	public boolean estManger(Coup c) {
		return grille[c.l][c.c] != false;
	}
	
	public boolean coupValide(Coup c){
		return !grille[c.l][c.c];
	}

	public boolean testFin(){
		return grille[0][1]&&grille[1][0];
	}

	public boolean[][] grille(){
		return grille;
	}

	public int longueur(){
		return getHeight();
	}

	public int largeur(){
		return getWidth();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	

}
