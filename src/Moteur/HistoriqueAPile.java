package Moteur;

import Global.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueAPile<Type extends Commande>{
    ArrayList<Type> passe;
    ArrayList<Type> futur;

    public HistoriqueAPile() {
        passe = new ArrayList<>();
        futur = new ArrayList<>();
    }

    protected void avancer(){
        Type c = futur.get(0);
        futur.remove(0);
        passe.add(0,c);
        c.execute();
    }

    protected void reculer(){
            Type c = passe.get(0);
            passe.remove(0);
            futur.add(0,c);
            c.desexecute();
    }

    public boolean reculable(){
        return !passe.isEmpty();
    }

    public boolean avancable(){
        return !futur.isEmpty();
    }

    protected void nouveau(Type t){
        futur = new ArrayList<>();
        passe.add(0,t);
    }

    protected void reset() {
        passe.clear();
        futur.clear();
    }
}
