package fcul.pco.dentalclinic.domain;



public class Appointment {

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

		public Appointment(Date date, String task, int duration, int pId) {
			this.date = date;
			this.task = task;
			this.duration = duration;
			Patient p = patientCatalog.getPatientById(pId);
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
		
		public static Appointment fromString(String s) {
			String[] elements = s.split("[$]");
			Date date = Date.fromString(elements[0]);
			Appointment ap = new Appointment(date, elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
			return ap;
		}
		
		@Override
		public String toString() {
			return date+"$"+task+"$"+duration+"$"+pId;
		}
}
