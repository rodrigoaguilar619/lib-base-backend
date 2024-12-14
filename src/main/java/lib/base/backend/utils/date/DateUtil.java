package lib.base.backend.utils.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {
	
	public int compareDatesNotTime(LocalDateTime dateOne, LocalDateTime dateTwo) {
	    LocalDate localDateOne = dateOne.toLocalDate();
	    LocalDate localDateTwo = dateTwo.toLocalDate();
	    
	    return localDateOne.compareTo(localDateTwo);
	}
	
	public LocalDateTime getDateWithoutTime(LocalDateTime date) {
	    LocalDate localDate = date.toLocalDate();
	    return localDate.atStartOfDay();
	}
	
	public Long getMillis(LocalDateTime date) {

		if (date == null)
			return null;

		return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
	public Long getMillis(LocalDate date) {

		if (date == null)
			return null;

		return date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
	public LocalDateTime getLocalDateTime(Long millis) {
		
		if (millis == null)
			return null;
		
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
	}
	
	public LocalDate getLocalDate(Long millis) {
		
		if (millis == null)
			return null;
		
		return getLocalDateTime(millis).toLocalDate();
	}
}
