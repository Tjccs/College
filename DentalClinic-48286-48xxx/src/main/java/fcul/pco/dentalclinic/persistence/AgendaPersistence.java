package fcul.pco.dentalclinic.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fcul.pco.dentalclinic.domain.Agenda;
import fcul.pco.dentalclinic.domain.Appointment;
import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

public class AgendaPersistence {
	
	public static void save(Agenda a, Doctor d) throws IOException {
		StringBuilder sbf = new StringBuilder();
		String dId = Integer.toString(d.getId());
		List k = a.getAppointments();
		for(Appointment ap : a) {
			
		}
		
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY,dId)));
	    bwr.write(sbf.toString());
	    bwr.flush();
	    bwr.close();
	 }

	public static Agenda load(Doctor d) throws FileNotFoundException {
		Agenda aLoad = new Agenda();
		String dId = Integer.toString(d.getId());
		Scanner scanner = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY+dId));
		scanner.useDelimiter("[$]");

		while (scanner.hasNext()) {
			String[] element = scanner.nextLine().split("[$]");
			Appointment a = Appointment.fromString(element[0]+"$"+element[1]+"$"+element[2]);
			aLoad.addAppointment(a);
		}	
		scanner.close();
		return aLoad;
	}
}

