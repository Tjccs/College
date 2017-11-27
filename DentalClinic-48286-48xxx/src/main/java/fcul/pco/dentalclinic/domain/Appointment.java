package fcul.pco.dentalclinic.domain;

public class Appointment {

		/*
		 * variables declaration
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
		
		@Override
		public String toString() {
			return date+"$"+duration+"$"+task;
		}
}
