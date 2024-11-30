package lib.base.backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorFormulaUtil {

	// A/B = C/D => D = (C * B) / A
	public BigDecimal ruleOfThree(BigDecimal a, BigDecimal c, BigDecimal b) {
		return c.multiply(b).divide(a, 4, RoundingMode.HALF_UP);
	}
}
