package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

public class Agenda implements Iterable<Appointment> {

	/**
	 * Attribute
	 */
	ArrayList<Appointment> appointments;
	
	List<Date> dateList;
	private int index;
	
	public Agenda() {
		appointments = new ArrayList<Appointment>();
		index = 0;
	}
	
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	public void save(Doctor d) throws IOException {
		fcul.pco.dentalclinic.persistence.AgendaPersistence.save(d.getAgenda(), d);
	}
	
	public static Agenda load(Doctor d) throws FileNotFoundException {
		return fcul.pco.dentalclinic.persistence.AgendaPersistence.load(d);
	}
	
	public List<Appointment> getDayAppointments(Date d){
		List<Appointment> aL = new ArrayList<Appointment>();
		for(Appointment ap : appointments) {
			if(ap.getDate().sameDay(d)) {
				aL.add(ap);
				Collections.sort(aL);
			}
		}
		return aL;
	}
	
	public List<Date> getNextAppointmentDates(Date from) {
		//TODO ??
		dateList = new ArrayList<>();
		for(Appointment ap : this) {
			if(!ap.getDate().isBefore(from)) {
				
				dateList.add(ap.getDate());
			}
		}
		return dateList;
	}
	
	@Override
    public Iterator<Appointment> iterator() {
		return appointments.iterator();
    }

	
}
