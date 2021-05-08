package Controleur;

import Modele.Coup;
import Modele.Jeu;
import Vue.CollecteurEvenements;
import Vue.InterfaceG;

public class ControleurMediateur implements CollecteurEvenements {
    InterfaceG ig;
    Jeu jeu;

    public ControleurMediateur(Jeu j) {
        jeu = j;
    }

    @Override
    public void clicSouris(int l, int c) {
        Coup cp = new Coup(l,c);
        jeu.jouerCoup(cp);
    }

    @Override
    public boolean commande(String c) {
        switch (c) {
            case "quit":
                System.exit(0);
                break;
            case "fullscreen":
                ig.basculePleinEcran();
                break;
            case "undo":
                break;
            case "redo":
                break;
            case "next":
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void fixerInterfaceUtilisateur(InterfaceG i) {
        ig=i;
    }
}
