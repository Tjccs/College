package fcul.pco.dentalclinic.main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.DoctorCatalog;
import fcul.pco.dentalclinic.domain.PatientCatalog;

public class App {

	
	static DoctorCatalog doctorCatalog;
	static PatientCatalog patientCatalog;
	
	
	public static void main(String[] args) throws IOException, ParseException {
		initialize();
		interactiveMode();
		//executeAllUseCases();
	}

	/**
	*This method may be called to use the application in default mode i.e.
	*interacting with the keyboard.
	*
	*@throws IOException
	 * @throws ParseException 
	*/
	private static void interactiveMode() throws IOException, ParseException {
		try (Scanner in = new Scanner(System.in)) {
			in.useLocale(Locale.US);
			Menu.mainMenu(in);
		}
	}

	private static void initialize() {
		doctorCatalog = DoctorCatalog.getInstance();
		patientCatalog = PatientCatalog.getInstance();
		try {
			doctorCatalog.load();
			patientCatalog.load();
		} catch (IOException ex) {
			System.err.println("Error loading DoctorCatalog.");
		}
	}

	/**
	*This method allows the application to work in non interactive mode i.e.
	*the input is read from a file. It should be used for testing. A file,
	*called a use-case contains a sequence of inputs that allows testing some
	*functionalities of the application. A use-case file may contain comments
	*(useful to make it easy to understand). Comments begin with the #
	*character and extend until the end of the line. Here is an example of
	*use-case file:
	*--------
	*# addDoctorUsecase
	* 3
	* 1
	* Johnny
	* 123
	* 2
	* 3
	* 4
	* --------
	*
	*
	*@param useCaseFileName A String that represents the name of a file that
	*contains a use-case.
	*@throws IOException
	 * @throws ParseException 
	*@requires the contents of the file must be correct with respect of the
	*menus (see class Menu) and the input data expected by the application,
	*unless the objective of the test is to verify an illegal situation.
	*/
	private static void executeUseCase(String useCaseFileName) throws IOException, ParseException {
		System.out.println("Test: " + useCaseFileName);
		Scanner in = new Scanner(new File(useCaseFileName));
		in.useLocale(Locale.US);
		in.nextLine();
		Menu.mainMenu(in);
		in.close();
	}
	
	public static void executeAllUseCases() throws ParseException {
		try {
			executeUseCase("data/addDoctorUsecase");
			// acrescentar aqui a execução de mais usecases.
			// executeUseCase("data/maisUsecaseAVossaEscolha");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static DoctorCatalog getDoctorCatalog() {
		
		return doctorCatalog;
	}

	public static PatientCatalog getPatientCatalog() {
		
		return patientCatalog;
	}
}
