package Controleur;

import Global.Configuration;
import IHM.AdaptateurTemps;
import IHM.InterfaceG;
import Moteur.Coup;
import Moteur.Jeu;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.NoSuchElementException;

public class ControleurMediateur implements CollecteurEvenements {
    InterfaceG ig;
    Jeu jeu;
    boolean iaActive;
    IA ia;
    Coup cp;
    int iaLenteur;
    Timer tempsIA;
    boolean iaPeutJouer;

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
        tempsIA = new Timer(3000,new AdaptateurTemps(ia,jeu,this));
        tempsIA.setRepeats(false);
    }

    public void activeIA(int state){
        if(state == ItemEvent.DESELECTED) {
            iaActive = true;
        }
    }

    @Override
    public void clicSouris(int l, int c) {
        System.out.println("Clic en ( l : " +l+" , c : "+c+" )" );
        cp = new Coup(l,c,jeu);
        jeu.jouerCoup(cp,false);

        if (iaActive && !jeu.testFin()){
            System.out.println("Coup de l'ia en préparation" );
            iaPeutJouer=true;
            tempsIA.restart();
        }
    }

    public void jouerCoupIA(Coup cp){
        if(iaPeutJouer)
        jeu.jouerCoup(cp,true);
    }

    @Override
    public void undo() {
        jeu.undo();

        if(iaActive){
            iaPeutJouer=false;
            System.out.println("Coup de l'ia annulé" );
        }
    }

    @Override
    public void redo() {
        jeu.redo();
        if (iaActive && !jeu.testFin()){
            System.out.println("Coup de l'ia en préparation" );
            iaPeutJouer=true;
            tempsIA.restart();
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
                undo();
                break;
            case "redo":
                redo();
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
