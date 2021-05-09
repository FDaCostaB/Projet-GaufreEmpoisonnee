package InterfaceGraphique;

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
        drawable.fillRect(j,i,tailleC,tailleC);
    }
    public void remplirRect(int i, int j, int width, int height, Color color){
        drawable.setColor(color);
        drawable.fillRect(j,i,width,height);
    }

    public void dessinerTrait(int x1,int y1,int x2, int y2){
        drawable.drawLine(x1,y1,x2,y2);
    }
    public void dessinerGrille(){
        int i=0,j=0;
        drawable.setColor(Color.BLACK);
        while(i<jeu.longueur()) {
            while (j < jeu.largeur() && !jeu.grille()[i][j] ) {
                j++;
            }
            dessinerTrait(0, i * tailleC, j * tailleC-1, i * tailleC);
            i++;
            j=0;
        }
        dessinerTrait(0, tailleC* jeu.longueur()-1, tailleC* jeu.largeur()-1, tailleC* jeu.longueur()-1);
        i=0;j=0;
        while(j<jeu.largeur()) {
            while ( i < jeu.longueur() && !jeu.grille()[i][j]) {
                i++;
            }
            dessinerTrait(j * tailleC, 0, j * tailleC, i * tailleC-1);
            j++;
            i=0;
        }
        dessinerTrait(tailleC* jeu.largeur()-1, 0, tailleC* jeu.largeur()-1, tailleC* jeu.longueur()-1);
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
        int hCase=hauteur/jeu.longueur();
        int lCase=largeur/jeu.largeur();
        tailleC=Math.min(hCase,lCase);



        remplirRect(0,0, tailleC* jeu.largeur(), tailleC*jeu.longueur(),Color.ORANGE);

        dessinerGrille();

        for(int i=0;i<jeu.longueur();i++){
            for(int j=0;j<jeu.largeur();j++){
                if(jeu.grille()[i][j]) remplirRect(i*tailleC,j*tailleC,Color.WHITE);
            }
        }
        remplirRect(0,0,Color.GREEN);
    }

    public int tailleC() {
        return tailleC;
    }
}
