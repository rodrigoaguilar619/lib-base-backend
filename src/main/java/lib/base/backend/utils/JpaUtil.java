package lib.base.backend.utils;

import jakarta.persistence.Table;
import jakarta.persistence.metamodel.StaticMetamodel;

public class JpaUtil {

	public String getTableMetaModel(Class<?> clazz) {
		
		return (clazz.getAnnotation(StaticMetamodel.class)).value().getSimpleName();
	}
	
	public String getTableName(Class<?> entityClass) {
        Table tableAnnotation = entityClass.getAnnotation(Table.class);

        if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
            return tableAnnotation.name();
        } else {
            return entityClass.getSimpleName();
        }
    }
}
