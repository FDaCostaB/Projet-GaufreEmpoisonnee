package Vue;

import Modele.Jeu;

import javax.swing.*;
import java.awt.*;

public class GaufreGraphique extends JComponent {
    Graphics2D drawable;
    int largeur,hauteur,tailleC;
    Jeu jeu;

    GaufreGraphique(Jeu j){
        jeu=j;
    }
    public void remplirRect(int i, int j, Color color){
        drawable.setColor(color);
        drawable.fillRect(i,j,tailleC,tailleC);
    }

    public void dessinerTrait(int x1,int y1,int x2, int y2){
        drawable.drawLine(x1,y1,x2,y2);
    }
    public void dessinerGrille(){
        int i=0,j=0;
        drawable.setColor(Color.BLACK);
        while(i<jeu.height) {
            while (!jeu.grille()[i][j] && j < jeu.width) {
                j++;
            }
            dessinerTrait(0, i * tailleC, j * tailleC, i * tailleC);
            i++;
        }
        i=0;j=0;
        while(j<jeu.width) {
            while (!jeu.grille()[i][j] && j < jeu.height) {
                i++;
            }
            dessinerTrait(j * tailleC, 0, j * tailleC, i * tailleC);
            j++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passé en paramètre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        drawable = (Graphics2D) g;

        // On reccupere quelques infos provenant de la partie JComponent
        largeur = getSize().width;
        hauteur = getSize().height;

        // On efface tout
        drawable.clearRect(0, 0, largeur, hauteur);

        //Calcul de la taille d'une case
        int hCase=hauteur/jeu.height;
        int lCase=largeur/jeu.width;
        tailleC=Math.min(hCase,lCase);

        dessinerGrille();

        for(int i=0;i<jeu.height;i++){
            for(int j=0;j<jeu.width;j++){
                if(jeu.grille()[i][j]) remplirRect(i*tailleC,j*tailleC,Color.ORANGE);
                else remplirRect(i*tailleC,j*tailleC,Color.WHITE);
            }
        }
    }

    public int tailleC() {
        return tailleC;
    }
}
