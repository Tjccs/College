package fcul.pco.dentalclinic;

public class Appointment {

		/*
		 * variables initialization
		 */
		private Date date;
		private String task;
		private int duration;
		//private Person patient;

		public Appointment(Date date, String task, int duration) {
			this.date = date;
			this.task = task;
			this.duration = duration;
		}
		public void setTask(String task) {
			this.task = task;
		}

		public Date getEndDate() {
			return date;
		}
}
