package Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
	private static Configuration instance = null;
	Properties prop;

	public static InputStream charge(String nom) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(nom);
	}

	private Configuration() {
		prop = new Properties();
		try {
			InputStream propIn = charge("defaut.cfg");
			prop.load(propIn);
			System.out.println(prop);
			String home = System.getProperty("user.home");
			FileInputStream f = new FileInputStream(home + File.separator + ".gaufre");
			prop = new Properties(prop);
			prop.load(f);
		} catch (Exception e) {
			System.err.println("Erreur lors de la lecture de la configuration : " + e);
		}
		System.out.println(prop);
	}

	public static Configuration instance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public String lis(String cle) {
		String resultat = prop.getProperty(cle);
		if (resultat == null)
			throw new NoSuchElementException("Propriété " + cle + " non définie");
		return resultat;
	}

	public Logger logger() {
		Logger log = Logger.getLogger("Gaufre.Logger");
		log.setLevel(Level.parse(lis("LogLevel")));
		return log;
	}
 }
