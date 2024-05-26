package lib.base.backend.utils;

import java.math.BigDecimal;
import java.util.Date;

public class DataParseUtil {
	
	public Integer parseInteger(Object value) {
	    return value != null ? Integer.parseInt(value.toString()) : null;
	}

	public BigDecimal parseBigDecimal(Object value) {
	    return value != null ? new BigDecimal(value.toString()) : null;
	}

	public Long parseDate(Object value) {
	    return value != null ? ((Date) value).getTime() : null;
	}
}
