package Vue;

import Modele.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable, Observateur, InterfaceUtilisateur {
	Jeu jeu;
	CollecteurEvenements controle;
	boolean maximized;
	JFrame frame;
	JLabel pas,pousse;
	NiveauGraphiqueSwing ng;

	InterfaceGraphique(Jeu j, CollecteurEvenements c) {
		jeu = j;
		jeu.ajouteObservateur(this);
		controle = c;
		controle.fixerInterfaceUtilisateur(this);
	}

	public static void demarrer(Jeu j, CollecteurEvenements c) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, c));
	}

	public void run() {
		frame = new JFrame("Sokoban");
		ng = new NiveauGraphiqueSwing(jeu);
		frame.add(ng);

		Box boite = Box.createVerticalBox();

		JLabel titre = new JLabel("Sokoban");
		ajouterCompCentre(boite,titre);
		boite.add(Box.createGlue());

		ng.addMouseListener(new AdaptateurSouris(ng, controle));
		frame.addKeyListener(new AdaptateurClavier(controle));
		Timer time = new Timer(16, new AdaptateurTemps(controle));
		time.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}

	@Override
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

}
