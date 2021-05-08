package Vue;


import Modele.Jeu;
import javax.swing.*;
import java.awt.*;

class NiveauGraphiqueSwing extends JComponent implements NiveauGraphique {
	int largeur, hauteur;
	Graphics2D drawable;
	VueNiveau vue;

	public NiveauGraphiqueSwing(Jeu j) {
		vue = new VueNiveau(j, this);
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
		vue.tracerNiveau();
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}


	int largeurCase() {
		return vue.largeurCase();
	}

	int hauteurCase() {
		return vue.hauteurCase();
	}

	@Override
	public void tracerCroix(int marque, int x, int y, int l, int h) {
		int s = l/10;
		drawable.setColor(new Color(marque));
		drawable.setStroke(new BasicStroke(s));
		drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawable.drawLine(x+s, y+s, x+l-s, y+h-s);
		drawable.drawLine(x+h-s, y+s, x+s, y+h-s);
	}
}