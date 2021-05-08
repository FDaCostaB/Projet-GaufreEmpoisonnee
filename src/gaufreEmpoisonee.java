import Controleur.ControleurMediateur;
import Modele.Jeu;
import Vue.InterfaceGraphique;

import java.io.File;
import java.io.InputStream;

class gaufreEmpoisonee {

	public static void main(String [] args) {
		try {
			Jeu j = new Jeu();
			ControleurMediateur c = new ControleurMediateur(j);
			InterfaceGraphique.demarrer(j, c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
