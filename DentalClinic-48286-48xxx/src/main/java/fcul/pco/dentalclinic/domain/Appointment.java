package fcul.pco.dentalclinic.domain;

import java.util.ArrayList;
import java.util.List;

public class Appointment implements Comparable<Appointment>{

		/*
		 * variables declaration
		 */
		private Date date;
		private String task;
		private int duration;
		private Patient patient;
		private int pId;
		PatientCatalog patientCatalog;
		Agenda a;

		public Appointment(Date date, String task, int duration, Patient p) {
			this.date = date;
			this.task = task;
			this.duration = duration;
			//this.pId = p.getSns();
			this.patient = p;
		}
		
		
		/**
		 * 
		 * @param task
		 * 
		 */
		public void setTask(String task) {
			this.task = task;
		}
		
		
		public Date getEndDate() {
			return date;
		}
		
		public Date getDate() {
			return date;
		}
		
		public List<String> toRow() {
			// TODO Fazer esta coisa
			List<String> result = new ArrayList<String>();
			return null;
		}
		
		public static Appointment fromString(String s) {
			String[] elements = s.split("[$]");
			Date date = Date.fromString(elements[0]);
			
			Appointment ap = new Appointment(date, elements[1], Integer.parseInt(elements[2]), Patient.fromString(elements[3]));
			return ap;
		}
		
		@Override
		public String toString() {
			return date+"$"+task+"$"+duration+"$"+patient;
		}


		@Override
		public int compareTo(Appointment a) {
			
			/*final int BEFORE = -1;
			final int EQUAL = 0;
			final int AFTER = 1;
			
			if (this == a) return EQUAL;
			
			if(this.getDate().isBefore(a.getDate())) return BEFORE;
			if(!(this.getDate().isBefore(a.getDate()))) return AFTER;
			
			return EQUAL;*/
			return this.compareTo(a);
		}
}
