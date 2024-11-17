package lib.base.backend.utils;

import java.math.BigDecimal;

public class NumberDataUtil {

	public boolean hasFractionalPart(double value) {
        return value % 1 != 0;
    }

	public boolean hasFractionalPart(BigDecimal value) {
		return value.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0;
	}
}
