import InterfaceGraphique.ControleurMediateur;
import Modele.Jeu;
import InterfaceGraphique.InterfaceGraphique;

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
