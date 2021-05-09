package Controleur;

import Global.Configuration;
import IHM.InterfaceG;
import Moteur.Coup;
import Moteur.Jeu;

import java.awt.event.ItemEvent;
import java.util.NoSuchElementException;

public class ControleurMediateur implements CollecteurEvenements {
    InterfaceG ig;
    Jeu jeu;
    boolean iaActive;
    IA ia;
    Coup cp;
    int iaLenteur;

    public ControleurMediateur(Jeu j) {
        jeu = j;
        iaActive = false;
    }

    public boolean iaActive(){
        return iaActive;
    }

    public void fixerIA(String com){
        iaActive = true;
        switch (com){
            case "facile":
                ia = new IAAleatoire(jeu);
                ig.updateCommencer();
                break;
            case "moyen":
                break;
            case "difficile":
                break;
            default:
                throw new NoSuchElementException();
        }
        iaActive = Boolean.parseBoolean(Configuration.instance().lis("IAActive"));
        iaLenteur = Integer.parseInt(Configuration.instance().lis("IALenteur"));
    }

    public void activeIA(int state){
        if(state == ItemEvent.DESELECTED) {
            iaActive = false;
        }
    }

    @Override
    public void clicSouris(int l, int c) {
        System.out.println("Clic en ( l : " +l+" , c : "+c+" )" );
        cp = new Coup(l,c);
        jeu.jouerCoup(cp);

        if (iaActive){
            jeu.jouerCoup(ia.coupIA(jeu));
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
            case "commencer":
                ig.lancerPartie();
                break;
            case "recommencer":
                ig.retourMenu();
                break;
            case "facile":
            case "moyen":
            case "difficile":
                fixerIA(c);
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
