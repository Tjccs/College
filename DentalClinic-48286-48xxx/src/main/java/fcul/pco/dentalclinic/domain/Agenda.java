package fcul.pco.dentalclinic.domain;

import java.util.ArrayList;

public class Agenda {

	/**
	 * Atribute
	 */
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}

}
