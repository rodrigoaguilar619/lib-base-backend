package lib.base.backend.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	
	
	/**
	 * format date, Date to String
	 * 
	 * @param dateToFormat date to format
	 * @param formatTo format required of date
	 * @return date formatted
	 */
	public String formatDate(Date dateToFormat, String formatTo) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatTo);
		return formatter.format(dateToFormat);
		
	}
	
	/**
	 * format date, String to Date
	 * 
	 * @param dateToFormat date to format
	 * @param formatFrom current format of date
	 * @return date object
	 */
	public Date formatDate(String dateToFormat, String formatFrom) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatFrom);
		return formatter.parse(dateToFormat);
		
	}
	
	/**
	 * format date to date formatted
	 * 
	 * @param dateToFormat date to format with a specific format
	 * @param formatFrom format of the date to reformat
	 * @param formatTo format final of date
	 * @return date formated
	 * @throws ParseException
	 */
	public String formatDate(String dateToFormat, String formatFrom, String formatTo) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatFrom);
		Date dateFormatted = formatter.parse(dateToFormat);
		
		SimpleDateFormat formatterTwo = new SimpleDateFormat(formatTo);
		return formatterTwo.format(dateFormatted);
		
	}

}
