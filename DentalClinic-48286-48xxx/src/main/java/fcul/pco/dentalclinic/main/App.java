package fcul.pco.dentalclinic.main;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.DoctorCatalog;

public class App {

	
	static DoctorCatalog doctorCatalog;
	
	public static void main(String[] args) {
		initialize();
		interactiveMode();
		// executeAllUseCases();
	}

	/**
	*This method may be called to use the application in default mode i.e.
	*interacting with the keyboard.
	*
	*@throws IOException
	*/
	private static void interactiveMode() throws IOException {
		try (Scanner in = new Scanner(System.in)) {
			in.useLocale(Locale.US);
			Menu.mainMenu(in);
		}
	}

	private static void initialize() {
		doctorCatalog = DoctorCatalog.getInstance();
		try {
			doctorCatalog.load();
		} catch (IOException ex) {
			System.err.println("Error loading DoctorCatalog.");
		}
	}
}
