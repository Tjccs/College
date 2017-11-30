package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

public class Agenda implements Iterable<Appointment> {

	/**
	 * Attribute
	 */
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	public void save(Doctor d) throws IOException {
		//Duvida
		AgendaPersistence.save(d.getAgenda(), d);
	}
	
	public static Agenda load(Doctor d) throws FileNotFoundException {
		return AgendaPersistence.load(d);
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	
	public List<Date> getNextAppointmentDates(Date from) {
		//TODO
		for(Appointment ap : appointments) {
			
		}
		return null;
	}
	
	@Override
	public Iterator<Appointment> iterator() {
		
		return null;
	}
	
	
}
