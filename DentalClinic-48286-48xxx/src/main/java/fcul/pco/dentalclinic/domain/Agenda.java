package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

public class Agenda implements Iterable<Appointment> {

	/**
	 * Atribute
	 */
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	public void save(Doctor d) throws IOException {
		AgendaPersistence.save(d);
	}
	
	public static Agenda load(Doctor d) throws FileNotFoundException {
		return AgendaPersistence.load(d);
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
}
