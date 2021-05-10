package Controleur;

import Moteur.Jeu;

public class HeuristiqueB {
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
     * calcule_heuristique : modifie par effet de bord l'heuristique d'un noeud donné pour le joueur B (inverse du joueur A)
     *  @param N : le noeud duquel il faut calculer l'heuristique
     */
	public static int calcule_heuristique(Noeud N) {
		//on recupere le tableau du pere

		//on recupere le tableau du noeud courant
		Jeu tcurrent = TabConverter.ToTab(N.valeur());
		boolean[][] tabcurrent = tcurrent.grille();
		
		//on recupere les dimensions
		int hauteur = tcurrent.getHeight();
		int largeur = tcurrent.getWidth();
		
		//traitement (on detecte des configurations gagnantes ou perdantes afin de les appliquer, ou pas.)
		

		if(tabcurrent[0][0] != false) {
			return 0;
			
		}
		
		if((tabcurrent[0][1] == false && tabcurrent[0][1] != false ) || (tabcurrent[0][1] == false && tabcurrent[0][1] !=false )) {	
			return 0;

		}
		if(nbFalseligne(tabcurrent,0,largeur) == nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1]  == false && tabcurrent[0][0] == false) {
			return 0;
		}
		if(nbFalseligne(tabcurrent,0,largeur) != nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1]  != false) {
			return 0;
		}
		
		if((tabcurrent[0][1] != false  && tabcurrent[1][0] != false) && tabcurrent[0][0] == false) {
			return 1000;
		}
		
		if(nbFalseligne(tabcurrent,0,largeur) == nbFalsecolonne(tabcurrent,0,hauteur) && tabcurrent[1][1] != false && tabcurrent[0][0] == false) {
			return 1000;
		}

		return 10;
	}
}

