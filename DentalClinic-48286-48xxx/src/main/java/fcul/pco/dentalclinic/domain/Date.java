package fcul.pco.dentalclinic.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author Fc48286
 * @author Fc48760
 *
 */
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
	private static final Date STARTDATE = new Date(2000, 1, 1, 0, 0);
	private static final int STARTDATEINT = STARTDATE.intValue();
	
	/**
	 * Constructor
	 * @param y The year of the date
	 * @param m The month of the date
	 * @param d The day of the date
	 */
	public Date(int y, int m, int d, int h, int minute) {
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
		return (this.day == other.day && this.month == other.month && this.year == other.year) ? true : false;
	}
	
	/**
	 * Check if the object date is before the other date
	 * 
	 * @param another Date variable
	 * @return True if the date is before the other date.
	 * false otherwise.
	 */
	public boolean isBefore(Date other) {
		//return (this.day < other.day && this.month < other.month) ? true : false;
		if(this.year < other.year) return true;
		if(this.year == other.year) {
			if(this.month < other.month) {
				return true;
			}
			if(this.month == other.month) {
				if(this.day < other.day) {
					return true;
				}
				if(this.day == other.day) {
					if(this.hour < other.hour) {
						return true;
					}
					if(this.hour == other.hour) {
						return this.min < other.min;
					}
				}
			}
		}
		return false;
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

	/**
	 * Check if it's leap year.
	 * @param year The year to check
	 * @return True if it's leap year or false otherwise.
	 */
	private static boolean isLeapYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
	
	/**
	 * Gives the days in the month param.
	 * @param month The month to check
	 * @param year The year to check its its a leap year or not.
	 * @return The days in a given month
	 */
	private static int daysInMonth(int month, int year) {
		int days = 0;
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
		return days;
	}
	
	/**
	 * Checks if it's a year with 366 or 365 days.
	 * @param year A given year
	 * @return The days of the year, either 365 or 366.
	 */
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
	
	/**
	 * Gives us the days that passed since the start date.
	 * @return The number of days passed since the start date.
	 */
	private int daysSinceStartDate() {
		
		return this.intValue() - STARTDATEINT; 
	}
	
	/**
	 * Adds the given minutes to the date.
	 * @param mins
	 * @return a new date with the minutes added.
	 */
	public Date addMinutes(int mins) {
		int minsAdd = min;
		minsAdd += mins;
		if(minsAdd >= 60) {
			if(minsAdd == 60) {
				minsAdd = 0;
				this.hour += 1;
			}else {
				this.hour += mins / 60;
				minsAdd %= 60;
			}
		}
		return new Date(this.year, this.month,this.day,this.hour,minsAdd);
	}
	/**
	 * Gives the day of the week as an int(0=Monday, 1=Thursday, etc..)
	 * @return the day of the week as an in.
	 * @throws ParseException
	 */
	public int dayOfWeek() throws ParseException {
		
		//this needs to be replaced, it's just a place holder.
		String dateString = day+"/"+month+"/"+year;
		java.util.Date dOW = new SimpleDateFormat("dd/M/yyyy").parse(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(dOW);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	
		 
	}
	
	/**
	 * Gives the current date.
	 * @return A date in the local time.
	 */
	public static Date getCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		return new Date(now.getYear(), now.getMonthValue(),now.getDayOfMonth(), now.getHour(), now.getMinute());
	}
	
	/**
	 * The method gives the next day starting at 9:00am.
	 * @return A new day starting at 9:00am from next day.
	 */
	public static Date getTomorrowMorning() {
		Date d = getCurrentDate();
		Date d2 = new Date(d.year,d.month,d.day+1,d.hour=9,d.min=0);
		return d2;
	}
	
	/**
	 * 
	 * @param every The minutes to add starting at the current date
	 * @param exclude
	 * @return A list with 10 Dates starting at the current one and adding "every" minutes, that excludes weekends, holidays and hours that are not within work hours.
	 * @throws ParseException
	 */
	public List<Date> makeSmartDateList(int every, List<Date> exclude) throws ParseException {
		Date refDate = this;
		List<Date> dList = new ArrayList<Date>();
		for(int i=0; i < 10; i++) {
			//Tem que ser corrigido fazer com que os mins n passem de 59 e avance a hora.
			Date smartDate = new Date(refDate.year, refDate.month, refDate.day, refDate.hour, refDate.min);
			Date smartDateR = smartDate.addMinutes(every);
			if(smartDateR.hour > 9 && smartDateR.hour < 12 && smartDateR.hour > 14 && smartDateR.hour < 18) {
				if(!(exclude.contains(smartDateR)) && (!(smartDateR.isHolyday())) && (smartDateR.dayOfWeek() != 5) && (smartDateR.dayOfWeek() != 6)) {
					dList.add(smartDateR);
				}
			}
		}
		return dList;
	}
	
	public static String dateListToString(List<Date> listDate) {
		//TODO
		return null;
	}
	
	@Override
	public String toString() {
		return year+"."+month+"."+day+"."+hour+"."+min;
	}

	public static Date fromString(String s) {
		String[] elements = s.split(".");
    	Date d = new Date(Integer.parseInt(elements[0]),Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), 
    			Integer.parseInt(elements[3]), Integer.parseInt(elements[4])); 
    	return d;
	}
}
