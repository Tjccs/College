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
	ArrayList<Appointment> appointments;
	
	public Agenda() {
		appointments = new ArrayList<Appointment>();
	}
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	public void save(Doctor d) throws IOException {
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
        Iterator<Appointment> it = new Iterator<Appointment>() {

            private int currentIndex = 0;

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Appointment next() {
				// TODO Auto-generated method stub
				return null;
			}
        };
        return it;
    }

	
}
