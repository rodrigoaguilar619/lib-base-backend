package lib.base.backend.utils.date;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatUtil {
	
	
	/**
	 * format date, LocalDateTime to String
	 * 
	 * @param dateToFormat date to format
	 * @param formatTo format required of date
	 * @return date formatted
	 */
	public String formatLocalDateTime(LocalDateTime dateToFormat, String formatTo) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatTo);
	    return dateToFormat.format(formatter);
	}
	
	/**
	 * format date, LocalDate to String
	 * 
	 * @param dateToFormat date to format
	 * @param formatTo format required of date
	 * @return date formatted
	 */
	public String formatLocalDate(LocalDate dateToFormat, String formatTo) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatTo);
	    return dateToFormat.format(formatter);
	}
	
	/**
	 * format date, String to LocalDateTime
	 * 
	 * @param dateToFormat date to format
	 * @param formatFrom current format of date
	 * @return date object
	 */
	public LocalDateTime formatLocalDateTime(String dateToFormat, String formatFrom) throws DateTimeParseException {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatFrom);
	    return LocalDateTime.parse(dateToFormat, formatter);
	}
	
	/**
	 * format date, String to LocalDate
	 * 
	 * @param dateToFormat date to format
	 * @param formatFrom current format of date
	 * @return date object
	 */
	public LocalDate formatLocalDate(String dateToFormat, String formatFrom) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatFrom);
        return LocalDate.parse(dateToFormat, formatter);
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
	public String formatLocalDateTime(String dateToFormat, String formatFrom, String formatTo) throws DateTimeParseException {
	    DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern(formatFrom);
	    LocalDateTime parsedDate = LocalDateTime.parse(dateToFormat, fromFormatter);
	    
	    DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern(formatTo);
	    return parsedDate.format(toFormatter);
	}

}
