package lib.base.backend.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class FormatStringUtil {

	public String formatCurrency(BigDecimal value) {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(value);
	}
}
