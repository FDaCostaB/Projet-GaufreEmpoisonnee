import Controleur.ControleurMediateur;
import Moteur.Jeu;
import IHM.InterfaceGraphique;

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
