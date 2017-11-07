package fcul.pco.dentalclinic;

public class Date {
	
	/*
	 * variables initialization 
	 */
	private int min;
	private int hour;
	private int day;
	private int month;
	private int year;
	
	public Date(int d, int m, int y, int h, int minute) {
		this.day = d;
		this.month = m;
		this.year = y;
		this.hour = h;
		this.min = minute;
	}
	
	
	/*
	 * Check if it's the same day
	 * 
	 * @param another Date variable
	 * @return True if it's in the same day, 
	 * false otherwise.
	 */
	public boolean sameDay(Date other) {
		return (this.day == other.day) ? true : false;
		
	}
	
	/*
	 * Check if it's before the other date
	 * 
	 * @param another Date variable
	 * @return True if it's before the other day, 
	 * false otherwise.
	 */
	public boolean isBefore(Date other) {
		return (this.day < other.day) ? true : false;
	}
	
}
