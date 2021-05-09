package IHM;

import Controleur.CollecteurEvenements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurIA implements ActionListener {
	CollecteurEvenements controle;
	String commande;

	AdaptateurIA(CollecteurEvenements c, String com) {
		controle = c;
		commande = com;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controle.commande(commande);
	}

}
