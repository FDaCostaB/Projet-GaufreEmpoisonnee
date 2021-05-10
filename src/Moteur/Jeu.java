package Moteur;

public class Jeu extends HistoriqueAPile<Commande> {
	public Niveau niv;
	private int height, width;
	public int joueur;

	public Jeu() {
		this(6,8);
	}
	public Jeu(int l,int c) {
		height=l;width=c;
		niv = new Niveau(l,c);
		niv.initNiveau(height,width);
		joueur = 0;
	}

	public Jeu( boolean [][] tab,int hauteur, int largeur) {

		this.height = hauteur;
		this.width = largeur;
		niv = new Niveau(hauteur,largeur);
		niv.initNiveau(tab);
	}

	public void reinitialiser(){
		niv.initNiveau(getHeight(),getWidth());
		joueur = 0;
	}

	public void jouerCoup(Coup c, boolean ia) {
		if(!coupValide(c)) return;
		if(ia) reset();
		else nouveau(c);
		joueur = (joueur+1)%2;
		niv.jouerCoup(c);
	}

	public void rejouerCoup(Coup c) {
		if(!coupValide(c)) return;
		joueur = (joueur+1)%2;
		niv.jouerCoup(c);
	}

	public void redo(){
		if(avancable())avancer();
		else System.out.println("Impossible de refaire");
	}

	public void undo(){
		if(reculable())reculer();
		else System.out.println("Impossible d'annuler");
	}

	public void restaurer(int l, int c) {
		niv.restaurer(l,c) ; //On met dans l'état mangé tout les morceau qui doivent l'être
	}

	public void maj(){
		niv.maj();
	}
	public void changeJoueur(){
		joueur = (joueur+1)%2;
	}

	public boolean coupValide(Coup c){
		return niv.coupValide(c);
	}

	public boolean testFin(){
		return niv.testFin();
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
	public int getHeight() {
		return height;
	}

	public boolean[][] grille(){
		return niv.grille();
	}
}
