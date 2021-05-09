package Controleur;

import Global.Configuration;
import InterfaceGraphique.CollecteurEvenements;
import InterfaceGraphique.InterfaceG;
import Modele.Coup;
import Modele.Jeu;

public class ControleurMediateur implements CollecteurEvenements {
    InterfaceG ig;
    Jeu jeu;
    boolean iaActive;
    IA ia;
    Coup cp;
    int iaLenteur;

    public ControleurMediateur(Jeu j) {
        jeu = j;
        iaActive = Boolean.parseBoolean(Configuration.instance().lis("IAActive"));
        iaLenteur = Integer.parseInt(Configuration.instance().lis("IALenteur"));
        ia = new IAAleatoire(jeu);
    }

    @Override
    public void clicSouris(int l, int c) {
        System.out.println("Clic en ( l : " +l+" , c : "+c+" )" );
        cp = new Coup(l,c);
        jeu.jouerCoup(cp);

        if (iaActive){
            jeu.jouerCoup(cp);
            System.out.println("IA a cliqué sur ( l : " +cp.l+" , c : "+cp.c+" )" );
        }
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
