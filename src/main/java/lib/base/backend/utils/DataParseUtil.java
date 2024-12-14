package lib.base.backend.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DataParseUtil {
	
	public Integer parseInteger(Object value) {
	    return value != null ? Integer.parseInt(value.toString()) : null;
	}

	public BigDecimal parseBigDecimal(Object value) {
	    return value != null ? new BigDecimal(value.toString()) : null;
	}

	public Long parseDate(Object value) {
	    if (value != null && value instanceof LocalDateTime) {
	        ZonedDateTime zonedDateTime = ((LocalDateTime) value).atZone(ZoneOffset.UTC);
	        return zonedDateTime.toInstant().toEpochMilli();
	    }
	    return null;
	}
}
