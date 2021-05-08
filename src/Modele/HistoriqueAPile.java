package Structures;

import Global.Configuration;
import Patterns.Commande;

public class HistoriqueAPile<Type extends Commande>{
    Sequence<Type> passe;
    Sequence<Type> futur;

    public HistoriqueAPile() {
        passe = Configuration.instance().nouvelleSequence();
        futur = Configuration.instance().nouvelleSequence();
    }

    protected void avancer(){
        Type c = futur.extraitTete();
        passe.insereTete(c);
        c.execute();
    }

    protected void reculer(){
            Type c = passe.extraitTete();
            futur.insereTete(c);
            c.desexecute();
    }

    protected boolean reculable(){
        return !passe.estVide();
    }

    protected boolean avancable(){
        return !futur.estVide();
    }

    protected void nouveau(Type t){
        futur = Configuration.instance().nouvelleSequence();
        passe.insereTete(t);
    }
}
