package lib.base.backend.utils;

import java.util.function.Function;

public class DataUtil {

	/**
	 * Get value of getter if entity is not null
	 * 
	 * @param entity entity to verify if is null
	 * @param getter getter with the value to return if entity is not null
	 * @return value of getter if entity is not null
	 */
	public <T, R> R getValueOrNull(T entity, Function<T, R> getter) {
        return entity != null ? getter.apply(entity) : null;
    }
}
