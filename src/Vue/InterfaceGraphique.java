package Vue;

import Modele.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class InterfaceGraphique implements Runnable, Observer, InterfaceG {
	Jeu jeu;
	CollecteurEvenements controle;
	boolean maximized;
	JFrame frame;
	//JLabel pas,pousse;
	GaufreGraphique gaufreG;

	InterfaceGraphique(Jeu j, CollecteurEvenements c) {
		jeu = j;
		jeu.addObserver(this);
		controle = c;
		controle.fixerInterfaceUtilisateur(this);
	}

	public static void demarrer(Jeu j, CollecteurEvenements c) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, c));
	}

	public void run() {
		frame = new JFrame("Sokoban");
		gaufreG = new GaufreGraphique(jeu);
		frame.add(gaufreG);

		Box boite = Box.createVerticalBox();

		JLabel titre = new JLabel("Sokoban");

		gaufreG.addMouseListener(new AdaptateurSouris(gaufreG, controle));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}

	public void basculePleinEcran() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		//pas.setText("Pas : "+ jeu.niveau().pas);
		//pousse.setText("Poussées : "+ jeu.niveau().pousse);
		gaufreG.repaint();
	}
}
