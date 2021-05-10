package Controleur;

import Moteur.Jeu;

public class Heuristique {
	//le nom parle de lui meme
	   public static int nbFalseligne(boolean[][] tab, int i, int M) {
	    	int res = 0;
	    	for(int j = 0; j < M; j++) {
	    		if(tab[i][j] == false) {
	    			res++;
	    		}
	    	}
	    	return res;
	    }
	   //le nom parle de lui meme
	    public static int nbFalsecolonne(boolean[][] tab, int j, int N) {
	    	int res = 0;
	    	for(int i = 0; i < N; i++) {
	    		if(tab[i][j] == false) {
	    			res++;
	    		}
	    	}
	    	return res;
	    }
		/**
	     * calcule_heuristique : modifie par effet de bord l'heuristique d'un noeud donné
	     *  @param N : le noeud duquel il faut calculer l'heuristique
	     */
		public static int calcule_heuristique(Noeud N) {
			//on recupere le tableau du noeud courant
			Jeu jcurrent = TabConverter.ToTab(N.valeur());
			boolean[][] tabcurrent = jcurrent.grille();
			
			//on recupere les dimensions
			int hauteur = jcurrent.getHeight();
			int largeur = jcurrent.getWidth();
			
		
			//traitement (on detecte des configurations gagnantes ou perdantes afin de les appliquer, ou pas.)
			

			if(tabcurrent[0][0] != false) {
				return 1000;
			}
			
			if(nbFalseligne(tabcurrent,0,largeur) != nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1] != false) {
				return 1000;
			}
			if(nbFalseligne(tabcurrent,0,largeur) == nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1]  == false && tabcurrent[0][0] == false) {
				return 1000;
			}
			if((tabcurrent[0][1] !=false && tabcurrent[1][0] != false) && tabcurrent[0][0] == false) {
				return 0;
			}
			
			if((tabcurrent[0][1] == false && tabcurrent[1][0] != false ) || (tabcurrent[1][0] == false && tabcurrent[0][1] != false))  {
				return 0;

			}
			
			if(nbFalseligne(tabcurrent,0,largeur) == nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1] !=false && tabcurrent[0][0] == true) {
				return 0;
			}
			
			return 10;
		}
}
