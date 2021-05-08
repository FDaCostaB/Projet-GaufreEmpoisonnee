package Vue;

import Global.Configuration;
import Modele.Jeu;
import Modele.Niveau;

import java.io.File;
import java.io.InputStream;

class VueNiveau {
	Jeu jeu;
	int largeurCase, hauteurCase;
	NiveauGraphique ng;

	public VueNiveau(Jeu j, NiveauGraphique n) {

	}

	void tracerNiveau() {

	}

	int largeurCase() {
		return largeurCase;
	}

	int hauteurCase() {
		return hauteurCase;
	}

}