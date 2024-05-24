package lib.base.backend.utils;

public class NumberDataUtil {

	public boolean hasFractionalPart(double value) {
        return value % 1 != 0;
    }
}
