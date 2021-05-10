package IHM;

import Controleur.CollecteurEvenements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AdaptateurInterface implements ItemListener {
	InterfaceG ig;
	CollecteurEvenements control;
	AdaptateurInterface(InterfaceG i, CollecteurEvenements c) {
		ig = i;
		control =c;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		control.activeIA(e.getStateChange());
		ig.activeIA();
	}
}
