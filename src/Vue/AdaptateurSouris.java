package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter {
	GaufreGraphique niveau;
	CollecteurEvenements controle;

	AdaptateurSouris(GaufreGraphique n, CollecteurEvenements c) {
		niveau = n;
		controle = c;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int c = e.getX() / niveau.tailleC();
		int l = e.getY() / niveau.tailleC();
		controle.clicSouris(l, c);
	}
}
