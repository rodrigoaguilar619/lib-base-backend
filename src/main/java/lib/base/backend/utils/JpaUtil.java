package lib.base.backend.utils;

import jakarta.persistence.metamodel.StaticMetamodel;

public class JpaUtil {

	public String getTableMetaModel(Class<?> clazz) {
		
		return (clazz.getAnnotation(StaticMetamodel.class)).value().getSimpleName();
	}
}
