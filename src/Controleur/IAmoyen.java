package Controleur;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import Moteur.Coup;
import Moteur.Jeu;

public class IAmoyen extends IA{
	
	
	 	Random r;
	    Jeu j;
	    IAmoyen(Jeu j) {
	        r = new Random();
	        this.j=j;
	    }
	    
	    private int evaluerA(Noeud N) {
			return Heuristique.calcule_heuristique(N);	

		}
	    
	    private int evaluerB(Noeud N) {
			return HeuristiqueB.calcule_heuristique(N);

		}
	    
	    private Coup reconstruireCoup(Jeu origin, Jeu nouveau) {
			Coup c;
			for(int i=0;i < origin.largeur();i++) {
				for(int j=0;j < origin.getHeight();j++) {
					c = new Coup(j,i);
					
					if(nouveau.coupValide(c) && !origin.coupValide(c)) {
						return c;
					}
				}
			}
			return new Coup(0,0);
		}
	    
	    private int minimaxA(Noeud n, HashMap<Integer,Integer> r, int profondeur) {
			TabConverter.FilsNoeud(n);	//calcul des fils
			int heuristique;
			if (n.estFeuille() || profondeur == 0) {
				// la configuration ne permet pas de jouer,
				// le joueur B gagne
				heuristique = evaluerA(n); 
				r.put(n.valeur(), heuristique);
				n.setHeuristic(heuristique);
				return heuristique;
			} else {
				// Le joueur A doit jouer
				heuristique = 0;
				// On parcours l'ensemble des coups jouables par A
				for(Noeud fils : n.fils()) {
					int curr = minimaxB(fils, r, profondeur-1);
					// Si fils n'as pas encore ete calcule, le faire et mettre a jour r
					if(!r.containsKey(fils.valeur())) {
						r.put(fils.valeur(), curr);
					}
					heuristique = Math.max(heuristique,r.get(fils.valeur()));
				}
				r.put(n.valeur(), heuristique);
				n.setHeuristic(heuristique);
				return heuristique;
			}
		}
	    
	    private int minimaxB(Noeud n,HashMap<Integer,Integer> r, int profondeur) {
			TabConverter.FilsNoeud (n);	//calcul des fils
			int heuristique;
			if (n.estFeuille() || profondeur == 0) {
				// la configuration ne permet pas de jouer
				// le joueur A gagne
				heuristique = evaluerB(n);
				r.put(n.valeur(), heuristique);
				n.setHeuristic(heuristique);
				return heuristique;
			} else {
				// Le joueur B doit jouer
				heuristique = 1000; // + infini
				// On parcours l'ensemble des coups jouables par B
				for(Noeud fils : n.fils()) {
					int curr = minimaxA(fils, r, profondeur-1);
					// Si fils n'as pas encore ete calcule , le faire et mettre a jour r
					if(! r.containsKey(fils.valeur())) {
						r.put(fils.valeur(), curr);
					}
					heuristique = Math.min(heuristique,r.get(fils.valeur()));
				}
				r.put(n.valeur(), heuristique);
				n.setHeuristic(heuristique);
				return heuristique;
			}
		}
		
	    
	    Coup jouerCoupFacile(Jeu jeu) {
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
		

		Coup coupIA(Jeu j) {
			//ajoute de ca pour tester un cas ou ca plante si on le laisse tourner
			boolean[][] test = j.grille();
			int htest = j.getHeight();
			int ltest = j.getWidth();
			if(ltest == 2 && htest == 2 && test[1][1] != false && test[0][1] == false && test[1][0] == false) {
				int testrand = r.nextInt(2);
				if(testrand == 1)
					return new Coup(1,0);
				else 
					return new Coup(0,1);
			}else if (test[0][1] !=false  && test[1][0] != false)
				return new Coup(0,0);
			
			ArbreConfig a = new ArbreConfig(TabConverter.ToInt(j)); // construction de l'arbre des configurations
			HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>();
			int profondeur = 1;
			if(minimaxA(a.racine(),memo,profondeur) > 0) {
				LinkedList<Noeud> cp;
				if(( a.racine().filsTaggue().size()) != 0) {
					cp = a.racine().filsTaggue(); //recuperations des solutions
				}
				else {
					return jouerCoupFacile(j);
				}
				int rand = r.nextInt(cp.size()); //choix d'une solution admissible aleatoire
				Jeu nouveau = TabConverter.ToTab(cp.get(rand).valeur()); //traduction de la solution en Plateau
				Coup res = reconstruireCoup(j , nouveau); //traduction de la solution en Couple
				//plateau.manger(res); //Appliquer solution
				return res;
			} else {
				return jouerCoupFacile(j);
			}
		
		
         }
}		
