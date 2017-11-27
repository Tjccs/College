package fcul.pco.dentalclinic.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	
	/*
	 * variables declaration
	 */
	private int min;
	private int hour;
	private int day;
	private int month;
	private int year;
	private static final Date[] HOLYDAYS = {
			new Date(1,1),
			new Date(4,14),
			new Date(4,16),
			new Date(4,25),
			new Date(5,1),
			new Date(6,10),
			new Date(6,15),
			new Date(8,15),
			new Date(10,5),
			new Date(11,1),
			new Date(12,1),
			new Date(12,8),
			new Date(12,25)
	};
	
	// Date constants
	private static final Date STARTDATE = new Date(2000, 1, 1);
	private static final int STARTDATEINT = STARTDATE.intValue();
	
	public Date(int d, int m, int y, int h, int minute) {
		this.day = d;
		this.month = m;
		this.year = y;
		this.hour = h;
		this.min = minute;
	}
	
	/**
	 * Constructor
	 * 
	 * @param month The month for the date
	 * @param day The day for the date
	 */
	private Date(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	/**
	 * Check if it's the same day
	 * 
	 * @param another Date variable
	 * @return True if it's in the same day, 
	 * false otherwise.
	 */
	public boolean sameDay(Date other) {
		return (this.day == other.day && month == other.month) ? true : false;
	}
	
	/**
	 * Check if the object date is before the other date
	 * 
	 * @param another Date variable
	 * @return True if the date is before the other date.
	 * false otherwise.
	 */
	public boolean isBefore(Date other) {
		return (this.day < other.day && month < other.month) ? true : false;
	}
	
	/**
	 * Check if it's holiday
	 * @return True if the date is an holiday, false by default
	 */
	public boolean isHolyday() {
		Date dateToCheck = new Date(this.day, this.month);
		boolean result = false;
		for(Date elem : HOLYDAYS) {
			if(dateToCheck.equals(elem)) {
				result = true;
			}
		}
		return result;
	}

	private static boolean isLeapYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
	
	private static int daysInMonth(int month, int year) {
		int days;
		switch (month) {
		    case 1:
		    case 3:
		    case 5:
		    case 7:
		    case 8:
		    case 10:
		    case 12:
		        days = 31;
		        break;
		    case 4:
		    case 6:
		    case 9:
		    case 11:
		        days = 30;
		        break;
		    case 2:
		    	if(isLeapYear(year)) {
		    		days = 28;
		    	}else {
		    		days = 29;
		    	}
		    	break;
		}
	}
	
	private static int daysInYear(int year) {
		return (isLeapYear(year)) ? 366 : 365;
	}
	
	private int intValue() {
		int value = min;
		value += hour * 60;
		value += (day - 1) * 1440;
		for (int m = 1; m < month; m++) {
		value += daysInMonth(m, year) * 1440;
		}
		for (int y = STARTDATE.year; y < year; y++) {
		value += daysInYear(y) * 1440;
		}
		return value - STARTDATEINT;
	}
	
	private int daysSinceStartDate() {
		
		//Date currentDate = new Date(this.day, this.month, this.year, this.hour, this.min);
		return this.intValue() - STARTDATEINT; 
	}
	
	public int dayOfWeek() {
		
		//this needs to be replaced, it's just a place holder.
		String dateString = day+"/"+month+"/"+year;
		java.util.Date dOW = new SimpleDateFormat("dd/M/yyyy").parse(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(dOW);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	
		 
	}


}
