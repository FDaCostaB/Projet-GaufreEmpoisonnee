package Controleur;

import IHM.InterfaceG;

public interface CollecteurEvenements {
		void clicSouris(int l, int c);
		boolean commande(String c);
		void fixerInterfaceUtilisateur(InterfaceG i);
		void fixerIA(String com);
		boolean iaActive();
		void activeIA(int state);
	}

