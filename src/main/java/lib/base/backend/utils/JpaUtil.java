package lib.base.backend.utils;

import javax.persistence.metamodel.StaticMetamodel;

public class JpaUtil {

	public String getTableMetaModel(Class<?> clazz) {
		
		return (clazz.getAnnotation(StaticMetamodel.class)).value().getSimpleName();
	}
}
