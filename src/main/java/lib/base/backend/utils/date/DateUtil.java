package lib.base.backend.utils.date;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTimeComparator;

public class DateUtil {
	
	public int compareDatesNotTime(Date dateOne, Date dateTwo) {
		
		DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
		
		return dateTimeComparator.compare(dateOne, dateTwo);
	}
	
	public Date getDateWithoutTime(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	 
	    return calendar.getTime();
	}

}
