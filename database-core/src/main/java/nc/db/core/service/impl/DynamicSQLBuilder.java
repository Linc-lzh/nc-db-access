package nc.db.core.service.impl;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

// DynamicSQLBuilder.java
public class DynamicSQLBuilder {
    public String buildDynamicSelect(Class<?> entityClass, Map<String, Object> conditions) {
        StringBuilder sql = new StringBuilder("SELECT ");

        // 获取所有字段
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append(fields[i].getName());
        }

        sql.append(" FROM ").append(getTableName(entityClass));

        // 动态条件
        if (conditions != null && !conditions.isEmpty()) {
            sql.append(" WHERE ");
            Iterator<Map.Entry<String, Object>> it = conditions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                sql.append(entry.getKey()).append(" = ?");
                if (it.hasNext()) {
                    sql.append(" AND ");
                }
            }
        }

        return sql.toString();
    }


    public String getTableName(Class<?> entityClass) {
        return null;
    }
}