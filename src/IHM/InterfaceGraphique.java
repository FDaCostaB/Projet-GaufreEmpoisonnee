package IHM;

import Controleur.CollecteurEvenements;
import Moteur.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

public class InterfaceGraphique implements Runnable, Observer, InterfaceG {
	Jeu jeu;
	CollecteurEvenements controle;
	boolean maximized;
	JFrame frame;
	GaufreGraphique gaufreG;
	ButtonGroup group;
	boolean ia = false;
	Box boite,boiteInfo;
	JLabel info;
	JButton commencer,undo,redo;

	InterfaceGraphique(Jeu j, CollecteurEvenements c) {
		jeu = j;
		jeu.niv.addObserver(this);
		controle = c;
		controle.fixerInterfaceUtilisateur(this);
	}

	public static void demarrer(Jeu j, CollecteurEvenements c) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, c));
	}

	public void run() {
		frame = new JFrame("Gauffre empoisonée");

		boite = Box.createVerticalBox();

		//Create the radio buttons.
		JRadioButton iaFacile = new JRadioButton("Facile");
		iaFacile.setActionCommand("facile");
		iaFacile.setEnabled(false);

		JRadioButton iaMoyen = new JRadioButton("Moyen");
		iaMoyen.setActionCommand("moyen");
		iaMoyen.setEnabled(false);

		JRadioButton iaDifficile = new JRadioButton("Difficile");
		iaDifficile.setActionCommand("difficile");
		iaDifficile.setEnabled(false);

		//Group the radio buttons.
		group = new ButtonGroup();
		group.add(iaFacile);
		group.add(iaMoyen);
		group.add(iaDifficile);

		//Register a listener for the radio buttons.
		iaFacile.addActionListener(new AdaptateurIA(controle, iaFacile.getActionCommand()));
		iaMoyen.addActionListener(new AdaptateurIA(controle, iaMoyen.getActionCommand()));
		iaDifficile.addActionListener(new AdaptateurIA(controle, iaDifficile.getActionCommand()));


		JLabel titre = new JLabel("Menu");

		//Choix de l'adversaire IA/Joueur
		JCheckBox iaActive = new JCheckBox("Joueur Vs IA");
		iaActive.setSelected(ia);

		//Lancer la partie
		commencer = new JButton("Commencer");
		commencer.addActionListener(new AdaptateurCommande(controle,"commencer"));

		//Register a listener for the check boxe.
		iaActive.addItemListener(new AdaptateurInterface(this,controle));

		//choix de la difficulté de l'ia
		JLabel titreIA = new JLabel("Difficulté IA");

		//Alignement et ajout des pemiers éléments
		iaActive.setAlignmentX(Component.CENTER_ALIGNMENT);
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		titreIA.setAlignmentX(Component.CENTER_ALIGNMENT);
		boite.add(titre);
		boite.add(Box.createVerticalGlue());
		boite.add(iaActive);
		boite.add(Box.createVerticalGlue());
		boite.add(titreIA);

		//Alignement Ajout des radios buttons
		iaFacile.setAlignmentX(Component.CENTER_ALIGNMENT);
		iaMoyen.setAlignmentX(Component.CENTER_ALIGNMENT);
		iaDifficile.setAlignmentX(Component.CENTER_ALIGNMENT);
		boite.add(iaFacile);
		boite.add(iaMoyen);
		boite.add(iaDifficile);
		boite.add(Box.createVerticalGlue());

		commencer.setAlignmentX(Component.CENTER_ALIGNMENT);
		boite.add(commencer);


		frame.add(boite);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public void activeIA(){
		if(ia) {
			group.clearSelection();
		}
		commencer.setEnabled(controle.iaActive());

		Enumeration<AbstractButton> enu = group.getElements();
		AbstractButton curr;
		while (enu.hasMoreElements()) {
			curr = enu.nextElement();
			curr.setEnabled(!ia);
		}
		ia= !ia;
	}

	public void updateCommencer(){
		commencer.setEnabled(controle.iaActive());
	}

	public void lancerPartie(){
		frame.remove(boite);

		gaufreG = new GaufreGraphique(jeu);
		frame.add(gaufreG);

		boiteInfo = Box.createHorizontalBox();

		info = new JLabel();
		info.setText("Au tour du joueur n°"+(jeu.joueur+1));

		JButton recommencer = new JButton("Recommencer");
		recommencer.addActionListener(new AdaptateurCommande(controle,"recommencer"));

		undo = new JButton("Annuler");
		undo.addActionListener(new AdaptateurCommande(controle,"undo"));

		redo = new JButton("Refaire");
		redo.addActionListener(new AdaptateurCommande(controle,"redo"));

		undo.setEnabled(false);
		redo.setEnabled(false);

		boiteInfo.add(info);
		boiteInfo.add(Box.createHorizontalGlue());
		boiteInfo.add(undo);
		boiteInfo.add(redo);
		boiteInfo.add(Box.createHorizontalGlue());
		boiteInfo.add(recommencer);

		frame.add(boiteInfo,BorderLayout.PAGE_END);
		gaufreG.addMouseListener(new AdaptateurSouris(gaufreG, controle));
		frame.setSize(500, 300);

	}

	public void retourMenu(){
		frame.remove(boiteInfo);
		frame.remove(gaufreG);
		frame.add(boite);
		jeu.reinitialiser();
		frame.setSize(300, 300);
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
		info.setText("Au tour du joueur n°"+(jeu.joueur+1));
		undo.setEnabled(jeu.reculable());
		redo.setEnabled(jeu.avancable());
		if(jeu.testFin()){
			if(jeu.abandon()){
				info.setText("Abandon du joueur n°"+(jeu.joueur+1));
			} else {
				jeu.joueur = (jeu.joueur+1)%2;
				info.setText("Victoire du joueur n°"+(jeu.joueur+1));
			}
			undo.setEnabled(false);
			redo.setEnabled(false);
		}
		gaufreG.repaint();
	}
}
