package nc.db.core.service.impl;

import nc.db.component.QueryBuilder;

import java.util.Iterator;
import java.util.Map;

// MySQLQueryBuilder.java
public class MySQLQueryBuilder implements QueryBuilder {
    @Override
    public String buildSelectQuery(Class<?> entityClass, Map<String, Object> conditions) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(getTableName(entityClass));

        if (!conditions.isEmpty()) {
            query.append(" WHERE ");
            Iterator<Map.Entry<String, Object>> it = conditions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                query.append(entry.getKey()).append(" = ?");
                if (it.hasNext()) {
                    query.append(" AND ");
                }
            }
        }

        return query.toString();
    }

    // 其他方法实现...
}