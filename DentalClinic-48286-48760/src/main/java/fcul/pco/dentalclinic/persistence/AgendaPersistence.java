package fcul.pco.dentalclinic.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fcul.pco.dentalclinic.domain.Agenda;
import fcul.pco.dentalclinic.domain.Appointment;
import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

public class AgendaPersistence {
	
	public static void save(Agenda a, Doctor d) throws IOException {
		String dId = Integer.toString(d.getId());
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY
                + "/"
                + dId, true))) {
            for (Appointment ap : a) {
                bw.write(ap.toString());
                bw.newLine();
            }
        }
	}

	
	public static Agenda load(Doctor d) throws FileNotFoundException {
		Agenda aLoad = d.getAgenda();
		String dId = Integer.toString(d.getId());
        try (Scanner br = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
                + "/"
                + dId))) {
            while (br.hasNextLine()) {
                Appointment a = Appointment.fromString(br.nextLine());
                aLoad.addAppointment(a);
            }
        } catch (FileNotFoundException ex) {
            // if the file is not found return the empty catalog.
        }
        return aLoad;
	}
}

