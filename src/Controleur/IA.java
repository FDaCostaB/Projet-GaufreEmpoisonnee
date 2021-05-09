package Controleur;

import InterfaceGraphique.CollecteurEvenements;
import Modele.Coup;
import Modele.Jeu;

import javax.swing.*;

abstract class IA{

    abstract Coup coupIA(Jeu j);
}
