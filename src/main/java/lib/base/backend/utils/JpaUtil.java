package lib.base.backend.utils;

import javax.persistence.metamodel.StaticMetamodel;

public class JpaUtil {

	@SuppressWarnings("unchecked")
	public String getTableMetaModel(Class clazz) {
		
		return ((StaticMetamodel) clazz.getAnnotation(StaticMetamodel.class)).value().getSimpleName();
	}
}
